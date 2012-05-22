package hawsensor;



import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "HAWSensorWebservice", targetNamespace = "http://hawmetering/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class HAWSensorWebservice {

	private final HAWSensor hawSensor;

	public HAWSensorWebservice(HAWSensor hawSensor) {
		this.hawSensor = hawSensor;
	}
	
	/*
	 * neuer sensor registriert sich beim coord. mit �bergeben muss er noch die gew�nschte anzeige-url, auf der er arbeiten will.
	 * coord braucht eine liste/hashmap/set, welche anzeige-urls es gibt. immer wenn sich ein sensor registriert, muss er schauen, ob die 
	 * gew�nschte url noch nicht in benutzung ist. wenn nicht, f�gt er sie der liste hinzu. Andernfalls -> exception und sensor wird beendet oder geht
	 * $time schlafen, probiert es dann erneut.
	 * wenn ein sensor nicht mehr existiert, muss er aus der sensorliste gel�scht werden und die anzeige url auch aus der entsprechenden liste.
	 * 
	 */
	public void registerSensor(
			@WebParam(name = "url") String url, 
			@WebParam(name = "hawmeterChart") String chart) throws Exception {
		System.out.println("==========\nNew sensor connecting from:\n" + url + "\nwants to use chart:\n" + chart);
		hawSensor.registerSensor(url, chart);
	}

	/*
	 * wird alle 2sek auf allen sensoren aufgerufen, meldet sich einer nicht (kriegt coord also ne exception), wird sensor entfernt.
	 * in der trigger methode soll der messwert berechnet und auf die anzeige gepackt werden.
	 */
	public void trigger() {
		hawSensor.trigger();
	}

	/*
	 * da ein beliebiger prozess beim sensorstart �bergeben werden kann, muss zun�chst rausgefunden werden, welcher sensor denn grad coord ist.
	 * methode liefert den aktuellen coord zur�ck. danach kann der neue sensor mit registerSensor sich selbst am coord anmelden.
	 * url/id des coord muss sich jeder sensor merken, falls er befragt wird.
	 * ist grad eine wahl in gange, muss der neue sensor warten, dann nochmal probieren -> keine exception, thread sleep oder so ...
	 */
	public String getCoordinatorUrl() {
		return hawSensor.getCoordinatorUrl();
	}
	

	/*
	 * wurde ein neuer sensor zugef�gt, muss das allen mitgeteilt werden (nur neuen sensor publishen oder ganze liste senden und auf jedem sensor 
	 * speichern?! nat�rlich nicht nur sensoren sondern auch anzeigenliste versenden -> eigene klasse/struktur?! 
	 * WICHTIG: Wann update versenden? wenn eine iteration in gange ist direkt wenn ein sensor vom iterator removed wird?
	 * oder am ende eines kompletten schleifendurchslauf ganze liste senden an alle?!
	 */
	public void sendUpdate(
			@WebParam(name = "sensorUrlMap") String[] sensorUrls, 
			@WebParam(name = "hawmeterUrlsMap") String[][] hawmeterUrls){
		hawSensor.sendUpdate(sensorUrls, hawmeterUrls);
	};	
	
	/*
	 * ausl�sender Prozess muss seine ID (URL) �bergeben, aufgerufener Prozess weiss sonst nich, wer das war, right?
	 * wenn aufgerufener prozess nicht tot, schickt er reply zurueck als "lebenszeichen". er schickt dann ebenfalls an alle prozesse, die eine 
	 * groessere id als er selbst haben eine startElection msg. Bekommt urspruenglicher prozess kein reply, ist er neuer coord...
	 */
	public void startElection(String url) {
		System.out.println("HAWSensorWebservice.startElection()");
		hawSensor.doElection();
	};
	
	/*
	 * wenn die wahl gelaufen ist, teilt der neue coord allen prozessen mit, dass er neuer coord ist ...
	 */
	public void newCoordinator(String url) {
		System.out.println("HAWSensorWebservice.newCoordinator(): " + url);
		hawSensor.newCoordinator(url);
	};
	
	/*
	 * rennt vielleicht noch ne wahl?
	 */
	public boolean isElectionRunning(){
		return hawSensor.isElectionRunning();
	}

}
