package a07;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Elevator implements Runnable {

	private static final int MAXIMAL_BLOCK_TIME = 5000;
	private int currentFloor;

	private final int nr;
	private ElevatorListener elevatorListener;

	private BlockingDeque<Integer> jobDeque = new LinkedBlockingDeque<Integer>();
	private boolean isBlocked = false;

	// private Object blockedMutex = new Object();

	public Elevator(int nr) {
		this.nr = nr;
		currentFloor = 0;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Integer newTargetFloor = jobDeque.takeFirst();
				// Türen zu, Knöppe aus
				elevatorListener.leavingFloor(nr);
				goToFloor(newTargetFloor);
				// Türen auf, Knöppe an
				elevatorListener.arrivedOnTargetFloor(nr);
				System.out.println(nr + ": arrived at " + currentFloor);
				Thread.sleep(5000);
				if (isBlocked) {
					System.out.println("we are blocked!");
					synchronized (this) {
						this.wait(MAXIMAL_BLOCK_TIME);
					}
					isBlocked = false;
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void goToFloor(int dstFloor) throws InterruptedException {
		while (dstFloor != currentFloor) {
			if (dstFloor > currentFloor) {
				currentFloor++;
			} else {
				currentFloor--;
			}
			Thread.sleep(300);
			elevatorListener.elevatorChangedFloor(nr, currentFloor);
		}
	}

	public void goTo(int floor) {
		jobDeque.addLast(floor);
	}

	public void goToPriority(int floor) {
		System.out.println("-------------------vorrang nach " + floor);
		jobDeque.clear();
		jobDeque.addLast(floor);
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setListener(ElevatorListener elevatorListener) {
		this.elevatorListener = elevatorListener;
	}

	public int getQueueLength() {
		return jobDeque.size();
	}

	public void block() {
		System.out.println("block " + nr);
		synchronized (this) {
			isBlocked = true;
			this.notify();
		}
	}

	public void unblock() {
		System.out.println("unblock " + nr);
		synchronized (this) {
			this.notify();
		}
	}

	public void driveTo(int floor) {
		jobDeque.addFirst(floor);
	}

}
