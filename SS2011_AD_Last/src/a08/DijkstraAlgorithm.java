package a08;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse enthaelt die Implementierung des Dijkstra-Algorithmus. Die
 * Implementierung funktioniert fuer beide Implementierungen des IGraph
 * Interface (ListGraph und MatrixsGraph)
 * 
 * @author Tugend und Laster
 */
public class DijkstraAlgorithm implements IDijkstra{

	/**
	 * Diese Methode berechnet fuer einen gegebenen Graphen den kuerzesten Pfad
	 * von einem Startknoten zu allen anderen Knoten
	 * 
	 * @param graph der Graph muss das IGraph Interface implementieren 
	 * 		  (ListGraph, MatrixGraph)
	 * @param startNode Knoten von wo aus der Algorithmus gestartet werden soll
	 * @return Node-Array, die den Nachbarknoten enthalten, fuer den
	 *         guenstigsten Weg zum Ziel
	 */
	public int[] getShortestPath(IGraph graph, int startNode) {
		CostNode[] costNodeArr = initCostNodes(graph);
		int[] shortestPath = new int[costNodeArr.length];
		List<CostNode> boundarySet = new ArrayList<CostNode>();
		builtBoundarySet(graph, startNode, costNodeArr, boundarySet);
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

	/**
	 * Hilfsmethode: Initialisierung des Startknoten und des Kostenknoten-Arrays
	 * 
	 * @param graph MatrixGraph oder ListGraph
	 * @return initialisiertes Kostenknoten-Array
	 */
	private CostNode[] initCostNodes(IGraph graph) {
		CostNode[] costNodeArr = new CostNode[graph.getOrder()];
		CostNode cn = new CostNode(0, null, 0, true);
		cn.pred = cn;
		costNodeArr[0] = cn;
		for (int i = 1; i < costNodeArr.length; i++) {
			costNodeArr[i] = new CostNode(i, null, Integer.MAX_VALUE, false);
		}
		return costNodeArr;
	}

	/**
	 * Hilfsmethode: Berechnung und Erweiterung der Randmenge eines Knoten
	 * @param graph MatrixGraph oder ListGraph
	 * @param startNode Knoten fuer den die Randmenge gebildet werden soll
	 * @param costNodeArr Kostenknoten-Array
	 * @param boundarySet derzeitige Randmenge als Liste
	 */
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

	/**
	 * Hilfsmethode: Erweitert die Randmenge um einen neuen Knoten, es wird 
	 * vorm Hinzufuegen geprueft, ob der Knoten bereits enthalten ist. Keine
	 * doppelten Knoten!
	 * 
	 * @param graph ListGraph oder MatrixGraph
	 * @param costNodeArr Kostenknoten-Array
	 * @param boundarySet Randmenge als Liste
	 * @param nextNode zu pruefender Knoten, fuer den die Randmenge erweitert
	 * 		  werden soll.
	 */
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

	/**
	 * Hilfsmethode: Knoten aus der Randmenge ermitteln, der die geringsten Kosten
	 * haelt.
	 * 
	 * @param boundarySet Randmenge als Liste
	 * @return Knoten mit den geringstens Kosten aus der Randmenge
	 */
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
