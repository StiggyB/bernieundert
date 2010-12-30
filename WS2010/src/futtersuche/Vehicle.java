package futtersuche;

import java.util.Random;

public class Vehicle implements Runnable {

	private final int i;
	private final FoodListener foodListener;
	private Random random = new Random();

	public Vehicle(int i, FoodListener foodListener) {
		this.i = i;
		this.foodListener = foodListener;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Searching " + i + " / " + Thread.currentThread().toString());
			try {
				Thread.sleep(2000);
				if (random.nextInt(10) == 5) {
					foodListener.foundFood(random.nextInt(10));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
