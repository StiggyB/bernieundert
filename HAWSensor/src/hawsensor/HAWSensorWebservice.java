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
	
	
//	TODO: Allgemeines:
//	- http://www.iks.hs-merseburg.de/~uschroet/Literatur/Java_Lit/JAVA_Insel/javainsel_19_007.htm
//		-> WebParam und WebResult machen den code nur schoener/lesbarer, needed sind die nicht.
//	- was ist mit dem prozess der coord ist, ruft der sich aus der sensorUrl liste einfach selber auf? z.B. bei trigger?
//	- bully oder ring algo ... meine methoden unten sehen bully vor, hat im schlechtesten fall n�, ring h�tte n als aufwand
//	- bei coord ausfall, startet ein sensor die wahl oder k�nnen eben einfach alle die es merken starten? wie verwaltet man die msges davon, geht das?
//	- scheinbar muss man bei anderen als einfachen datentypen oder string noch $mehr tun ...
//		-> http://www.heise.de/developer/artikel/Webservices-mit-Java-EE-6-JAX-WS-und-RESTful-Services-1247464.html?artikelseite=2
//		-> http://predic8.de/groovy-web-services-jax-ws.htm
//	TODO: Fragen
//	- Was passiert mit dem aufruf von trigger, wenn eine wahl startet oder rennt?
//	- Was passiert, wenn der coord wegen vielen ausgefallenen sensoren l�nger braucht? kann das trigger ausbleiben? was passiert, wenn dann eine wahl
//	  startet und der coord aber noch da ist?
//	- prozess f�llt w�hrend der wahl aus:
//		-> wer merkt das und wie? anzeigen freigeben? wer speichert/reicht das weiter, es gibt ja keinen coord ...?! oder merkt das der neue coord erst?
//	- gibt es ein timeout, wann der coord einen toten sensor l�scht? oder kommt die exception sofort beim triggern? 
//	- ein sensor wird f�r tot erkl�rt, ist es aber nicht, war vlt nur lag oder trigger msg verpasst, dann ist seine anzeige freigegeben und sensor
//	  wird wohl noch ne wahl starten?! was tun?!
//	- nie mehr als einen coord haben, sonst problem.
//	- wird ein sensor beendet, z.B. strg+c, meldet er sich nicht ab oder? also einfach killen und beim n�chsten trigger failed das ganze und er wird
//	- gel�scht?!
//	- reihenfolge?! neuer sensor meldet sich bei einem sensor, erh�lt cord url, meldet sich am coord an, wann bekommt er trigger? bevor das update an
//	  alle sensoren ging oder schon w�hrenddessen? wenn das zulange dauern sollte, w�rde der sensor ja ne wahl starten sofort ...

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
			@WebParam(name = "hawmeterChart") String chart) throws Exception
	{
		System.out.println("\n\nNew sensor connecting from:\n" + url + "\n wants to use chart:\n" + chart);
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
	

//	public void sendUpdate();		wurde ein neuer sensor zugef�gt, muss das allen mitgeteilt werden (nur neuen sensor publishen oder ganze liste 
//									senden und auf jedem sensor speichern?! nat�rlich nicht nur sensoren sondern auch anzeigenliste versenden ->
//									eigene klasse/struktur?!
//									WICHTIG: Wann update versenden? wenn eine iteration in gange ist direkt wenn ein sensor vom iterator removed wird?
//									oder am ende eines kompletten schleifendurchslauf ganze liste senden an alle?!
//	public void startElection(); 	ausl�sender Prozess muss seine ID (URL) �bergeben, aufgerufener Prozess weiss sonst nich, wer das war, right?
//									wenn aufgerufener prozess nicht tot, schickt er reply zurueck als "lebenszeichen". er schickt dann ebenfalls an 
//									alle prozesse, die eine groessere id als er selbst haben eine startElection msg.
//									Bekommt urspruenglicher prozess kein reply, ist er neuer coord...
//	public void replyElection(); 	wenn der aufgerufene Prozess noch lebt, sendet er dem Aufrufenden ein reply, dieser macht dann nuescht weiter mehr  ...
//	public void newCoordinator(); wenn die wahl gelaufen ist, teilt der neue coord allen prozessen mit, dass er neuer coord ist ...

}
