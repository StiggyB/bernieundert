package a05;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Testklasse zum Testen unserer RingBuffer
 *         Implementierung mit JUnit.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RingBufferJUnit {

	private RingBuffer<Integer> primitive;
	//Integer sind bereits Objekttypen, man kann aber primitive Typen
	//reinwerfen (int, char), autoboxing/unboxing

	//Dieses Beispiel sparen wir uns daher :)
	// private RingBuffer<String> objective; 

	@Before
	public void setUp() throws Exception {

		primitive = new RingBuffer<Integer>(5);
		// objective = new RingBuffer<String>(10);
	}

	@Test
	public void testEnqueue() {
		int i = 1;
		primitive.enqueue(i);
		assertEquals(1, primitive.size());

	}

	@Test
	public void testDequeue() {
		int i = 5;
		primitive.enqueue(i);
		int j = primitive.dequeue();
		assertEquals(0, primitive.size());
		assertEquals(i, j);

	}

	@Test(expected = RuntimeException.class)
	public void testUnderflowException() {
		primitive.dequeue();
	}

	@Test(expected = RuntimeException.class)
	public void testOverflowException() {
		for (int i = 0; i < 6; i++) {
			primitive.enqueue(i);
		}
	}

	@Test
	public void testIsFull() {
		assertTrue(primitive.isEmpty());
		for (int i = 0; i < 4; i++) {
			primitive.enqueue(i);
		}
		assertFalse(primitive.isFull());

		primitive.enqueue(1);
		assertTrue(primitive.isFull());
	}

	@Test
	public void testPeek() {
		primitive.enqueue(1);
		int i = primitive.peek();
		assertEquals(1, i);
		assertEquals(1, primitive.size());
	}
}
