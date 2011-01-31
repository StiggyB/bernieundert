package futtersuche;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VehicleController implements Runnable, FoodListener {

	
	private ExecutorService executor = Executors.newFixedThreadPool(100);
	
	public static void main(String[] args) {
		Thread controllerThread = new Thread(new VehicleController());
		controllerThread.start();
	}		

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			executor.submit(new Vehicle(i, this));
		}
		executor.shutdown();
	}

	@Override
	public synchronized void foundFood(int type) {
		System.out.println("Vehicle " + Thread.currentThread() + " found food: " + type);
	}

}
