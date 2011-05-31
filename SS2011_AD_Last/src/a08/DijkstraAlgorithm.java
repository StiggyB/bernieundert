package a08;

import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithm {

	
	Node[] getShortestPath2(IGraph g, Node startNode) {
//		 1  Funktion Dijkstra(Graph, Startknoten):
		CostNode[] Q = initCostNodes(g);
		while(Q.length != 0) {
			CostNode u = Q[0];
			for (int i = 1; i < Q.length; i++) {
				if(u.cost < Q[i].cost) {
					Q[0] = null;
				}
			}
		}
//		 1  Funktion Dijkstra(Graph, Startknoten):
//			 2      initialisiere(Graph,Startknoten,abstand[],vorgänger[],Q)
//			 3      solange Q nicht leer:                       // Der eigentliche Algorithmus
//			 4          u := Knoten in Q mit kleinstem Wert in abstand[]
//			 5          entferne u aus Q                                // für u ist der kürzeste Weg nun bestimmt
//			 6          für jeden Nachbarn v von u:
//			 7              falls v in Q:
//			 8                 distanz_update(u,v,abstand[],vorgänger[])   // prüfe Abstand vom Startknoten zu v
//			 9      return vorgänger[]
		
		
		return null;
	}

	
//	2. Bestimme den Rand R, der aus adjazenten Knoten zu σ besteht.
//	3. while R != ∅ do
//	• w¨ahle ν ∈ R so dass ν.cost minimal, und entferne ν aus R
//	• ν.marked = true
//	• erg¨anze Rand R bei ν
//	Beim Erg¨anzen des Randes R in einem Knoten ν muss folgendes gemacht werden:
//	1. w¨ahle adjazente Knoten κ von ν die nicht markiert sind.
//	2. f¨ur jeden dieser Knoten κ finde heraus, ob
//	κ.cost > ν.cost + c(ν, κ).
//	Wenn dem so ist, dann setze
//	κ.cost = ν.cost + c(ν, κ)
//	und
//	κ.pred = ν.	
	
	Node[] getShortestPath(IGraph graph, int startNode) {
		CostNode[] costNodeArr = initCostNodes(graph);
		List<CostNode> costArrTmp = new ArrayList<CostNode>();
		Helper.printArr(costArrTmp);
		
		int [] adjacencyArr = graph.getAdjacencies(startNode);
		Helper.printArr(adjacencyArr);
		int [] costArr = graph.getWeights(startNode);
		Helper.printArr(costArr);
		//TODO implement getLowestWeight(int nodeIdx) in IGraph
		
		for (int i = 0; i < adjacencyArr.length; i++) {
			costNodeArr[adjacencyArr[i]].pred = costNodeArr[startNode];	//init the previous
			costNodeArr[adjacencyArr[i]].cost = costArr[i];				//init costs
			costArrTmp.add(costNodeArr[adjacencyArr[i]]);				//init the set of adj.
		}
		int minCost = costArrTmp.get(0).cost;
		int costNodeArrIdx = 0;
		for(int i = 1; i < costArrTmp.size() && costArrTmp.size() != 0; i++) {
			if(costArrTmp.get(i).cost < minCost) {
				costNodeArrIdx = i;
			}
		}
		costArrTmp.get(costNodeArrIdx).marked = true; //could be wrong!
		costArrTmp.remove(costNodeArrIdx);
//		
//		adjacencyArr = graph.getAdjacencys(costNodeArrIdx/*false*/);
//		costArr = graph.getWeights(startNode);
//		
//		for (int i = 0; i < adjacencyArr.length; i++) {
//			costNodeArr[adjacencyArr[i]].pred = costNodeArr[startNode];	//init the previous
//			costNodeArr[adjacencyArr[i]].cost = costArr[i];				//init costs
//			costArrTmp.add(costNodeArr[adjacencyArr[i]]);				//init the set of adj.
//		}
//		minCost = costArrTmp.get(0).cost;
//		costNodeArrIdx = 0;
//		for(int i = 1; costArrTmp.size() != 0; i++) {
//			if(minCost < costArrTmp.get(i).cost) {
//				costNodeArrIdx = i;
//			}
//		}
//		
		
		
		
		
		
		
		
		
		return null;
	}


	public CostNode[] initCostNodes(IGraph graph) {
		CostNode[] costNodeArr = new CostNode[graph.getOrder()];
		CostNode cn =  new CostNode(0, null, 0, true);
		cn.pred = cn;
		costNodeArr[0] = cn;
		for (int i = 1; i < costNodeArr.length; i++) {
			costNodeArr[i] = new CostNode(i, null, Integer.MAX_VALUE, false);
		}
		return costNodeArr;
	}
}

