package a02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.Box;
import javax.swing.JTextArea;

public class GUI_Master extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JTextField textFieldNumberOfActors;
	private JTextField textFieldNumberToCalc;
	private JTable tableWorker;
	private final DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Master frame = new GUI_Master();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JButton btnStartWorker;
	private JButton btnTerminateWorker;

	/**
	 * Create the frame.
	 */
	public GUI_Master() {
		setTitle("Master");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStartWorker = new JButton("Start");
		btnStartWorker.setBounds(11, 200, 103, 23);
		contentPane.add(btnStartWorker);
		
		textFieldIP = new JTextField();
		textFieldIP.setBounds(11, 25, 103, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		textFieldPort = new JTextField();
		textFieldPort.setBounds(10, 72, 104, 20);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		textFieldNumberOfActors = new JTextField();
		textFieldNumberOfActors.setBounds(10, 119, 104, 20);
		contentPane.add(textFieldNumberOfActors);
		textFieldNumberOfActors.setColumns(10);
		
		JLabel lblEnterIpAdress = new JLabel("Enter IP Adress");
		lblEnterIpAdress.setBounds(10, 11, 87, 14);
		contentPane.add(lblEnterIpAdress);
		
		JLabel lblEnterPort = new JLabel("Enter Port");
		lblEnterPort.setBounds(10, 56, 64, 14);
		contentPane.add(lblEnterPort);
		
		JLabel lblEnterNumberOf = new JLabel("Enter Number of Actors");
		lblEnterNumberOf.setBounds(10, 103, 131, 14);
		contentPane.add(lblEnterNumberOf);
		
		JLabel lblEnterNumberTo = new JLabel("Enter number to calculate");
		lblEnterNumberTo.setBounds(10, 150, 131, 14);
		contentPane.add(lblEnterNumberTo);
		
		textFieldNumberToCalc = new JTextField();
		textFieldNumberToCalc.setBounds(10, 169, 104, 20);
		contentPane.add(textFieldNumberToCalc);
		textFieldNumberToCalc.setColumns(10);
		
		tableWorker = new JTable(model);
		tableWorker.setBounds(151, 25, 143, 164);
		contentPane.add(tableWorker);
		
		btnTerminateWorker = new JButton("Terminate Worker");
		btnTerminateWorker.setBounds(151, 200, 143, 23);
		contentPane.add(btnTerminateWorker);
		
		JTextArea txtrResult = new JTextArea();
		txtrResult.setBounds(11, 257, 283, 100);
		contentPane.add(txtrResult);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(11, 234, 46, 14);
		contentPane.add(lblResult);
		
		JLabel lblListOfWorkers = new JLabel("List of Workers");
		lblListOfWorkers.setBounds(151, 11, 87, 14);
		contentPane.add(lblListOfWorkers);
	}
	
	// Action Listeners
	public void setBtnStartWorkerListener(ActionListener l) {
		btnStartWorker.addActionListener(l);
	}
	
	public void setBtnTerminateWorkerListener(ActionListener l) {
		btnStartWorker.addActionListener(l);
	}
	
	// Methods
	public String getTextFieldIP() {
		return textFieldIP.getText();
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
	
	public void setTableWorker(String txt) {
		int size1 = model.getColumnCount();

		// einen neuen Vector mit Daten herstellen
		Vector newDatas = createDataVector("row", size1);

		// eine neue Row hinzuf�gen
		model.addRow(newDatas);

		int size2 = model.getRowCount();
		Vector newDatas2 = createDataVector("column", size2);
		String name = String.valueOf(model.getColumnCount());
		model.addColumn(name, newDatas2);

	}

	public static Vector createDataVector(String prefix, int size) {
		Vector vector = new Vector(size);
		for (int i = 0; i < size; i++)
			vector.add(prefix + " : " + size + " : " + i);

		return vector;
    }
}
