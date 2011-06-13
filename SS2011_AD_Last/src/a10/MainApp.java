package a10;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainApp {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Hashtable<Integer, String> testTable = new Hashtable<Integer, String>();
		testTable.put(0, "a");
		testTable.remove(0);
		HashMap<Integer, String> testMap = new HashMap<Integer, String>();
		testMap.put(0, "a");
		testMap.remove(0);
		List<String> list = new LinkedList<String>();
	}
	
}
