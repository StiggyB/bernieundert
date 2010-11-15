package a07;


public class ElevatorJob implements Runnable {
	
	private int currentFloor;
	private boolean busy;
	private int entryFloor;
	private int exitFloor;
	
	private boolean initialized;
	private final int nr;
	private ElevatorListener elevatorListener;
	
	public ElevatorJob(int nr) {
		this.nr = nr;
		currentFloor = 0;
		entryFloor = -1;
		exitFloor = -1;
		busy = false;
		initialized = false;
	}
	
	@Override
	public void run() {
		try {
			goToFloor(entryFloor);
			System.out.println(nr + ": arrived at " + currentFloor);
			
			goToFloor(exitFloor);
			System.out.println(nr + ": arrived at " + currentFloor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void goToFloor(int dstFloor) throws InterruptedException {
		if (dstFloor != currentFloor) {
			if (dstFloor > currentFloor) {
				for (; currentFloor < dstFloor; currentFloor++) {
					Thread.sleep(300);
					elevatorListener.elevatorChangedFloor(nr, currentFloor);
					System.out.println(nr + ": " + currentFloor);
				}
			} else {
				for (; currentFloor > dstFloor; currentFloor--) {
					Thread.sleep(300);
					elevatorListener.elevatorChangedFloor(nr, currentFloor);
					System.out.println(nr + ": " + currentFloor);
				}
			}
			elevatorListener.elevatorChangedFloor(nr, currentFloor);
		}
	}
	
	public void goTo(int entryFloor, int exitFloor) {
		this.entryFloor = entryFloor;
		this.exitFloor = exitFloor;
		synchronized (this) {
			this.notify();
		}
	}
	
	public boolean isBusy() {
		return busy;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public boolean isInitialized() {
		synchronized (this) {
			return initialized;
		}
	}

	public void setListener(ElevatorListener elevatorListener) {
		this.elevatorListener = elevatorListener;
	}
	
}
