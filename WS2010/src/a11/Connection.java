package a11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import a11.TCPServer.MODE;
import a11.TCPServer.STATE;

/**
 * Zugehörige Klasse zum TCPServer. Reagiert auf die Serverzustände/Modi.
 *  
 * @author Bernie und Ert
 * @version 1.0beta
 */
public class Connection implements Runnable {
	
	private final static Random RND = new Random();
	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
	private TCPServer tcpServer;

	/**
	 * Konstruktor
	 * @param clientSocket im TCPServer erzeugter Socket
	 * @param tcpServer Zugehöriges TCPServer Objekt
	 */
	public Connection(Socket clientSocket, TCPServer tcpServer) {
		try {
			this.clientSocket = clientSocket;
			this.tcpServer = tcpServer;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection: " + e.getMessage());
		}
	}

	/**
	 * Run-Methode für das Runnable, definiert, was bei welchem State/Modus passiert.
	 */
	public void run() {
		try {
			String data = in.readUTF();

			MODE currentMode = tcpServer.getCurrentMode();
			STATE currentState = tcpServer.getCurrentState();
			String reply = null;
			switch (currentState) {
				case REGULAR:
					reply = doForMode(data, currentMode, currentState);
					break;
				case BUSY:
					reply = "Server busy, try again later.";
					break;
				case HACKED:
					// zufälligen Mode holen
					MODE randomMode = MODE.values()[RND.nextInt(3)];
					reply = doForMode(data, randomMode, currentState);
				}

			out.writeUTF(reply);
				
			clientSocket.close();
		} catch (EOFException e) {
			System.out.println("EOFException: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: ");
			e.printStackTrace();
		}
	}

	/**
	 * Ausgelagerte Methode, was passiert in welchem Mode
	 * @param data Übergabestring
	 * @param currentMode derzeitiger Servermodus
	 * @param currentState derzeitiger Serverstatus
	 * @return
	 */
	private String doForMode(String data, MODE currentMode, STATE currentState) {
		String reply = null;
		switch (currentMode) {
			case ECHO:
				reply = data;
				tcpServer.setCurrentMode(MODE.REVERSE);
				break;
			case REVERSE: 
				reply = new StringBuffer(data).reverse().toString();
				tcpServer.setCurrentMode(MODE.LOL);
				break;
			case LOL:
				StringBuilder sb = new StringBuilder();
				char[] chars = data.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					char c = chars[i];
					if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' )) {
						// ist konsonant
						sb.append(c).append("o").append(c);
					} else {
						sb.append(c);
					}
				}
				reply = sb.toString();
				
				// Mode abhängig vom State ändern
				switch (currentState) {
					case REGULAR:
						tcpServer.setCurrentMode(MODE.ECHO);
						break;
					case HACKED:
						MODE randomMode = MODE.values()[RND.nextInt(3)];
						tcpServer.setCurrentMode(randomMode);
						break;
				}
		}
		return reply;
	}
}