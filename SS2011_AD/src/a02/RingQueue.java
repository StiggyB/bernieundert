package a02;

import java.util.Arrays;

/**
 * Implementierung einer Queue als Ring (RingBuffer) mittels des
 * IQueue Interface.
 * 
 * @author Tugend und Laster
 * @param <T> Typ der Elemente der Queue.
 */
public class RingQueue<T> implements IQueue<T> {

	private T[] elements;
	private int size = 0;
	private int head = 0;
	private int last = 0;

	// suppress unchecked warnings
	// entweder an dieser Stelle oder in jeder Methode, die ein T zurückliefert
	// casten.
	@SuppressWarnings("unchecked")
	public RingQueue(int capacity) {
		this.elements = (T[]) new Object[capacity];
	}

	/**
	 * Die Methode fuegt ein neues Element am Ende der Queue ein. Der Aufwand
	 * beim Einfuegen in die Queue ist immer O(1), da das Einfuegen nicht von
	 * der Groeße der Queue abhaengig ist.
	 * 
	 * @param item Das einzufuegende Objekt
	 */
	@Override
	public void enqueue(T item) throws RuntimeException {
		if (isFull()) {
			throw new RuntimeException("RingBuffer full: overflow");
		}
		elements[last] = item;
		last = (last + 1) % elements.length;
		size++;
	}

	/**
	 * Diese Methode loescht das erste Element in der Queue. Der Aufwand beim
	 * loeschen des ersten Elements ist konstant und steht nicht in
	 * Abhaengigkeit zur Groesse der Queue, daher gilt ein Aufwand von O(1).
	 */
	@Override
	public void dequeue() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("RingBuffer empty: underflow");
		}
		// nach Ablauf der Methode wird das zurückgelieferte
		// Element dem Garbagecollector geopfert
		elements[head] = null;
		head = (head + 1) % elements.length;
		size--;
	}

	/**
	 * Diese Methode liefert das erste Element der Queue (head).
	 * Der Aufwand ist konstant und nicht abhaengig von der Queuegroesse.
	 * Es gilt ein Aufwand von O(1).
	 * 
	 * @return Liefert das erste Element (head) aus der Queue
	 */
	@Override
	public T front() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("RingBuffer empty: underflow");
		}
		return elements[head];

	}

	/**
	 * Liefert <b>true</b>, wenn die Queue voll ist, andernfalls <b>false</b>
	 * Der Aufwand ist konstant, da nur eine Integervariable abgefragt und 
	 * geprueft werden muss. Daher hat die Methode einen Aufwand von O(1).
	 * 
	 * @return Liefert <b>true</b>, wenn die Queue voll ist, andernfalls <b>false</b>
	 */
	public boolean isFull() {
		return size == elements.length;
	}

	/**
	 * Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 * Der Aufwand ist konstant, da nur eine Integervariable abgefragt und 
	 * geprueft werden muss. Daher hat die Methode einen Aufwand von O(1).
	 * 
	 * @return Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Liefert die Anzahl der in der Queue befindlichen Elemente.
	 * Da nur eine Integervariable zurueckgegeben wird, gilt ein Aufwand von
	 * O(1).
	 * 
	 * @return Anzahl der in der Queue befindlichen Elemente
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Diese Methode gibt zu Testzwecken die Elemente in der Queue als String zurueck
	 * 
	 * @return Elemente der Queue als String
	 */
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
}
