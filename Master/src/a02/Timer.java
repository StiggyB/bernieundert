package a02;


public class Timer {

	private long startTime = -1;
	private long durationTime = - 1;

	
	public Timer(long durationTime) {
		super();
		this.durationTime = durationTime;
	}

	public long getDurationTime() {
		return durationTime;
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
		
	}
	
	public Timer stopTimer() {
		this.durationTime = (System.currentTimeMillis() - this.startTime);
		return this;
	}
	
	@Override
	public String toString() {
		return "[" + (durationTime / 1000 / 3600) + " Hours : " + ((durationTime / 1000 % 3600) / 60) + " Min : " + (durationTime / 1000 % 60) + " Sec : " + (durationTime % 1000) + " Msec]";
	}
	
	
}
