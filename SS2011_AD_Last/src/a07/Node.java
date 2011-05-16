package a07;

public class Node<E> {

	E key;
	Node<E> parent;
	Node<E> left;
	Node<E>	right;
	
	public Node(E key, Node<E> parent, Node<E> left, Node<E> right) {
		this.key = key;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
}
