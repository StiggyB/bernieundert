package a04;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Testklasse mit JUnit Tests für die Implemetierungen
 *         des Deques. Klasse Deque wurde "Test-Driven" mittels
 *         der JUnit-Testfälle hier erstellt.
 * 
 */

import org.junit.Assert;
import org.junit.Test;

import a01.LinkedList;

public class DequeTest {

	
	private Deque<String> list;

	public DequeTest() {
		list = new Deque<String>(new LinkedList<String>());
	}
	
	@Test
	public void addLastElement() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.addLast("last");
		Assert.assertEquals("last", list.get(list.size() - 1));
	}
	
	@Test
	public void poll() {
		list.add("1");
		list.add("2");
		list.add("3");
		Assert.assertEquals("1", list.pollFirst());
		Assert.assertEquals("3", list.pollLast());
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void remove() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.removeFirst();
		Assert.assertEquals("2", list.peekFirst());
		list.removeLast();
		Assert.assertEquals("2", list.peekFirst());
		Assert.assertEquals(1, list.size());
	}
	
	
}
