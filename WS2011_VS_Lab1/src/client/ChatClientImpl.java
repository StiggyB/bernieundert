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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.PriorityQueue;
import java.util.Queue;

import server.MessageServerIF;

/**
 * This class provides the implementation of the functionality 
 * of the <i>ChatClient</i>. It contains the stubs of the methods
 * <i>sendMessage</i> and <i>dropMessage</i> they realize the real
 * functionality over the java.rmi.
 *
 */
public class ChatClientImpl {

	/**
	 * SERVER_NAME is the name from the <i>ChatServer</i>.
	 */
	public static final String SERVER_NAME = "MessageServer";
	
	/**
	 * NO_MORE_MSG is the exception to show that no more messages in the queue.
	 */
	public static final String NO_MORE_MSG = "java.rmi.RemoteException: no more messages";
	
	
	/**
	 * Unique ID from the <i>ChatClient</i>.
	 */
	public 	String ID;
	
	/**
	 * IP address from the <i>ChatServer</i>.
	 */
	private String server_ip;
	
	/**
	 * Port from the <i>ChatServer</i>.
	 */
	private int server_port;
   
	/**
     * Reference on the RMI Interface <i>MessageServerIF</i>.
     */
    private MessageServerIF server;
    
    /**
     * List with messages they should be receive on the <i>ChatServer</i>.
     */
    private Queue<String> msgList;
    
    /**
     * Specific URL from the <i>ChatServer</i>.
     */
    private String url;
    
    /**
     * Time in seconds that give a limit of connection tries.
     */
    private long s; 


	/**
	 * This Constructor initializes the class <i>ChatClientIml</i>
	 * with the method <i>bind</i> that connect the Client with 
	 * the Server.
	 * 
	 * @param ID - unique ID from the <i>ChatClient</i>.
	 * @param s - Time in seconds that give a limit of connection tries.
	 */
	public ChatClientImpl(String ID, long s) {
		this.server_ip = "localhost";
        this.server_port = Registry.REGISTRY_PORT;
        this.msgList = new PriorityQueue<String>();
        this.ID = ID;
        this.s = s;
    	try {
			bind();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getServer_port() {
		return server_port;
	}

	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}
	
	/**
	 * This method connects the <i>ChatClient</i> with <i>ChatServer</i>
	 * and uses the class Naming to find the specific Server.
	 * 
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private void bind() throws MalformedURLException, RemoteException, NotBoundException {
    	url = "//" + server_ip + ":" + server_port + "/" + SERVER_NAME;
		server = (MessageServerIF) Naming.lookup(url);
		System.out.println("Found server: " + url);	
	}

	/**
	 * This method calls over the java.rmi <i>dropMessage</i> 
	 * on the <i>ChatServer</i> that sends a message to the server.
	 * Additional it implements a <i>at least once</i> error handling.
	 * 
	 * @param msg - message that is sending to <i>ChatServer</i>.
	 */
	public void sendMSG(String msg) {
		msgList.add(msg);
		try {
			server.dropMessage(ID, msg);
		} catch (RemoteException e) {
			System.err.print("Server not available.");
			//Check the connection to the server!
			if(checkServerTimeout(System.currentTimeMillis())) {
				sendMSG(msgList.poll());
			}
			e.printStackTrace();
		}
		msgList.clear();
	}
	
    /**
     * This method calls over the java.rmi <i>dropMessage</i> 
	 * on the <i>ChatServer</i> that receives a message from the server.
	 * Additional it implements a <i>maybe</i> error handling in case
	 * of the receive frequently.
     * 
     * @return the message from the Delivery-Queue.
     */
    public String receiveMSG() {
    	try {
			return server.getMessage(ID);
		} catch (RemoteException e) {
			if (e.getCause().toString().equals(NO_MORE_MSG)) {
				return e.getCause().toString();
			} else {
				System.err.print("Server not available.");
				e.printStackTrace();
			}
		}
		return null;
    }
    
    /**
     * This method checks the connection <i>ChatServer</i> over a
     * specific time <i>s</i>.
     * 
     * @param timestamp - actual system time  
     * @return true - if the connection is established otherwise false.
     */
    private boolean checkServerTimeout(long timestamp) {
    	boolean success = false;
    	while(Math.abs(timestamp - System.currentTimeMillis()) < s) {
			try {
				bind();
				success = true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				success = false;
				e.printStackTrace();
			}
    	}
    	return success;
    }
}
