package a10;

import java.io.IOException;

/**
 * This class extends the abstract class "AbstractTestHashTable.java" for
 * testing the implementation with a GUI. The class ExplorerTree.java was
 * reused from the PR2 course in WS2010.
 * 
 * @author Tugend und Laster
 * @see IHashTable
 */
public class TestHashTableWithGUI extends AbstractTestHashTable {

	public TestHashTableWithGUI() throws IOException {
		super.readAndStoreDataFromLog();
	}

	public void startGUI() throws IOException {
		HashTable<String, String> hTable = new HashTable<String, String>();

		for (int i = 0; i < values.size(); i++) {
			hTable.put(ips.get(i), values.get(i));
		}

		// IPs adden, die es nicht in der HashTable gibt, als Test
		ipsGUI.add("127.0.0.1");
		ipsGUI.add("192.168.0.1");

		new ExplorerTree<String, String>(hTable, ipsGUI).buildFrame();
	}
}