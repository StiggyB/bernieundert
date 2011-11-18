package namensdienst;

import java.util.HashMap;
import java.util.Map;

import mware_lib.NameService;


public class LocalNameService extends NameService {

	private Map<String, RemoteObject> remoteEntries = new HashMap<String, RemoteObject>();
	
	public Map<String, RemoteObject> getRemoteEntries() {
		return remoteEntries;
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
