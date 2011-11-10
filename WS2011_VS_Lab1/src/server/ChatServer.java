package server;
/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 01<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.rmi.server.RemoteServer;


/**
 * This class starts the <i>ChatServer</i> and creates
 * a log file of the RMI data transfer.
 */
public class ChatServer {

	/**
	 * This method starts the <i>ChatServer</i>.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Setting up server...");
		try {
			ChatServerImpl impl = new ChatServerImpl();
			impl.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.getProperties().put("java.rmi.server.logCalls","true");
		FileOutputStream logFile = null;
		try {
			logFile = new FileOutputStream(new File("src/server/logfile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RemoteServer.setLog(logFile);
		System.out.println("Server up an running...");
	}
}
