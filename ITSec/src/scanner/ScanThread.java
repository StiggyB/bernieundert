package scanner;

import java.net.Socket;

public class ScanThread implements Runnable {
	private int port;
	private String host;

	public ScanThread(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			Socket target = new Socket(host, port);
			target.close();
			System.out.println("Found open Port: " + port);
		} catch (Exception e) {
			// oeffnen des Sockets schlug fehl, Port closed/filtered
		}
	}
}