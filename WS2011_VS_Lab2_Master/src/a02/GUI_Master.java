package a02;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GUI_Master extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7195507977908110811L;
	
	private JPanel contentPane;
	private JTextField textFieldHost;
	private JTextField textFieldPort;
	private JTextField textFieldNumberOfActors;
	private JTextField textFieldNumberToCalc;
	private JButton btnStartWorker;
	private JButton btnCreateWorker;
	private List listOfWorkers;
	private JTextField txtNumberofiterations;
	private JTextArea txtrResult;
	private JButton btnClose;
	private JButton btnGetResult;
	private JScrollPane scrollPane_1;
	private JButton btnRemoveWorker;

	/**
	 * Create the frame.
	 */
	public GUI_Master() {
		setTitle("Master");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStartWorker = new JButton("Start Workers");
		btnStartWorker.setBounds(436, 248, 138, 23);
		contentPane.add(btnStartWorker);
		
		textFieldHost = new JTextField();
		textFieldHost.setText("localhost");
		textFieldHost.setBounds(11, 35, 131, 20);
		contentPane.add(textFieldHost);
		textFieldHost.setColumns(10);
		
		textFieldPort = new JTextField();
		textFieldPort.setText("2500");
		textFieldPort.setBounds(10, 80, 132, 20);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		textFieldNumberOfActors = new JTextField();
		textFieldNumberOfActors.setText("2");
		textFieldNumberOfActors.setBounds(10, 126, 132, 20);
		contentPane.add(textFieldNumberOfActors);
		textFieldNumberOfActors.setColumns(10);
		
		JLabel lblEnterHost = new JLabel("Enter Host");
		lblEnterHost.setBounds(11, 21, 131, 14);
		contentPane.add(lblEnterHost);
		
		JLabel lblEnterPort = new JLabel("Enter Port");
		lblEnterPort.setBounds(11, 66, 131, 14);
		contentPane.add(lblEnterPort);
		
		JLabel lblEnterNumberOf = new JLabel("Enter Number of Actors");
		lblEnterNumberOf.setBounds(11, 111, 165, 14);
		contentPane.add(lblEnterNumberOf);
		
		JLabel lblEnterNumberTo = new JLabel("Enter number to calculate");
		lblEnterNumberTo.setBounds(10, 203, 166, 14);
		contentPane.add(lblEnterNumberTo);
		
		textFieldNumberToCalc = new JTextField();
		textFieldNumberToCalc.setText("1137047281562824484226171575219374004320812483047");
		textFieldNumberToCalc.setBounds(11, 217, 563, 20);
		contentPane.add(textFieldNumberToCalc);
		textFieldNumberToCalc.setColumns(10);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(10, 282, 46, 14);
		contentPane.add(lblResult);
		
		JLabel lblListOfWorkers = new JLabel("List of Workers");
		lblListOfWorkers.setBounds(183, 11, 143, 14);
		contentPane.add(lblListOfWorkers);
		
		listOfWorkers = new List();
		listOfWorkers.setBounds(182, 25, 392, 167);
		contentPane.add(listOfWorkers);
		
		JLabel lblEnterNoOf = new JLabel("Enter number of iterations");
		lblEnterNoOf.setBounds(11, 157, 165, 14);
		contentPane.add(lblEnterNoOf);
		
		txtNumberofiterations = new JTextField();
		txtNumberofiterations.setText("50");
		txtNumberofiterations.setHorizontalAlignment(SwingConstants.LEFT);
		txtNumberofiterations.setBounds(11, 172, 131, 20);
		contentPane.add(txtNumberofiterations);
		txtNumberofiterations.setColumns(10);
		
		btnCreateWorker = new JButton("Create Worker");
		btnCreateWorker.setBounds(10, 248, 132, 23);
		contentPane.add(btnCreateWorker);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(485, 548, 89, 23);
		contentPane.add(btnClose);
		
		btnGetResult = new JButton("Get Result");
		btnGetResult.setBounds(11, 548, 131, 23);
		contentPane.add(btnGetResult);
				
		txtrResult = new JTextArea();
		txtrResult.setLineWrap(true);
		txtrResult.setWrapStyleWord(true);
		txtrResult.setEditable(false);
		txtrResult.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrResult.setBounds(11, 296, 461, 241);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(11, 295, 563, 242);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(txtrResult);
		
		btnRemoveWorker = new JButton("Remove Worker");
		btnRemoveWorker.setBounds(183, 248, 143, 23);
		contentPane.add(btnRemoveWorker);
	}
	
	// Action Listeners
	public void setBtnStartWorkerListener(ActionListener l) {
		btnStartWorker.addActionListener(l);
	}
	
	public void setBtnCreateWorkerListener(ActionListener l) {
		btnCreateWorker.addActionListener(l);
	}
	
	public void setBtnGetResultListener(ActionListener l) {
		btnGetResult.addActionListener(l);
	}
	
	public void setBtnCloseListener(ActionListener l) {
		btnClose.addActionListener(l);
	}
	
	public void setBtnRemoveWorkerListener(ActionListener l) {
		btnRemoveWorker.addActionListener(l);
	}
	
	// Methods
	public String getTextFieldHost() {
		return textFieldHost.getText();
	}
	
	public String getTextFieldPort() {
		return textFieldPort.getText();
	}
	
	public String getTextFieldNumberOfActors() {
		return textFieldNumberOfActors.getText();
	}
	
	public String getTextFieldNumbertoCalc() {
		return textFieldNumberToCalc.getText();
	}
	
	public void addWorkerToList(String txt) {
		listOfWorkers.add(txt + "\n");
	}
	
	public int removeWorkerFromList() {
		int selectedWorker = listOfWorkers.getSelectedIndex();
		if (selectedWorker == -1)
			return -1;
		else {
			listOfWorkers.remove(selectedWorker);
			return selectedWorker;
		}
	}

	public String getTextFieldNumberOfIterations() {
		return txtNumberofiterations.getText();
	}
	
	public void setTxtrResult(String txtrResult) {
		this.txtrResult.append(txtrResult);
	}

	public List getListOfWorkers() {
		return listOfWorkers;
	}
}
