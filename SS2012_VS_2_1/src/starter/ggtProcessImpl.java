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
	private int delay;
	private int timeout;
	private Monitor mntr;
	private int terminateId = 0;
	private LinkedBlockingQueue<Integer> msges = new LinkedBlockingQueue<Integer>();
	private Queue<TerminateRequest> terminateRequests = new LinkedList<TerminateRequest>();
	private final Coordinator coordRef;
	private ggtProcess ggtProcess;
	private boolean isTerminated =  false;
	private Thread calcThread;
	private Thread termThread;
	private long lastMsg;
	
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
		this.delay = delay;
		this.timeout = timeout;
		this.mntr = mntr;
		System.out.println("ggtProcessImpl.initProcess()");
		
		calcThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isTerminated) {
					try {
						Integer y = msges.poll(timeout, TimeUnit.SECONDS);
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
							right.terminate(processName, true, terminateId);
							terminateId++;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				mntr.ergebnis(processName, Mi);
				coordRef.processCalcDone(ggtProcess);
			}				
		});
		
		termThread = new Thread(new Runnable() {

			@Override
			public void run() {
				TerminateRequest req;

				while (!isTerminated) {
					req = terminateRequests.poll();
					if (req != null) {
						if (req.getProcessName() == processName	&& req.getTerminate()) {
							if(req.getTerminateId() == terminateId){
								isTerminated = true;
							}
						}
						if ((System.currentTimeMillis() - lastMsg) / 1000 < timeout / 2) {
							right.terminate(req.getProcessName(), false, req.getTerminateId());
						} else {
							right.terminate(req.getProcessName(), true, req.getTerminateId());
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
		lastMsg = System.currentTimeMillis();
		mntr.rechnen(processName, msgFrom, y);
	}
	
	@Override
	public void terminate(String processName, boolean isAllowed, int terminateId) { 
		terminateRequests.offer(new TerminateRequest(processName, System.currentTimeMillis(), isAllowed, terminateId));
		mntr.terminieren(this.processName, processName, isAllowed);
		
		//TODO: Ttimeout/2 muss abgelaufen sein, wenn JA dann termkinate mit true, sonst false
		//TODO: zusätzlich zum namen noch n bool, ob ok ist oder eben nicht?! return param ist doch quatsch hier ...
		
	}

	@Override
	public String getName() {
		return processName;
	}

	@Override
	public void kill() {
		Runtime.getRuntime().exit(1);
	}
	
	
	//TODO: 
	// - Was passiert, wenn der Timeout abgelaufen ist? Terminate wird ausgelöst, wird dann noch auf msges gewartet?
	// - Was passiert, wenn terminate negativ beantwortet wurde? wieder in poll()?
	// - Was macht der Thread, während das terminate zirkuliert? pollen oder nicht?
	// - Wenn terminate ausgelöst wurde, was macht der Thread? Weiter pollen? und wenn timout nochmals erreicht wird, was dann?
	// - Muss ursprünglicher startwert erhalten bleiben? Oder kann Mi immer durch y ersetzt werden?!

}
