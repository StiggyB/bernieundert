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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements the controller part of
 * the MVC-Pattern and react to some actions
 * with various listeners.
 *
 */
public class ClientGUIImpl {

	private ChatClientImpl client;
	private ClientGUI gui;
	private Receiver rec;
	
    public ClientGUIImpl(ChatClientImpl client, ClientGUI gui) {
        this.client = client;
        this.gui = gui;
    }
    
    public void start() {
        gui.setSendBtnListener(new SendBtnActionListener());
        gui.setRcvBtnListener(new RcvBtnActionListener());
        gui.setRcvAllBtnListener(new RcvAllBtnActionListener());
        gui.setClearBtnListener(new ClearBtnActionListener());
        gui.setjToggleButtonListener(new RcvAllTimeActionListener());
    }
    
    private class SendBtnActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            client.sendMSG(gui.getSendFieldText());
            gui.clearSendText();
        }
    }
    
    private class RcvBtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String msg = client.receiveMSG();
            if (msg == null) {
                System.err.println("Message receive failed");
            } else if (!msg.equals(ChatClientImpl.NO_MORE_MSG)) {
                gui.appendRcvAreaText(msg);
            } else {
            	System.err.println(msg);
            }
        }
    }
    
    private class RcvAllBtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String msg = client.receiveMSG();
            while (msg != null && !msg.equals(ChatClientImpl.NO_MORE_MSG)) {
                gui.appendRcvAreaText(msg);
                msg = client.receiveMSG();
            }
            if (msg == null) {
                System.err.println("Receiving message failed: check stack trace");
            }
        }
    }
    
	private class RcvAllTimeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (gui.getjToggleButton().getModel().isSelected()) {
				rec = new Receiver(client, gui);
				Thread rcvThread = new Thread(rec, "ClientController");
				rcvThread.start();
			}else{
				rec.setStopped();
			}
		}
	}
   
    private class ClearBtnActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	gui.clearRcvText();
        }
    }
}
