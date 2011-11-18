package tester;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import namensdienst.InvokeMessage;
import namensdienst.ResultMessage;

public class Test_Server {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("TESTER");
		Socket sock = new Socket("localhost", 14001);
		
		OutputStream os = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		
//		InvokeMessage iMsg = new InvokeMessage("YESSS!", null);
//		oos.writeObject(iMsg);
		
		ResultMessage rMsg = new ResultMessage(new String ("RESULT!"));
		oos.writeObject(rMsg);
		
		oos.close();
		sock.close();
		System.out.println("TESTER... closed");
	}
	
}
