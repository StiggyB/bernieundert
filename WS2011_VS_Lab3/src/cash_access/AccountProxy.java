package cash_access;

import java.io.IOException;
import java.net.UnknownHostException;

import namensdienst.InvokeMessage;
import tcp_advanced.Client;

public class AccountProxy extends Account {

	//TODO Maybe new Client connection in the methods and close
	
	private String host;
	private int port;
	private Client client;

	public AccountProxy(String hostName, int port) {
		super();
		this.host = hostName;
		this.port = port;
		try {
			this.client = new Client(this.host, this.port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(double amount) {
		//TODO Lookup the correct className in iMsg ("Account"?) - should be accID
		try {
			InvokeMessage iMsg = new InvokeMessage("Account", "deposit", amount);
			client.send(iMsg);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void withdraw(double amount) throws OverdraftException {
		try {
			InvokeMessage iMsg = new InvokeMessage("Account", "withdraw", amount);
			client.send(iMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public double getBalance() {
		Double result = null;
		try {
			InvokeMessage iMsg = new InvokeMessage("Account", "getBalance", (Object[])null);
			client.send(iMsg);
			Object resultMsg = client.receive();
			if (resultMsg instanceof Double) {
				result = (Double)resultMsg;
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
