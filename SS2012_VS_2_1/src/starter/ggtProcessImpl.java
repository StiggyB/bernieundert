package starter;

import java.util.concurrent.LinkedBlockingQueue;

import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import monitor.Monitor;
import ggt.Coordinator;
import ggt.ggtProcess;
import ggt.ggtProcessHelper;
import ggt.ggtProcessPOA;

public class ggtProcessImpl extends ggtProcessPOA implements Runnable{

	private String processName;
	private ggtProcess left;
	private ggtProcess right;
	private int startValue;
	private int delay;
	private int timeout;
	private Monitor mntr;
	private LinkedBlockingQueue<Integer> msges = new LinkedBlockingQueue<Integer>();
	private final Coordinator coordRef;
	ggtProcess ggtProcess;
	private boolean ready =  false;
	
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
		this.startValue = startValue;
		this.delay = delay;
		this.timeout = timeout;
		this.mntr = mntr;
		System.out.println("ggtProcessImpl.initProcess()");
		
		new Thread(this).start();
	}

	@Override
	public void start() {
//		left.calc(startValue);
//		right.calc(startValue);
		ready = true;
	}

	@Override
	public void calc(int y) {
		ready = true;
		//TODO: msg mntr new number
		//TODO: monitor will ausgeben, von wo die zahl kam, also muss wohl idl angepasst werden, damit id/name des prozesses bekannt
//		if (y < startValue) {
//			//TODO: wennn er calcen muss, $delay warten b4 msg2neighbours...
//			startValue = ((startValue - 1) % y) + 1;
//			try {
//				Thread.sleep(delay * 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			left.calc(startValue);
//			right.calc(startValue);
//		}
	}
	
	//TODO: run methode bauen?! die eine state-gesteuerte endlos while-loop hat, worin zeit geprüft wird und msges verwaltet werden?!
	//TODO: LinkedBlockingQueue -> wg der eintreffenden msges, wenn keine da ist -> blocked sie bis timeout und dann terminate()
	@Override
	public boolean terminate(String processName) {
		return false;
	}

	@Override
	public String getName() {
		return processName;
	}

	@Override
	public void run() {
		while(ready);
		System.out.println("ggtProcessImpl.run()");
		coordRef.processCalcDone(ggtProcess);
	}

	@Override
	public void kill() {
		Runtime.getRuntime().exit(1);
	}

}
