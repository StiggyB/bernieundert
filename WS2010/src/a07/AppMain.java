package a07;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AppMain extends JFrame {

	private static final long serialVersionUID = 2496796316068948959L;
	private static final int COUNT_FLOORS = 10;
	private static final int COUNT_ELEVATOR = 1;

	private GridBagConstraints constraint;
	private GridBagLayout layout;
	
	private JLabel[][] labels = new JLabel[COUNT_ELEVATOR+2][COUNT_FLOORS+2];
	private JButton[] blockButtons = new JButton[COUNT_ELEVATOR];
	private JButton[] entryButtons = new JButton[COUNT_ELEVATOR];
	private ElevatorController controller;

	
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
		
		controller = new ElevatorController(COUNT_ELEVATOR, COUNT_FLOORS);
		this.initFloors(controller);
		
		controller.setListener(new ElevatorListener() {
			@Override
			public void elevatorChangedFloor(int elevatorNr, int floor) {
				int x = elevatorNr + 1;
				int y = COUNT_FLOORS + 1 - floor;
				
				int yAbove = COUNT_FLOORS  - floor;
				int yUnder = COUNT_FLOORS + 2 - floor;
				
				labels[x][y].setText("[x]");
				
				if (yAbove >= 1) {
					labels[x][yAbove].setText("[ ]");
				}
				
				if (yUnder <= COUNT_FLOORS + 1) {
					labels[x][yUnder].setText("[ ]");
				}
//				System.out.println("Listener got called: " + elevatorNr + "/" + floor);
			}

			@Override
			public void arrivedOnTargetFloor(int elevatorNr) {
				blockButtons[elevatorNr].setEnabled(true);
				entryButtons[elevatorNr].setEnabled(true);
			}

			@Override
			public void leavingFloor(int elevatorNr) {
				blockButtons[elevatorNr].setEnabled(false);
				entryButtons[elevatorNr].setEnabled(false);
			}
		});
		
//		controller.callElevator(8, 1);
	}

	private void initFloors(final ElevatorController controller) {
		for (int i = 0; i <= AppMain.COUNT_FLOORS; i++) {
			final int floor = COUNT_FLOORS - i;
			
			constraint.weightx = 10;
			constraint.weighty = 50;
			
			constraint.gridx = 0;
			constraint.gridy = i + 1;
			final JButton vorrangfButton = new JButton("Vorrangf.");
			vorrangfButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("floor: " + floor);
					controller.callElevatorPriority(floor);
				}
			});
			this.add(vorrangfButton, constraint);
			
			constraint.gridx = 1;
			constraint.gridy = i + 1;
			
			JButton callButton = new JButton("rufen");
			callButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("floor: " + floor);
					controller.callElevator(floor);
				}
			});
			this.add(callButton, constraint);
			
			constraint.gridx = 2;
			constraint.gridy = i + 1;
			this.add(new JLabel(String.valueOf(AppMain.COUNT_FLOORS - i)), constraint);
		}

		for (int i = 1; i <= AppMain.COUNT_ELEVATOR; i++) {
			constraint.gridx = i + 2;
			constraint.gridy = 0;
			
			this.add(getFsHeadPanel(i), constraint);	
			constraint.gridy = COUNT_FLOORS;
			this.add(new JLabel(new ImageIcon("C:/elevator.jpg")), constraint);
		}
		
		// Start einfärben
		for (int x = 1; x <= AppMain.COUNT_ELEVATOR; x++) {
			for (int y = 1; y <= AppMain.COUNT_FLOORS; y++) {
				constraint.gridx = x + 2;
				constraint.gridy = y;
				JLabel label = new JLabel("[ ]");
				labels[x][y] = label;
				this.add(label, constraint);
			}
		}
		
		// Alle auf 0 setzen
		for (int x = 1; x <= AppMain.COUNT_ELEVATOR; x++) {
			constraint.gridx = x + 2;
			constraint.gridy = 11;
			JLabel label = new JLabel("[x]");
			labels[x][11] = label;
			this.add(label, constraint);
		}
	}

	private JPanel getFsHeadPanel(final int fsNr) {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(3, 1);
		panel.setLayout(layout);
		panel.add(new JLabel("Fahrstuhl - " + String.valueOf(fsNr)));
		
		// Blockbutton
		final JButton blockButton = new JButton("<>");
		blockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.blockFahrstuhl(fsNr - 1);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				controller.unblockFahrstuhl(fsNr - 1);
			}
		});
		blockButton.setEnabled(false);
		blockButtons[fsNr - 1] = blockButton;
		
		// Einsteigeknopf
		final JButton entryButton = new JButton("-->");
		entryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer[] possibilities = new Integer[COUNT_FLOORS + 1];
				for (int i = 0; i <= COUNT_FLOORS; i++) {
					possibilities[i] = i;
				}
				int choice = (Integer) JOptionPane.showInputDialog(
						AppMain.this, "Bitte Stockwerk auswählen",
						"Stockwerk wählen", JOptionPane.PLAIN_MESSAGE, null,
						possibilities, 0);

				controller.driveTo(fsNr - 1, choice);
			}
		});
		entryButton.setEnabled(false);
		entryButtons[fsNr - 1] = entryButton;
		
		panel.add(blockButton);
		panel.add(entryButton);
		return panel;
	}

	private void initSimpleComponents(GridBagConstraints constraint) {
		JPanel buttonPane = new JPanel();
	
		JButton btnDownstairs = new JButton("Runter fahren");
		btnDownstairs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("test");
			}
		});
	
		JButton btnUpstairs = new JButton("Hoch fahren");
		btnUpstairs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("test");
			}
		});
		buttonPane.add(btnUpstairs, constraint);
		
						
		final JTextField txtFloor = new JTextField("Stockwerk eingeben", 15); 
			constraint.fill = GridBagConstraints.CENTER;
			
		buttonPane.add(txtFloor, constraint);
		
		
		
		JButton btnConfirmFloor = new JButton("Losfahren");
		btnConfirmFloor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					System.out.println(txtFloor.getText());
			}
		});
		buttonPane.add(btnConfirmFloor, constraint);
		
	
		JButton btnRandomize = new JButton("Random fahrn");
		btnRandomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Double floor = (Math.random() * COUNT_FLOORS);
				System.out.println(floor.intValue());
			}
		});
		buttonPane.add(btnRandomize, constraint);
//		this.add(buttonPane);		// Button Pane für Erweiterungen erstmal ausgeblendet.
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		
		AppMain appMain;
		appMain = new AppMain();
		appMain.init();
		appMain.pack();
		appMain.setVisible(true);
		appMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
