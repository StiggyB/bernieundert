package a10;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainApp {

	public static void main(String args[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ChristmasTreePattern ctp = new ChristmasTreePattern();
		ChristmasFrame cf = new ChristmasFrame(ctp);
		cf.buildFrame();

	}

}
