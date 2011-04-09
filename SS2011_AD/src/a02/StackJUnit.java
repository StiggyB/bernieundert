package a02;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Testklasse: Testet die Implementierung des Stack
 * 
 * @author Tugend und Laster
 */
public class StackJUnit {

	private Stack<Integer> stack;

	@Before
	public void setUp() throws Exception {

		stack = new Stack<Integer>();
	}

	@Test
	public void testPushAndTop() {
		int i = 1;
		stack.push(i);
		assertFalse(stack.isEmpty());
		int j = stack.top();
		assertEquals(i, j);

	}

	@Test
	public void testPushAndTopWithMore() {
		int i = 1;
		int j = 2;
		int k = 3;

		stack.push(i);
		stack.push(j);
		stack.push(k);

		assertFalse(stack.isEmpty());

		int l = stack.top();
		assertEquals(k, l);
		assertFalse(k == j);
		stack.pop();
		l = stack.top();
		assertEquals(l, j);

	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyStackExceptionWithPop() {
		stack.pop();
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyStackExceptionWithTop() {
		int i = stack.top();
		stack.push(i);
	}

}
