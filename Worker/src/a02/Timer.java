package a02;

/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 02<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timer implements Serializable {

	private static final long serialVersionUID = 1L;
	private long startTime = -1;
	private long durationTime = -1;
	private long accCpuTime = -1;
	private List<Long> cpuTimes;

	public void setCpuTimes(List<Long> cpuTimes) {
		this.cpuTimes = cpuTimes;
	}

	public Timer(long durationTime) {
		super();
		this.durationTime = durationTime;
		this.cpuTimes = new ArrayList<Long>();
	}

	public long getAccCpuTime() {
		return accCpuTime;
	}

	public void setAccCpuTime(long accCpuTime) {
		this.accCpuTime = accCpuTime;
	}

	public List<Long> getCpuTimes() {
		return cpuTimes;
	}

	public long getDurationTime() {
		return durationTime;
	}

	public void addCpuTime(Long cpuTime) {
		if (cpuTime != null) {
			this.cpuTimes.add(cpuTime);
		}
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();

	}

	public void stopTimer() {
		this.durationTime = (System.currentTimeMillis() - this.startTime);
	}

	public void printTimes() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		StringBuilder cpuTimeString = new StringBuilder();
		int i = 1;
		long completeCpuTime = 0;
		for (Long cpuTime : cpuTimes) {
			cpuTimeString.append("\t\t[Factor " + i + ": "
					+ (cpuTime / 1000 % 60) + " Sec : " + (cpuTime % 1000)
					+ " Msec]\n");
			completeCpuTime += cpuTime;
			i++;
		}
		return "Wall time:\t\t[" + (durationTime / 1000 / 3600) + " Hours : "
				+ ((durationTime / 1000 % 3600) / 60) + " Min : "
				+ (durationTime / 1000 % 60) + " Sec : "
				+ (durationTime % 1000) + " Msec]" + "\nCPU Times:"
				+ cpuTimeString + "Complete CPU Time:\t["
				+ (completeCpuTime / 1000 % 60) + " Sec : "
				+ (completeCpuTime % 1000) + " Msec]";
	}

}
