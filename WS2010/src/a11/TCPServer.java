package a11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Klasse für einen einfachen TCPServer.
 * 
 * @author Bernie und Ert
 * @version 1.0beta
 */
public class TCPServer {

	private STATE currentState = STATE.REGULAR;
	private MODE currentMode = MODE.ECHO;

	/**
	 * Enums für die verschiedenen Status
	 */
	public enum STATE {
		REGULAR, BUSY, HACKED;
	}

	/**
	 * Enums für die verschiedenen Modi
	 */
	public enum MODE {
		ECHO, REVERSE, LOL;
	}

	/**
	 * Main-Methode zum Starten des Servers
	 * 
	 * @param args
	 *            Derzeit keine Funktion, man könnte hier (bei verschiedenen
	 *            IPs/NICs) Bind-Adresse und Wunschport übergeben. Bspw: args[0]
	 *            bindadresse und args[1] serverport
	 */
	public static void main(String args[]) {
		TCPServer tcpServer = new TCPServer();
		tcpServer.run();
	}

	/**
	 * 
	 */
	public void run() {
		try {
			new Thread(new Runnable() {

				@Override
				public void run() {

					// Möglichkeit, den Server gezielt in einen bestimmten STATE
					// zu setzen
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
			ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors
					.newFixedThreadPool(5);
			// Über den ThreadPoolExecutor hat man folgende Methoden zur Wahl:
			// threadPool.getActiveCount();
			// threadPool.getPoolSize();
			// Darüber koennte man abfragen, wieviele Threads derzeit laufen und
			// wieviele noch
			// gestartet werden können, bevor der Server dann in den STATE: BUSY
			// übergeht,
			// bzw. ab wenn er wieder neue Verbindungen annehmen kann.

			while (true) {
				try {
					Socket clientSocket = listenSocket.accept();
					Connection c = new Connection(clientSocket, this);
					// auf dem Future könnte man prüfen ob die Connection
					// abgearbeitet ist
					// Future<?> future = threadPool.submit(c);
					threadPool.submit(c);
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

	/**
	 * Diese Methode liefert den derzeitigen Mode
	 * 
	 * @return current Mode
	 */
	public MODE getCurrentMode() {
		return currentMode;
	}

	/**
	 * Diese Methode setzt den Mode
	 * 
	 * @param currentMode
	 */
	public void setCurrentMode(MODE currentMode) {
		System.out.println("setting mode to: " + currentMode);
		this.currentMode = currentMode;
	}

	/**
	 * Diese Methode liefert den derzeitigen Status
	 * 
	 * @return current State
	 */
	public STATE getCurrentState() {
		return currentState;
	}

	/**
	 * Diese Methode setzt den State
	 * 
	 * @param currentState
	 */
	public void setCurrentState(STATE currentState) {
		System.out.println("setting state to: " + currentState);
		this.currentState = currentState;
	}

}
