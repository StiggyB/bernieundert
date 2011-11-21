package branch_access;

import java.io.IOException;

import namensdienst.InvokeMessage;
import namensdienst.ResultMessage;
import tcp_advanced.Client;

public class ManagerProxy extends Manager {

	private String host;
	private int port;
	private Client client;
	private String remoteName;
	
	public ManagerProxy(String hostName, int port, String remoteName) {
		super();
		this.host = hostName;
		this.port = port;
		this.remoteName = remoteName;
	}

	@Override
	public String createAccount(String owner) {
		System.out.println("remote call");
		String result = null;
		try {
			this.client = new Client(this.host, this.port);
			InvokeMessage iMsg = new InvokeMessage(remoteName, "createAccount", owner);
			System.out.println("INVOKEMSG: " + iMsg);
			client.send(iMsg);
			Object resultMsg = client.receive();
			
			if (resultMsg instanceof ResultMessage) {
				Object remoteResult = ((ResultMessage) resultMsg).getResult();
				if (remoteResult instanceof String) {
					result = (String)remoteResult;
				} 
			}
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean removeAccount(String accountID) {
		System.out.println("remote call");
		return false;
	}

}

