package coordinator;

import ggt.CoordinatorPOA;
import ggt.Starter;
import ggt.ggtProcess;
import ggt.CoordinatorPackage.starterAlreadyExists;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import monitor.Monitor;


public class CoordinatorImpl extends CoordinatorPOA{

	private Set<Starter> starters = new HashSet<Starter>();
	private List<ggtProcess> processes = new LinkedList<ggtProcess>();
	private int processCount;
	private int delay;
	private int timeout;
	private int ggt;
	
	@Override
	public Starter[] getStarters() {
		return starters.toArray(new Starter[starters.size()]);
	}

	@Override
	public void start(int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) {
		System.out.println("CoordinatorImpl.start()");
		this.delay = maxDelay;
		this.timeout = timeout;
		this.ggt = ggt;

		Random rnd = new Random();
		
		delay = rnd.nextInt(maxDelay - minDelay) + minDelay;
		
		int processCountTmp;
		for (Starter s : starters) {
			processCountTmp = rnd.nextInt(maxProcess - minProcess) + minProcess;
			processCount += processCountTmp;
			s.createProcesses(processCountTmp);
		}
		
		//timeout einbauen mit exception
		while(processCount != processes.size());
		// prozesse zufällig wählen (liste shufflen?!)
		Collections.shuffle(processes);
		mntr.ring(processes.to)
		// ring aufbauen
		// daten setzen, nachbarn, ....
		// monitor zahlen und ringbaufbau mitteilen
		// berechnung starten, 3 prozesse mit kleinsten zahlen auswählen
		
		
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
		processes.add(process);
	}

}
