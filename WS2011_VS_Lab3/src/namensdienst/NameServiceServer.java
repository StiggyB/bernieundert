package namensdienst;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mware_lib.NameService;
import mware_lib.ObjectBroker;
import tcp_advanced.Server;

/**
 * This NameServiceServer delegates requests from the Clients
 *  to the NameServerWorker
 * 
 * @author Administrator
 *
 */
public class NameServiceServer {

	private ServerSocket socket;
	private List<Thread> workerList = new ArrayList<Thread>();

	public void delegateRequest(String host, int port) {
		
	}
	
	
	public static void main(String[] args) throws IOException {
		Server server = new Server(14001);
		ObjectBroker ob = new ObjectBroker("localhost", 14001);
		ObjectBroker.getBroker("localhost", 14001);
		NameService nService = ob.getNameService();
	}
	
}
