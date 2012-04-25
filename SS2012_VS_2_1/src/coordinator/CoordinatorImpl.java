package coordinator;

import ggt.CoordinatorPOA;
import ggt.Starter;
import ggt.ggtProcess;
import ggt.CoordinatorPackage.starterAlreadyExists;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import monitor.Monitor;

public class CoordinatorImpl extends CoordinatorPOA {

	private Set<Starter> starters = new HashSet<Starter>();
	// private List<ggtProcess> processes = new LinkedList<ggtProcess>();
	private ProcessStruct processes = new ProcessStruct();
	private int processCount;
	private int timeout;
	private int ggt;

	@Override
	public Starter[] getStarters() {
		return starters.toArray(new Starter[starters.size()]);
	}

	@Override
	public void start(int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) {
		System.out.println("CoordinatorImpl.start()");
		this.timeout = timeout;
		this.ggt = ggt;

		Random rnd = new Random();

		int processCountTmp;
		for (Starter s : starters) {
			processCountTmp = rnd.nextInt(maxProcess - minProcess) + minProcess;
			processCount += processCountTmp;
			s.createProcesses(processCountTmp);
		}

		// timeout einbauen mit exception, sleep einbauen um cpu zeit zu sparen
		while (processCount != processes.size());
		// prozesse zufällig wählen (liste shufflen?!)
		processes.shuffleProcesses();

		// ring aufbauen
		// daten setzen, nachbarn, ....
		// monitor zahlen und ringbaufbau mitteilen
		mntr.ring(processes.getProcessNames());
		mntr.startzahlen(processes.initProcesses(minDelay, maxDelay, timeout, ggt, mntr));
		ggtProcess[] startProcesses = processes.getStartProcesses();
		
		// berechnung starten, 3 prozesse mit kleinsten zahlen auswählen
		for (ggtProcess s: startProcesses) {
			s.start();
		}


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

	@Override
	public void registerProcess(ggtProcess process, String processName) {
		processes.add(process);
	}

}