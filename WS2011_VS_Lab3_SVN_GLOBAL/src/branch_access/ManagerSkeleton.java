package branch_access;

import java.io.IOException;
import java.net.UnknownHostException;

import messages.InvokeMessage;
import tcp_advanced.Connection;
import tcp_advanced.Server;

public class ManagerSkeleton implements Runnable {

	private volatile boolean running;
	private Thread thread;
	private Manager manager;
	private Server server;
	private int port;

	public ManagerSkeleton(int managerPort, Manager manager) throws IOException {
		this.running = true;
		this.thread = new Thread(this);
		this.manager = manager;
		this.port = managerPort;
	}

	@Override
	public void run() {
		try {
			this.server = new Server(port);
			while (running) {
				Connection connection = new Connection(server.accept());
				System.out.println("NEW MANSKEL" + connection);
				try {
					Object rcvdObj = connection.receive();
					if (rcvdObj instanceof InvokeMessage) {
						InvokeMessage iMsg = (InvokeMessage)rcvdObj;
						ManagerWorker mWorker = new ManagerWorker(connection, iMsg, manager);
						mWorker.start();
					} else {
						// connection.send(new
						// IllegalMessageException(rcvdObj.getClass().toString()));
					}
				} catch (ClassNotFoundException e) {
					connection.send(new ClassNotFoundException(e.getMessage()));
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		this.running = false;
	}

	public void start() {
		this.thread.start();
	}

}
