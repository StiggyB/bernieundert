package a02;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainApp {
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SudokoFrame sudukoframe = new SudokoFrame();
		sudukoframe.buildFrame();
		
	}

}