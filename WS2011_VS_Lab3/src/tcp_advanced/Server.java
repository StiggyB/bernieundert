package tcp_advanced;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private ServerSocket mySvrSocket;
	
	public Server(int listenPort) throws IOException {
		mySvrSocket = new ServerSocket(listenPort);		
	}
	
	public Connection getConnection() throws IOException {
		return new Connection(mySvrSocket.accept());
	}
	
	public void shutdown() throws IOException {
		mySvrSocket.close();
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server s = new Server(14001);
		Connection conn = s.getConnection();
		
		System.out.println("SERVER");
		System.out.println(conn.receive());
		System.out.println("SERVER...");
	}
}
