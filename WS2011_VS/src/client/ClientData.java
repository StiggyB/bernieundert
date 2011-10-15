package client;
/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 01<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import java.util.ArrayList;
import java.util.List;

import server.Message;

/**
 * This class <i>ClientData</i> contains some information
 * over the various clients they communicate with the <i>ChatServer</i>. 
 *
 */
public class ClientData {

	private String clientID;
	private long rememTime;
	private List<Message> clientMsgs;
	
	public ClientData(String clientID) {
		this.clientID = clientID;
		this.rememTime = System.currentTimeMillis();
		this.clientMsgs = new ArrayList<Message>();
	}

	public long getRememTime() {
		return rememTime;
	}
	
	public void setRememTime(long rememTime) {
		this.rememTime = rememTime;
	}

	public List<Message> getClientMsgs() {
		return clientMsgs;
	}

	public String getClientID() {
		return clientID;
	}
	
	public void addMsg(Message msg) {
		clientMsgs.add(msg);
	}
	
	public boolean containsMsg(Message msg) {
		return clientMsgs.isEmpty() ? false : clientMsgs.contains(msg);
	}
	
}
