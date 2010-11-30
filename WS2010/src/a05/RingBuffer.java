package a05;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Generische Klasse für einen RingBuffer aus einem Array.
 * 
 */

public class RingBuffer<T> implements IRingBuffer<T> {
	private T[] elements;
	private int size = 0;
	private int head = 0;
	private int last = 0;

	// suppress unchecked warnings
	@SuppressWarnings("unchecked")
	public RingBuffer(int capacity) {
		this.elements = (T[]) new Object[capacity];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == elements.length;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T item) throws RuntimeException {
		if (isFull()) {
			throw new RuntimeException("RingBuffer full: overflow");
		}
		elements[last] = item;
		last = (last + 1) % elements.length;
		size++;
	}

	@Override
	public T dequeue() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("RingBuffer empty: underflow");
		}
		T item = elements[head];
		//nach Ablauf der Methode wird das zurückgelieferte
		//Element dem Garbagecollector geopfert
		elements[head] = null;
		head = (head + 1) % elements.length;
		size--;
		return item;
	}

	@Override
	public T peek() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("RingBuffer empty: underflow");
		}
//		T item = elements[head];
		return elements[head];

	}

}
