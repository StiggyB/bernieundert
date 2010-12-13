package networknew;
import java.net.*;
import java.io.*;

public class UDPServer {
	public static void main(String args[]) {
		try {
			int serverPort = 6789;
			DatagramSocket aSocket = new DatagramSocket(serverPort);
			System.out.println("Server gestartet auf Port " + serverPort + ".");
			byte[] buffer = new byte[1000];
			while (true) {
				DatagramPacket request =
					new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				System.out.println("Request erhalten. Sende Reply.");
				DatagramPacket reply =
					new DatagramPacket(
						request.getData(),
						request.getLength(),
						request.getAddress(),
						request.getPort());
				aSocket.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}
}
