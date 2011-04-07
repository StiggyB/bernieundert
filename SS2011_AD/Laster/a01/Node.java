package a01;

/**
 * @author Administrator
 *
 * @param <E>
 */
public class Node<E> {
	
	Node<E> next;
	E data;
	
	public Node(Node<E> next, E data) {
		this.next = next;
		this.data = data;
	}
}
