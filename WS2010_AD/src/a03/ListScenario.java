package a03;

import a01.Liste;


public class ListScenario {

	private static final int ITERATIONS = 50000;

	public static void main(String[] args) throws Exception {
		
		doBenchmark("java.util.LinkedList", new ListeAdapter<String>(new java.util.LinkedList<String>()));
		doBenchmark("java.util.ArrayList", new ListeAdapter<String>(new java.util.ArrayList<String>()));
		doBenchmark("java.util.concurrent.CopyOnWriteArrayList", new ListeAdapter<String>(new java.util.concurrent.CopyOnWriteArrayList<String>()));
		doBenchmark("a01.LinkedList", new a01.LinkedList<String>());
		doBenchmark("a02.ArrayList", new a02.ArrayList<String>());
		
	}
	
	private static void doBenchmark(String name, Liste<String> list) throws Exception {
		System.out.println("******************************************");
		System.out.println("Test für Liste: " + name);
		
		// String vorher erzeugen, soll nicht das Meßergebnis verfälschen
		String testString = "abc";
		long start, end, startMemory, endMemory;
		
		/*
		 * add(Element)
		 */
		start = System.currentTimeMillis();
		startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		for (int i = 0; i < ITERATIONS; i++) {
			list.add(testString);
		}
		endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		end = System.currentTimeMillis();
		System.out.println("add(Element): Zeit: " + (end - start) + "ms , Speicher: " + (endMemory - startMemory) + " byte");
		
		/*
		 * get(pos)
		 */
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			list.get(i);
		}
		end = System.currentTimeMillis();
		System.out.println("get(pos): Zeit: " + (end - start) + "ms");
		
		/*
		 * remove(abc)
		 * Wichtiger Unterschied zwischen "unserem" remove(Element) und dem von Java: Unserer entferne ALLE, die Implementierung von Java nur das erste gefundene
		 */
		start = System.currentTimeMillis();
		list.remove("abc");
		end = System.currentTimeMillis();
		System.out.println("remove(\"abc\"): Zeit: " + (end - start) + "ms");
		
		/*
		 * remove(pos)
		 * immer remove(0), da alles nach vorne rückt und es, bis die Liste leer ist, immer ein Element auf Position 0 gibt.
		 */
		
		// wieder befüllen, weil sie bei remove(abc) geleert wurde
		for (int i = 0; i < ITERATIONS; i++) {
			list.add(testString);
		}
		
		start = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			list.remove(0);
		}
		end = System.currentTimeMillis();
		System.out.println("remove(pos): Zeit: " + (end - start) + "ms");
		
	}
	
}
