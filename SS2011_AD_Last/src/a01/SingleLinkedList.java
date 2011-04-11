package a01;

/**
 * 
 * 
 * @author Administrator
 *
 * @param <E>
 */

//TODO 

public class SingleLinkedList<E> implements IList<E> {	
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	
	public SingleLinkedList() {	
		this.head = new Node<E>(null, null);
		this.tail = new Node<E>(this.head, null);
		this.head.next = this.tail;
		this.size = 0;
	}
	
	
	
	/**
	 * @see a01.IList#insert(a01.Node, java.lang.Object)
	 */
	public void insert2(Node<E> targetNode, E e) {
		Node<E> newNode = new Node<E>(targetNode.next.next, e);
		targetNode.next.next = newNode;
		if(targetNode.next == this.tail) {
			this.tail.next = newNode;
		}
		size++;
	}
	
	/** Why no function with the constructor?
	 * 
	 * @see a01.IList#insert(a01.Node, java.lang.Object)
	 */
	@Override
	public void insert(Node<E> pos, E element) {
		Node<E> newNode = new Node<E>(null, null);// 1
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
	
	/**
	 * @see a01.IList#append(java.lang.Object)
	 */
	@Override
	public void append(E e) {
		insert(this.tail, e);	
	}
	
	/**
	 * @see a01.IList#delete(a01.Node)
	 */
	@Override
	public void delete(Node<E> targetNode) {
		targetNode.next = targetNode.next.next;
		if(targetNode.next == this.tail) {
			this.tail.next = targetNode;
		}
		size--;
	}
	
	/**
	 * find mit Stopper-Element tail.data = e
	 * 
	 * @see a01.IList#find(java.lang.Object)
	 */
	@Override
	public Node<E> find(E e) {
		this.tail.data = e;
		Node<E> tmp = this.head.next;
		//Wichtig durch antizipative ind. ref-1
		for(;!(e.equals(tmp.next.data)); tmp = tmp.next);
		//if tmp.next == tail -> return tail - otherwise no anti ind.
//		if(tmp.next == this.tail) {
//			tmp = this.tail;
//		}
		return tmp;
	}

	
	/**
	 * Referenz oder neues Objekt zurückgeben?
	 * @see a01.IList#retrieve(a01.Node)
	 */
	@Override
	public E retrieve(Node<E> targetNode) {	
		E result;
		if(targetNode == null) {
			result = null;
		} else {
			//Wichtig durch antizipative ind. ref-1
			result = targetNode.next.data;
		}
		return result;
	}
	
	/** 
	 * @see a01.IList#concat(a01.IList)
	 */
	@Override
	public void concat(IList<E> list) {
		if(list instanceof SingleLinkedList<?>) {
			this.tail.next.next = list.getHead().next;
		}
		
	}


	/**
	 * @see a01.IList#getHead()
	 */
	@Override
	public Node<E> getHead() {
		return this.head;
	}
	
	/**
	 * @see a01.IList#getTail()
	 */
	@Override
	public Node<E> getTail() {
		return this.tail;
	}



	/**
	 * @see a01.IList#size()
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node<E> cur = head.next;
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

