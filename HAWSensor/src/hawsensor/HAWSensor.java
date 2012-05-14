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

	public void run(String[] args) {
    	startWebservice(args[0]);
        
    	try {
	    	if (args.length == 3) {
	    		// coordinator angegeben
	    		URL coordinatorUrl = new URL(args[2]);
	
	            HAWSensorWebserviceService service = new HAWSensorWebserviceService(coordinatorUrl, new QName("http://hawmetering/", "HAWSensorWebserviceService"));
	            hawmetering.HAWSensorWebservice coordinator = service.getHAWSensorWebservicePort();
	    		coordinator.registerSensor(args[0]);
	    	} else {
	    		new SensorTriggerThread(sensorUrls).start();
	    	}
    	
            URL url = new URL(args[1]);

            HAWMeteringWebserviceService service = new HAWMeteringWebserviceService(url, new QName("http://hawmetering/", "HAWMeteringWebserviceService"));
            HAWMeteringWebservice metering = service.getHAWMeteringWebservicePort();
            
			while (true) {
				long messwert = System.currentTimeMillis() % 200;
				if (messwert > 100) {
					messwert = 200 - messwert;
				}
				metering.setValue(messwert);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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

}
