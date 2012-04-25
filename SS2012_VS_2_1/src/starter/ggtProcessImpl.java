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
		if (y < startValue) {
			startValue = ((startValue - 1) % y) + 1;
			left.calc(startValue);
			right.calc(startValue);
		}
	}

	@Override
	public boolean terminate(String processName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return processName;
	}

}
