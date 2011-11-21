package namensdienst;

import java.util.HashMap;
import java.util.Map;

import mware_lib.MWCommunication;
import mware_lib.NameService;
import application.AccountImpl;
import application.ManagerImpl;
import branch_access.ManagerProxy;
import cash_access.AccountProxy;

public class LocalNameService extends NameService {

	private String host;
	private int port;
	private NameServiceServer nameServiceServer;
	private MWCommunication mwCom;
	private Thread nsServerThread;
	private Map<String, Object> remoteEntries;

	public LocalNameService(String host, int port) {
		this.host = host;
		this.port = port;
		this.nameServiceServer = new NameServiceServer(this.host, this.port,
				this);
		this.nsServerThread = new Thread(this.nameServiceServer);
		this.nsServerThread.setDaemon(true);
	}

	synchronized public Map<String, Object> getRemoteEntries() {
		return remoteEntries;
	}

	synchronized public void put(String key, Object value) {
		remoteEntries.put(key, value);
	}

	synchronized public Object get(Object key) {
		return remoteEntries.get(key);
	}
	

	@Override
	public void rebind(Object servant, String name) {
		if (remoteEntries == null && !(nsServerThread.isAlive())) {
			System.out.println("create it!");
			this.nsServerThread.start();
			this.remoteEntries = new HashMap<String, Object>();
		}
		System.out.println("REBIND Servant: " + servant + "; Name: " + name);
		if (servant != null) {
			remoteEntries.put(name, servant);
		}
		System.out.println("REBIND MapID: " + remoteEntries);
	}

	@Override
	public Object resolve(String name) {
		System.out.println("RESOLVE Servant: " + name);
		if (remoteEntries == null) {
			this.remoteEntries = new HashMap<String, Object>();
			this.mwCom = new MWCommunication(host, port);
		}
		Object remoteObjType = mwCom.sendRequest(name);
		System.out.println("OBJECTTYPE: " + remoteObjType.getClass());
		Object remoteObj = generateObjectRef(remoteObjType, name);
		if(remoteObj != null) {
			this.remoteEntries.put(name, remoteObj);
		}
		for (String key : remoteEntries.keySet()) {
			System.out.println("Entries: " + remoteEntries.get(key));
		}
		System.out.println("RESOLVE MapID: " + remoteEntries);
		return remoteEntries.get(name);
	}

	public Object generateObjectRef(Object remoteObj, String name) {
		Class<?> remoteClass = null;  
		if (remoteObj instanceof RemoteObject) {
			remoteClass = ((RemoteObject)remoteObj).getType();
			System.out.println("TYPE: " + remoteClass);
		}
		if (AccountImpl.class.equals(remoteClass)) {
			remoteObj = new AccountProxy(host, port, name);
		} else if (ManagerImpl.class.equals(remoteClass)) {
			remoteObj = new ManagerProxy(host, port, name);
		} else {
			remoteObj = null;
		}
		return remoteObj;
	}

}
