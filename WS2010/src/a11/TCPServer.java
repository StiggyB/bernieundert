package a11;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class TCPServer {
	
	private STATE currentState = STATE.REGULAR;
	private MODE currentMode = MODE.ECHO;
	
	public enum STATE {
		REGULAR, BUSY, HACKED;
	}
	
	public enum MODE {
		ECHO, REVERSE, LOL;
	}

	public static void main(String args[]) {
		TCPServer tcpServer = new TCPServer();
		tcpServer.run();
	}
	
	public void run() {
		try {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						Scanner scanner = new Scanner(System.in);
						String line = scanner.next();
						if (line.equals("REGULAR")) {
							TCPServer.this.setCurrentState(STATE.REGULAR);
						} else if (line.equals("BUSY")) {
							TCPServer.this.setCurrentState(STATE.BUSY);
						} else {
							TCPServer.this.setCurrentState(STATE.HACKED);
						}
					}
				}
			}).start();
			int serverPort = 12345;
			System.out.println("Server gestartet auf Port " + serverPort + ".");
			ServerSocket listenSocket = new ServerSocket(serverPort);
			ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
//			threadPool.getActiveCount();
//			threadPool.getPoolSize();
			
			while (true) {
				try {
					Socket clientSocket = listenSocket.accept();
					Connection c = new Connection(clientSocket, this);
					// auf dem Future könnte man prüfen ob die Connection abgearbeitet ist
					Future<?> future = threadPool.submit(c);
				} catch (IOException e) {
					System.out.println("accept :");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("Listen :");
			e.printStackTrace();
		}
	}
	
	
	public MODE getCurrentMode() {
		return currentMode;
	}
	
	public void setCurrentMode(MODE currentMode) {
		System.out.println("setting mode to: " + currentMode);
		this.currentMode = currentMode;
	}
	
	public STATE getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(STATE currentState) {
		System.out.println("setting state to: " + currentState);
		this.currentState = currentState;
	}
	
}


