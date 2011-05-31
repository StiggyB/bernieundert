package a08;

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
