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
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import client.ClientData;

public class ChatServerImpl extends UnicastRemoteObject implements MessageServerIF {
	
	private static final long serialVersionUID = -4917373673314532190L;
	public static final int PORT = Registry.REGISTRY_PORT;
	private static final long MAX_REMEM_TIME = 10000;
    
	private static AtomicInteger idCnt = new AtomicInteger(0);
	
	private Map<String, ClientData> clientDataMap;
	private Queue<Message> msgs;
	private int nom;
	
	public ChatServerImpl(int nom) throws RemoteException {
        this.clientDataMap = new HashMap<String, ClientData>();
        this.msgs = new ArrayDeque<Message>();
        this.nom = nom;
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
	
	private boolean existsClient(String clientID) {
		return clientDataMap.isEmpty() ? false : clientDataMap.containsKey(clientID);
	}
	
	@Override
	public String getMessage(String clientID) throws RemoteException {
		ClientData tmpClData;
		checkClientTime();
		if(!(existsClient(clientID))) {
			System.out.println("cID:" + clientID);
			tmpClData = new ClientData(clientID, MAX_REMEM_TIME);
			clientDataMap.put(clientID, tmpClData);
		}
		if (msgs.isEmpty()) throw new RemoteException("no more messages");
		tmpClData = clientDataMap.get(clientID);
		for (Message msgIt : msgs) {
			if(!(tmpClData.getClientMsgs().contains(msgIt))) {
				System.out.println("recv: " + msgIt.getMsg());
				tmpClData.addMsg(msgIt);
				tmpClData.setRememTime(System.currentTimeMillis());
				return msgIt.toString();
			}
		}
		tmpClData.setRememTime(System.currentTimeMillis());
		throw new RemoteException("no more messages");					
	}
	
	@Override
	public void dropMessage(String clientID, String msg) throws RemoteException {
		ClientData tmpClData;
		if(!(existsClient(clientID))) {
			System.out.println("cID:" + clientID);
			tmpClData = new ClientData(clientID, MAX_REMEM_TIME);
			clientDataMap.put(clientID, tmpClData);
		}
		if (msgs.size() >= nom) {
			msgs.poll();
		}
		System.out.println("MSG: " + msg);
		msgs.add(new Message(idCnt.getAndIncrement(), clientID, msg));
	}
	
	public void checkClientTime() {
		for (String client : clientDataMap.keySet()) {
			if (Math.abs((clientDataMap.get(client).getRememTime() - System.currentTimeMillis())) > MAX_REMEM_TIME) {
				clientDataMap.remove(client);
			}
		}
	}
}