package a03;

import java.awt.BorderLayout;
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
	private JFrame aboutFrame;
	JLabel labelTitle2 = new JLabel("<html><h1>Explorer</h1></html>");
	private JTextArea fileInfoTextArea;
	private ExplorerIO explorerIO;
	private File rootDir;
	private JTree tree;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	JMenuBar menuBar = new JMenuBar();
	JMenu data = new JMenu("Datei");
	JMenu help = new JMenu("Hilfe");
	JMenuItem newDir = new JMenuItem("Verzeichnis wählen");
	JMenuItem closeApp = new JMenuItem("Programm beenden");
	JMenuItem aboutApp = new JMenuItem("Über 1337-FileLister");

	// für die Formatierung
	public final long one_kb = 1024;
	public final long one_mb = one_kb * one_kb;
	public final long one_gb = one_kb * one_mb;

	public String byteCountToDisplaySize(long size) {
		String displaySize;

		if (size / one_gb > 0) {
			displaySize = String.valueOf(size / one_gb) + " GB";
		} else if (size / one_mb > 0) {
			displaySize = String.valueOf(size / one_mb) + " MB";
		} else if (size / one_kb > 0) {
			displaySize = String.valueOf(size / one_kb) + " KB";
		} else {
			displaySize = String.valueOf(size) + " MB";
		}
		return displaySize;
	}

	// End Formatierung

	public ExplorerTree(ExplorerIO explorerIO) {
		this.explorerIO = explorerIO;
	}

	public void buildFrame() throws IOException {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 FileLister (c) Bernie & Ert");
		frame.setLayout(new BorderLayout());
		rootDir = explorerIO.loadDir();

		DefaultMutableTreeNode rootDirNode = new DefaultMutableTreeNode(rootDir);
		addNodes(rootDirNode);

		tree = new JTree(rootDirNode);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) e
						.getNewLeadSelectionPath().getLastPathComponent();
				File selectedFile = (File) selectedNode.getUserObject();
				fillTextAreaWithFileInfos(selectedFile);
			}
		});

		fileInfoTextArea = new JTextArea("Fileinfo:");
		frame.add(fileInfoTextArea);

		JSplitPane splitPane = new JSplitPane();
		JScrollPane scrollPane = new JScrollPane(tree);

		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				if (newDir == event.getSource()) {
					try {
						rootDir = explorerIO.loadDir();
						DefaultMutableTreeNode rootDirNode = new DefaultMutableTreeNode(
								rootDir);
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
					aboutFrame();

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

		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(fileInfoTextArea);

		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		frame.setSize(splitPane.getWidth(), splitPane.getHeight());

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		// frame.pack(); // macht nur wieder das maximieren kaputt
	}

	private void fillTextAreaWithFileInfos(File file) {
		StringBuilder b = new StringBuilder();
		b.append("Fileinfo:\n").append("- Name: ").append(file.getName())
				.append("\n").append("- Größe: ")
				.append(byteCountToDisplaySize(file.length())).append("\n")
				.append("- Ausführbar: ")
				.append(file.canExecute() ? "ja" : "nein").append("\n")
				.append("- Schreibrechte: ")
				.append(file.canWrite() ? "ja" : "nein").append("\n")
				.append("- Zuletzt geändert: ")
				.append(sdf.format(file.lastModified()));

		fileInfoTextArea.setText(b.toString());
	}

	public void aboutFrame() {
		JTextArea aboutTxt = new JTextArea("Work done by:\nJan-Tristan Rudat\nMartin Slowikowski\n\n(c)1337-2010 Bernie und Ert");
		aboutFrame = new JFrame("About 1337-FileLister");
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