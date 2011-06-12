package a10;

import java.io.IOException;
import java.util.List;

public class MainGUI<K, V> {

	private HashTable<Integer, V> hashTable;
	private List<Integer> keys;
	private List<String> ips;

	public MainGUI(HashTable<Integer, V> hashTable, List<Integer> keys, List<String> ips) {
		this.hashTable = hashTable;
		this.keys = keys;
		this.ips = ips;
	}

	public void startGUI() throws IOException {

		ExplorerTree<K, V> ept = new ExplorerTree<K, V>(hashTable, keys, ips);
		ept.buildFrame();
	}
}
