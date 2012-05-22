/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hawsensor;



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
	
	
//	TODO: Fragen
//	- Was passiert mit dem aufruf von trigger, wenn eine wahl startet oder rennt? -> coord tot, kommt kein trigger, right? :> es sei denn, er hatte lag
//	  dann rennt wahl ins leere ... da coord ja wieder antwortet
//	- Was passiert, wenn der coord wegen vielen ausgefallenen sensoren länger braucht? kann das trigger ausbleiben? was passiert, wenn dann eine wahl
//	  startet und der coord aber noch da ist? -> siehe darüber
//	- prozess fällt während der wahl aus:
//		-> wer merkt das und wie? anzeigen freigeben? wer speichert/reicht das weiter, es gibt ja keinen coord ...?! oder merkt das der neue coord erst?
//	- ein sensor wird für tot erklärt, ist es aber nicht, war vlt nur lag oder trigger msg verpasst, dann ist seine anzeige freigegeben und sensor
//	  wird wohl noch ne wahl starten?! was tun?!
//	- nie mehr als einen coord haben, sonst problem.

	/*
	 * neuer sensor registriert sich beim coord. mit übergeben muss er noch die gewünschte anzeige-url, auf der er arbeiten will.
	 * coord braucht eine liste/hashmap/set, welche anzeige-urls es gibt. immer wenn sich ein sensor registriert, muss er schauen, ob die 
	 * gewünschte url noch nicht in benutzung ist. wenn nicht, fügt er sie der liste hinzu. Andernfalls -> exception und sensor wird beendet oder geht
	 * $time schlafen, probiert es dann erneut.
	 * wenn ein sensor nicht mehr existiert, muss er aus der sensorliste gelöscht werden und die anzeige url auch aus der entsprechenden liste.
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
	 * da ein beliebiger prozess beim sensorstart übergeben werden kann, muss zunächst rausgefunden werden, welcher sensor denn grad coord ist.
	 * methode liefert den aktuellen coord zurück. danach kann der neue sensor mit registerSensor sich selbst am coord anmelden.
	 * url/id des coord muss sich jeder sensor merken, falls er befragt wird.
	 * ist grad eine wahl in gange, muss der neue sensor warten, dann nochmal probieren -> keine exception, thread sleep oder so ...
	 */
	public String getCoordinatorUrl() {
		return hawSensor.getCoordinatorUrl();
	}
	

	/*
	 * wurde ein neuer sensor zugefügt, muss das allen mitgeteilt werden (nur neuen sensor publishen oder ganze liste senden und auf jedem sensor 
	 * speichern?! natürlich nicht nur sensoren sondern auch anzeigenliste versenden -> eigene klasse/struktur?! 
	 * WICHTIG: Wann update versenden? wenn eine iteration in gange ist direkt wenn ein sensor vom iterator removed wird?
	 * oder am ende eines kompletten schleifendurchslauf ganze liste senden an alle?!
	 */
	public void sendUpdate(
			@WebParam(name = "sensorUrlMap") String[] sensorUrls, 
			@WebParam(name = "hawmeterUrlsMap") String[][] hawmeterUrls){
		hawSensor.sendUpdate(sensorUrls, hawmeterUrls);
	};	
	
	/*
	 * auslösender Prozess muss seine ID (URL) übergeben, aufgerufener Prozess weiss sonst nich, wer das war, right?
	 * wenn aufgerufener prozess nicht tot, schickt er reply zurueck als "lebenszeichen". er schickt dann ebenfalls an alle prozesse, die eine 
	 * groessere id als er selbst haben eine startElection msg. Bekommt urspruenglicher prozess kein reply, ist er neuer coord...
	 */
	public void startElection(String url){
		System.out.println("HAWSensorWebservice.startElection()");
		hawSensor.doElection();
	}; 	
	
	/*
	 * wenn der aufgerufene Prozess noch lebt, sendet er dem Aufrufenden ein reply, dieser macht dann nuescht weiter mehr  ...
	 */
//	public void replyElection(){
//		System.out.println("HAWSensorWebservice.replyElection()");
//	}; 	
	
	/*
	 * wenn die wahl gelaufen ist, teilt der neue coord allen prozessen mit, dass er neuer coord ist ...
	 */
	public void newCoordinator(String url){
		System.out.println("HAWSensorWebservice.newCoordinator(): " + url);
		hawSensor.newCoordinator(url);
	}; 
	
	public boolean isElectionRunning(){
		return hawSensor.isElectionRunning();
	}

}
