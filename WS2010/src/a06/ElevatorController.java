package a06;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ElevatorController {
	private Elevator[] elevator;
	
	private ThreadPoolExecutor executor;
	
	public static void main(String[] args) throws InterruptedException {
		ElevatorController controller = new ElevatorController(3, 10);
		System.out.println(controller);
		controller.callElevator(6, 2);
		Thread.sleep(100);
		controller.callElevator(8, 1);
		Thread.sleep(100);
		controller.callElevator(2, 10);
		Thread.sleep(2000);
		System.out.println(controller);
	}
	
	public ElevatorController(int elevatorCount, int floorCount) {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(elevatorCount);
		
//		((LinkedBlockingQueue<Runnable>) executor.getQueue()).clear();
		
		elevator = new Elevator[elevatorCount];
		for (int i = 0; i < elevator.length; i++) {
			elevator[i] = new Elevator(i);
			executor.execute(elevator[i]);
		}	
		
	}
	
	public void callElevator(int entryFloor, int exitFloor) {
		// eigentlich müsste man alle nicht-beschäftigten raussuchen und den FS nehmen der am dichtesten dran ist
		Elevator chosen = null;
		for (int i = 0; i < elevator.length; i++) {
			if (!elevator[i].isBusy()) {
				chosen = elevator[i];
			}
		}
		if (chosen == null) {
			// Wenn keiner gefunden, dann gucken welcher am gewünschten Stockwerk vorbei kommt
			throw new IllegalStateException("All busy");
		}
		while (!chosen.isInitialized()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		chosen.goTo(entryFloor, exitFloor);
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < elevator.length; i++) {
			buf.append("Elevator ").append(i).append(": ").append(elevator[i].getCurrentFloor()).append("\n");
		}	
		return buf.toString();
	}

	public void setListener(ElevatorListener elevatorListener) {
		for (int i = 0; i < elevator.length; i++) {
			elevator[i].setListener(elevatorListener);
		}
	}
}
