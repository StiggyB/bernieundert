package client;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI2 {

	JFrame frame;
	private JTextField sendField;
	private JTextArea receiveArea;
	private JButton sendBtn;
	private JButton receiveBtn;
	private JButton clearBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI2 window = new ClientGUI2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ClientGUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		sendField = new JTextField();
		sendField.setBounds(121, 244, 323, 28);
		frame.getContentPane().add(sendField);
		sendField.setColumns(10);
		
		receiveArea = new JTextArea();
		receiveArea.setBounds(122, 6, 322, 234);
		frame.getContentPane().add(receiveArea);
		
		sendBtn = new JButton("Send");
		sendBtn.setBounds(0, 243, 117, 29);
		frame.getContentPane().add(sendBtn);
		
		receiveBtn = new JButton("Recieve");
		receiveBtn.setBounds(0, 6, 117, 29);
		frame.getContentPane().add(receiveBtn);
		
		clearBtn = new JButton("Clear");
		clearBtn.setBounds(0, 36, 117, 29);
		frame.getContentPane().add(clearBtn);

	}
	
    public String getSendFieldText() {
        return sendField.getText();
    }
    
    public void appendRcvAreaText(String txt) {
        receiveArea.append(txt + "\n");
    }
    
    public void clearRcvText() {
        receiveArea.setText("");
    }
    
    public void clearSendText() {
        sendField.setText("");
    }
	
    // Action Listeners
    public void setSendBtnListener(ActionListener l) {
        sendBtn.addActionListener(l);
    }
    
    public void setRcvBtnListener(ActionListener l) {
        receiveBtn.addActionListener(l);
    }
    
	public void setClearBtnListener(ActionListener l) {
		clearBtn.addActionListener(l);
	}
	

}
