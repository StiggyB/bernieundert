package a02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Testklasse: Testet die Implementierung der beiden
 * Queue Varianten ListQueue und RingQueue
 * 
 * @author Tugend und Laster
 */
public class QueueJUnit {

	//Immer nur eine Implementierung zur Zeit testen, bitte!
	private Ringbuffer<Integer> queue;
//	private ListQueue<Integer> queue;

	// Integer sind bereits Objekttypen, man kann aber primitive Typen
	// reinwerfen (int, char), autoboxing/unboxing

	// Dieses Beispiel sparen wir uns daher :)
	// private RingQueue<String> objective;

	@Before
	public void setUp() throws Exception {

		//Immer nur eine Implementierung zur Zeit testen, bitte!
		queue = new Ringbuffer<Integer>(5);
//		queue = new ListQueue<Integer>();
		// objective = new RingQueue<String>(10);
	}

	@Test
	public void testEnqueueAndSize() {
		int i = 1;
		queue.enqueue(i);
		assertEquals(1, queue.size());

	}

	@Test
	public void testDequeueAndSize() {
		int i = 5;
		queue.enqueue(i);
		int j = queue.front();
		assertEquals(1, queue.size());
		queue.dequeue();
		assertEquals(0, queue.size());
		assertEquals(i, j);

	}

	@Test(expected = RuntimeException.class)
	public void testUnderflowException() {
		queue.dequeue();
	}

	//isFull() nicht in der Listenversion implementiert.
	//Weiterhin kann es bei der Listenimplementierung keinen
	//Overflow geben. Daher bitte beim Testen mit der ListQueue 
	//diese beiden Tests auskommentieren!
//	/*
	@Test(expected = RuntimeException.class)
	public void testOverflowException() {
		for (int i = 0; i < 6; i++) {
			queue.enqueue(i);
		}
	}

	
	@Test
	public void testIsFull() {
		assertTrue(queue.isEmpty());
		for (int i = 0; i < 4; i++) {
			queue.enqueue(i);
		}
		assertFalse(queue.isFull());

		queue.enqueue(1);
		assertTrue(queue.isFull());
	}
//	*/

}
