package a02;

public class Node<T> {
	
	Node<T> next;
	T data;
	
	public Node(Node<T> next, T data) {
		this.next = next;
		this.data = data;
	}
}
