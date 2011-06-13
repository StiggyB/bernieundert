package a10;

/**
 * 
 * @author Bernie und Ert
 * 
 *         In dieser Klasse befinden sich die Methoden, die ein FileExplorer 
 *         Fenster öffnen. Der Frame setzt sich aus einer gesplitteten Oberfläche
 *         für einen Verzeichnisbaum auf der Linken und Verzeichnis/Dateiinfos
 *         auf der Rechten, sowie einem Menü zusammen.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class ExplorerTree<K, V> {

	JFrame frame = new JFrame();
	private JTextArea fileInfoTextArea;
	private JTree tree;
	private HashTable<K, V> hashTable;
	private List<String> ips;

	public ExplorerTree(HashTable<K, V> hashTable, List<String> ips) {
		this.hashTable = hashTable;
		this.ips = ips;
	}

	public void buildFrame() throws IOException {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 IP HashTable Lister (c) Tugend & Laster");
		frame.setLayout(new BorderLayout());
		StringBuilder sb = new StringBuilder();
		sb.append("Log-Entries:\n\n\n");
		sb.append("Dies ist eine minimale Applikation, die die Values zu einem angeklickten Key anzeigen kann.\n");
		sb.append("Die Keys sind dabei IP-Adresse und die Values Log-Eintraege von einem Apache Webserver\n\n");
		sb.append("Die IP-Adress-Liste und das HashTable-Objekt werden bei der Objekterstellung der GUI an dessen Konstruktor uebergeben;\n");
		sb.append("anschliessend werden mittels der get()-Methode unserer hashTable unter Angabe der uebergebenen IP_Adressen\n");
		sb.append("die zugehoerigen Log-Eintraege (Values) wieder aus der HashTable geholt.");

		fileInfoTextArea = new JTextArea(sb.toString());

		JSplitPane splitPane = new JSplitPane();
		JScrollPane scrollPane = new JScrollPane(buildExplorerTree());

		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(fileInfoTextArea);

		frame.getContentPane().add(buildMenuBar(), BorderLayout.NORTH);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(640, 480));
	}

	private JTree buildExplorerTree() throws IOException {
		String rootDir = "IPs";

		DefaultMutableTreeNode rootDirNode = new DefaultMutableTreeNode(rootDir);

		for (int i = 0; i < ips.size(); i++) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(ips.get(i));
			rootDirNode.add(node);
		}

		tree = new JTree(rootDirNode);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getNewLeadSelectionPath() != null) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) e
							.getNewLeadSelectionPath().getLastPathComponent();

					@SuppressWarnings("unchecked")
					K selectedIp = (K) selectedNode.getUserObject();
					fillTextAreaWithFileInfos(selectedIp);
				}
			}
		});

		return tree;
	}

	private JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenu help = new JMenu("Hilfe");
		final JMenuItem closeApp = new JMenuItem("Programm beenden");
		final JMenuItem aboutApp = new JMenuItem(
				"Über 1337-IP HashTable-Lister");

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
				"Work done by:\nTell Müller-Pettenpohl\nMartin Slowikowski\n\n(c)1337-2011 Tugend & Laster");
		final JFrame aboutFrame = new JFrame("About 1337-Ip HashTable-Lister");
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

	private void fillTextAreaWithFileInfos(K ip) {
		StringBuilder sb = new StringBuilder();
		List<V> values = hashTable.get(ip);
		if (values != null) {
			for (V v : values) {
				sb.append(v + "\n");
			}
		} else {
			// Falls man IP-Einträges holen will, zu denen es keine Entries gibt
			sb.append("Hier gibt's keine Hash-Entries zu sehen, weil es die IP nicht in der HashTable gibt! :)");
		}

		fileInfoTextArea.setText(sb.toString());
	}
}