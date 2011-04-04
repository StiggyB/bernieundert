package a01;

/**
 * 
 * @author <Not Known>
 * 
 *         Implementierung einer einfach verketteten Liste mittels
 *         antizipativer Indizierung. Implementierung des Interface List
 *         aus selbigem package.
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
		tail.data = element; //1
		Node<T> pos = head; //1
		Benchmark.ops += 4;
		//TODO: 4 = die ersten 2ops + die 2 aus while header 
		while (pos.next.data != element)  {//2
			pos = pos.next;//1
			//TODO: 3 = die 2 aus while header + 1 
			Benchmark.ops +=3;
			
		}
		return pos;
	}
	
	@Override
	public void insert(Node<T> pos, T element) {
		Node<T> newNode = new Node<T>();//1
		newNode.data = element;//1
		newNode.next = pos.next;//2
		pos.next = newNode;//1
		Benchmark.ops +=6;
		if (newNode.next == tail) {
			tail.next = newNode;
			Benchmark.ops++;
		}
		size++;
	}

	@Override
	public void insert(T element) {
		insert(tail.next, element);//1
		Benchmark.ops++;
	}


	@Override
	public void delete(Node<T> pos) throws IndexOutOfBoundsException {
		Node<T> nextNode = pos.next;//1
		pos.next = nextNode.next;//2
		nextNode.next = null;//1
		Benchmark.ops +=4;
		size--;
	}

	@Override
	public T retrieve(Node<T> pos) throws Exception {
		Benchmark.ops++;
		return pos.next.data;//1
	}

	@Override
	public void concat(List<T> list) throws Exception {
		if (list instanceof LinkedList<?>) {//2
			LinkedList<T> ll = (LinkedList<T>) list;//1
			tail.next.next = ll.head.next;//2
			ll.tail.next.next = tail; //3
			tail.next = ll.tail.next; //2
			ll.head.next = null; // 2
			ll.tail.next = null; //2
			size += ll.size; //1
			Benchmark.ops += 15;
			
		} else {
			throw new IllegalStateException();
			//TODO: hier noch n bench gelöt?!
		}
	}

	@Override
	public boolean isEmpty() {
		Benchmark.ops++;
		return size == 0;
	}

	@Override
	public int size() {
		Benchmark.ops++;
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
