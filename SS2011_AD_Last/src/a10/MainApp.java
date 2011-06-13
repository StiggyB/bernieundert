package a10;

import javax.swing.UIManager;

/**
 * This class is the main-class for the whole HashTable implementation. System
 * look&feel is set and then the GUI is created and started for visual testing.
 * The JUnit test cases from the "TestHashTableWithJUnit" must be started
 * seperately.
 * 
 * @author Tugend und Laster
 * @see IHashTable
 */
public class MainApp {

	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		TestHashTableWithGUI testGui = new TestHashTableWithGUI();
		testGui.startGUI();

	}

}
