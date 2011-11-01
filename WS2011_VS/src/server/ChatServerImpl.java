package server;
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
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import client.ClientData;

/**
 * This class <i>ChatServerImpl</i> implements the java.rmi Remote 
 * Interface <i>MessageServerIF</i> that provides the calls
 * over the RMI. It implements the hole functionality of the <i>ChatServer</i>
 * and registers the informations in the RMI Registry.
 *
 */
public class ChatServerImpl extends UnicastRemoteObject implements
		MessageServerIF {

	/**
	 * serialVersionUID is an unique ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * PORT is a specific free port from the <i>Registry</i>.
	 */
	public static final int PORT = Registry.REGISTRY_PORT;

	/**
	 * This is an unique ID for the messages.
	 */
	private static AtomicInteger idCnt = new AtomicInteger(0);

	/**
	 * This reference on a HashMap contains CliendIds and Data about the Clients.
	 */
	private Map<String, ClientData> clientDataMap;
	
	/**
	 * This is the message delivery-queue with the maximal length <i>nom</i>.
	 */
	private Queue<Message> msgs;
	
	/**
	 * Reference on the thread <i>TimeChecker</i>.
	 */
	private TimeChecker tChecker;
	
	/**
	 * This is the number of messages in the delivery-queue <i>msgs</i>.
	 */
	private int nom;

	/**
	 * This Constructor initializes the class <i>ChatServerIml</i>
	 * with thread safe synchronized components and starts the 
	 * TimeChecker Thread that checks the remember time from the clients. 
	 * 
	 * @param nom - number of messages in the delivery-queue <i>msgs</i>.
	 * @throws RemoteException
	 */
	public ChatServerImpl(int nom) throws RemoteException {
		this.clientDataMap = Collections.synchronizedMap(new HashMap<String, ClientData>());
		this.msgs = new ArrayDeque<Message>();
		this.tChecker = new TimeChecker(clientDataMap);
		Thread tcheckerThread = new Thread(tChecker);
		tcheckerThread.start();
		this.nom = nom;
	}

	public ChatServerImpl() throws RemoteException {
		this(5);
	}

	/**
	 * This Method creates the registry entries with
	 * the specific port and binds the URL on it.
	 */
	public void setUp() {
		try {
			LocateRegistry.createRegistry(PORT);
			System.out.println("Created Registry: " + PORT);
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			Naming.rebind("//" + addr.getHostAddress() + ":" + PORT
					+ "/MessageServer", this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

    /**
     * This method implements the function to receive
     * messages from the server. It works over the
     * java.rmi as a call method.
     * The <i>at most once</i> error handling
     * implements the java.rmi over the RemoteExceptions
     * 
     * #see http://download.oracle.com/javase/1.4.2/docs/api/index.html 
     */
	@Override
	public synchronized String getMessage(String clientID) throws RemoteException {
		ClientData tmpClData;
		if (!(existsClient(clientID))) {
			tmpClData = new ClientData(clientID);
			synchronized(clientDataMap) {
				clientDataMap.put(clientID, tmpClData);
			}
		}
		if (msgs.isEmpty())
			throw new RemoteException("no more messages");
		synchronized(clientDataMap) {
		tmpClData = clientDataMap.get(clientID);
		}
		for (Message msgIt : msgs) {
			if (!(tmpClData.getClientMsgs().contains(msgIt))) {
				tmpClData.addMsg(msgIt);
				tmpClData.setRememTime(System.currentTimeMillis());
				return msgIt.toString();
			}
		}
		tmpClData.setRememTime(System.currentTimeMillis());
		throw new RemoteException("no more messages");
	}

    /**
     * This method implements the function to send
     * messages to the server. It works over the
     * java.rmi as a call method.
     * The <i>at most once</i> error handling
     * implements the java.rmi over the RemoteExceptions
     * 
     * #see http://download.oracle.com/javase/1.4.2/docs/api/index.html 
     */
	@Override
	public synchronized void dropMessage(String clientID, String msg) throws RemoteException {
		ClientData tmpClData;
		if (!(existsClient(clientID))) {
			tmpClData = new ClientData(clientID);
			synchronized(clientDataMap) {
				clientDataMap.put(clientID, tmpClData);
			}
		}
		if (msgs.size() >= nom) {
			msgs.poll();
		}
		msgs.add(new Message(idCnt.getAndIncrement(), clientID, msg));
	}

	/**
	 * This Method checks the <i>HashMap</i> over <i>ClientData</i> Objects 
	 * if it contains no or not this specific entry.
	 * 
	 * @param clientID - unique ID from the <i>ChatClient</i>.
	 * @return true - if the client exists in the Map otherwise false.
	 */
	private synchronized boolean existsClient(String clientID) {
		return clientDataMap.isEmpty() ? false : clientDataMap
				.containsKey(clientID);
	}
}