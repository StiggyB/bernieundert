package server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import client.ClientData;

public class ChatServerImpl extends UnicastRemoteObject implements MessageServerIF {
	
	private static final long serialVersionUID = -4917373673314532190L;
    private int nom;

	public static final int PORT = Registry.REGISTRY_PORT;
	private static final long REMEM_TIME = 5000;
    
//	private HashMap<String, ArrayDeque<Message>> clientList;
	private ArrayList<ClientData> clientDataList;
	private Queue<Message> msgs = new ArrayDeque<Message>();
	private static AtomicInteger idCnt = new AtomicInteger(0);
	
	public ChatServerImpl(int nom) throws RemoteException {
        this.nom = nom;
        msgs = new ArrayDeque<Message>();
        clientDataList = new ArrayList<ClientData>();
//        clientList = new HashMap<String, ArrayDeque<Message>>();
	}
	
	public ChatServerImpl() throws RemoteException {
        this(5);
	}

	public void setUp() {
			try {
				LocateRegistry.createRegistry(PORT);
				System.out.println("Created Registry: " + PORT);
				InetAddress addr;
				addr = InetAddress.getLocalHost();
				Naming.rebind("//"+addr.getHostAddress()+":"+PORT+"/MessageServer", this);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public String getMessage(String clientID) throws RemoteException {
		if (clientDataList.isEmpty()) {					// wenn noch kein client da,
			ClientData tmp = new ClientData(clientID, REMEM_TIME);
			clientDataList.add(tmp);					// fuege neuen hinzu
		}
		
		if (msgs.isEmpty()) throw new RemoteException("no more messages");
		for (ClientData cd : clientDataList) {			// wenn in clientdata
			System.out.println(cd.toString());
			if (cd.getClientID().equals(clientID)) {	// die client id's �bereinstimmen
				for (Message msgIt : msgs) {			// dann iteriere �ber die clientspezifischen messages
					System.out.println(msgIt.toString());
					if (!(cd.containsMsg(msgIt.getMsg()))) {	// falls ein client die message schon gelesen hat
						System.out.println("recv: " + msgIt.getMsg());
						cd.addMsg(msgIt.getMsg());
						return msgIt.getMsg();
					}
				}
			}
		}
		return null;					// gib keine message aus
	}
	
	@Override
	public void dropMessage(String clientID, String msg) throws RemoteException {
		if (clientDataList.isEmpty()) {						// wenn noch kein client da,
			ClientData tmp = new ClientData(clientID, REMEM_TIME);
//			tmp.addMsg(msg);								// fuege zum tmpclient die msg hinzu
			clientDataList.add(tmp);						// und adde den client zur liste
		} else {
			for (ClientData cd : clientDataList) {
				if (cd.getClientID().equals(clientID)) {
//					cd.addMsg(msg);
				}
			}
		}
		if (msgs.size() >= nom) {
			msgs.poll();
		}
		System.out.println("MSG: " + msg);
		msgs.add(new Message(idCnt.getAndIncrement(), clientID, msg));
		//--
		for (Message msgIt : msgs) {
			System.out.println(msgIt.getMsg());
		}
		//--
	}
	
	public void setNom(int nom) {
		this.nom = nom;
	}

}