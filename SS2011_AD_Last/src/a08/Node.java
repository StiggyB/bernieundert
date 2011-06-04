package a08;

import java.util.List;

/**
 * Interface f�r die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
public class Node {

	int data;
	List<Edge> adjacencies;
	
	public Node(int data, List<Edge> adjacencies) {
		super();
		this.data = data;
		this.adjacencies = adjacencies;
	}
	
	@Override
	public String toString() {
		return data+"";
	}
}
