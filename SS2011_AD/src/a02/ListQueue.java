package a02;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementierung einer Queue als Ring (RingBuffer) mittels des IQueue
 * Interface.
 * 
 * @author Tugend und Laster
 * @param <T> Typ der Elemente der Queue.
 */
public class ListQueue<T> implements IQueue<T> {

	// F�r die Implementierung kommt eine LinkedList zum Einsatz. Fuer Hinweise
	// zur Entscheidung bitte die Dokumentation lesen!
	List<T> list = new LinkedList<T>();

	/**
	 * Diese Methode liefert das erste Element der Queue (head). Der Aufwand ist
	 * konstant und nicht abhaengig von der Queuegroesse (Listengroesse). Es gilt 
	 * ein Aufwand von O(1).
	 * 
	 * @return Liefert das erste Element (head) aus der Queue
	 */
	@Override
	public T front() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("ListBuffer empty: underflow");
		}
		return list.get(0);
	}

	/**
	 * Die Methode fuegt ein neues Element am Ende der Queue ein. 
	 * Hier muss nicht wie bei der Array Version der Queue geprueft werden,
	 * ob Platz ist, da das neue Element in die Liste einfach eingehaengt wird.
	 * Daher muss hier keine gesonderte Exception-Behandlung erfolgen.
	 * Anhand der Implementation der LinkedList, die verwendet wurde, kann der
	 * Aufwand ermittelt werden. An den von der LinkedList genutzten Methoden
	 * add() und daraufhin addBefore() erkennt man den konstanten Aufwand (O(1)).
	 * @param element Das einzufuegende Objekt
	 */
	@Override
	public void enqueue(T element) {
		list.add(element);
	}

	/**
	 * Diese Methode loescht das erste Element in der Queue. Der Aufwand beim
	 * loeschen des ersten Elements ist konstant und steht nicht in
	 * Abhaengigkeit zur Groesse der Queue (als Liste), daher gilt ein Aufwand von O(1).
	 */
	@Override
	public void dequeue() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("ListBuffer empty: underflow");
		}
		list.remove(0);
	}

	/**
	 * Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 * Der Aufwand ist konstant, da nur eine Integervariable abgefragt und
	 * geprueft werden muss. Daher hat die Methode einen Aufwand von O(1).
	 * Siehe auch Implementation der isEmpty() Methode in java.util.LinkedList 
	 * 
	 * @return Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Liefert die Anzahl der in der Queue befindlichen Elemente. Da nur eine
	 * Integervariable zurueckgegeben wird, gilt ein Aufwand von O(1).
	 * Siehe auch Implementation der size() Methode in java.util.LinkedList
	 * 
	 * @return Anzahl der in der Queue befindlichen Elemente
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	/**
	 * Diese Methode gibt zu Testzwecken die Elemente in der Queue als String zurueck
	 * 
	 * @return Elemente der Queue als String
	 */
	@Override
	public String toString() {
		return list.toString();
	}
}
