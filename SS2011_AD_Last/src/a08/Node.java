package a08;

import java.util.List;

public class Node {

	int data;
	List<Edge> adjacencies;
	
	public Node(int data, List<Edge> adjacencies) {
		super();
		this.data = data;
		this.adjacencies = adjacencies;
	}
}
