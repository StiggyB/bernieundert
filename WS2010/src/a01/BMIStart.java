package a01;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BMIStart {
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		BMIFrame bmicalc = new BMIFrame();
		bmicalc.buildFrame();
	}

}
