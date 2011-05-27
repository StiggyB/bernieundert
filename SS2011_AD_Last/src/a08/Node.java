package a08;

import java.util.List;

public class Node {

	int data;
	List<Edge> adjacencys;
	
	public Node(int data, List<Edge> adjacencys) {
		super();
		this.data = data;
		this.adjacencys = adjacencys;
	}
}
