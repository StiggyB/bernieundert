package tmp;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server extends Thread implements Runnable{
	private ServerSocket welcomeSocet;
	private static final int PORT = 50000;
	private Socket connectionSocket;
	private final static Map<String,String> CLIENTLIST= new HashMap<String,String>();

	public Server() throws IOException {
		welcomeSocet = new ServerSocket(PORT);
	}

	public synchronized static Map<String, String> getClientlist() {
		return CLIENTLIST;
	}
	
	public void run() {
		System.out.println("Server : Herzlich Willkommen!!!");
		while (true) {
			try{connectionSocket = welcomeSocet.accept();			
				System.out.println("Server : mit Client connectet");
                Thread worker = new Worker(connectionSocket);
                worker.start();
			}catch (IOException e) {System.out.println("Server : connection to Client Interrupted");	}
		}			
	}

	public static void main(String[] args) {
		try {
			Server myServer = new Server();
			myServer.start();
		} catch (IOException e) {
			System.out.println("Server :couldn'n start Server");
		}
	}
}