package mware_lib;

import java.io.IOException;

import namensdienst.RemoteObject;
import tcp_advanced.Client;

public class MWCommunication {

	private String host;
	private int port;
	private Client client;
	
	public MWCommunication(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	
	public Object sendRequest(String name) {
		Object result = null;
		try {
			this.client = new Client(this.host, this.port);
			client.send(name);
			Object resMsg = client.receive();
			System.out.println("Message: " + resMsg);
			if ( resMsg instanceof RemoteObject) {
				result = ((RemoteObject)resMsg);
				System.out.println("Result: " + result);
			}
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
