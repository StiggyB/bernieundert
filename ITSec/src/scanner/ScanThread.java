package scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
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

			BufferedReader in = new BufferedReader(new InputStreamReader(target.getInputStream()));
			String s = in.readLine();
			target.close();
			System.out.println("Found open Port: " + port + " --> " + s);
		} catch (Exception e) {
		}
	}
}