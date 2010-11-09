package a04;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Implementierung einer Double Ended Queue mittels
 *         unserer beiden Listen aus a01 und a02
 * 
 */

import a01.Liste;

public class Deque<T> implements Liste<T> {

	private Liste<T> list;
	
	public Deque(Liste<T> list) {
		this.list = list;
	}
	
	public void addFirst(T element) {
		list.add(0, element);
	}
	
	public void addLast(T element) {
		list.add(list.size(), element);
	}
	
	public T pollFirst() {
		T element = list.get(0);
		removeFirst();
		return element;
	}
	
	public T pollLast() {
		T element = list.get(list.size() - 1);
		removeLast();
		return element;
	}
	
	public void removeFirst() {
		list.remove(0);
	}
	
	public void removeLast() {
		list.remove(list.size() - 1);
	}
	
	public T peekFirst() {
		return list.get(0);
	}
	
	public T peekLast() {
		return list.get(list.size() - 1);
	}
	
	//////////////////////////////////
	@Override
	public void add(int pos, T element) throws IndexOutOfBoundsException {
		list.add(pos, element);
	}

	@Override
	public void add(T element) {
		list.add(element);
	}

	@Override
	public T get(int pos) throws IndexOutOfBoundsException {
		return list.get(pos);
	}


	@Override
	public void remove(int pos) throws IndexOutOfBoundsException {
		list.remove(pos);
	}

	@Override
	public void remove(T element) throws Exception {
		list.remove(element);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public int size() {
		return list.size();
	}
}
