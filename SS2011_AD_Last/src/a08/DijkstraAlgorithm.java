package a08;

import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm {


	CostNode[] getShortestPath(IGraph graph, int startNode) {
		CostNode[] costNodeArr = initCostNodes(graph);
		List<CostNode> boundarySet = new ArrayList<CostNode>();
		Helper.printArr(costNodeArr);
		// TODO implement getLowestWeight(int nodeIdx) in IGraph
		builtBoundarySet(graph, startNode, costNodeArr, boundarySet);
		Helper.printArr(boundarySet);
		int nextNode = 0;
		while (!(boundarySet.isEmpty())) {
			nextNode = getLowestCost(boundarySet);
			addNewNode(graph, costNodeArr, boundarySet, nextNode);
		}
		//TODO implement list of marked nodes (CostNode or int)
		return null;
	}

	private void addNewNode(IGraph graph, CostNode[] costNodeArr,
			List<CostNode> boundarySet, int nextNode) {
		 System.out.println(nextNode + " - actual node");
		int[] adjacencyArr = graph.getAdjacencies(nextNode);
		// Helper.printArr(adjacencyArr);
		int[] costArr = graph.getWeights(nextNode);
		// Helper.printArr(costArr);
		// Helper.printArr(boundarySet);
		for (int i = 0; i < adjacencyArr.length; i++) {

			if (!(costNodeArr[adjacencyArr[i]].marked)) {

				if (costNodeArr[adjacencyArr[i]].cost > costNodeArr[nextNode].cost
						+ costArr[i]) {
					if((boundarySet.contains(costNodeArr[adjacencyArr[i]]))) {
////					boundarySet.add((boundarySet.get(adjacencyArr[i]).cost = costArr[i])) ;
////					CostNode tmpNode = boundarySet.get(adjacencyArr[i]);
						boundarySet.remove(costNodeArr[adjacencyArr[i]]);
					}
					costNodeArr[adjacencyArr[i]].cost = costNodeArr[nextNode].cost
							+ costArr[i];
					costNodeArr[adjacencyArr[i]].pred = costNodeArr[nextNode];
					// init the set of adj.
					boundarySet.add(costNodeArr[adjacencyArr[i]]);
				}
			}
		}
		Helper.printArr(boundarySet);
	}

	private void builtBoundarySet(IGraph graph, int startNode,
			CostNode[] costNodeArr, List<CostNode> boundarySet) {

		int[] adjacencyArr = graph.getAdjacencies(startNode);
		// Helper.printArr(adjacencyArr);
		int[] costArr = graph.getWeights(startNode);
		// Helper.printArr(costArr);

		for (int i = 0; i < adjacencyArr.length; i++) {
			// init previous
			costNodeArr[adjacencyArr[i]].pred = costNodeArr[startNode];
			costNodeArr[adjacencyArr[i]].cost = costArr[i]; // init costs
			// init the set of adj.
			boundarySet.add(costNodeArr[adjacencyArr[i]]);
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
		// Helper.printArr(boundarySet);
		// Helper.printArr(costNodeArr);
		boundarySet.get(costNodeArrIdx).marked = true;
		int nextNode = boundarySet.get(costNodeArrIdx).data;
		boundarySet.remove(costNodeArrIdx);
		// Helper.printArr(costNodeArr);
		// Helper.printArr(boundarySet);
		return nextNode;
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
}
