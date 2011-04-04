package a01;


import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class ListTest {

	LinkedList<String> list;
	@Before
	public void setUp() throws Exception {
		list = new LinkedList<String>();
	}

	//TODO: mir fiel nix besseres ein, als erst insert, dann neuen node erstellen
	//und darauf n find machen ... bessere idee?
	@Test
	public void addElementFindAndGetElement() throws Exception {
		list.insert("a");
		assertFalse(list.isEmpty());

		Node<String> node = list.find("a");
		assertEquals("a", list.retrieve(node));
	}
	
	@Test
	public void addManyElementsAndGetElement() throws Exception {
		list.insert("bla1");
		list.insert("bla2");
		list.insert("bla3");
		list.insert("bla4");
		list.insert("bla5");
		
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
		list.insert("bla");
		assertFalse(list.isEmpty());
		Node<String> node = list.find("bla");
		assertEquals("bla", list.retrieve(node));
		
		for (int i = 0; i < 10; i++) {
			list.insert("bla" + i);
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
	
	//TODO: ist das so überhaupt korrekt?
	@Test(expected = Exception.class)
	public void getNotContainedElement() throws Exception {
		Node<String> node = new Node<String>();
		list.retrieve(node);
	}
	
	//TODO: das dürfte doch gar nicht gehen oder? die exception fliegt nicht ...
	@Test(expected = Exception.class)
	public void addElementOnImpossiblePosition() throws Exception {
		list.insert("a");

		Node<String> node = list.find("a");
		assertEquals("a", list.retrieve(node));
		Node<String> newNode = new Node<String>();
		list.insert(newNode, "bla");
	}
	
	//TODO: reicht das als test?
	@Test(expected = NullPointerException.class)
	public void removeNonExistentElement() throws Exception {
		list.delete(null);
	}
	
	//TODO: next try
	@Test(expected = NullPointerException.class)
	public void removeAnotherNonExistentElement() throws Exception {
		Node<String> newNode = new Node<String>();
		list.delete(newNode);
	}
	
	//TODO: kann ich das noch anders testen?
	@Test
	public void emptyList() {
		assertTrue(list.isEmpty());
	}
	
}
