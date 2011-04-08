package a02.queue;

import java.util.LinkedList;
import java.util.List;


public class ListQueue<T> implements IQueue<T> {

	List<T> list = new LinkedList<T>();
	
	@Override
	public T front() {
		return list.get(0);
	}

	@Override
	public void enqueue(T element) {
		list.add(element);
	}

	@Override
	public void dequeue() {
		list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
