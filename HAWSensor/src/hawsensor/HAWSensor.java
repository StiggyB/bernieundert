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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

public class HAWSensor {
	
	//C:\Users\martin\workspace\HAWSensor>wsimport -d src -keep  http://localhost:9998/hawmetering/sensor?wsdl
	

    public static void main(String[] args) {
    	new HAWSensor().run(args);
    }

    private Map<String, hawmetering.HAWSensorWebservice> sensorUrls = new HashMap<String, hawmetering.HAWSensorWebservice>();
    private URL coordinatorUrl;
    
	public void run(String[] args) {
    	startWebservice(args[0]);
        
    	try {
	    	if (args.length == 3) {
	    		// coordinator angegeben
	    		URL sensorUrl = new URL(args[2]);
	
	    		HAWSensorWebserviceService service = new HAWSensorWebserviceService(sensorUrl, new QName("http://hawmetering/", "HAWSensorWebserviceService"));
	    		hawmetering.HAWSensorWebservice anySensor = service.getHAWSensorWebservicePort();
	    		
	    		coordinatorUrl = new URL(anySensor.getCoordinatorUrl());
//	    		
//	    		HAWSensorWebserviceService service2 = new HAWSensorWebserviceService(coordUrl, new QName("http://hawmetering/", "HAWSensorWebserviceService"));
//	    		hawmetering.HAWSensorWebservice coordinator = service2.getHAWSensorWebservicePort();
//	    		
//	    		coordinator.registerSensor(args[0]);
	    	} else {
	    		coordinatorUrl = new URL(args[0] + "?wsdl");
	    		new SensorTriggerThread(sensorUrls).start();
	    	}
    	

//            HAWMeteringWebserviceService service = new HAWMeteringWebserviceService(coordinatorUrl, new QName("http://hawmetering/", "HAWMeteringWebserviceService"));
//            HAWMeteringWebservice metering = service.getHAWMeteringWebservicePort();
            
			while (true);//s {
//				long messwert = System.currentTimeMillis() % 200;
//				if (messwert > 100) {
//					messwert = 200 - messwert;
//				}
//				metering.setValue(messwert);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
        } catch (MalformedURLException ex) {
            Logger.getLogger(HAWSensor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void startWebservice(String url) {
    		HAWSensorWebservice webservice = new HAWSensorWebservice(this);
            Endpoint.publish(url, webservice);
    }

	public void registerSensor(String url) {
		try {
			HAWSensorWebserviceService service = new HAWSensorWebserviceService(new URL(url), new QName("http://hawmetering/", "HAWSensorWebserviceService"));
			hawmetering.HAWSensorWebservice sensor = service.getHAWSensorWebservicePort();
			sensorUrls.put(url, sensor);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public URL getCoordinatorUrl() {
		return coordinatorUrl;
	}
}
