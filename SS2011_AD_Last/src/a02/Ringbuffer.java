package a02;

/**
 * @author Administrator
 *
 * @param <T>
 */
public class Ringbuffer<T> implements IQueue<T> {

	private int head;
	private int tail;
	private int size;
	private T[] buffer;
	
	/**
	 * @param size
	 */
	@SuppressWarnings ("unchecked")
	public Ringbuffer(int size) {		
		this.size = size;
		this.buffer = (T[])(new Object[size+1]);
		this.tail = size;
		this.head = tail;
	}
	
	
	/**
	 * @see a02.IQueue#front()
	 */
	@Override
	public T front() {
		if(isEmpty()) {
			throw new RuntimeException ("Ringbuffer empty: underflow"); 
		}
		return buffer[head];
	}

	
	/**
	 * @see a02.IQueue#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(T element) {
		if(isFull()) {
			throw new RuntimeException ("Ringbuffer full: overflow");
		}
		this.buffer[tail] = element;
		tail = (tail - 1) % buffer.length;
	}

	/**
	 * @see a02.IQueue#dequeue()
	 */
	@Override
	public void dequeue() {
		if(isEmpty()) {
			throw new RuntimeException ("Ringbuffer empty: underflow");
		}	
		this.buffer[head] = null;
		head = (head -1) % buffer.length;
	}

	/**
	 * @see a02.IQueue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.head == this.tail;
	}

	/**
	 * @return
	 */
	public boolean isFull() {
		return tail == 0;
	}


	@Override
	public int size() {
		return Math.abs(this.tail- this.head);
	}

}
