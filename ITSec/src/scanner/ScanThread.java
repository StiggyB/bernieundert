package scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

			PrintStream os = new PrintStream(target.getOutputStream());
			String test = "GET / http1.1\n\n";
			os.println(test);
			BufferedReader in = new BufferedReader(new InputStreamReader(target.getInputStream()));
			// String s = "";
			String s = in.readLine();
			target.close();
			System.out.println("Found open Port: " + port + " --> " + s);
		} catch (Exception e) {
		}
	}
}