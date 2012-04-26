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
	private ProcessStruct processes = new ProcessStruct();
	private int processCount;
	private int timeout;
	private int ggt;
	
	//TODO: wenn eine berechnung fertig ist, alles wieder in ursprungszustand setzen (starter, coord) fuer neue berechnung

	@Override
	public Starter[] getStarters() {
		return starters.toArray(new Starter[starters.size()]);
	}

	@Override
	public void start(int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) {
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

	//TODO: Nach Terminierung der Berechnung beauftragt der Koordinator die Starter mit der Beendigung der Prozesse.
	// woher wois der, dass es fertig ist? ok, prozess sagt coord bescheid, wenn er terminiert ... -> moar shutdown in idl fuer coord und starter:)
	@Override
	public void shutdown() {
		// TODO Der Client kann über den Koordinator die Beendigung des Systems
		// anstossen. Die Starter werden vom Koordinator über die Beendigung des
		// Systems informiert. Falls noch ggT-Prozesse laufen, sollen diese
		// unabhängig von ihrem momentanen Zustand möglichst unverzüglich
		// beendet werden. Anschließend beenden sich die Starter und zum Schluss
		// der Koordinator.
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