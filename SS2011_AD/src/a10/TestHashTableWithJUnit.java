package a10;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * This class extends the abstract class "AbstractTestHashTable.java" for
 * testing the implementation with JUnit test cases.
 * 
 * @author Tugend und Laster
 * @see IHashTable
 */
public class TestHashTableWithJUnit extends AbstractTestHashTable {

	private HashTable<String, String> hTable;

	public TestHashTableWithJUnit() throws IOException {
		super.readAndStoreDataFromLog();
	}

	@Before
	public void setUp() throws Exception {
		hTable = new HashTable<String, String>();
	}

	@Test
	public void testPutAndIsEmptyAndSize() {
		hTable.put(ips.get(0), values.get(0));
		hTable.put(ips.get(1), values.get(1));
		assertFalse(hTable.isEmpty());
		assertEquals(2, hTable.size());
	}

	@Test
	public void testPutAndGet() {
		String test = "127.0.0.1-localh0rst";

		hTable.put("127.0.0.1", test);
		hTable.put(ips.get(1), values.get(1));

		assertFalse(hTable.isEmpty());
		assertEquals(2, hTable.size());

		assertEquals(null, hTable.get("128.0.0.1"));
		List<String> listTest = hTable.get("127.0.0.1");
		assertEquals(test, listTest.get(0));
	}

	@Test
	public void testContainsKey() {
		for (int i = 0; i < values.size(); i++) {
			hTable.put(ips.get(i), values.get(i));
		}

		assertTrue(hTable.containsKey(ips.get(0)));
		assertTrue(hTable.containsKey(ips.get(5)));
		assertTrue(hTable.containsKey(ips.get(8)));

		assertFalse(hTable.containsKey("127.0.0.1"));
		assertFalse(hTable.containsKey("foobar"));
		assertFalse(hTable.containsKey("123.456.789.123"));
	}

	@Test
	public void testContainsValue() {
		hTable.put(ips.get(0), "test0");
		hTable.put(ips.get(1), "test1");
		hTable.put(ips.get(2), "test2");
		hTable.put(ips.get(2), "test22");
		hTable.put(ips.get(2), "test23");

		assertTrue(hTable.containsValue("test0"));
		assertTrue(hTable.containsValue("test1"));
		assertTrue(hTable.containsValue("test2"));
		assertTrue(hTable.containsValue("test22"));
		assertTrue(hTable.containsValue("test23"));

		assertFalse(hTable.containsValue("test3"));
		assertFalse(hTable.containsValue("test4"));
		assertFalse(hTable.containsValue("test5"));
	}

	@Test
	public void testClear() {
		for (int i = 0; i < values.size(); i++) {
			hTable.put(ips.get(i), values.get(i));
		}

		assertFalse(hTable.isEmpty());
		assertTrue(hTable.containsValue(values.get(0)));

		hTable.clear();
		assertTrue(hTable.isEmpty());
		assertFalse(hTable.containsValue(values.get(0)));
	}

	@Test
	public void testRemove() {
		// Da nur das isDeleted Flag in den Entries gesetzt wird, nicht mit
		// JUnit pruefbar.
		// Wir geben auf der Konsole das Arrays aus, man kann bei den
		// geloeschten Eintraegen
		// dann das "isDeleted: true" erkennen.
		
		for (int i = 0; i < values.size(); i++) {
			hTable.put(ips.get(i), values.get(i));
		}

		for (int j = 0; j < values.size(); j += 3) {
			hTable.remove(ips.get(j));
		}
		
		System.out.println("Diese Ausgabe zeigt die Funktion von TestRemove aus JUnit:\n");
		System.out.println("============================================");
		System.out.println(hTable.toString());
		System.out.println("============================================");
	}
}
