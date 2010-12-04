package a10;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChristmasFrame {

	private JFrame frame;
	private JEditorPane editorPane;
	private ChristmasTreePattern ctp;
	private int ITERATIONS = 8;
	private int i;

	public ChristmasFrame(ChristmasTreePattern ctp) {
		this.ctp = ctp;
		this.i = 1;
	}

	public void buildFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 ChristmasTreePattern (c) Bernie & Ert");
		frame.setLayout(new BorderLayout());

		// List<List<String>> lines = ctp.preparePattern();
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.setText(ctp.getChristmasTreePattern(i, ITERATIONS));

		JScrollPane scrollPane = new JScrollPane(editorPane);

		frame.getContentPane().add(buildMenuBar(), BorderLayout.NORTH);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(640, 480));
	}

	private JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenu help = new JMenu("Hilfe");
		final JMenuItem closeApp = new JMenuItem("Programm beenden");
		final JMenuItem aboutApp = new JMenuItem("Über 1337-ObjectBrowser");

		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (closeApp == event.getSource()) {
					System.exit(0);
				}

				if (aboutApp == event.getSource()) {
					buildAboutFrame();
				}
			}
		};

		closeApp.addActionListener(menuListener);
		aboutApp.addActionListener(menuListener);
		menuBar.add(data);
		menuBar.add(help);
		data.add(closeApp);
		help.add(aboutApp);

		return menuBar;
	}

	private void buildAboutFrame() {
		JTextArea aboutTxt = new JTextArea(
				"Work done by:\nJan-Tristan Rudat\nMartin Slowikowski\n\n(c)1337-2010 Bernie und Ert");
		final JFrame aboutFrame = new JFrame("About 1337-ObjectLister");
		JButton exitButton = new JButton("Bump me!!!11eins");
		ActionListener exitListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aboutFrame.dispose();
			}
		};
		exitButton.addActionListener(exitListener);
		aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		aboutFrame.setLocationRelativeTo(frame);
		aboutFrame.setSize(800, 300);
		aboutFrame.setResizable(false);
		aboutFrame.setLayout(new BorderLayout());
		aboutFrame.getContentPane().add(new JLabel(new ImageIcon("1337.gif")),
				BorderLayout.WEST);
		aboutFrame.getContentPane().add(aboutTxt, BorderLayout.CENTER);
		aboutFrame.getContentPane().add(
				new JLabel(new ImageIcon("bernieert.jpg")), BorderLayout.EAST);
		aboutFrame.getContentPane().add(exitButton, BorderLayout.SOUTH);
		aboutFrame.setVisible(true);
	}

}
