package a00;
/**
 * JUnit Testfälle für die Klasse Counter
 * @author Bernd Kahlbrandt
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class CounterTest {

	@Test
	public void testCounter() {
	Counter counter = new Counter();
	assertTrue(counter.show()==0);
	}

	@Test
	public void testCounterInt() {
		Counter counter = new Counter(42);
		assertTrue(counter.show()==42);
	}

	@Test
	public void testIncrement() {
		Counter counter = new Counter();
		for(int i=0;i<10;i++){
			counter.increment();
			assertTrue(counter.show()==i+1);
		}
	}

	@Test
	public void testDecrement() {
		Counter counter = new Counter(10);
		for(int i=0;i<10;i++){
			counter.decrement();
			assertEquals(10-i-1,counter.show());
		}
	}

	@Test
	public void testReset() {
		Counter counter = new Counter(42);
		assertTrue(counter.show()==42);
		counter.reset();
		assertTrue(counter.show()==0);
	}
	@Test
	public void testShow() {
		this.testIncrement();
		this.testDecrement();
	}

}
