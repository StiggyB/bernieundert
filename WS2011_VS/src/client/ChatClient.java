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
import java.awt.EventQueue;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * This class starts the <i>ChatClient</i> and all the
 * GUI and Controller parts of the MCV-Pattern.
 *
 */
public class ChatClient {
	
    /**
     * This method starts the <i>ChactClient</i>.
     * 
     * @param args
     */
    static public void main(String args[]) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecureRandom sr = new SecureRandom();
					ChatClientImpl c = new ChatClientImpl(new BigInteger(10, sr).toString(), 5000);
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
					ClientGUIImpl guiimpl = new ClientGUIImpl(c, frame);
					guiimpl.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    	});
    }
}

