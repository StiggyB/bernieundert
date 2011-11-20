package namensdienst;

import java.util.HashMap;
import java.util.Map;

import mware_lib.NameService;


public class LocalNameService extends NameService {

	private String host;
	private int port;
	private NameServiceServer nameServiceServer;
	private Thread nsServerThread;
	private Map<String, RemoteObject> remoteEntries;
	

	public LocalNameService(String host, int port) {
		this.host = host;
		this.port = port;
		this.nameServiceServer = new NameServiceServer(this.host, this.port, this);
	}
	
	synchronized public Map<String, RemoteObject> getRemoteEntries() {
		return remoteEntries;
	}

	synchronized public void put(String key, RemoteObject value) {
		remoteEntries.put(key, value);
	}
	
	synchronized public RemoteObject get(Object key) {
		return remoteEntries.get(key);
	}

	@Override
	public void rebind(Object servant, String name) {
		if(remoteEntries == null) {
			this.remoteEntries = new HashMap<String, RemoteObject>();
			this.nsServerThread = new Thread(this.nameServiceServer);
			this.nsServerThread.setDaemon(true);
			this.nsServerThread.start();
		}
		if(servant != null) {
			remoteEntries.put(name, (RemoteObject)servant);
		}
	}

	@Override
	public Object resolve(String name) {
		System.out.println(name);
		
		return remoteEntries.get(name);
	}

}
