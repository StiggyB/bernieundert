package coordinator;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import monitor.Monitor;

import ggtCorba.ggtProcess;

/**
 * Management- and adapter-class for administration of the registered
 * {@link ggtProcess}. Processes are hold in a LinkedList. The TreeMap is used
 * to have all processes sorted by their startValue. So with this it is fairly
 * easy to determine the three processes with the smallest startValue to start
 * the algorithm.
 * 
 * @author Martin
 * 
 */
public class ProcessManager {

	private List<ggtProcess> processes = new LinkedList<ggtProcess>();
	private Map<Integer, ggtProcess> sortedProcesses = new TreeMap<Integer, ggtProcess>();

	/**
	 * Adds a new {@link ggtProcess} to the list.
	 * 
	 * @param process
	 *            new process
	 */
	public void add(ggtProcess process) {
		processes.add(process);
	}

	/**
	 * Determines the left neighbor of a given {@link ggtProcess} .
	 * 
	 * @param process
	 *            a {@link ggtProcess}
	 * @return left neighbor
	 */
	private ggtProcess left(ggtProcess process) {
		return processes.get(processes.indexOf(process) == 0 ? processes.size() - 1 : processes.indexOf(process) - 1);
	}

	/**
	 * 
	 * Determines the left right of a given {@link ggtProcess} .
	 * 
	 * @param process
	 *            a {@link ggtProcess}
	 * @return right neighbor
	 */
	private ggtProcess right(ggtProcess process) {
		return processes.get((processes.indexOf(process) + 1) % processes.size());
	}

	/**
	 * Mixes all processes in the list to get a random order.
	 */
	public void shuffleProcesses() {
		Collections.shuffle(processes);
	}

	/**
	 * Size of the list, means count of {@link ggtProcess} inside the list.
	 * 
	 * @return count of {@link ggtProcess} in the list.
	 */
	public int size() {
		return processes.size();
	}

	/**
	 * Get a {@link ggtProcess} from a specific position within the list.
	 * 
	 * @param i
	 *            index of {@link ggtProcess}
	 * @return {@link ggtProcess} with given index
	 */
	public ggtProcess get(int i) {
		return processes.get(i);
	}

	/**
	 * Sets up all processes to be ready for start of calculation. Each process
	 * gets its right and left neighbor, a random startValue based on the given
	 * ggt, random delay based on given delay-interval, a preset timeout and a
	 * monitor-reference for logging purpose.
	 * 
	 * @param minDelay
	 *            minimum interval boundary for delay in seconds
	 * @param maxDelay
	 *            maximum interval boundary for delay in seconds
	 * @param timeout
	 *            time in seconds before a termination request will be started
	 * @param ggt
	 *            greatest common divisor to calculate random startValues for
	 *            the processes
	 * @param mntr
	 *            reference for a logging monitor
	 * @return startValues of all processes in an array
	 */
	public int[] initProcesses(int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) {

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
			sortedProcesses.put(startValue, processes.get(i));
		}
		return startValues;
	}

	/**
	 * Getter method for all process names.
	 * 
	 * @return all process names
	 */
	public String[] getProcessNames() {
		String[] processNames = new String[processes.size()];
		for (int i = 0; i < processes.size(); i++) {
			processNames[i] = processes.get(i).getName();
		}
		return processNames;
	}

	/**
	 * Method determines the three processes with the smallest startValues and
	 * returns them.
	 * 
	 * @return three processes with the smallest startValues
	 */
	public ggtProcess[] getStartProcesses() {
		return Arrays.copyOf(sortedProcesses.values().toArray(new ggtProcess[sortedProcesses.size()]), 3);
	}

	/**
	 * Determines if the list of processes is empty or has elements.
	 * 
	 * @return <code>true</code> if list is empty, <code>false</code> if not.
	 */
	public boolean isEmpty() {
		return processes.isEmpty();
	}

	/**
	 * Method removes a process from the list of processes.
	 * 
	 * @param process
	 *            process to be removed
	 */
	public void remove(ggtProcess process) {
		if (processes.contains(process)) {
			processes.remove(process);
			sortedProcesses.remove(process.getStartValue());
		}

	}
}