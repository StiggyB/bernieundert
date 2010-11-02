package a01;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Implementierung einer doppelt verketteten Liste mittels
 *         des vorgegebenen Interface Liste<E>
 * 
 */

import java.util.NoSuchElementException;

public class LinkedList<T> implements Liste<T> {

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	private Node head = new Node(null, null, null);
	private Node tail = new Node(null, null, null);
	private int size = 0;

	public LinkedList() {
		head.next = tail;
		tail.prev = head;
	}

	@Override
	public void add(int pos, T element) throws IndexOutOfBoundsException {
		if (pos > size || pos < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node newNode = new Node(element, null, null);
		Node currentNode = null;
		if (pos <= size / 2) {
			currentNode = head.next;
			for (int i = 0; i < pos; i++) {
				currentNode = currentNode.next;
			}
		} else {
			currentNode = tail;
			for (int i = size - 1; i >= pos; i--) {
				currentNode = currentNode.prev;
			}
		}
		// [head] <-> [0] <-> [1] <-> [2] <-> [tail]
		// einfügen in pos: 1
		// [head] <-> [0] <-> [new1] <-> [1] <-> [2] <-> [tail]
		newNode.prev = currentNode.prev;
		newNode.next = currentNode;
		currentNode.prev.next = newNode;
		currentNode.prev = newNode;
		size++;
	}

	@Override
	public void add(T element) {
		// add(size, element);
		// [head] <-> [tail]
		// einfügen in pos: $size
		// [head] <-> [new1] <-> [tail]
		Node newNode = new Node(element, null, null);
		newNode.prev = tail.prev;
		newNode.next = tail;
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
	}

	@Override
	public T get(int pos) throws IndexOutOfBoundsException {
		if (pos >= size || pos < 0) {
			throw new IndexOutOfBoundsException();
		}

		Node currentNode = getNodeOnPosition(pos);
		return currentNode.data;
	}

	@Override
	public void remove(int pos) throws IndexOutOfBoundsException {
		if (pos >= size || pos < 0) {
			throw new IndexOutOfBoundsException();
		}

		Node currentNode = getNodeOnPosition(pos);
		currentNode.prev.next = currentNode.next;
		currentNode.next.prev = currentNode.prev;
		size--;
	}

	@Override
	public void remove(T element) throws Exception {
		boolean found = false;
		Node currentNode = head.next;

		while (currentNode != tail) {
			if (currentNode.data == element) {
				// [head] <-> [0] <-> [1] <-> [2] <-> [tail]
				// entfernen von [1]
				// [head] <-> [0] <-> [2] <-> [tail]
				currentNode.prev.next = currentNode.next;
				currentNode.next.prev = currentNode.prev;
				size--;
				found = true;
			}
			currentNode = currentNode.next;
		}

		if (!found) {
			throw new NoSuchElementException();
		}

	}

	private Node getNodeOnPosition(int pos) {
		Node currentNode = null;
		if (pos <= size / 2) {
			currentNode = head.next;
			for (int i = 0; i < pos; i++) {
				currentNode = currentNode.next;
			}
		} else {
			currentNode = tail;
			for (int i = size; i > pos; i--) {
				currentNode = currentNode.prev;
			}
		}
		return currentNode;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("[");
		Node currentNode = head.next;
		while (currentNode.next != null) {
			buf.append(currentNode.data);
			buf.append(", ");
			currentNode = currentNode.next;
		}
		buf.setLength(buf.length() - 2);
		buf.append("]");
		return buf.toString();
	}
}
