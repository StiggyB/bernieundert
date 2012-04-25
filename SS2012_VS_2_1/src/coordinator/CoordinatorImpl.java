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
//	private List<ggtProcess> processes = new LinkedList<ggtProcess>();
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
		
		//timeout einbauen mit exception, sleep einbauen um cpu zeit zu sparen
		while(processCount != processes.size());
		// prozesse zufällig wählen (liste shufflen?!)
		processes.shuffleProcesses();
		
		mntr.ring(processes.getProcessNames());
		// ring aufbauen
		// daten setzen, nachbarn, ....
		ggtProcess left;
		ggtProcess right;
		int startValue;
		int delay;
		for (int i = 0; i < processes.size(); i++) {
			left = processes.get(i-1 < 0 ? processes.size()-1 : i-1);
			right = processes.get(i+1 % processes.size());
			startValue = ggt * (rnd.nextInt(100)+1) * (rnd.nextInt(100)+1);
			delay = rnd.nextInt(maxDelay - minDelay) + minDelay;
			processes.get(i).initProcess(left, right, startValue, delay, this.timeout, mntr);
		}
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
	
	class ProcessStruct {

		private List<ggtProcess> processes = new LinkedList<ggtProcess>();

		void add(ggtProcess process) {
			processes.add(process);
		}

		ggtProcess left(ggtProcess process) {
			return processes
					.get(processes.indexOf(process) - 1 == 0 ? processes.size() - 1 : processes.indexOf(process) - 1);
		}

		ggtProcess right(ggtProcess process) {
			return processes.get(processes.indexOf(process) + 1	% processes.size());
		}
		
		void shuffleProcesses(){
			Collections.shuffle(processes);
		}
		
		int size(){
			return processes.size();
		}
		
		ggtProcess get(int i){
			return processes.get(i);
		}

		String[] getProcessNames() {
			String[] processNames = new String[processes.size()];
			for (int i = 0; i < processes.size(); i++) {
				processNames[i] = processes.get(i).getName();
				System.out.println(processes.get(i).getName());
			}
			return processNames;
		}
	}

}