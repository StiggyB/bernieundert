package a06ws;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AppMain extends JFrame {

	private static final long serialVersionUID = 2496796316068948959L;
	public static final int COUNT_FLOORS = 10;
	public static final int COUNT_ELEVATOR = 3;

	private GridBagConstraints constraint;
	private GridBagLayout layout;

	
	
	
	/**
	 * Initialisiert diesen <strong>JFrame</strong>, welcher eine simple 
	 * Fahrstuhlsimulation beinhaltet. Dieser Fahrstuhl hat die Möglichkeit
	 * 
	 * <ul>
	 * 	<li>hochzufahren</li>
	 * 	<li>runterzufahren</li>
	 *  <li>gerufen zu werden</li>
	 * </ul>
	 * 
	 * Die maximale Anzahl der Stockwerke darf 20 aufgrund der Auflösung
	 * andererer Systeme nicht übeschreiten. <strong> COUNT_FLOORS </strong>
	 * ist per Default auf 10 gesetzt. 
	 * 
	 * @see JFrame ImageIcon GridBagConstraint
	 */
	public void init() {
	
		this.layout = new GridBagLayout();
		this.setLayout(layout);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.constraint = new GridBagConstraints();
		this.constraint.gridx = 0;
		this.constraint.gridy = 0;
		this.constraint.insets = new Insets(0, 5, 0, 5); // padding

		this.initSimpleComponents(constraint);
		this.initFloors();
	}

	private void initFloors() {
		for (int i = 0; i <= AppMain.COUNT_FLOORS; i++) {
			constraint.gridx = 0;
			constraint.gridy = i + 1;
			this.add(new JLabel(String.valueOf(AppMain.COUNT_FLOORS - i)),
					constraint);
		}

		for (int i = 1; i <= AppMain.COUNT_ELEVATOR; i++) {
			constraint.gridx = i;
			constraint.gridy = 0;
			this.add(new JLabel("Fahrstuhl - " + String.valueOf(i)), constraint);
			constraint.gridy = 1;
			this.add(new JLabel(new ImageIcon("C:/elevator.jpg")), constraint);
		}
	}

	private void initSimpleComponents(GridBagConstraints constraint) {
		this.constraint.gridx = COUNT_ELEVATOR + 1;
		this.constraint.gridy = 0;
		
		JButton btnDownstairs = new JButton("Runter fahren");
		btnDownstairs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("test");
			}
		});
		this.add(btnDownstairs, constraint);

		this.constraint.gridx = COUNT_ELEVATOR + 1;
		this.constraint.gridy = 1;
		
		JButton btnUpstairs = new JButton("Hoch fahren");
		btnUpstairs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("test");
			}
		});
		this.add(btnUpstairs, constraint);
		
		this.constraint.gridx = COUNT_ELEVATOR + 1;
		this.constraint.gridy = 2;
				
		final JTextField txtFloor = new JTextField("Stockwerk eingeben", 15); 
		this.add(txtFloor, constraint);
		
		this.constraint.gridx = COUNT_ELEVATOR + 1;
		this.constraint.gridy = 3;
		
		JButton btnConfirmFloor = new JButton("Losfahren");
		btnConfirmFloor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					System.out.println(txtFloor.getText());
			}
		});
		this.add(btnConfirmFloor, constraint);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		AppMain appMain;
		appMain = new AppMain();
		appMain.init();
		appMain.pack();
		appMain.setVisible(true);
		appMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
