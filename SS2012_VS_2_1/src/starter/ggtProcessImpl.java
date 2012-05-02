package starter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import monitor.Monitor;
import ggtCorba.Coordinator;
import ggtCorba.ggtProcess;
import ggtCorba.ggtProcessHelper;
import ggtCorba.ggtProcessPOA;

public class ggtProcessImpl extends ggtProcessPOA implements Runnable{

	private String processName;
	private ggtProcess left;
	private ggtProcess right;
	private int Mi;
	private int delay;
	private int timeout;
	private Monitor mntr;
	private LinkedBlockingQueue<Integer> msges = new LinkedBlockingQueue<Integer>();
	private final Coordinator coordRef;
	private ggtProcess ggtProcess;
	private boolean isTerminated =  false;
	private Thread thread;
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
	public void initProcess(ggtProcess left, ggtProcess right,int startValue, int delay, int timeout, Monitor mntr) {
		this.left = left;
		this.right = right;
		this.Mi = startValue;
		this.delay = delay;
		this.timeout = timeout;
		this.mntr = mntr;
		System.out.println("ggtProcessImpl.initProcess()");
		
		thread = new Thread(this);
		thread.start();
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
	public void terminate(String processName, boolean isAllowed) { 
		if(this.processName == processName && isAllowed){
			isTerminated = true;
		}
		mntr.terminieren(this.processName, processName, isAllowed);
		
		if((System.currentTimeMillis() - lastMsg) / 1000 < timeout/2){
			//false an nachbarn
		} else {
			//true an nachbarn
		}
		
		//TODO: Ttimeout/2 muss abgelaufen sein, wenn JA dann termkinate mit true, sonst false
		//TODO: zus�tzlich zum namen noch n bool, ob ok ist oder eben nicht?! return param ist doch quatsch hier ...
	}

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
					right.terminate(processName, true);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mntr.ergebnis(processName, Mi);
		coordRef.processCalcDone(ggtProcess);
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
	// - Was passiert, wenn der Timeout abgelaufen ist? Terminate wird ausgel�st, wird dann noch auf msges gewartet?
	// - Was passiert, wenn terminate negativ beantwortet wurde? wieder in poll()?
	// - Was macht der Thread, w�hrend das terminate zirkuliert? pollen oder nicht?
	// - Wenn terminate ausgel�st wurde, was macht der Thread? Weiter pollen? und wenn timout nochmals erreicht wird, was dann?
	// - Muss urspr�nglicher startwert erhalten bleiben? Oder kann Mi immer durch y ersetzt werden?!

}
