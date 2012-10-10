package scanner;

import java.util.LinkedList;
import java.util.List;

public class PortScanner {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("-- usage: java -cp portscan.jar scanner.PortScanner <hostname> <startport> <endport>");
		} else {
			try {
				int startPort = Integer.parseInt(args[1]);
				int endPort = Integer.parseInt(args[2]);
				if (startPort > endPort) {
					System.out.println("\nParameter startport must be less or equal to endport");
					return;
				}
				PortScanner portscan = new PortScanner();
				portscan.startScan(args[0], startPort, endPort);
			} catch (Exception e) {
			}
		}
	}

	private void startScan(String host, int startPort, int endPort) {
		System.out.println("Portscan started...");
		List<Thread> runningPortscans = new LinkedList<Thread>();
		for (int i = startPort; i <= endPort; i++) {
			Thread scanThread = new Thread(new ScanThread(host, i));
			runningPortscans.add(scanThread);
			scanThread.start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		for (Thread thread : runningPortscans) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Portscan finished");
	}
}