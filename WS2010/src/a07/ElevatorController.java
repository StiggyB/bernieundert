package a07;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ElevatorController {

	private Elevator[] elevator;
	private ThreadPoolExecutor executor;

	public ElevatorController(int elevatorCount, int floorCount) {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(elevatorCount);
		elevator = new Elevator[elevatorCount];

		for (int i = 0; i < elevator.length; i++) {
			elevator[i] = new Elevator(i);
			executor.execute(elevator[i]);
		}
	}

	public void callElevator(int floor) {
		// eigentlich müsste man alle nicht-beschäftigten raussuchen und den FS
		// nehmen der am dichtesten dran ist
		Elevator chosen = null;
		int length = 0;
		for (int i = 0; i < elevator.length; i++) {
			if (i == 0 || elevator[i].getQueueLength() < length) {
				chosen = elevator[i];
				length = elevator[i].getQueueLength();
			}
		}
		chosen.goTo(floor);
	}

	public void callElevatorPriority(int floor) {
		elevator[0].goToPriority(floor);
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < elevator.length; i++) {
			buf.append("Elevator ").append(i).append(": ")
					.append(elevator[i].getCurrentFloor()).append("\n");
		}
		return buf.toString();
	}

	public void setListener(ElevatorListener elevatorListener) {
		for (int i = 0; i < elevator.length; i++) {
			elevator[i].setListener(elevatorListener);
		}
	}

	public void blockFahrstuhl(int fsNr) {
		elevator[fsNr].block();
	}

	public void unblockFahrstuhl(int fsNr) {
		elevator[fsNr].unblock();
	}

	public void driveTo(int fsNr, int floor) {
		elevator[fsNr].driveTo(floor);
	}

}
