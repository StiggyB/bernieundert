package client;
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
import java.util.concurrent.TimeUnit;


/**
 * This class provides the features of a receiving mode
 * for the <i>ChatClient</i>.
 *
 */
public class Receiver implements Runnable {

	private ChatClientImpl client;
	private ClientGUI gui;
	private boolean running;
	
    public Receiver(ChatClientImpl client, ClientGUI gui) {
        this.client = client;
        this.gui = gui;
        this.running = true;
    }
	
    public void setStopped(){
    	running = false;
    }
    
	@Override
	public void run() {
		String msg = null;
		while(running) {
			boolean nmm = false;
			while (!nmm) {
				msg = client.receiveMSG();
				if (msg != null && msg.equals(ChatClientImpl.NO_MORE_MSG)) {
					nmm = true;
				} else {
					gui.appendRcvAreaText(msg);
				}
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
