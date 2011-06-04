package a08;

/**
 * Interface f�r die Implementierung einer Queue nach dem TI3-AD_Script. Das
 * Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
public class MatrixBenchmark extends MatrixGraph implements IDijkstra {

	int ops;
	DijkstraAlgorithm da;
	
	public MatrixBenchmark(DijkstraAlgorithm da) {
		this.da = da;
	}

	@Override
	public int[] getShortestPath(IGraph graph, int startNode) {
		return da.getShortestPath(graph, startNode);
	}
	

	@Override
	protected int getAdjecenciesFor(int nodeIdx, int[] adjacencyIndexArr,
			int adjacencyIdx, int i) {
		ops++;
		return super.getAdjecenciesFor(nodeIdx, adjacencyIndexArr,
				adjacencyIdx, i);
	}

	@Override
	protected int getWeightsFor(int nodeIdx, int[] weightArr, int weightIdx,
			int i) {
		ops++;
		return super.getWeightsFor(nodeIdx, weightArr, weightIdx, i);
	}

	@Override
	protected int getCountOfAdjacenciesForNodeFor(int[] arr, int count, int i) {
		ops++;
		return super.getCountOfAdjacenciesForNodeFor(arr, count, i);
	}

	@Override
	protected int getHeightFor(int height, int i, int j) {
		ops++;
		return super.getHeightFor(height, i, j);
	}
}
