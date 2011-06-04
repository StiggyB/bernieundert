package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Interface für die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
public class TestDijkstraAlgo {

	public static void test() {
		DijkstraAlgorithm da = new DijkstraAlgorithm();
		IGraph graph = new MatrixGraph();
		try {
			graph.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int[] shortestPath = da.getShortestPath(graph, 0);
		Helper.printArr(shortestPath);
	}
	
}
