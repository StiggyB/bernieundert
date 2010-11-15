package a07;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ElevatorController {
	private ElevatorJob[] elevator;
	
	private ThreadPoolExecutor executor;
	
//	public static void main(String[] args) throws InterruptedException {
//		ElevatorController controller = new ElevatorController(3, 10);
//		System.out.println(controller);
//		controller.callElevator(6, 2);
//		Thread.sleep(100);
//		controller.callElevator(8, 1);
//		Thread.sleep(100);
//		controller.callElevator(2, 10);
//		Thread.sleep(2000);
//		System.out.println(controller);
//	}
	
	public ElevatorController(int elevatorCount, int floorCount) {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(elevatorCount);
		
	}
	
	public void callElevator(int floor) {
		ElevatorJob job = new ElevatorJob(floor);
		executor.execute(job);
	}
	
	public void callPriorityElevator(int floor) {
		ElevatorJob job = new ElevatorJob(floor);
		((LinkedBlockingQueue<Runnable>) executor.getQueue()).clear();
		executor.execute(job);
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
