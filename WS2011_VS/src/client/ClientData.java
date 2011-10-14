package client;

import java.util.ArrayList;
import java.util.List;

import server.Message;

public class ClientData {

	private String clientID;
	private long rTime;
	private List<Message> clientMsgs;
	
	public ClientData(String clientID, long rTime) {
		this.clientID = clientID;
		this.rTime = rTime;
		this.clientMsgs = new ArrayList<Message>();
	}

	public List<Message> getClientMsgs() {
		return clientMsgs;
	}

	public String getClientID() {
		return clientID;
	}

//	public boolean hasPassed(String msg) {
//		for (String m : msgMap.keySet()) {
//			if (m.equals(msg)) {
//				if ((Math.abs(msgMap.get(m) - System.currentTimeMillis())) > rTime) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	public void addMsg(Message msg) {
		clientMsgs.add(msg);
	}
	
//	public void addMsg(String msg) {
//		msgMap.put(msg, System.currentTimeMillis());
//	}
	
	public boolean containsMsg(Message msg) {
		return clientMsgs.isEmpty() ? false : clientMsgs.contains(msg);
	}
	
//	public boolean containsMsg(String msg) {
//		System.out.println((msgMap.isEmpty() ? false : msgMap.containsKey(msg)));
//		return (msgMap.isEmpty() ? false : msgMap.containsKey(msg));
//	}
	
}
