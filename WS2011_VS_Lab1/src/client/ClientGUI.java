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
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

/**
 * This class provides the GUI functionality and
 * extends a JFrame with the <i>ChatClient</i> features.
 *
 */
public class ClientGUI extends JFrame {

	private static final long serialVersionUID = -7873545878974332668L;
	private JPanel contentPane;
	private JTextField sendField;
	private JTextField txtRetry;
	private JButton btnReceive;
	private JButton btnReceiveAll;
	private JToggleButton tglbtnReceiveFrequently;
	private JButton btnClear;
	private JButton btnSend;
	private JTextArea receiveArea;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnReceive = new JButton("Receive");
		btnReceive.setBounds(20, 11, 89, 23);
		contentPane.add(btnReceive);
		
		btnReceiveAll = new JButton("Receive all");
		btnReceiveAll.setBounds(20, 34, 89, 23);
		contentPane.add(btnReceiveAll);
		
		tglbtnReceiveFrequently = new JToggleButton("Receive frequently");
		tglbtnReceiveFrequently.setBounds(10, 57, 123, 23);
		contentPane.add(tglbtnReceiveFrequently);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(20, 81, 89, 23);
		contentPane.add(btnClear);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(20, 196, 89, 23);
		contentPane.add(btnSend);
		
		sendField = new JTextField();
		sendField.setBounds(136, 197, 288, 20);
		contentPane.add(sendField);
		sendField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 10, 288, 183);
		contentPane.add(scrollPane);
		
		receiveArea = new JTextArea();
		scrollPane.setViewportView(receiveArea);
		receiveArea.setEditable(false);
		
		JLabel lblDurationOfRetries = new JLabel("Duration of retries");
		lblDurationOfRetries.setBounds(20, 121, 89, 14);
		contentPane.add(lblDurationOfRetries);
		
		txtRetry = new JTextField();
		txtRetry.setText("Enter in sec");
		txtRetry.setBounds(20, 134, 86, 20);
		contentPane.add(txtRetry);
		txtRetry.setColumns(10);
	}
	
    public JToggleButton getjToggleButton() {
		return tglbtnReceiveFrequently;
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
        btnSend.addActionListener(l);
    }
    
    public void setRcvBtnListener(ActionListener l) {
        btnReceive.addActionListener(l);
    }
    
    public void setRcvAllBtnListener(ActionListener l) {
        btnReceiveAll.addActionListener(l);
    }

	public void setClearBtnListener(ActionListener l) {
		btnClear.addActionListener(l);
	}
	
	public void setjToggleButtonListener(ActionListener l) {
		tglbtnReceiveFrequently.addActionListener(l);
	}
}
