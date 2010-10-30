package a05;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Generische Klasse f�r einen RingBuffer aus einem Array.
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
	public void enqueue(T item) {
		if (isFull()) {
			throw new RuntimeException("Ring buffer overflow");
		}
		elements[last] = item;
		last = (last + 1) % elements.length; 
		size++;
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		}
		T item = elements[head];
		elements[head] = null; 
		size--;
		head = (head + 1) % elements.length; 
		return item;
	}

	@Override
	public T peek() {

		if (isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		}
		T item = elements[head];
		return item;

	}

}