package tester;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import namensdienst.InvokeMessage;

public class Test_Server {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket("localhost", 14001);
		
		OutputStream os = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		
		InvokeMessage message = new InvokeMessage("YESSS!", null);
		oos.writeObject(message);
	}
	
}
