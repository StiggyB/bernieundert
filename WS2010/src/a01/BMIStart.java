package a01;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse, welche unseren BMI Rechner startet und das Look&Feel des Systems setzt
 *         für das GUI.
 * 
 */

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BMIStart {
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		BMIFrame bmicalc = new BMIFrame();
		bmicalc.buildFrame();
	}

}
