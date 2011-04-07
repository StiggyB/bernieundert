package a01;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestSignleLinkedList {

	/**
	 * correct implementation?
	 */
	@Test
	public void testImplementation() {
		SignleLinkedList<String> slList = new SignleLinkedList<String>();
		//assertEquals("initialisation failed", "initialized", slList.toString());
		assertEquals(slList.getHead().next, slList.getTail());
		assertEquals(slList.getTail().next, slList.getHead());
		assertTrue(true);
		
	}
	
	@Test
	public void testInsert() {
		
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testFind() {
		
	}
	
	@Test
	public void testRetrieve() {
		
	}
	
	@Test
	public void testConcat() {
		
	}
	
	@Test
	public void insertAndRetrieve() {
		
	}
}
