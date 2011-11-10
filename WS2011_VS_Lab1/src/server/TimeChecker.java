package server;
/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 01<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import client.ClientData;

/**
 * This class checks all clients connection as a 
 * concurrent thread that is thread safe implemented.
 *
 */
public class TimeChecker implements Runnable {

	/**
	 * This constant variable defines the maximum 
	 * remember time for the <i>ChatClient</i>.
	 */
	private static final long MAX_REMEM_TIME = 10000;
	
	/**
	 * Reference on the <i>HashMap</i> that contains all
	 * information about the clients. 
	 */
	private Map<String, ClientData> clientDataMap;
	
	/**
	 * This constructor initializes the <i>HashMap</i> about <i>ClientData</i>.
	 * 
	 * @param clientDataMap - reference on the <i>HashMap</i> in <i>ChatServerImpl</i>.
	 */
	public TimeChecker(Map<String, ClientData> clientDataMap){
		this.clientDataMap = clientDataMap;
	}
	
	@Override
	public void run() {
		while(true) {
			if (!(clientDataMap.isEmpty())) {
				checkClientTime();
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This Method checks all clients if they the remember 
	 * time is over and they need a new connection .
	 */
	private synchronized void checkClientTime() {
		if (!(clientDataMap.isEmpty())) {
			Set<String> s = null;
			synchronized(clientDataMap) {
			s = clientDataMap.keySet();
			}
			for (String client : s) {
				long time = 0;
				synchronized(clientDataMap) {
					time = clientDataMap.get(client).getRememTime();
				}
				if (Math.abs((time - System.currentTimeMillis())) > MAX_REMEM_TIME) {
					synchronized(clientDataMap) {
						clientDataMap.remove(client);
					}
					client = null; // -- be sure that all references are deleted
									// ->ConcurrentModificationException!
				}
			}
		}
	}
	
}
