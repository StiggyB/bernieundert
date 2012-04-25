package starter;

import monitor.Monitor;
import ggt.Coordinator;
import ggt.ggtProcessPOA;

public class ggtProcess extends ggtProcessPOA{
	

	private String processName;
	
	public ggtProcess(int i, StarterImpl starterImpl, Coordinator coordRef) {
			coordRef.registerProcess(this, starterImpl.getName(), i);
	}

	@Override
	public void initProcess(ggt.ggtProcess left, ggt.ggtProcess right,int startValue, int delay, int timeout, Monitor mntr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calc(int mi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean terminate(String processName) {
		// TODO Auto-generated method stub
		return false;
	}

}
