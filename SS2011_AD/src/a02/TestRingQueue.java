package a02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class TestRingQueue {
	
	private static final int RUNS = 50;
	private static final int EVICT_AFTER_RUNS = 5;

	public static void main(String[] args) {
		
		List<IQueue<Integer>> list = new ArrayList<IQueue<Integer>>(21);
		for (int i = 0; i < 21; i++) {
			list.add(null);
		}
		
		Random random = new Random();
		
		for (int i = 0; i < RUNS; i++) {
			int key = random.nextInt(20) + 1;
			int value = random.nextInt(100);
			
			IQueue<Integer> queue = list.get(key);
			if (queue == null) {
//				queue = new RingQueue<Integer>(10);
				queue = new ListQueue<Integer>();
				list.set(key, queue);
				System.out.println("Setting new Queue: " + key);
			}
			queue.enqueue(value);
			System.out.println("Setting Value " + value + " to Queue: " + key);
			
			if (i % EVICT_AFTER_RUNS == 0) {
				int queueNrToEvict = random.nextInt(20) + 1;
				IQueue<Integer> queueToEvict = list.get(queueNrToEvict);
				if (queueToEvict != null) {
					int nrOfElementsToEvict = random.nextInt(10);
					for (int j = 0; j < nrOfElementsToEvict && !queueToEvict.isEmpty(); j++) {
						queueToEvict.dequeue();
						System.out.println("Evicting from queue: " + queueToEvict);
					}
					if (queueToEvict.isEmpty()) {
						// Liste shiftet bei remove() nach links 
						list.set(queueNrToEvict, null);
						System.out.println("Removing Queue: " + queueNrToEvict);
					}
				}
			}
			
		}
		System.out.println(list);
	}
}
