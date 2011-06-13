package a10;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestHashTableWithJUnit extends AbstractTestHashTable{
	private HashTable<String, String> hTable;
	
	public TestHashTableWithJUnit() throws IOException {
		super.readAndStoreDataFromLog();
	}

	@Before
	public void setUp() throws Exception {
		 hTable = new HashTable<String, String>();
	}

	@Test
	public void testPut(){
		hTable.put(ips.get(0), values.get(0));
		hTable.put(ips.get(0), values.get(0));
		assertFalse(hTable.isEmpty());
		assertEquals(hTable.size(), 2);
	}
}
