package starter;

import java.util.LinkedList;
import java.util.Queue;
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
	private ggtProcess left;
	private ggtProcess right;
	private int Mi;
	private Monitor mntr;
	private LinkedBlockingQueue<Integer> msges = new LinkedBlockingQueue<Integer>();
	private Queue<TerminateRequest> terminateRequests = new LinkedList<TerminateRequest>();
	private final Coordinator coordRef;
	private ggtProcess ggtProcess;
	private boolean isTerminated =  false;
	private Thread calcThread;
	private Thread termThread;
	private long lastMsg;
	private int startValue;
	
	public ggtProcessImpl(int i, StarterImpl starterImpl, Coordinator coordRef) {
		this.coordRef = coordRef;
		this.processName = starterImpl.getName() + "_" + i;	
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
	public void initProcess(final ggtProcess left, final ggtProcess right,int startValue, final int delay, final int timeout, final Monitor mntr) {
		this.left = left;
		this.right = right;
		this.Mi = startValue;
		this.startValue = startValue;
		this.mntr = mntr;
		System.out.println(processName + " called ggtProcessImpl.initProcess()");
		
		calcThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isTerminated) {
					try {
						Integer y = msges.poll(timeout, TimeUnit.SECONDS);
						// poll() liefert null, wenn das timeout erreicht wurde, ist also der retval != null -> rechnen!
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
							//retval vom poll war null, also timeout abgelaufen, starte terminierungsanfrage
							right.terminate(processName, true);
							System.out.println(processName + " started term req");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//				mntr.ergebnis(processName, Mi);
//				coordRef.processCalcDone(ggtProcess);
			}				
		});
		
		termThread = new Thread(new Runnable() {

			@Override
			public void run() {
				TerminateRequest req;

				boolean running = true;
				//solange laufen, bis der starter mich killt... ich wois, frisst viel cpu zeit ... kA wie man das verbessern kann, da ich ja nicht
				//weiss, ob die anderen prozesse noch arbeiten und ich noch msges zum verarbeiten kriege
				while (true) {
					req = terminateRequests.poll();
					//wenn beim poll nix drin war -> NULL und damit neuer anlauf ...
					if (req != null) {
						// wenn die gepollte term anfrage von mir selbst kam und sie immer noch true ist, kann ich aufhören .... 
						// war die msg zwar von mir, aber false, verwirf sie einfach...deswegen das else if in z. 113
						if (req.getProcessName().equals(processName) && req.getTerminate() && running) {
							System.out.println(processName + " isTerminated == true;");
							// calc thread auslaufen lassen -> ist dann beendet
							isTerminated = true;
							//dieses if nicht nochmal ausführen ...
							running = false;
							//... sonst würde dieser teil hier mehrmals ausgeführt ...
							mntr.ergebnis(processName, Mi);
							coordRef.processCalcDone(ggtProcess);
							//kam die msg nicht von mir selbst, weiterleiten
						} else if(!req.getProcessName().equals(processName)) {
							//kam die gepollte nachricht nicht von mir, dann weiterleiten ...
							//Zeit vergleichen, wenn timeout/2 verstrichen ist, seit dem letzten aufruf von calc() und true drin stand, an 
							//nachbar entsprechend mit dem urspürnglichen absender und dem true weitersenden
							if (((System.nanoTime() - lastMsg) >= ((timeout / 2) >> 9)) && req.getTerminate()) {
								right.terminate(req.getProcessName(), true);
								System.out.println(processName + " forwarded positive req");
							} else {
								//timeout ist nocht nicht abgelaufen oder es stand eh false im request, dann eben false weiterleiten ...
								right.terminate(req.getProcessName(), false);
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

	@Override
	public void start() {
		left.calc(Mi, processName);
		right.calc(Mi, processName);
	}

	@Override
	public void calc(int y, String msgFrom) {
		msges.offer(y);
		lastMsg = System.nanoTime();
		mntr.rechnen(processName, msgFrom, y);
	}
	
	@Override
	public void terminate(String processName, boolean isAllowed) { 
		terminateRequests.offer(new TerminateRequest(processName, System.currentTimeMillis(), isAllowed));
		mntr.terminieren(this.processName, processName, isAllowed);
	}

	@Override
	public String getName() {
		return processName;
	}

	@Override
	public void kill() {
		Runtime.getRuntime().exit(1);
	}
	
	@Override
	public int getStartValue() {
		return startValue;
	}
	
}
