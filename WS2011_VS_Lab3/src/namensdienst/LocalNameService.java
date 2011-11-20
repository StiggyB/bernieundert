package namensdienst;

import java.util.HashMap;
import java.util.Map;

import mware_lib.MWCommunication;
import mware_lib.NameService;

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
		if (servant != null) {
			remoteEntries.put(name, servant);
		}
	}

	@Override
	public Object resolve(String name) {
		System.out.println("Servant: " + name);
		if (remoteEntries == null && !(nsServerThread.isAlive())) {
			this.remoteEntries = new HashMap<String, Object>();
			this.mwCom = new MWCommunication(host, port);
		}
		this.remoteEntries.put(name, mwCom.sendRequest(name));
		System.out.println(remoteEntries);
		for (String key : remoteEntries.keySet()) {
			System.out.println("Entries: " + remoteEntries.get(key));
		}
		return remoteEntries.get(name);
	}

}
