package a02;

import java.util.Arrays;


public class RingQueue<T> implements IQueue<T> {
	
	private T[] elements;
	private int size = 0;
	private int head = 0;
	private int last = 0;

	// suppress unchecked warnings
	@SuppressWarnings("unchecked")
	public RingQueue(int capacity) {
		this.elements = (T[]) new Object[capacity];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == elements.length;
	}

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

	@Override
	public T front() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("RingBuffer empty: underflow");
		}
		// T item = elements[head];
		return elements[head];

	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}

}
