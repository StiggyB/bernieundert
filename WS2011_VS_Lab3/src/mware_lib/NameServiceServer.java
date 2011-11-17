package mware_lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import namensdienst.NameService;
import namensdienst.ObjectRef;
import tcp_advanced.Server;

public class NameServiceServer {

	private Map<String, ObjectRef> entries = new HashMap<String, ObjectRef>();
	private Server socket;
	private List<Thread> workerList = new ArrayList<Thread>();

	
	public static void main(String[] args) throws IOException {
		Server server = new Server(14001);
		ObjectBroker ob = new ObjectBroker();
		ObjectBroker.getBroker("localhost", 14001);
		NameService nService = ob.getNameService();
	}
	
}
