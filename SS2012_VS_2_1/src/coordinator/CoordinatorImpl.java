package coordinator;

import ggt.CoordinatorPOA;
import ggt.Starter;
import ggt.ggtProcess;
import ggt.CoordinatorPackage.starterAlreadyExists;

import java.util.HashSet;
import java.util.Set;

import monitor.Monitor;


public class CoordinatorImpl extends CoordinatorPOA{

	private Set<Starter> starters = new HashSet<Starter>();
	private int minProcess;
	private int maxProcess;
	private int minDelay;
	private int maxDelay;
	private int timeout;
	private int ggt;
	
	@Override
	public Starter[] getStarters() {
		return starters.toArray(new Starter[starters.size()]);
	}

	@Override
	public void start(int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) {
		this.minProcess = minProcess;
		this.maxProcess = maxProcess;
		this.minDelay = minDelay;
		this.maxDelay = maxDelay;
		this.timeout = timeout;
		this.ggt = ggt;

		System.out.println("CoordinatorImpl.start()");
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		System.out.println("CoordinatorImpl.shutdown()");
	}

	@Override
	public void registerStarter(Starter starter) throws starterAlreadyExists {
		for (Starter s : starters) {
			if (s.getName().equals(starter.getName())) {
				throw new starterAlreadyExists("Starter " + starter.getName() + " is already registered");
			}
		}
		
		starters.add(starter);
	}

//	@Override
//	public void registerProcess(ggtProcess process, String starterName, int id) {
//		String processName = starterName + id;
//		// eigentlich soll sich doch nur der prozess mit seiner id registrieren, die aus startername und id besteht
//		// do more stuff here
//	}

	@Override
	public void registerProcess(ggtProcess process, String processName) {
		//do time consuming stuff
	}

}
