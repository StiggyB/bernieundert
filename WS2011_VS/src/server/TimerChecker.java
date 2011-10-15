package server;

import java.util.concurrent.TimeUnit;

public class TimerChecker implements Runnable {

	@Override
	public void run() {
		while(true) {
			
		
		
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
