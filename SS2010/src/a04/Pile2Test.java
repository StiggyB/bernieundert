package a04;

import static org.junit.Assert.*;

import org.junit.Test;

public class Pile2Test {
	IPile myHeap = new Pile2(3);

	@Test
	public void testLeft() {
		assertEquals(1, myHeap.leftChild(2));	
		assertEquals(2, myHeap.leftChild(4));	
	}

	@Test
	public void testRight() {
		assertEquals(0, myHeap.rightChild(3));	
		assertEquals(3, myHeap.rightChild(2));	
	}

	@Test
	public void testNeighbour() {
		assertEquals(3, myHeap.sibling(1));	
		assertEquals(6, myHeap.sibling(2));	
		assertEquals(1, myHeap.sibling(3));	
		assertEquals(0, myHeap.sibling(4));	
	}

	@Test
	public void testParent() {
		assertEquals(2, myHeap.parent(3));	
		
	}


}
