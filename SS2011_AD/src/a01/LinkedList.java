package a01;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T> implements a01.List<T> {

	class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
		}
	}

	private Node head = new Node(null);
	private Node tail = new Node(null);

	private int size = 0;

	public LinkedList() {
		head.next = tail;
		tail.next = head;
	}

	@Override
	public void insert(Node pos, T element) throws IndexOutOfBoundsException {
		Node newNode = new Node();
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node find(T element) throws IndexOutOfBoundsException {
		tail.data = element;
		Node pos = head;
		do {
			pos = pos.next;
		} while (pos.data != element);
		return pos;
	}

	@Override
	public void delete(Node pos) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub

	}

	@Override
	public T retrieve(Node pos) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void concat(a01.List<T> list) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
