package a10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class TestHashTable {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		
		List<Integer> keys = new ArrayList<Integer>();
		List<String> values = new ArrayList<String>();
		List<String> ips = new ArrayList<String>();
		Scanner sc = new Scanner(new File("./src/a10/apache.txt"));
		
		String[] nextLine = null;
		String[] keyString = null;
		int key = 0;
		
		while (sc.hasNextLine()) {
			nextLine = sc.nextLine().trim().split(" - - ");
			ips.add(nextLine[0]);
			keyString = nextLine[0].split("[.]");
			key = Integer.valueOf(keyString[0] + keyString[3]);
			
			keys.add(key);
			values.add(nextLine[0]  + " - " +  nextLine[1]);
		}
		
		HashTable<Integer, String> hTable = new HashTable<Integer, String>();
		/**
//		System.out.println(hTable.hash(17));
		hTable.put(1, "a1");
		hTable.put(1, "a2");
		hTable.put(2, "b");
		hTable.put(3, "c");
		hTable.put(4, "d");
		hTable.put(5, "e");
		hTable.put(17, "f1");
		hTable.put(17, "f2");
		hTable.put(18, "achtzehn");
		hTable.put(19, "neunzehn");
		**/
		for (int i = 0; i < values.size(); i++) {
			hTable.put(keys.get(i), values.get(i));
		}
		
//		System.out.println(hTable.containsKey(3));
//		System.out.println(hTable.containsKey(7));
//		System.out.println(hTable.containsValue("c"));
//		System.out.println(hTable.containsValue("g"));
//		System.out.println(hTable.toString());
//		hTable.clear();
		System.out.println(hTable.toString());
		
		String s = hTable.remove(1947);
		System.out.println("Removed: " + s);
		System.out.println(hTable.toString());
		
		Object[] test = hTable.get(95251);
		System.out.println(test.toString());
		
		
		
//		new MainGUI(hTable, keys, ips).startGUI();
	}
	
}