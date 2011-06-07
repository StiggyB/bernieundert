package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Testklasse fuer den Dijkstra-Algorithmus mit der Listen- und
 * Matriximplementation
 * 
 * @author Tugend und Laster
 */
public class TestDijkstraAlgo {

	/**
	 * Testmethode fuer den Dijkstra-Algorithmus. Berechnet fuer beide
	 * Graph-Implementierungen den kuerzesten Weg und gibt das Ergebnis auf der
	 * Konsole aus.
	 */
	public static void test() {
		DijkstraAlgorithm da = new DijkstraAlgorithm();
		IGraph graphMatrix = new MatrixGraph();
		IGraph graphList = new ListGraph();
		try {
			graphMatrix.readXML(new File("src/a08/testgraph.xml"));
			graphList.readXML(new File("src/a08/testgraph.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		CostNode[] shortestPathMatrix = da.getShortestPath(graphMatrix, 0);
		CostNode[] shortestPathList = da.getShortestPath(graphList, 0);
		Helper.printArr(shortestPathMatrix);
		Helper.printArr(shortestPathList);
	}

}
