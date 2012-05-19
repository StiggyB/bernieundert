/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawsensor;

import hawmetering.HAWMeteringWebservice;
import hawmetering.HAWMeteringWebserviceService;
import hawmetering.HAWSensorWebserviceService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import net.java.dev.jaxb.array.StringArray;
import net.java.dev.jaxb.array.StringArrayArray;

public class HAWSensor {

	// C:\Users\martin\workspace\HAWSensor>wsimport -d src -keep
	// http://localhost:9998/hawmetering/sensor?wsdl
	// http://www.vorlesungen.uni-osnabrueck.de/informatik/da02/va.pdf
	// http://computersciencesource.wordpress.com/2009/09/10/year-1-distributed-systems-bully-algorithm/
	// http://de.wikipedia.org/wiki/Ringalgorithmus

	public static void main(String[] args) {
		new HAWSensor().run(args);
	}

	//TODO: geht sowas? damit ich in der inneren klasse für den einen timertask das obj einpacken kann für den neuen triggerthread?
	private HAWSensor hawSensor = this;
	private Map<String, hawmetering.HAWSensorWebservice> sensorWebservices = new HashMap<String, hawmetering.HAWSensorWebservice>();
	private Map<String, String> hawmeterUrls = new HashMap<String, String>();
	private String coordinatorUrl;
	private String ownUrl;
	private hawmetering.HAWSensorWebservice coordinator;
	private HAWMeteringWebservice meteringChart;
	private Endpoint endpoint;
	private String sensorName;
	private Timer timer;
	private TimerTask task;
	private boolean isCoord;

	private void run(String[] args) {
		installShutdownHook();
		startWebservice(args[0]);

		try {
			
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
				
				task = createTask();
				timer = new Timer("coordTimeout");
				timer.schedule(task, 2500);

			} else {
				// kein sensor angegeben, make me coord
				isCoord = true;
				sensorName = args[2];
				meteringChart.setTitle(sensorName);
				coordinatorUrl = args[0];
				registerSensor(coordinatorUrl, args[1]);

				new SensorTriggerThread(this).start();
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
		if (!isCoord) {
			task.cancel();
			task = createTask();
			timer.schedule(task, 2500);
		}
		System.out.println("i am '" + ownUrl + "', have been triggered by coord");
		long messwert = System.currentTimeMillis() % 200;
		if (messwert > 100) {
			messwert = 200 - messwert;
		}
		meteringChart.setValue(messwert);

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

	private TimerTask createTask() {
		return new TimerTask() {
			@Override
			public void run() {
				System.out.println("Wahl gestartet");
				task.cancel();
				task = createCoordTask();
				timer.schedule(task, 2500);
				isCoord = true;
				electionLoop();
				
				//TODO: bei nicht coords rennt nun wohl der immer dauernd im hintergrund ... mit dem else ok behoben?
				//wait $appropriate time, if no msges recved, i am coord; else i do nothing ...
				//TODO: Bei der Wahl stürzt neuer Coord ab ... keiner triggert das mehr ...
			}
		};
	}
	

	private TimerTask createCoordTask() {
		return new TimerTask() {
			@Override
			public void run() {
				if (isCoord) {
					System.out.println("I is admin nao!");

					for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : sensorWebservices.entrySet()) {
						try {
							entry.getValue().newCoordinator(ownUrl);

						} catch (Exception e) {
							System.out.println(e.toString());
						}
					}

					new SensorTriggerThread(hawSensor).start();
				} else {
					task.cancel();
				}
			}
		};
	}
	
	public void startElection(){
		electionLoop();
	}
	
	private void electionLoop(){
		for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : sensorWebservices.entrySet()) {
			try {
				if (entry.getKey().compareTo(ownUrl) > 0) {
					if(entry.getValue().startElection("abc")){
						isCoord = false;
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
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
