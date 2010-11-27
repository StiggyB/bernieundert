package a08;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse, welche unseren ObjectExplorer startet und das Look&Feel des Systems setzt
 *         für das GUI. 
 *         
 * @version 1.0        
 * 
 */

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainApp {

	/**
	 * main-Methode zum Starten des Objektbrowsers
	 * 
	 * @param args Übergabeparameter an das Programm
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IOException
	 */
	public static void main(String args[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		ExplorerTree ept = new ExplorerTree();
		
		ept.buildFrame();
		
	}

}