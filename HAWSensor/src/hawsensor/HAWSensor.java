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

	private Map<String, hawmetering.HAWSensorWebservice> sensorWebservices = new HashMap<String, hawmetering.HAWSensorWebservice>();
	private Map<String, String> hawmeterUrls = new HashMap<String, String>();
	private String coordinatorUrl;
	private String ownUrl;
	private hawmetering.HAWSensorWebservice coordinator;
	private HAWMeteringWebservice meteringChart;
	private Endpoint endpoint;

	private void run(String[] args) {
		installShutdownHook();
		startWebservice(args[0]);

		try {
			
			// webservice fuer chart, worauf angezeigt werden soll, anlegen
			HAWMeteringWebserviceService service2 = new HAWMeteringWebserviceService(new URL(args[1]), new QName("http://hawmetering/",	"HAWMeteringWebserviceService"));
			meteringChart = service2.getHAWMeteringWebservicePort();
			// meine eigene url abspeichern
			ownUrl = args[0];
			
			if (args.length == 3) {
				// $beliebiger sensor angegeben, coord finden
				hawmetering.HAWSensorWebservice anySensor = createHAWSensorWebservice(args[2]);

				coordinatorUrl = anySensor.getCoordinatorUrl();

				coordinator = createHAWSensorWebservice(coordinatorUrl);
				coordinator.registerSensor(args[0], args[1]);

			} else {
				// kein sensor angegeben, make me coord
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
			if (getHawmeterUrls().containsKey(chart)) {
				System.out.println("chart is NOT free for use");
				throw new Exception("Selected chart is already in use, try later or with different chart");
			}
	
			System.out.println("chart is free for use, sensor will be registered");
	
			try {
				hawmetering.HAWSensorWebservice sensor = createHAWSensorWebservice(url);
				getSensorWebservices().put(url, sensor);
				getHawmeterUrls().put(chart, url);
				
	//			TODO: eigentlich doch an alle ausser mich selbst oder?! oder macht das nüscht, dass ichs auch nochmal kriege?!
				sendUpdateAllSensors();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendUpdateAllSensors() {
		System.out.println("new sensor was registered, broadcasting update to all known sensors");
		String[] sensorUrlsArray = getSensorWebservices().keySet().toArray(new String[0]);
		String[][] hawmeterUrlsArray = MapArrayAdapter.toArray(getHawmeterUrls());
		StringArray sensors = new BlubStringArray(sensorUrlsArray);
		StringArrayArray meters = new BlubStringArrayArray(hawmeterUrlsArray);
		for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : getSensorWebservices().entrySet()) {
			entry.getValue().sendUpdate(sensors, meters);
		}
	}

	public void sendUpdate(String[] sensorUrls, String[][] hawmeterUrls2) {
		// hat der sensortriggerthread damit automatishc auch die neue liste?
		System.out.println(Arrays.toString(sensorUrls));
		HashMap<String, hawmetering.HAWSensorWebservice> newSensorWebservices = new HashMap<String, hawmetering.HAWSensorWebservice>();
		for (String sensorUrl : sensorUrls) {
			if (getSensorWebservices().containsKey(sensorUrl)) {
				newSensorWebservices.put(sensorUrl, getSensorWebservices().get(sensorUrl));
			} else {
				try {
					newSensorWebservices.put(sensorUrl, createHAWSensorWebservice(sensorUrl));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		sensorWebservices = (newSensorWebservices);
		
		hawmeterUrls = MapArrayAdapter.toMap(hawmeterUrls2);
	}

	public String getCoordinatorUrl() {
		return coordinatorUrl + "?wsdl";
	}

	public void trigger() {
		System.out.println("i am " + ownUrl + ", have been triggered by coord");
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


	public Map<String, hawmetering.HAWSensorWebservice> getSensorWebservices() {
		return sensorWebservices;
	}


	public Map<String, String> getHawmeterUrls() {
		return hawmeterUrls;
	}


}
