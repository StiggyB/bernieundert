package tester;

import java.io.IOException;

import mware_lib.NameService;
import mware_lib.ObjectBroker;
import tcp_advanced.Server;

public class TestNameService {
	
	public static void main(String[] args) throws IOException {
		Server server = new Server(14001);
		ObjectBroker ob = new ObjectBroker("localhost", 14001);
		ObjectBroker.getBroker("localhost", 14001);
		NameService nService = ob.getNameService();
	}
}
