package a04;

import static org.junit.Assert.*;

import org.junit.Test;

public class Pile1Test {
	IPile myHeap = new Pile1(4);

	@Test
	public void testLeft() {
		assertEquals(4, myHeap.leftChild(2));	
	}

	@Test
	public void testRight() {
		assertEquals(7, myHeap.rightChild(3));	
	}

	@Test
	public void testNeighbour() {
		assertEquals(7, myHeap.sibling(6));	
		assertEquals(6, myHeap.sibling(7));	
		assertEquals(2, myHeap.sibling(3));	
		assertEquals(3, myHeap.sibling(2));	
	}

	@Test
	public void testParent() {
		assertEquals(1, myHeap.parent(3));	
	}

}
