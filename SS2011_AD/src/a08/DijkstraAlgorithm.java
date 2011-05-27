package a08;

public class DijkstraAlgorithm {

	CostNode[] costArr;
	Node[] adjacencyArr;
	CostNode[] Q;
	
	
	Node[] getShortestPath2(/*I*/ListGraph g, Node startNode) {
//		 1  Funktion Dijkstra(Graph, Startknoten):
		Q = initCostNodes(g);
		while(Q.length != 0) {
			CostNode u = Q[0];
			for (int i = 1; i < Q.length; i++) {
				if(u.cost < Q[i].cost) {
					Q[0] = null;
				}
			}
			
		}
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
	
	Node[] getShortestPath(/*I*/ListGraph g, Node startNode) {
		costArr = initCostNodes(g);
		adjacencyArr = g.getAdjacencys(startNode);
		for (int i = 1; i < adjacencyArr.length; i++) {
			costArr[i].pred = costArr[0];
		}
		
		
		//TODO implement Method selectShortestPath() use getWeights()!
		while (adjacencyArr.length != 0) {
			for (int i = 0; i < adjacencyArr.length; i++) {
			}
		}
		return null;
	}


	private CostNode[] initCostNodes(ListGraph g) {
		CostNode[] costNodeArr = new CostNode[g.adjancencyList.size()];
		CostNode cn =  new CostNode(0, null, 0, true);
		cn.pred = cn;
		costNodeArr[0] = cn;
		for (int i = 1; i < costNodeArr.length; i++) {
			costNodeArr[i] = new CostNode(1, null, Integer.MAX_VALUE, false);
		}
		return costNodeArr;
	}
}

