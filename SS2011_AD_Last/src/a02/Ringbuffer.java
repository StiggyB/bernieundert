package a02;

public class Ringbuffer<T> implements IQueue<T> {

	private int head;
	private int tail;
	private int size;
	
	
	public Ringbuffer() {
		this.head = 0;
		this.tail = 0;
		this.size = 0;
	}
	
	
	@Override
	public T front() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dequeue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
