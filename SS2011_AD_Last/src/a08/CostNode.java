package a08;

public class CostNode {

	int data;
	CostNode pred;
	int cost;
	boolean marked;
	
	public CostNode(int data, CostNode pred, int cost, boolean marked) {
		super();
		this.data = data;
		this.pred = pred;
		this.cost = cost;
		this.marked = marked;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("node: " + data + "\t\t");
		if (pred == null) {
			sb.append("vorgaenger: null" + "\t");
		} else if (!(pred.equals(this))) {
			sb.append("vorgaenger: " + pred.data + "\t\t");
		} else {
			sb.append("vorgaenger: " + this.data + "\t\t");
		}
		sb.append("kosten: " + cost + "\t");
		sb.append("fertig: " + marked);
		return sb.toString();
	}
}
