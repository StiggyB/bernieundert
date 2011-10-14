package client;

import java.util.HashMap;

public class ClientData {

	private String clientID;
	private HashMap<String, Long> msgMap; //What is better with ClientData Obj
	private long rTime;
	
	public ClientData(String clientID, long rTime) {
		this.clientID = clientID;
		this.msgMap = new HashMap<String, Long>();
		this.rTime = rTime;
	}

	public String getClientID() {
		return clientID;
	}

	public boolean hasPassed(String msg) {
		for (String m : msgMap.keySet()) {
			if (m.equals(msg)) {
				if ((Math.abs(msgMap.get(m) - System.currentTimeMillis())) > rTime) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addMsg(String msg) {
		msgMap.put(msg, System.currentTimeMillis());
	}
	
	public boolean containsMsg(String msg) {
		for (String m : msgMap.keySet()) {
			System.out.println("map: " + m);
		}
		System.out.println((msgMap.isEmpty() ? false : msgMap.containsKey(msg)));
		return (msgMap.isEmpty() ? false : msgMap.containsKey(msg));
	}
	
}
