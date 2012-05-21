/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawsensor;

import hawmetering.HAWMeteringWebservice;
import hawmetering.HAWMeteringWebserviceService;
import hawmetering.HAWSensorWebserviceService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;

import net.java.dev.jaxb.array.StringArray;
import net.java.dev.jaxb.array.StringArrayArray;

public class HAWSensor {

	// C:\Users\martin\workspace\HAWSensor>wsimport -d src -keep
	// http://localhost:9998/hawmetering/sensor?wsdl
	// http://www.vorlesungen.uni-osnabrueck.de/informatik/da02/va.pdf
	// http://computersciencesource.wordpress.com/2009/09/10/year-1-distributed-systems-bully-algorithm/
	// http://de.wikipedia.org/wiki/Ringalgorithmus
	// http://www.java.net/node/667072

	public static void main(String[] args) {
		// Timeouts:
		// http://stackoverflow.com/questions/808487/how-to-set-a-connection-timeout-when-using-jaxrpc-ri-web-services-client
		System.setProperty("sun.net.client.defaultConnectTimeout", "3000");
		System.setProperty("sun.net.client.defaultReadTimeout", "3000");

		new HAWSensor().run(args);
	}

	private Map<String, hawmetering.HAWSensorWebservice> sensorWebservices = new HashMap<String, hawmetering.HAWSensorWebservice>();
	private Map<String, String> hawmeterUrls = new HashMap<String, String>();
	private String coordinatorUrl;
	private String ownUrl;
	private hawmetering.HAWSensorWebservice coordinator;
	private HAWMeteringWebservice meteringChart;
	private Endpoint endpoint;
	private String sensorName;
	private Timer timer = new Timer("coordTimeout");
	private TimerTask triggerTimeoutTask;
	private boolean isCoord;
	private boolean electionRunning;
	private SensorTriggerThread sensorTriggerThread;

	private void run(String[] args) {
		installShutdownHook();
		startWebservice(args[0]);

		try {
			// TODO: zwei coords sind noch möglich .... wenn man sie manuell startet!
			// webservice fuer chart, worauf angezeigt werden soll, anlegen
			HAWMeteringWebserviceService service2 = new HAWMeteringWebserviceService(new URL(args[1]), new QName("http://hawmetering/",	"HAWMeteringWebserviceService"));
			meteringChart = service2.getHAWMeteringWebservicePort();
			// meine eigene url abspeichern
			ownUrl = args[0];
			
			if (args.length == 4) {
				sensorName = args[3];
				// $beliebiger sensor angegeben, coord finden
				hawmetering.HAWSensorWebservice anySensor = createHAWSensorWebservice(args[2]);

				coordinatorUrl = anySensor.getCoordinatorUrl();

				coordinator = createHAWSensorWebservice(coordinatorUrl);
				coordinator.registerSensor(args[0], args[1]);
				meteringChart.setTitle(sensorName);
				
				scheduleTriggerTimeoutTask();

			} else {
				// kein sensor angegeben, make me coord
				isCoord = true;
				sensorName = args[2];
				meteringChart.setTitle(sensorName);
				coordinatorUrl = args[0];
				registerSensor(coordinatorUrl, args[1]);

				createSensorTriggerThread();
			}

		} catch (MalformedURLException ex) {
			Logger.getLogger(HAWSensor.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception e) {
			endpoint.stop();
			e.printStackTrace();
		}

	}


	private void startWebservice(String url) {
		HAWSensorWebservice webservice = new HAWSensorWebservice(this);
		endpoint = Endpoint.publish(url, webservice);
	}

	public void registerSensor(String url, String chart) throws Exception {
		synchronized (sensorWebservices) {
			if (hawmeterUrls.containsKey(chart)) {
				System.out.println("chart is NOT free for use\n==========");
				throw new Exception("Selected chart is already in use, try later or with different chart");
			}
	
			System.out.println("chart is free for use, sensor will be registered\n==========");
	
			try {
				hawmetering.HAWSensorWebservice sensor = createHAWSensorWebservice(url);
				sensorWebservices.put(url, sensor);
				hawmeterUrls.put(chart, url);
				
	//			TODO: eigentlich doch an alle ausser mich selbst oder?! oder macht das nüscht, dass ichs auch nochmal kriege?!
				sendUpdateAllSensors();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendUpdateAllSensors() {
		System.out.println("sensor list changed, broadcasting update to all known sensors");
		String[] sensorUrlsArray = sensorWebservices.keySet().toArray(new String[0]);
		String[][] hawmeterUrlsArray = MapArrayAdapter.toArray(hawmeterUrls);
		StringArray sensors = new StringArrayConverter(sensorUrlsArray);
		StringArrayArray meters = new StringArrayArrayConverter(hawmeterUrlsArray);
		for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : sensorWebservices.entrySet()) {
			entry.getValue().sendUpdate(sensors, meters);
		}
	}

	public void sendUpdate(String[] sensorUrls, String[][] hawmeterUrls2) {
		// hat der sensortriggerthread damit automatishc auch die neue liste?
		System.out.println(Arrays.toString(sensorUrls));
		HashMap<String, hawmetering.HAWSensorWebservice> newSensorWebservices = new HashMap<String, hawmetering.HAWSensorWebservice>();
		for (String sensorUrl : sensorUrls) {
			if (sensorWebservices.containsKey(sensorUrl)) {
				newSensorWebservices.put(sensorUrl, sensorWebservices.get(sensorUrl));
			} else {
				try {
					newSensorWebservices.put(sensorUrl, createHAWSensorWebservice(sensorUrl));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		sensorWebservices = newSensorWebservices;
		
		hawmeterUrls = MapArrayAdapter.toMap(hawmeterUrls2);
	}

	public String getCoordinatorUrl() {
		return coordinatorUrl;
	}

	public void trigger() {
		if (electionRunning) {
			System.err.println("election is already running, ignoring trigger()");
		} else {
			if (!isCoord) {
				scheduleTriggerTimeoutTask();
			}
			System.out.println("i am '" + ownUrl + "', have been triggered by coord");
			long messwert = System.currentTimeMillis() % 200;
			if (messwert > 100) {
				messwert = 200 - messwert;
			}
			meteringChart.setValue(messwert);
		}
	}
	
	private hawmetering.HAWSensorWebservice createHAWSensorWebservice(String url) throws MalformedURLException{
		HAWSensorWebserviceService service = new HAWSensorWebserviceService(new URL(url), new QName("http://hawmetering/", "HAWSensorWebserviceService"));
		hawmetering.HAWSensorWebservice sensor = service.getHAWSensorWebservicePort();
		return sensor;
	}
	
	private void installShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("shutdownhook tut dinge ...");
				endpoint.stop();
			}
		}));
	}

	private void scheduleTriggerTimeoutTask() {
		if (triggerTimeoutTask != null) {
			triggerTimeoutTask.cancel();
		}
		
		triggerTimeoutTask = createTriggerTimeoutTask();
		timer.schedule(triggerTimeoutTask, 5000);
	}
	
	private TimerTask createTriggerTimeoutTask() {
		return new TimerTask() {
			@Override
			public void run() {
				doElection();
			}
		};
	}
	
	
	public void doElection(){
		if (electionRunning) {
			System.err.println("election already running, called from: " + Thread.currentThread().getName());
		} else {
			electionRunning = true;
			System.out.println("Wahl gestartet");
			isCoord = true;
			for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : sensorWebservices.entrySet()) {
				try {
					if (entry.getKey().compareTo(ownUrl) > 0) {
						entry.getValue().startElection("abc");
						isCoord = false;
					}
				} catch (Exception e) {
					System.err.println("couldn't reach " + entry.getValue() + ", " + e.toString());
				}
			}
			System.out.println("electionLoop finished");
			
			if (isCoord) {
				System.out.println("I is admin nao!");
				meteringChart.setTitle("newCoord " + sensorName);

				for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : sensorWebservices.entrySet()) {
					try {
						entry.getValue().newCoordinator(ownUrl);
					} catch (Exception e) {
						System.err.println("couldn't set coordinator on " + entry.getValue() + ", " + e.toString());
					}
				}
				createSensorTriggerThread();
			} else {
				scheduleTriggerTimeoutTask();
			}
			electionRunning = false;
		}
	}


	private void createSensorTriggerThread() {
		if (sensorTriggerThread != null) {
			sensorTriggerThread.shutdown();
			sensorTriggerThread.interrupt();
		}
		sensorTriggerThread = new SensorTriggerThread(this);
		sensorTriggerThread.start();
	}
	
	public Map<String, hawmetering.HAWSensorWebservice> getSensorWebservices() {
		return sensorWebservices;
	}


	public Map<String, String> getHawmeterUrls() {
		return hawmeterUrls;
	}


	public void newCoordinator(String url) {
		coordinatorUrl = url;
	}


}
