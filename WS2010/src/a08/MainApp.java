package a08;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse, welche unseren FileExplorer startet und das Look&Feel des Systems setzt
 *         für das GUI. Ein Objekt der Klasse ExplorerIO wird vorab erzeugt und an bei der Erzeugung
 *         des ExplorerTree Objekts an dessen Konstruktor mit übergeben. So kann damit gearbeitet 
 *         werden und es gibt weniger Querverweise und Abhängigkeiten.
 * 
 */

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainApp {

	public static void main(String args[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, IOException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		ExplorerTree ept = new ExplorerTree();
		ept.buildFrame();
		
	}

}