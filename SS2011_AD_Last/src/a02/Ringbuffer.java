package a02;

public class Ringbuffer<T> implements IQueue<T> {

	private int head;
	private int tail;
	private int size;
	private T[] buffer;
	
	
	public Ringbuffer(int size) {		
		this.size = size;
		this.buffer = (T[])(new Object[size+1]);
		this.tail = size;
		this.head = tail;
	}
	
	
	@Override
	public T front() {
		if(isEmpty()) {
			throw new RuntimeException ("Ringbuffer empty: underflow"); 
		}
		return buffer[head];
	}

	@Override
	public void enqueue(T element) {
		//Throw Exp
		if(!(isFull())) {
			this.buffer[tail] = element;
			tail--;
		}
	}

	@Override
	public void dequeue() {
		if(!(isEmpty())) {
			this.buffer[head] = null;
			head--;
		}	
	}

	@Override
	public boolean isEmpty() {
		return this.head == this.tail;
	}

	@Override
	public boolean isFull() {
		return buffer.length == size+1;
	}

}
