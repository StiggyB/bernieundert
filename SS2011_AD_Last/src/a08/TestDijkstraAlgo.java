package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Testklasse fuer den Dijkstra-Algorithmus mit der Listen-
 * und Matriximplementation
 * 
 * @author Tugend und Laster
 */
public class TestDijkstraAlgo {

	public static void test() {
		DijkstraAlgorithm da = new DijkstraAlgorithm();
		IGraph graphMatrix = new MatrixGraph();
//		IGraph graphList = new ListGraph();
		try {
			graphMatrix.readXML(new File("src/a08/graph.xml"));
//			graphList.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int[] shortestPath = da.getShortestPath(graphMatrix, 0);
//		int[] shortestPath = da.getShortestPath(graphList, 0);
		Helper.printArr(shortestPath);
	}
	
}
