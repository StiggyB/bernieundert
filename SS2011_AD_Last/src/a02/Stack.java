package a02;

import a01.IList;
import a01.SingleLinkedList;

public class Stack<T> implements IStack<T> {

	IList<T> stack;

	@Override
	public void create() {
		this.stack = new SingleLinkedList<T>();
	}

	@Override
	public void push(T elem) {
		this.stack.append(elem);
	}

	@Override
	public void pop() {
		if(isEmpty()) {
			throw new RuntimeException("Stack empty: underflow");
		}
		this.stack.delete(this.stack.getHead());
	}

	@Override
	public T top() {
		if(isEmpty()) {
			throw new RuntimeException("Stack empty: underflow");
		}
		return this.stack.retrieve(this.stack.getHead());
	}

	@Override
	public boolean isEmpty() {
		return this.stack.size() == 0;
	}
	
	
}
