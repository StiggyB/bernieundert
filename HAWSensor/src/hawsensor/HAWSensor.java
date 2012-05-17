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
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

public class HAWSensor {

	// C:\Users\martin\workspace\HAWSensor>wsimport -d src -keep
	// http://localhost:9998/hawmetering/sensor?wsdl
	// http://www.vorlesungen.uni-osnabrueck.de/informatik/da02/va.pdf
	// http://computersciencesource.wordpress.com/2009/09/10/year-1-distributed-systems-bully-algorithm/
	// http://de.wikipedia.org/wiki/Ringalgorithmus

	public static void main(String[] args) {
		new HAWSensor().run(args);
	}

	private Map<String, hawmetering.HAWSensorWebservice> sensorUrls = new HashMap<String, hawmetering.HAWSensorWebservice>();
//	http://svn.apache.org/repos/asf/cxf/trunk/distribution/src/main/release/samples/java_first_jaxws/src/main/java/demo/hw/server/
//	http://mycenes.wordpress.com/2009/10/27/apache-cxf-how-tos-well-not-exactly/
	@XmlJavaTypeAdapter(value=SensorUrlMapAdapter.class)
	@XmlElement(name = "sensorUrls")
	private Map<String, String> hawmeterUrls = new HashMap<String, String>();
	private String coordinatorString;
	private String ownString;
	private hawmetering.HAWSensorWebservice coordinator;
	private HAWMeteringWebservice meteringChart;
	private Endpoint endpoint;
	private Timer timer;

	private void run(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("shutdownhook tut dinge ...");
				endpoint.stop();
			}
		}));
		
		startWebservice(args[0]);

		try {
			
			// webservice fuer chart, worauf angezeigt werden soll, anlegen
			HAWMeteringWebserviceService service2 = new HAWMeteringWebserviceService(new URL(args[1]), new QName("http://hawmetering/",	"HAWMeteringWebserviceService"));
			meteringChart = service2.getHAWMeteringWebservicePort();
			// meine eigene url abspeichern
			ownString = args[0];
			
			if (args.length == 3) {
				// $beliebiger sensor angegeben, coord finden
				URL sensorUrl = new URL(args[2]);

				HAWSensorWebserviceService service = new HAWSensorWebserviceService(sensorUrl, new QName("http://hawmetering/", "HAWSensorWebserviceService"));
				hawmetering.HAWSensorWebservice anySensor = service.getHAWSensorWebservicePort();

				coordinatorString = anySensor.getCoordinatorUrl();

				service = new HAWSensorWebserviceService(new URL(coordinatorString), new QName("http://hawmetering/", "HAWSensorWebserviceService"));
				coordinator = service.getHAWSensorWebservicePort();

				coordinator.registerSensor(args[0], args[1]);

			} else {
				// kein sensor angegeben, make me coord
				coordinatorString = args[0];
				registerSensor(coordinatorString, args[1]);

				new SensorTriggerThread(sensorUrls, hawmeterUrls).start();
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

	public synchronized void registerSensor(String url, String chart) throws Exception {
		if (hawmeterUrls.containsKey(chart)) {
			System.out.println("chart is NOT free for use");
			throw new Exception("Selected chart is already in use, try later or with different chart");
		}

		System.out.println("chart is free for use, sensor will be registered");

		try {
			HAWSensorWebserviceService service = new HAWSensorWebserviceService(new URL(url), new QName("http://hawmetering/", "HAWSensorWebserviceService"));
			hawmetering.HAWSensorWebservice sensor = service.getHAWSensorWebservicePort();
			sensorUrls.put(url, sensor);
			hawmeterUrls.put(chart, url);
			
//			TODO: eigentlich doch an alle ausser mich selbst oder?!
			sendUpdateAllSensors();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void sendUpdateAllSensors(){
		System.out.println("new sensor was registered, broadcasting update to all known sensors");
		for (Map.Entry<String, hawmetering.HAWSensorWebservice> entry : sensorUrls.entrySet()) {
//			entry.getValue().
		}
	}
	
	public void sendUpdate(Map<String, hawmetering.HAWSensorWebservice> sensorUrls, Map<String, String> hawchartMap){
		// hat der sensortriggerthread damit automatishc auch die neue liste?
		this.sensorUrls = sensorUrls;
		this.hawmeterUrls = hawchartMap;
	}

	public String getCoordinatorUrl() {
		return coordinatorString + "?wsdl";
	}

	public void trigger() {
		System.out.println("i am " + ownString + ", have been triggered by coord");
		long messwert = System.currentTimeMillis() % 200;
		if (messwert > 100) {
			messwert = 200 - messwert;
		}
		meteringChart.setValue(messwert);

	}
}
