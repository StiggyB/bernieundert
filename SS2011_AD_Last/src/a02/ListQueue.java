package a02;

import a01.IList;
import a01.SingleLinkedList;

/**
 * @author Administrator
 *
 * @param <T>
 */
public class ListQueue<T> implements IQueue<T> {

	IList<T> list = new SingleLinkedList<T>();
	
	
	/**
	 * @see a02.IQueue#front()
	 */
	@Override
	public T front() {
		if(isEmpty()) {
			throw new RuntimeException ("ListQueue empty: underflow");
		}
		return this.list.retrieve(this.list.getHead());
	}

	/**
	 * @see a02.IQueue#enqueue(java.lang.Object)
	 */
	@Override
	public void enqueue(T element) {
		this.list.append(element);
	}

	/**
	 * @see a02.IQueue#dequeue()
	 */
	@Override
	public void dequeue() {
		if(isEmpty()) {
			throw new RuntimeException ("ListQueue empty: underflow");
		}
		this.list.delete(list.getHead());
	}

	/**
	 * @see a02.IQueue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.list.size() == 0;
	}


	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}


}
