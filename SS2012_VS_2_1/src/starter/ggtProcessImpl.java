package starter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import monitor.Monitor;
import ggtCorba.Coordinator;
import ggtCorba.ggtProcess;
import ggtCorba.ggtProcessHelper;
import ggtCorba.ggtProcessPOA;

public class ggtProcessImpl extends ggtProcessPOA {

	private String processName;
	private int Mi;
	private int startValue;
	private long lastMsg;
	private ggtProcess ggtProcess;
	private ggtProcess left;
	private ggtProcess right;
	private Monitor monitor;
	private final Coordinator coordRef;
	private boolean isCalculationTerminated = false;
	private boolean isTerminationDone = false;
	private boolean isFirstCalculationRunning = true;
	private Thread calcThread;
	private Thread termThread;
	private BlockingQueue<Integer> msges = new LinkedBlockingQueue<Integer>();
	private BlockingQueue<TerminateRequest> terminateRequests = new LinkedBlockingQueue<TerminateRequest>();

	// Konstruktor: ID, Referenz auf Starter und Coordinator setzen
	public ggtProcessImpl(int processId, StarterImpl starterImpl, Coordinator coordRef) {
		this.coordRef = coordRef;
		this.processName = starterImpl.getName() + "_" + processId;
		try {
			ggtProcess = ggtProcessHelper.narrow(starterImpl._poa().servant_to_reference(this));
			// Prozess meldet sich am Coordinator an, ist damit rechenbereit.
			coordRef.registerProcess(ggtProcess, processName);
		} catch (ServantNotActive e) {
			e.printStackTrace();
		} catch (WrongPolicy e) {
			e.printStackTrace();
		}

	}

	// Initialisiert den Prozess, dann werden zwei Threads gestartet; einer fuer die Berechnung, einer fuer die Terminierung -> Aufruf asynchron!
	@Override
	public void initProcess(final ggtProcess left, final ggtProcess right, int startValue, final int delay, final int timeout, final Monitor mntr) {
		this.left = left;
		this.right = right;
		this.Mi = startValue;
		this.startValue = startValue;
		this.monitor = mntr;
		System.out.println(processName + " called ggtProcessImpl.initProcess()");

		calcThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Rechenthread rennt solange, bis eine ausgeloeste Terminierungsanfrage erfolgreich einmal im Kreis gelaufen ist
				while (!isCalculationTerminated) {
					try {
						Integer y = msges.poll(timeout, TimeUnit.SECONDS);
						// poll() liefert null, wenn das timeout erreicht wurde,
						// ist also der retval != null -> rechnen!
						if (y != null) {
							if (y < Mi) {
								Mi = ((Mi - 1) % y) + 1;
								try {
									Thread.sleep(delay * 1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								left.calc(Mi, processName);
								right.calc(Mi, processName);
							}
						} else {
							// retval vom poll war null, also timeout
							// abgelaufen, starte terminierungsanfrage, aber nur
							// wenn mind. 1x gerechnet wurde
							if (!isCalculationTerminated && !isFirstCalculationRunning) {
								right.terminate(processName, true);
								System.out.println(processName + " started term req");
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		termThread = new Thread(new Runnable() {

			@Override
			public void run() {
				TerminateRequest terminationRequest;

				boolean isOwnCalcFinished = true;
				// solange laufen, bis der starter mich killt... 
				while (!isTerminationDone) {
					try {
						// Solange blockieren, bis ein Terminierungsrequest da ist 
						terminationRequest = terminateRequests.take();
					} catch (InterruptedException e) {
						// kommt der Interrupt aus kill, req auf null und raus aus der while -> Thread endet
						terminationRequest = null;
					} 
					if (terminationRequest != null) {
						// wenn die gepollte term anfrage von mir selbst kam und sie immer noch true ist, kann ich aufhören ....
						// war die msg zwar von mir, aber false, verwirf sie einfach...deswegen das else if 
						if (terminationRequest.getProcessName().equals(processName) && terminationRequest.isTerminationOk() && isOwnCalcFinished) {
							System.out.println(processName + " isTerminated == true;");
							// calc thread auslaufen lassen -> ist dann beendet
							isCalculationTerminated = true;
							// dieses if nicht nochmal ausführen ...
							isOwnCalcFinished = false;
							// ... sonst würde dieser teil hier mehrmals
							// ausgeführt ...
							monitor.ergebnis(processName, Mi);
							coordRef.processCalcDone(ggtProcess);
							// kam die msg nicht von mir selbst, weiterleiten
						} else if (!terminationRequest.getProcessName().equals(processName)) {
							// Zeit vergleichen, wenn timeout/2 verstrichen ist, seit dem letzten aufruf von calc() und true drin stand, an
							// nachbar entsprechend mit dem urspürnglichen absender und dem true weitersenden
							boolean isTerminationTimeoutReached = (System.currentTimeMillis() - lastMsg) >= (timeout * 1000 / 2);
							if (isTerminationTimeoutReached && terminationRequest.isTerminationOk()) {
								right.terminate(terminationRequest.getProcessName(), true);
								System.out.println(processName + " forwarded positive req");
							} else {
								// timeout ist nocht nicht abgelaufen oder es stand eh false im request, dann eben false weiterleiten ...
								right.terminate(terminationRequest.getProcessName(), false);
								System.out.println(processName + " forwarded negative req");
							}
						}
					}
				}
			}
		});

		calcThread.start();
		termThread.start();
	}

	// start wird nur auf den drei Prozessen mit den kleinsten Startwerten gerufen, damit der Algorithmus funktioniert
	@Override
	public void start() {
		left.calc(Mi, processName);
		right.calc(Mi, processName);
	}

	// neue Nachricht mit neuer Zahl, da asynchron sein soll, Wert in Queue legen, Zeit merken, wann das war, Monitor bescheid geben
	@Override
	public void calc(int y, String msgFrom) {
		// Damit keine Terminierung ausgelöst wird, ohne dass der Thread einmal gerechnet hat ...
		isFirstCalculationRunning = false;
		msges.offer(y);
		lastMsg = System.currentTimeMillis();
		monitor.rechnen(processName, msgFrom, y);
	}

	// Eingehende Terminierungsrequests, enthalten Absender, Status
	@Override
	public void terminate(String processName, boolean isAllowed) {
		// Req queuen und Monitor bescheid geben
		terminateRequests.offer(new TerminateRequest(processName, System.currentTimeMillis(), isAllowed));
		monitor.terminieren(this.processName, processName, isAllowed);
	}

	@Override
	public String getName() {
		return processName;
	}

	// Prozess beenden
	@Override
	public void kill() {
		isTerminationDone = true;
		termThread.interrupt();
		System.out.println(processName + " has exited ... now die ..");
	}

	@Override
	public int getStartValue() {
		return startValue;
	}

}
