package a10;

import java.util.HashMap;
import java.util.Hashtable;


public class MainApp {

	public static void main(String[] args) {
		
		Hashtable<Integer, String> testTable = new Hashtable<Integer, String>();
		testTable.put(0, "a");
		testTable.remove(0);
		HashMap<Integer, String> testMap = new HashMap<Integer, String>();
		testMap.put(0, "a");
		testMap.remove(0);
	}
	
}
