package a05;

// suppress unchecked warnings in Java 1.5.0_6 and later
public class RingBuffer<T> implements IRingBuffer<T> {
	private T[] elements; // queue elements
	private int size = 0; // number of elements on queue
	private int head = 0; // index of first element of queue
	private int last = 0; // index of next available slot

	// cast needed since no generic array creation in Java
	@SuppressWarnings("unchecked")
	public RingBuffer(int capacity) {
		this.elements = (T[]) new Object[capacity];
	}

	/* (non-Javadoc)
	 * @see a05.IRingBuffer#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/* (non-Javadoc)
	 * @see a05.IRingBuffer#isFull()
	 */
	@Override
	public boolean isFull() {
		return size == elements.length;
	}

	/* (non-Javadoc)
	 * @see a05.IRingBuffer#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see a05.IRingBuffer#enqueue(T)
	 */
	@Override
	public void enqueue(T item) {
		if (isFull()) {
			throw new RuntimeException("Ring buffer overflow");
		}
		elements[last] = item;
		last = (last + 1) % elements.length; // wrap-around
		size++;
	}

	// remove the least recently added item - doesn't check for underflow
	/* (non-Javadoc)
	 * @see a05.IRingBuffer#dequeue()
	 */
	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		}
		T item = elements[head];
		elements[head] = null; // to help with garbage collection
		size--;
		head = (head + 1) % elements.length; // wrap-around
		return item;
	}

	/* (non-Javadoc)
	 * @see a05.IRingBuffer#peek()
	 */
	@Override
	public T peek() {

		if (isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		}
		T item = elements[head];
		return item;

	}

}
