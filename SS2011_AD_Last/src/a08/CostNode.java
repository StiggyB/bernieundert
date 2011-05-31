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
		sb.append(data + ", ");
		if (pred == null) {
			sb.append("null" + ", ");
		} else if (!(pred.equals(this))) {
			sb.append(pred.toString() + ", ");
		} else {
			sb.append(this.data + ", ");
		}
		sb.append(cost + ", ");
		sb.append(marked);
		return sb.toString();
	}
}
