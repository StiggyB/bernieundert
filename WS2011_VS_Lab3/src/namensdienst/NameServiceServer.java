package namensdienst;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import tcp_advanced.Client;
import tcp_advanced.Connection;
import tcp_advanced.Server;

/**
 * This NameServiceServer delegates requests from the Clients
 *  to the NameServerWorker
 * 
 * @author Administrator
 *
 */
public class NameServiceServer implements Runnable{

	//TODO implement dynamic proxy/ skeleton
	private String host;
	private int port;
	private boolean isRunning;
	private Client client;
	private Server server;
	private LocalNameService nameService;
	private List<Thread> workerList = new ArrayList<Thread>();

	public NameServiceServer(String host, int port, LocalNameService nameService) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		this.isRunning = true;
		this.nameService = nameService;
		this.client = new Client(this.host, this.port);
		this.server = new Server(this.port);
	}
	
	public void delegateRequest(Connection connection) {
		NameServiceWorker nsWorker = new NameServiceWorker(connection, nameService);
		Thread worker = new Thread(nsWorker);
		workerList.add(worker);
		worker.start();
	}
	
	@Override
	public void run() {
		while(isRunning) {
			try {
				Connection connection = server.getConnection();
				delegateRequest(connection);
				connection.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
