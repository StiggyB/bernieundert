package a08;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class ExplorerTree {

	public class FieldAndValue {
		private final Field field;
		private final Object value;

		public FieldAndValue(Field field, Object value) {
			this.field = field;
			this.value = value;
			;
		}
	}

	JFrame frame = new JFrame();
	private JTextArea fileInfoTextArea;
	private JTree tree;


	public void buildFrame() throws IOException, IllegalArgumentException, IllegalAccessException {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 FileLister (c) Bernie & Ert");
		frame.setLayout(new BorderLayout());

		fileInfoTextArea = new JTextArea("Fileinfo:");
		// frame.add(fileInfoTextArea);
		// frame.add(fileInfoTextArea); //jetzt überflüssig wg. Zeile 64

		JSplitPane splitPane = new JSplitPane();
//		JScrollPane scrollPane = new JScrollPane(buildExplorerTree(Integer.valueOf(10)));
		JScrollPane scrollPane = new JScrollPane(buildExplorerTree(new DummyClass(5, 10)));
//		JScrollPane scrollPane = new JScrollPane(buildExplorerTree(new ArrayList<String>()));

		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(fileInfoTextArea);

		frame.getContentPane().add(buildMenuBar(), BorderLayout.NORTH);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		// frame.setSize(splitPane.getWidth(), splitPane.getHeight());
		// frame.setSize(splitPane.getWidth(), splitPane.getHeight());
		// //überflüssig wg. Zeile 71

		frame.setVisible(true);
//		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(640, 480));
		// frame.pack(); // macht nur wieder das maximieren kaputt
	}

	private JTree buildExplorerTree(Object objectToInspect) throws IOException, IllegalArgumentException, IllegalAccessException {
		DefaultMutableTreeNode rootDirNode = new DefaultMutableTreeNode(objectToInspect);
		addMethodsAndFields(rootDirNode, objectToInspect);

		tree = new JTree(rootDirNode) {
			@Override
			public String convertValueToText(Object value, boolean selected,
					boolean expanded, boolean leaf, int row, boolean hasFocus) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
				Object userObject = node.getUserObject();
				if (userObject != null) {
					if (userObject instanceof FieldAndValue) {
						return ((FieldAndValue) userObject).field.getName();
					} else if (userObject instanceof Method) {
						return ((Method) userObject).getName();
					} else if (userObject.equals("Fields") || userObject.equals("Methods")) {
						return userObject.toString();
					} else {
						return userObject.getClass().getCanonicalName();
					}
				}
				return "";
			}
		};
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				Object userObject = selectedNode.getUserObject();
				
				fillTextAreaWithFileInfos(userObject);
			}
		});
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				for (int i = 0; i < node.getChildCount(); i++) {
					DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) node.getChildAt(i);
					
					Object userObject = currentNode.getUserObject();
					if (userObject instanceof FieldAndValue) {
						FieldAndValue fav = (FieldAndValue) userObject;
						Field field = fav.field;
						Class<?> type = field.getType();
						if (!type.isPrimitive()) {
							try {
								addMethodsAndFields(currentNode, fav.value);
							} catch (IllegalArgumentException e1) {
								e1.printStackTrace();
							} catch (IllegalAccessException e1) {
								e1.printStackTrace();
							}
						}
					} else if (userObject instanceof Method) {
						Class<?> type = ((Method) userObject).getReturnType();
						if (!type.isPrimitive()) {
						}
					}
				}
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent e) {
			}
		});

		return tree;
	}

	private void addMethodsAndFields(DefaultMutableTreeNode parentNode, Object objectToInspect) throws IllegalArgumentException, IllegalAccessException {
		DefaultMutableTreeNode fieldsChildNode = new DefaultMutableTreeNode("Fields");
		parentNode.add(fieldsChildNode);

		if (objectToInspect != null) {
			Field[] declaredFields = objectToInspect.getClass().getDeclaredFields();
			for (int i = 0; i < declaredFields.length; i++) {
				Field field = declaredFields[i];
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				Object fieldValue = field.get(objectToInspect);
				FieldAndValue fav = new FieldAndValue(field, fieldValue);
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(fav);
				fieldsChildNode.add(node);
			}
	
			DefaultMutableTreeNode methodsChildNode = new DefaultMutableTreeNode("Methods");
			parentNode.add(methodsChildNode);
			
			Method[] declaredMethods = objectToInspect.getClass().getDeclaredMethods();
			for (int i = 0; i < declaredMethods.length; i++) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(declaredMethods[i]);
				methodsChildNode.add(node);
			}
		}
	}

	private JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenu help = new JMenu("Hilfe");
		// final JMenuItem newDir = new JMenuItem("Verzeichnis wählen");
		final JMenuItem closeApp = new JMenuItem("Programm beenden");
		final JMenuItem aboutApp = new JMenuItem("Über 1337-ObjectBrowser");

		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				// if (newDir == event.getSource()) {
				// try {
				// File rootDir = explorerIO.loadDir();
				// DefaultMutableTreeNode rootDirNode = new
				// DefaultMutableTreeNode(rootDir);
				// addNodes(rootDirNode);
				// tree.setModel(new DefaultTreeModel(rootDirNode));
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// }

				if (closeApp == event.getSource()) {
					System.exit(0);

				}

				if (aboutApp == event.getSource()) {
					buildAboutFrame();

				}
			}
		};

		// newDir.addActionListener(menuListener);
		closeApp.addActionListener(menuListener);
		aboutApp.addActionListener(menuListener);
		menuBar.add(data);
		menuBar.add(help);
		// data.add(newDir);
		data.add(closeApp);
		help.add(aboutApp);

		return menuBar;
	}

	private void buildAboutFrame() {
		JTextArea aboutTxt = new JTextArea(
				"Work done by:\nJan-Tristan Rudat\nMartin Slowikowski\n\n(c)1337-2010 Bernie und Ert");
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
		aboutFrame.getContentPane().add(new JLabel(new ImageIcon("1337.gif")),
				BorderLayout.WEST);
		aboutFrame.getContentPane().add(aboutTxt, BorderLayout.CENTER);
		aboutFrame.getContentPane().add(
				new JLabel(new ImageIcon("bernieert.jpg")), BorderLayout.EAST);
		aboutFrame.getContentPane().add(exitButton, BorderLayout.SOUTH);
		aboutFrame.setVisible(true);
	}

	private void fillTextAreaWithFileInfos(Object o) {
		StringBuilder sb = new StringBuilder();
		
		if (o instanceof FieldAndValue) {
			Field field = ((FieldAndValue) o).field;
			sb.append("Feld:\n");
			sb.append(printFieldNames(field));
		} else if (o instanceof Method) {
			sb.append("Methode:\n");
			sb.append(printMethod((Method) o));
		} else if (!o.equals("Fields") && !o.equals("Methods")) {
			// Klasse
			sb.append("Klasse:\n");
			Class<?> c = o.getClass();
			sb.append("- Name: ").append(c.getSimpleName())
			.append("\n").append("- Class Modifier: ").append(printClassModifiers(c)).append("\n")
			.append("- Superklasse: ").append(printSuperclasses(o)).append("\n")
			.append("- Interfaces: ").append(printInterfaces(o)).append("\n")
			.append("- Konstruktoren: ").append(printConstructors(o)).append("\n");
		}

		fileInfoTextArea.setText(sb.toString());
	}



	private String printClassModifiers(Class<?> c) {
		return Modifier.toString(c.getModifiers());
	}

	private String printSuperclasses(Object o) {
		StringBuilder sb = new StringBuilder();
		Class<?> subclass = o.getClass();
		Class<?> superclass = subclass.getSuperclass();
		while (superclass != null) {
			String className = superclass.getName();
			sb.append(className);
			sb.append(", ");
			subclass = superclass;
			superclass = subclass.getSuperclass();
		}
		return sb.toString();
	}
	
	private String printInterfaces(Object o){
		StringBuilder sb = new StringBuilder();
		Class<?> c = o.getClass();
		Class<?>[] theInterfaces = c.getInterfaces();
		sb.append("\n");
		for (int i = 0; i < theInterfaces.length; i++) {
			sb.append("  - ").append(theInterfaces[i].getCanonicalName()).append("\n");
		}
		return sb.toString();
	}
	
	private String printConstructors(Object o) {
		StringBuilder sb = new StringBuilder();
		Class<?> c = o.getClass();
		Constructor[] theConstructors = c.getDeclaredConstructors();
		sb.append("\n");
		for (int i = 0; i < theConstructors.length; i++) {
			sb.append("  - ").append(theConstructors[i]).append("\n");
//			Class[] parameterTypes = theConstructors[i].getParameterTypes();
//			for (int k = 0; k < parameterTypes.length; k++) {
//				String parameterString = parameterTypes[k].getName();
//				sb.append(parameterString);
//				sb.append(", ");
//			}
		}
		return sb.toString();
	}
	
	private String printFieldNames(Field field){
		StringBuilder sb = new StringBuilder();
		String fieldName = field.getName();
		Class<?> typeClass = field.getType();
		String fieldType = typeClass.getName();
		String fieldModifier = printClassModifiers(typeClass);
		sb.append("Name: " + fieldName + ", Type: " + fieldType + ", Modifier: " + fieldModifier);
		sb.append("\n");
		return sb.toString();
	}
	
	
	// TODO: Modifier
	private String printMethod(Method method) {
		StringBuilder sb = new StringBuilder();
		String methodString = method.getName();
		sb.append("Name: " + methodString);
		String returnString = method.getReturnType().getName();
		sb.append(", Rückgabetyp: " + returnString);
		Class[] parameterTypes = method.getParameterTypes();
		sb.append(", Übergabeparamtertyp:");
		for (int k = 0; k < parameterTypes.length; k++) {
			String parameterString = parameterTypes[k].getName();
			sb.append(" " + parameterString + " ");
		}
		return sb.toString();
	}

}