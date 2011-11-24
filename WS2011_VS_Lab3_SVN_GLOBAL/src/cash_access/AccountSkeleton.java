package cash_access;

import java.io.IOException;

import tcp_advanced.Connection;
import tcp_advanced.Server;

public class AccountSkeleton implements Runnable {

	private int port;
	private boolean isRunning;
	private Server server;
	private Thread skelThread;
	private Connection connection;
	
	public AccountSkeleton(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		while(isRunning) {
			try {
				this.server = new Server(this.port);
				while (isRunning) {
					System.out.println("Waiting for client" + "...on" + port);
					connection = new Connection(server.accept());
					AccountWorker accWorker = new AccountWorker(connection);
					accWorker.start();
				}
				server.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		this.skelThread.start();
	}
	
	public void stop() {
		this.isRunning = false;
	}

}
