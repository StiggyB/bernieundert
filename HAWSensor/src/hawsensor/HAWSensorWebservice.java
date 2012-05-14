/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawsensor;

import java.net.URL;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

//@WebService
//@SOAPBinding(style = Style.RPC)
@WebService(name = "HAWSensorWebservice", targetNamespace = "http://hawmetering/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HAWSensorWebservice {

	private final HAWSensor hawSensor;

	public HAWSensorWebservice(HAWSensor hawSensor) {
		this.hawSensor = hawSensor;
	}

	public void registerSensor(@WebParam(name = "url") String url) {
		System.out.println(url);
		hawSensor.registerSensor(url);
	}

	public void trigger() {
		System.out.println("triggered");
	}

	public URL getCoordinatorUrl() {
		return hawSensor.getCoordinatorUrl();
	}

}
