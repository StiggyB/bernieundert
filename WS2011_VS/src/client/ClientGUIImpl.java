package client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


// Client Controller
public class ClientGUIImpl {

	private static final int MAX_ITERATIONS = 5;
	private ChatClientImpl client;
	private ClientGUI gui;
	
    public ClientGUIImpl(ChatClientImpl client, ClientGUI gui) {
        this.client = client;
        this.gui = gui;
    }
    
    public void start() {
    	gui.startup();
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
            System.out.println("send");
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
				String msg = client.receiveMSG();
	            for (;;) {
	            	setRcvMode(msg);
	            	if(!(gui.getjToggleButton().getModel().isEnabled())) {
	            		break;
	        		}
				}
			}
		}
	}
    
	private void setRcvMode(String msg) {
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			gui.appendRcvAreaText(msg);
            msg = client.receiveMSG();
		}
		
	}
	
    private class ClearBtnActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        	gui.clearRcvText();
        }
    }
    
}
