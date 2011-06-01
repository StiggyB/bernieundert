package a08;

import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm {

	int [] getShortestPath(IGraph graph, int startNode) {
		CostNode[] costNodeArr = initCostNodes(graph);
		int[] shortestPath = new int[costNodeArr.length];
		List<CostNode> boundarySet = new ArrayList<CostNode>();
		builtBoundarySet(graph, startNode, costNodeArr, boundarySet);
		Helper.printArr(boundarySet);
		int nextNode = 0;
		int pathIdx = 0;
		shortestPath[pathIdx++] = nextNode;
		while (!(boundarySet.isEmpty())) {
			nextNode = getLowestCost(boundarySet);
			addNewNode(graph, costNodeArr, boundarySet, nextNode);
			shortestPath[pathIdx++] = nextNode;
		}
		return shortestPath;
	}

	public CostNode[] initCostNodes(IGraph graph) {
		CostNode[] costNodeArr = new CostNode[graph.getOrder()];
		CostNode cn = new CostNode(0, null, 0, true);
		cn.pred = cn;
		costNodeArr[0] = cn;
		for (int i = 1; i < costNodeArr.length; i++) {
			costNodeArr[i] = new CostNode(i, null, Integer.MAX_VALUE, false);
		}
		return costNodeArr;
	}

	private void builtBoundarySet(IGraph graph, int startNode,
			CostNode[] costNodeArr, List<CostNode> boundarySet) {
		int[] adjacencyArr = graph.getAdjacencies(startNode);
		int[] costArr = graph.getWeights(startNode);
		for (int i = 0; i < adjacencyArr.length; i++) {
			// init previous
			costNodeArr[adjacencyArr[i]].pred = costNodeArr[startNode];
			// init costs
			costNodeArr[adjacencyArr[i]].cost = costArr[i];
			// init the set of adjacencies
			boundarySet.add(costNodeArr[adjacencyArr[i]]);
		}
	}

	private void addNewNode(IGraph graph, CostNode[] costNodeArr,
			List<CostNode> boundarySet, int nextNode) {
		int[] adjacencyArr = graph.getAdjacencies(nextNode);
		int[] costArr = graph.getWeights(nextNode);
		for (int i = 0; i < adjacencyArr.length; i++) {
			if (!(costNodeArr[adjacencyArr[i]].marked)) {
				if (costNodeArr[adjacencyArr[i]].cost > costNodeArr[nextNode].cost
						+ costArr[i]) {
					if ((boundarySet.contains(costNodeArr[adjacencyArr[i]]))) {
						boundarySet.remove(costNodeArr[adjacencyArr[i]]);
					}
					costNodeArr[adjacencyArr[i]].cost = costNodeArr[nextNode].cost
							+ costArr[i];
					costNodeArr[adjacencyArr[i]].pred = costNodeArr[nextNode];
					boundarySet.add(costNodeArr[adjacencyArr[i]]);
				}
			}
		}
	}

	private int getLowestCost(List<CostNode> boundarySet) {
		int minCost = boundarySet.get(0).cost;
		int costNodeArrIdx = 0;
		for (int i = 1; i < boundarySet.size() && boundarySet.size() != 0; i++) {
			if (boundarySet.get(i).cost < minCost) {
				minCost = boundarySet.get(i).cost;
				costNodeArrIdx = i;
			}
		}
		boundarySet.get(costNodeArrIdx).marked = true;
		int nextNode = boundarySet.get(costNodeArrIdx).data;
		boundarySet.remove(costNodeArrIdx);
		return nextNode;
	}
}
