package networknew;

import java.net.*;
import java.util.concurrent.Executors;
import java.io.*;

public class TCPServer {
	public static void main(String args[]) {
		int serverPort = 9876;// 7896;
		System.out.println("Server gestartet auf Port " + serverPort + ".");
		try {
			ServerSocket listenSocket = new ServerSocket(serverPort);

			while (true) {
				try {
					Socket clientSocket = listenSocket.accept();
					System.out.println("Neue Verbindung! Erzeuge Connection ...");
					Connection c = new Connection(clientSocket);
					Executors.newFixedThreadPool(1).submit(c);
				} catch (IOException e) {
					System.out.println("Listen :" + e.getMessage());
				}

			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}
}

class Connection implements Runnable {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try { // an echo server
			String data = in.readUTF();
			out.writeUTF(data);
			clientSocket.close();
		} catch (EOFException e) {
			System.out.println("EOFException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}
}
