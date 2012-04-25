package coordinator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import monitor.Monitor;

import ggt.ggtProcess;

class ProcessStruct {

	private List<ggtProcess> processes = new LinkedList<ggtProcess>();

	public void add(ggtProcess process) {
		processes.add(process);
	}

	private ggtProcess left(ggtProcess process) {
		return processes
				.get(processes.indexOf(process) - 1 == 0 ? processes.size() - 1 : processes.indexOf(process) - 1);
	}

	private ggtProcess right(ggtProcess process) {
		return processes.get(processes.indexOf(process) + 1	% processes.size());
	}
	
	public void shuffleProcesses(){
		Collections.shuffle(processes);
	}
	
	public int size(){
		return processes.size();
	}
	
	public ggtProcess get(int i){
		return processes.get(i);
	}
	
	public int[] initProcesses(int minDelay, int maxDelay, int timeout,	int ggt, Monitor mntr) {

		ggtProcess right;
		ggtProcess left;
		int startValue;
		int delay;
		Random rnd = new Random();
		int[] startValues = new int[processes.size()];

		for (int i = 0; i < processes.size(); i++) {
			right = right(processes.get(i));
			left = left(processes.get(i));
			startValue = ggt * (rnd.nextInt(100) + 1) * (rnd.nextInt(100) + 1);
			startValues[i] = startValue;
			delay = rnd.nextInt(maxDelay - minDelay) + minDelay;
			processes.get(i).initProcess(left, right, startValue, delay, timeout, mntr);
		}
		return startValues;
	}

	public String[] getProcessNames() {
		String[] processNames = new String[processes.size()];
		for (int i = 0; i < processes.size(); i++) {
			processNames[i] = processes.get(i).getName();
			System.out.println(processes.get(i).getName());
		}
		return processNames;
	}
}