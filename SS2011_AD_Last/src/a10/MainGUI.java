package a10;

import java.util.List;

import javax.swing.UIManager;


public class MainGUI<K, V>{
	
	private HashTable<Integer, V> hashTable;
	private List<Integer> keys;
	
	public MainGUI(HashTable<Integer, V> hashTable, List<Integer> keys) {
		this.hashTable = hashTable;
		this.keys = keys;
	}
	
	ExplorerTree<K, V> ept = new ExplorerTree<K, V>(hashTable, keys);
	ept.buildFrame();
}
