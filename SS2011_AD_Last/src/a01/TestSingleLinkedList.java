package a01;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         Diese Klasse testet die Implementierung und Operationen
 *         einer einfach verketteten Liste (antizipativ).
 *         
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSingleLinkedList {

	LinkedList<String> list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<String>();
	}

	@Test
	public void addElementFindAndGetElement() throws Exception {
		list.append("a");
		assertFalse(list.isEmpty());

		Node<String> node = list.find("a");
		// assertEquals("a", list.retrieve(list.tail));
		// assertEquals("a", list.retrieve(list.head));
		assertEquals("a", list.retrieve(node));
	}

	@Test
	public void testSearchWithStopElement() throws Exception {
		list.append("a");
		list.append("b");
		assertFalse(list.isEmpty());

		Node<String> node = list.find("x");
		assertEquals("x", list.retrieve(node));
	}

	@Test
	public void addOnSpecificPosition() throws Exception {
		list.append("a");
		list.append("b");
		list.append("d");
		assertFalse(list.isEmpty());

		Node<String> node = list.find("b");
		list.insert(node, "c");
		System.out.println(list);
	}

	@Test
	public void addManyElementsAndGetElement() throws Exception {
		list.append("bla1");
		list.append("bla2");
		list.append("bla3");
		list.append("bla4");
		list.append("bla5");

		Node<String> node1 = list.find("bla1");
		Node<String> node2 = list.find("bla2");
		Node<String> node3 = list.find("bla3");
		Node<String> node4 = list.find("bla4");
		Node<String> node5 = list.find("bla5");

		assertEquals(5, list.size());
		assertEquals("bla1", list.retrieve(node1));
		assertEquals("bla2", list.retrieve(node2));
		assertEquals("bla3", list.retrieve(node3));
		assertEquals("bla4", list.retrieve(node4));
		assertEquals("bla5", list.retrieve(node5));
	}

	@Test
	public void addElementOnPosition() throws Exception {
		list.append("bla");
		assertFalse(list.isEmpty());
		Node<String> node = list.find("bla");
		assertEquals("bla", list.retrieve(node));

		for (int i = 0; i < 10; i++) {
			list.append("bla" + i);
		}
		Node<String> insertHere1 = list.find("bla8");
		list.insert(insertHere1, "bla8New");
		Node<String> node2 = list.find("bla8New");
		assertEquals("bla8New", list.retrieve(node2));
		Node<String> insertHere2 = list.find("bla2");
		list.insert(insertHere2, "bla2New");
		Node<String> node3 = list.find("bla2New");
		assertEquals("bla2New", list.retrieve(node3));
		assertEquals(13, list.size());
	}

	@Test(expected = Exception.class)
	public void getNotContainedElement() throws Exception {
		Node<String> node = new Node<String>();
		list.retrieve(node);
	}

	@Test(expected = Exception.class)
	public void addElementOnImpossiblePosition() throws Exception {
		list.append("a");

		Node<String> node = list.find("a");
		assertEquals("a", list.retrieve(node));
		Node<String> newNode = new Node<String>();
		list.insert(newNode, "bla");
	}

	@Test(expected = NullPointerException.class)
	public void removeNonExistentElement() throws Exception {
		list.delete(null);
	}

	@Test(expected = NullPointerException.class)
	public void removeAnotherNonExistentElement() throws Exception {
		Node<String> newNode = new Node<String>();
		list.delete(newNode);
	}

	@Test
	public void emptyList() {
		assertTrue(list.isEmpty());
	}

	@Test
	public void emptyListTwo() {
		assertTrue(list.head.next == list.tail);
		assertTrue(list.head == list.tail.next);
	}

	@Test
	public void deleteElement() {
		list.append("a");
		list.append("b");
		list.append("c");
		list.append("d");
		list.append("e");
		list.append("f");

		Node<String> node1 = list.find("b");
		Node<String> node2 = list.find("f");
		list.delete(node1);
		System.out.println(list);
		list.delete(node2);
		System.out.println(list);
		assertTrue(list.tail.next.data.equals("e"));
	}
}
