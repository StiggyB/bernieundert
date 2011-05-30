package a02;

import java.util.NoSuchElementException;

import a01.IList;
import a01.SingleLinkedList;

/**
 * @author Administrator
 *
 * @param <T>
 */
public class Stack<T> implements IStack<T> {

	IList<T> stack;

	/**
	 * @see a02.IStack#create()
	 */
	@Override
	public void create() {
		this.stack = new SingleLinkedList<T>();
	}

	/**
	 * @see a02.IStack#push(java.lang.Object)
	 */
	@Override
	public void push(T elem) {
		this.stack.append(elem);
	}

	/**
	 * @see a02.IStack#pop()
	 */
	@Override
	public void pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack empty: underflow");
		}
		this.stack.delete(this.stack.getTail());
	}

	/**
	 * @see a02.IStack#top()
	 */
	@Override
	public T top() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack empty: underflow");
		}
		return this.stack.retrieve(this.stack.getHead());
	}

	/**
	 * @see a02.IStack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	@Override
	public String toString() {
		return stack.toString();
	}
	
	
}
