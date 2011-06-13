package a10;

import java.io.IOException;

import org.junit.Before;

public class TestHashTableWithJUnit extends AbstractTestHashTable{
	private HashTable<String, String> hTable;
	
	public TestHashTableWithJUnit() throws IOException {
		super.readAndStoreDataFromLog();
	}

	@Before
	public void setUp() throws Exception {
		 hTable = new HashTable<String, String>();
	}

}
