package a02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasse fuer das geforderte Beispiel einer Testroutine. Simuliert werden
 * Druckerwarteschlangen, die mit Auftraegen gefuellt werden koennen.
 * Die Konstante RUNS steht fuer die Anzahl an Durchlaeufen, die 
 * simuliert werden.
 * Die Konstante EVICT_AFTER_RUNS legt fest, nach wievielen Duechlaeufen
 * Elemente aus den Queues und ggf. Queues geloescht werden sollen.
 * 
 * @author Tugend und Laster
 */
public class PrinterQueue {

	private static final int RUNS = 50;
	private static final int EVICT_AFTER_RUNS = 5;

	public static void main(String[] args) {

		List<IQueue<Integer>> list = new ArrayList<IQueue<Integer>>(21);
		for (int i = 0; i < 21; i++) {
			list.add(null);
		}

		Random random = new Random();

		for (int i = 0; i < RUNS; i++) {
			// key wird als Synonym fuer das q (Queue) aus der Aufgabenstellung
			// verwendet
			int key = random.nextInt(20) + 1;
			// value wird als Synonym fuer das p (Printer) aus der
			// Aufgabenstellung verwendet
			int value = random.nextInt(100);

			IQueue<Integer> queue = list.get(key);
			// Wenn es noch keine entsprechende Queue gibt, neu anlegen
			if (queue == null) {
				// Hier kann entschieden werden, welche Implementierung genutzt
				// werden soll.
				queue = new RingQueue<Integer>(10);
//				queue = new ListQueue<Integer>();
				list.set(key, queue);
				System.out.println("Setting new Queue: " + key);
			}
			// Wenn es die Queue schon gibt, nur noch value einfuegen
			queue.enqueue(value);
			System.out.println("Setting Value " + value + " to Queue: " + key);

			// Alle i % EVICT_AFTER_RUNS Durchlaeufe zufaellig "Auftraege" aus
			// den "Queues"
			// loeschen
			if (i % EVICT_AFTER_RUNS == 0) {
				int queueNrToEvict = random.nextInt(20) + 1;
				
				IQueue<Integer> queueToEvict = list.get(queueNrToEvict);
				
				if (queueToEvict != null) {
					int nrOfElementsToEvict = random.nextInt(10);
					for (int j = 0; j < nrOfElementsToEvict && !queueToEvict.isEmpty(); j++) {
						queueToEvict.dequeue();
						System.out.println("Evicting from queue: " + queueNrToEvict);
					}
					if (queueToEvict.isEmpty()) {
						// Liste shiftet bei remove() nach links, daher null setzen!
						list.set(queueNrToEvict, null);
						System.out.println("Removing Queue: " + queueNrToEvict);
					}
				}
			}

		}
		System.out.println("Ende des Durchlaufs:");
		System.out.println("====================");
		System.out.println("\nInhalt der Queues:");
		for (IQueue<Integer> l : list) {
			System.out.println(l);
			
		}
	}
}
