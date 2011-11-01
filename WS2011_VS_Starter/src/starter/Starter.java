package starter;

import a02.Master;
import a02.Worker;

public class Starter {

	public static void main(String[] args) {
		Worker worker = new Worker();
		Master master = new Master();
		worker.start();
		master.start();
		
	}
}
