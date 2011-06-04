package a08;

public class ListBenchmark extends ListGraph {

	int ops;

	@Override
	protected void getAdjacenciesFor(Node node, int[] adjancencyArr, int i) {
		ops++;
		super.getAdjacenciesFor(node, adjancencyArr, i);
	}

	@Override
	protected void getWeightsFor(Node node, int[] weightArr, int i) {
		ops++;
		super.getWeightsFor(node, weightArr, i);
	}


	@Override
	protected int getHeightFor(int height, Edge edge) {
		ops++;
		return super.getHeightFor(height, edge);
	}
}
