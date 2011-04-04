package a01;

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
		tail.data = element;
		Node<T> pos = head;
		while (pos.next.data != element)  {//2
			pos = pos.next;//1
		}
		return pos;
	}
	
	@Override
	public void insert(Node<T> pos, T element) {
		Node<T> newNode = new Node<T>();
		newNode.data = element;
		newNode.next = pos.next;
		pos.next = newNode;
		if (newNode.next == tail) {
			tail.next = newNode;
		}
		size++;
	}

	@Override
	public void insert(T element) {
		insert(tail.next, element);
	}


	@Override
	public void delete(Node<T> pos) throws IndexOutOfBoundsException {
		Node<T> nextNode = pos.next;
		pos.next = nextNode.next;
		nextNode.next = null;
		size--;
	}

	@Override
	public T retrieve(Node<T> pos) throws Exception {
		return pos.next.data;
	}

	@Override
	public void concat(List<T> list) throws Exception {
		if (list instanceof LinkedList<?>) {
			LinkedList<T> ll = (LinkedList<T>) list;
			tail.next.next = ll.head.next;
			ll.tail.next.next = tail; //3
			tail.next = ll.tail.next; //2
			ll.head.next = null; // 2
			ll.tail.next = null; //2
			size += ll.size; //1
			Benchmark.ops += 7;
			
		} else {
			throw new IllegalStateException();
		}
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
