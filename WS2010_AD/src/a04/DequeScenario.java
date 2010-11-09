package a04;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse zum Testen unserer eigenen Deque-Implementierung
 *         im Vergleich zu denen aus dem JDK.
 * 
 */

import java.util.ArrayDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class DequeScenario {

	private static final int ITERATIONS = 50000;
	
	public static void main(String[] args) throws Exception {
		doBenchmark("a04.Deque mit a01.LinkedList", new a04.Deque<String>(new a01.LinkedList<String>()));
		doBenchmark("a04.Deque mit a02.ArrayList", new a04.Deque<String>(new a02.ArrayList<String>()));
		
		doBenchmark("LinkedBlockingDeque", new LinkedBlockingDeque<String>());
		doBenchmark("ArrayDeque", new ArrayDeque<String>());
	}
	

	private static void doBenchmark(String name, a04.Deque<String> deque) throws Exception {
		System.out.println("******************************************");
		System.out.println("Test für Liste: " + name);
		
		// String vorher erzeugen, soll nicht das Meßergebnis verfälschen
		String testString = "abc";
		long start, end;
		
		/*
		 * addFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.addFirst(testString);
		}
		end = System.currentTimeMillis();
		System.out.println("addFirst(Element): Zeit: " + (end - start) + "ms");
		
		/*
		 * peekFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.peekFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("peekFirst(): Zeit: " + (end - start) + "ms");
		
		/*
		 * removeFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.removeFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("removeFirst(): Zeit: " + (end - start) + "ms");
		
		// wieder befüllen, weil sie bei removeFirst(Element) geleert wurde
		for (int i = 0; i < ITERATIONS; i++) {
			deque.add(testString);
		}
		
		/*
		 * pollFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.pollFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("pollFirst(): Zeit: " + (end - start) + "ms");
		
		
		/*
		 * addLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.addLast(testString);
		}
		end = System.currentTimeMillis();
		System.out.println("addLast(Element): Zeit: " + (end - start) + "ms");
		
		/*
		 * peekLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.peekLast();
		}
		end = System.currentTimeMillis();
		System.out.println("peekLast(): Zeit: " + (end - start) + "ms");
		
		/*
		 * removeLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.removeLast();
		}
		end = System.currentTimeMillis();
		System.out.println("removeLast(): Zeit: " + (end - start) + "ms");
		
		// wieder befüllen, weil sie bei removeFirst(Element) geleert wurde
		for (int i = 0; i < ITERATIONS; i++) {
			deque.add(testString);
		}
		
		/*
		 * pollLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.pollFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("pollLast(): Zeit: " + (end - start) + "ms");
		
	}
	
	private static void doBenchmark(String name, java.util.Deque<String> deque) throws Exception {
		System.out.println("******************************************");
		System.out.println("Test für Liste: " + name);
		
		// String vorher erzeugen, soll nicht das Meßergebnis verfälschen
		String testString = "abc";
		long start, end;
		
		/*
		 * addFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.addFirst(testString);
		}
		end = System.currentTimeMillis();
		System.out.println("addFirst(Element): Zeit: " + (end - start) + "ms");
		
		/*
		 * peekFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.peekFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("peekFirst(): Zeit: " + (end - start) + "ms");
		
		/*
		 * removeFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.removeFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("removeFirst(): Zeit: " + (end - start) + "ms");
		
		// wieder befüllen, weil sie bei removeFirst(Element) geleert wurde
		for (int i = 0; i < ITERATIONS; i++) {
			deque.add(testString);
		}
		
		/*
		 * pollFirst(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.pollFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("pollFirst(): Zeit: " + (end - start) + "ms");
		
		
		/*
		 * addLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.addLast(testString);
		}
		end = System.currentTimeMillis();
		System.out.println("addLast(Element): Zeit: " + (end - start) + "ms");
		
		/*
		 * peekLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.peekLast();
		}
		end = System.currentTimeMillis();
		System.out.println("peekLast(): Zeit: " + (end - start) + "ms");
		
		/*
		 * removeLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.removeLast();
		}
		end = System.currentTimeMillis();
		System.out.println("removeLast(): Zeit: " + (end - start) + "ms");
		
		// wieder befüllen, weil sie bei removeFirst(Element) geleert wurde
		for (int i = 0; i < ITERATIONS; i++) {
			deque.add(testString);
		}
		
		/*
		 * pollLast(Element)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			deque.pollFirst();
		}
		end = System.currentTimeMillis();
		System.out.println("pollLast(): Zeit: " + (end - start) + "ms");
		
	}
	
}
