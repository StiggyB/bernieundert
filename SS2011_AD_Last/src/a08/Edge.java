package a08;

/**
 * Interface für die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
public class Edge {

	Node node;
	int weight;
	
	public Edge(Node node, int weight) {
		super();
		this.node = node;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("nachbar: " + node.toString() + " ");
		sb.append("kosten: " + weight);
		
		return sb.toString();
	}
}
