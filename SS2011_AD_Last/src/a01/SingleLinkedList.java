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
	public void insert(Node<E> targetNode, E e) {
		Node<E> newNode = new Node<E>(targetNode.next.next, e);
		targetNode.next.next = newNode;
		if(newNode.next == this.tail) {
			this.tail.next = newNode;
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
		Node<E> tmp = this.head;
		//Durch antizipative ind. ref-1
		for(;!(e.equals(tmp.next.data)); tmp = tmp.next);
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
			System.out.println("null");
		} else {
			//Durch antizipative ind. ref-1
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
	
	/**
	 * @see a01.IList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.head.next.equals(this.tail);
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

