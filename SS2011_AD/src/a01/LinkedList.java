package a01;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         Implementierung einer einfach verketteten Liste mittels antizipativer
 *         Indizierung. Implementierung des Interface List aus selbigem package.
 * 
 *         TODO: pos.next = tmp; // sind das 2 oder ein op
 * 
 */

public class LinkedList<T> implements List<T> {

	Node<T> head = new Node<T>();
	Node<T> tail = new Node<T>();

	private int size = 0;

	public LinkedList() {
		head.next = tail;
		tail.next = head;
	}

	@Override
	public Node<T> find(T element) {
		tail.data = element; // 1
		Node<T> pos = head; // 1
		Benchmark.ops += 5;
		while (!(pos.next.data.equals(element))) {// 2
			pos = pos.next;// 1
			Benchmark.ops += 3;
		}
		return pos;
	}

	@Override
	public void insert(Node<T> pos, T element) {
		Node<T> newNode = new Node<T>();// 1
		newNode.data = element;// 1
		newNode.next = pos.next.next;// 2
		pos.next.next = newNode;// 2
		Benchmark.ops += 8;
		if (newNode.next == tail) {
			tail.next = newNode;
			Benchmark.ops++;
		}
		size++;
	}

	@Override
	public void insert(T element) {
		insert(tail, element);// 1
		Benchmark.ops++;
	}

	@Override
	public void delete(Node<T> pos) throws IndexOutOfBoundsException {
		pos.next = pos.next.next; // 2
		Benchmark.ops += 4;
		if (pos.next == tail) {
			tail.next = pos;
			Benchmark.ops++;
		}
		size--;
	}

	@Override
	public T retrieve(Node<T> pos) throws Exception {
		Benchmark.ops++;
		return pos.next.data;// 1
	}

	@Override
	public void concat(List<T> list) throws Exception {
		if (list instanceof LinkedList<?>) {// 2
			LinkedList<T> ll = (LinkedList<T>) list;// 2
			tail.next.next = ll.head.next;// 2
			ll.tail.next.next = tail; // 2
			tail.next = ll.tail.next; // 1
			ll.head.next = null; // 1
			ll.tail.next = null; // 1
			size += ll.size; // 1
			Benchmark.ops += 12;

		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public boolean isEmpty() {
		// Benchmark.ops++;
		return size == 0;
	}

	@Override
	public int size() {
		// Benchmark.ops++;
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<T> cur = head.next;
		while (cur != tail) {
			sb.append(cur.data).append(", ");
			cur = cur.next;
		}
		if (sb.length() != 1) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("]");
		return sb.toString();
	}
}
