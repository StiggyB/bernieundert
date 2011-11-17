package tcp_advanced;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import namensdienst.InvokeMessage;

public class Client {
	private Socket mySocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Client(String host, int port) throws UnknownHostException, IOException {
		mySocket = new Socket(host, port);
		InputStream is = mySocket.getInputStream();
		in = new ObjectInputStream(is);
		OutputStream os = mySocket.getOutputStream();
		out = new ObjectOutputStream(os);
	}
	
	public Object receive() throws IOException, ClassNotFoundException {
		return in.readObject();
	}
	
	public void send(Object message) throws IOException {
		out.writeObject(message);
	}
	
	public void close() throws IOException {
		in.close();
		out.close();
		mySocket.close();
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Client myClient = new Client("localhost", 14001);
		
		InvokeMessage message = new InvokeMessage("IT IS!", null);
		myClient.send(message);
		
	}

}
