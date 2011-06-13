package a10;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainApp {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		TestHashTableWithGUI testGui = new TestHashTableWithGUI();
		testGui.startGUI();
		
	}
	
}
