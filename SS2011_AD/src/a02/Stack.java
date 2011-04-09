package a02;

import java.util.LinkedList;

public class Stack<T> implements IStack<T> {

	LinkedList<T> list = new LinkedList<T>();
	
	
	@Override
	public void push(T element) {
		list.add(element);
	}

	@Override
	public void pop() {
		list.removeLast();
	}

	@Override
	public T top() {
		return list.getLast();
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

}
