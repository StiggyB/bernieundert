package a03;

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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
import javax.swing.tree.DefaultTreeModel;

public class ExplorerTree {
	
	JFrame frame = new JFrame();
	private JTextArea fileInfoTextArea;
	private JTree tree;
	private ExplorerIO explorerIO;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public ExplorerTree(ExplorerIO explorerIO) {
		this.explorerIO = explorerIO;
	}

	public void buildFrame() throws IOException {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 FileLister (c) Bernie & Ert");
		frame.setLayout(new BorderLayout());

		fileInfoTextArea = new JTextArea("Fileinfo:");
//		frame.add(fileInfoTextArea);
//		frame.add(fileInfoTextArea); //jetzt überflüssig wg. Zeile 64

		JSplitPane splitPane = new JSplitPane();
		JScrollPane scrollPane = new JScrollPane(buildExplorerTree());

		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(fileInfoTextArea);

		frame.getContentPane().add(buildMenuBar(), BorderLayout.NORTH);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
//		frame.setSize(splitPane.getWidth(), splitPane.getHeight());
//		frame.setSize(splitPane.getWidth(), splitPane.getHeight()); //überflüssig wg. Zeile 71

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(640, 480));
		// frame.pack(); // macht nur wieder das maximieren kaputt
	}
	
	private JTree buildExplorerTree() throws IOException {
		File rootDir = explorerIO.loadDir();

		DefaultMutableTreeNode rootDirNode = new DefaultMutableTreeNode(rootDir);
		addNodes(rootDirNode);

		tree = new JTree(rootDirNode);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getNewLeadSelectionPath() != null) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent();
					File selectedFile = (File) selectedNode.getUserObject();
					fillTextAreaWithFileInfos(selectedFile);
				}
			}
		});

		return tree;
	}

	private JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenu help = new JMenu("Hilfe");
		final JMenuItem newDir = new JMenuItem("Verzeichnis wählen");
		final JMenuItem closeApp = new JMenuItem("Programm beenden");
		final JMenuItem aboutApp = new JMenuItem("Über 1337-FileLister");

		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				if (newDir == event.getSource()) {
					try {
						File rootDir = explorerIO.loadDir();
						DefaultMutableTreeNode rootDirNode = new DefaultMutableTreeNode(rootDir);
						addNodes(rootDirNode);
						tree.setModel(new DefaultTreeModel(rootDirNode));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (closeApp == event.getSource()) {
					System.exit(0);

				}

				if (aboutApp == event.getSource()) {
					buildAboutFrame();

				}
			}
		};

		newDir.addActionListener(menuListener);
		closeApp.addActionListener(menuListener);
		aboutApp.addActionListener(menuListener);
		menuBar.add(data);
		menuBar.add(help);
		data.add(newDir);
		data.add(closeApp);
		help.add(aboutApp);

		return menuBar;
	}

	private void buildAboutFrame() {
		JTextArea aboutTxt = new JTextArea("Work done by:\nJan-Tristan Rudat\nMartin Slowikowski\n\n(c)1337-2010 Bernie und Ert");
		final JFrame aboutFrame = new JFrame("About 1337-FileLister");
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
		aboutFrame.getContentPane().add(new JLabel(new ImageIcon("1337.gif")), BorderLayout.WEST);
		aboutFrame.getContentPane().add(aboutTxt, BorderLayout.CENTER);
		aboutFrame.getContentPane().add(new JLabel(new ImageIcon("bernieert.jpg")), BorderLayout.EAST);
		aboutFrame.getContentPane().add(exitButton, BorderLayout.SOUTH);
		aboutFrame.setVisible(true);
	}

	private void fillTextAreaWithFileInfos(File file) {
		StringBuilder b = new StringBuilder();
		b.append("Fileinfo:\n").append("- Name: ").append(file.getName())
				.append("\n").append("- Größe: ")
				.append(ExplorerUtils.byteCountToDisplaySize(file.length()))
				.append("\n").append("- Ausführbar: ")
				.append(file.canExecute() ? "ja" : "nein").append("\n")
				.append("- Schreibrechte: ")
				.append(file.canWrite() ? "ja" : "nein").append("\n")
				.append("- Zuletzt geändert: ")
				.append(sdf.format(file.lastModified()))
				.append("\n\n- vollständiger Dateipfad: ")
				.append(file.getAbsolutePath())
				.append("\n- zugehöriges Oberverzeichnis: ")
				.append(file.getParent());

		fileInfoTextArea.setText(b.toString());
	}

	private void addNodes(DefaultMutableTreeNode parentNode) {
		File dir = (File) parentNode.getUserObject();

		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
				addNodes(node);
				parentNode.add(node);
			}
		}

		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				parentNode.add(new DefaultMutableTreeNode(file));
			}
		}

	}

}