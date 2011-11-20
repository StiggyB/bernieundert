package tester;

import java.io.IOException;
import java.net.UnknownHostException;

import mware_lib.NameService;
import mware_lib.ObjectBroker;
import application.ManagerImpl;
import branch_access.Manager;

public class TestMiddleware {
	public static final int PORT = 14001;

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
	System.out.println("Test started");
	ObjectBroker obRemote = ObjectBroker.getBroker("localhost", PORT);
	NameService remoteNS = obRemote.getNameService();

	System.out.println("Testing...");
//	ObjectBroker obLocal = ObjectBroker.getBroker("localhost", PORT);
//	NameService localNS = obLocal.getNameService();

	System.out.println("Nameservices implemented");
	Manager remoteManager = new ManagerImpl(remoteNS);
	remoteNS.rebind(remoteManager, "Manager");
	}
	
}