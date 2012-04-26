package starter;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import monitor.Monitor;
import ggt.Coordinator;
import ggt.ggtProcess;
import ggt.ggtProcessHelper;
import ggt.ggtProcessPOA;

public class ggtProcessImpl extends ggtProcessPOA{

	private String processName;
	private ggtProcess left;
	private ggtProcess right;
	private int startValue;
	private int delay;
	private int timeout;
	private Monitor mntr;
	
	public ggtProcessImpl(int i, StarterImpl starterImpl, Coordinator coordRef) {
		this.processName = starterImpl.getName() + "_" + i;	
		ggtProcess ggtProcess;
		try {
			ggtProcess = ggtProcessHelper.narrow(starterImpl._poa().servant_to_reference(this));
			coordRef.registerProcess(ggtProcess, processName);
		} catch (ServantNotActive e) {
			e.printStackTrace();
		} catch (WrongPolicy e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initProcess(ggtProcess left, ggtProcess right,int startValue, int delay, int timeout, Monitor mntr) {
		this.left = left;
		this.right = right;
		this.startValue = startValue;
		this.delay = delay;
		this.timeout = timeout;
		this.mntr = mntr;
		
	}

	@Override
	public void start() {
		left.calc(startValue);
		right.calc(startValue);
	}

	@Override
	public void calc(int y) {
		//TODO: msg mntr new number
		//TODO: monitor will ausgeben, von wo die zahl kam, also muss wohl idl angepasst werden ...
		if (y < startValue) {
			//TODO: wennn er calcen muss, $delay warten b4 msg2neighbours...
			startValue = ((startValue - 1) % y) + 1;
			try {
				Thread.sleep(delay * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			left.calc(startValue);
			right.calc(startValue);
		}
	}
	
	//TODO: terminate dient ja der beendigung durch rechnen, muss wohl noch ne shutdown geben, fuer ausserplanmässiges töten der prozesse -> idl
	//TODO: run methode bauen?! die eine state-gesteuerte endlos while-loop hat, worin zeit geprüft wird und msges verwaltet werden?!
	//TODO: LinkedBlockingQueue -> wg der eintreffenden msges, wenn keine da ist -> blocked
	// TODO: Erhält ein ggT-Prozess im Zustand "bereit" eine Zahl, so versetzt
	// er sich in den Zustand "rechnen". Meldet ein ggT-Prozess die Terminierung
	// der aktuellen Berechnung, so erhält der Koordinator gleichzeitig von ihm
	// das Endergebnis der Berechnung. Der ggT-Prozess versetzt sich in den
	// Zustand "beendet

	@Override
	public boolean terminate(String processName) {
		// TODO terminierung...
		// Erhält während eines Laufs des Algorithmus (Zustand "rechnen") ein
		// ggT-Prozess tTimeout Sekunden lang keine Zahl y, startet er eine
		// Terminierungsabstimmung: Dazu stößt der Prozess die Übertragung einer
		// Terminierungsanfrage an, die von den Prozessen entlang des Ringes
		// solange weitergereicht wird, bis sie wieder bei dem ursprünglichen
		// Absender angekommt. Die Terminierungsnachricht wird mit dem Inhalt
		// "terminiere" gestartet. Ist ein Empfänger bereit zu terminieren,
		// reicht dieser die Terminierungsnachricht weiter ebenfalls mit dem
		// Inhalt "terminiere". Falls er nicht bereit ist, reicht er die
		// Terminierungsnachricht auch weiter, aber mit dem Inhalt
		// "terminiere nicht". Ist der Inhalt "terminiere nicht" so muss ein
		// Empfänger an seinen rechten Nachbarn ebenfalls "terminiere nicht"
		// senden. Ein Empfänger ist bereit zu terminieren, wenn seit der
		// letzten Zusendung einer Zahl mehr als tTimeout/2 Sekunden vergangen
		// sind. Erhält ein Prozess von seinem linken Nachbarn die eigene
		// Terminierungsanfrage mit dem Inhalt "terminiere" so terminiert der
		// Prozess. Er meldet sein Ergebnis an den Monitor und teilt dem
		// Koordinator mit, dass die Berechnung beendet ist. Der Koordinator
		// veranlasst dann die Beendigung aller ggT-Prozesse. Das System ist
		// jetzt bereit, eine neue Berechnung durchzuführen.
		
		// nochmal details:
		// Der ggT-Prozess beobachtet die Zeit seit dem letzten Empfang einer
		// Zahl. Hat diese tTimeout Sekunden überschritten, startet er eine
		// Terminierungsanfrage.
		// Die Abstimmung arbeitet wie folgt:

		// Wird eine Terminierungsanfrage empfangen, so ist das dem Monitor
		// mitzuteilen
		// Ist der Inhalt der Anfrage "terminiere" und ist seit dem letzten
		// Empfang einer Zahl mehr als tTimeout/2 (tTimeout halbe) Sekunden
		// vergangen, dann ist der Inhalt der an seinen rechten Nachbarn
		// weitergeleiteten Anfrage ebenfalls "terminiere". Ansonsten ist die
		// Anfrage mit dem Inhalt "terminiere nicht" weiterzuleiten.

		// Ist die Terminierungsanfrage erfolgreich durchgeführt, sendet der
		// Prozess dem Koordinator eine Mitteilung über die Terminierung der
		// aktuellen Berechnung. Zusätzlich ist der Monitor über die
		// Terminierung zu informieren. Dabei ist die Identifizierung und der
		// errechnete ggT.
		
		return false;
	}

	@Override
	public String getName() {
		return processName;
	}

}
