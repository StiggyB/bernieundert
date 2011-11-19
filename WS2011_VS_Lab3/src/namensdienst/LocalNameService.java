package namensdienst;

import java.util.HashMap;
import java.util.Map;

import mware_lib.NameService;


public class LocalNameService extends NameService {

	private Map<String, RemoteObject> remoteEntries;
	
	synchronized public Map<String, RemoteObject> getRemoteEntries() {
		return remoteEntries;
	}

	public LocalNameService() {
		this.remoteEntries = new HashMap<String, RemoteObject>();
	}
	
	synchronized public void put(String key, RemoteObject value) {
		remoteEntries.put(key, value);
	}
	
	synchronized public RemoteObject get(Object key) {
		return remoteEntries.get(key);
	}

	@Override
	public void rebind(Object servant, String name) {
		if(servant instanceof RemoteObject) {
			if(servant != null) {
				remoteEntries.put(name, (RemoteObject)servant);
			}
		}
	}

	@Override
	public Object resolve(String name) {
		return remoteEntries.get(name);
	}

}
