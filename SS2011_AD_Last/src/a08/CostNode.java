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
}
