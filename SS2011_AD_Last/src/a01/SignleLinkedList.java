package a01;

/**
 * 
 * 
 * @author Administrator
 *
 * @param <E>
 */

//TODO 

public class SignleLinkedList<E> implements IList<E> {	
	
	private Node<E> head;
	private Node<E> tail;	
	//private int counter; //Auslagern
	
	
	public SignleLinkedList() {	
		this.tail = new Node<E>(null, null);
		this.head = new Node<E>(this.tail, null);
		this.tail.next = this.head;
//		this.counter = 0;
	}
	
	
	
	/**
	 * @see a01.IList#insert(a01.Node, java.lang.Object)
	 */
	@Override
	public void insert(Node<E> targetNode, E e) {

		Node<E> newNode = new Node<E>(targetNode, e);
			
		if(targetNode == null || this.head.next == this.tail) {
			this.head.next = newNode;
			newNode.next = this.tail;
			this.tail.next = newNode;
		} else {
			if(targetNode.next == this.tail) {
				this.tail.next = newNode;
			}
			newNode.next = targetNode.next.next;
			targetNode.next.next = newNode;
		}
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
		for(;e != tmp.next.data; tmp = tmp.next);
		//if tmp.next == tail -> return tail - otherwise no anti ind.
		if(tmp.next == this.tail) {
			tmp = this.tail;
		}
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
		//Woher referenz auf den head der list?
		this.tail.next = list.getHead().next;
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
	
}

