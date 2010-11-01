package a01;


import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import a02.ArrayList;

public class LinkedListTest {

	private Liste<String> list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<String>();
		list = new ArrayList<String>();
	}
	
	@Test
	public void addElementAndGetElement() {
		list.add("bla");
		assertFalse(list.isEmpty());
		assertEquals("bla", list.get(0));
	}
	
	@Test
	public void addManyElementsAndGetElement() {
		list.add("bla1");
		list.add("bla2");
		list.add("bla3");
		list.add("bla4");
		list.add("bla5");
		assertEquals(5, list.size());
		assertEquals("bla1", list.get(0));
		assertEquals("bla2", list.get(1));
		assertEquals("bla3", list.get(2));
		assertEquals("bla4", list.get(3));
		assertEquals("bla5", list.get(4));
	}
	
	@Test
	public void addElementOnPosition() {
		list.add(0, "bla");
		assertFalse(list.isEmpty());
		assertEquals("bla", list.get(0));
		
		for (int i = 0; i < 10; i++) {
			list.add("bla" + i);
		}
		list.add(8, "bla8New");
		assertEquals("bla8New", list.get(8));
		list.add(2, "bla2New");
		assertEquals("bla2New", list.get(2));
		assertEquals("bla8New", list.get(9));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getNotContainedElement() {
		list.get(100);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getNotContainedElement0() {
		list.get(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void addElementOnImpossiblePosition() {
		list.add(1, "bla");
	}
	
	@Test
	public void removeElement() throws Exception {
		list.add("bla");
		list.add("bla");
		list.add("blax");
		list.add("blay");
		list.remove("bla");
		assertEquals(2, list.size());
		assertEquals("blax", list.get(0));
		assertEquals("blay", list.get(1));
	}
	
	@Test
	public void removeNullElement() throws Exception {
		list.add(null);
		list.remove(null);
		assertEquals(0, list.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removeElementOnImpossiblePosition() {
		list.remove(0);
	}
	
	@Test
	public void removeElementOnPosition() throws Exception {
		list.add("bla");
		list.add("bla");
		list.add("blax");
		list.add("blay");
		list.remove(1);
		assertEquals(3, list.size());
		assertEquals("bla", list.get(0));
		assertEquals("blax", list.get(1));
		assertEquals("blay", list.get(2));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void removeNonExistentElement() throws Exception {
		list.remove(null);
	}

}
