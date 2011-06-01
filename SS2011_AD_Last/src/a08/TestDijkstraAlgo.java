package a08;

import java.io.File;
import java.io.FileNotFoundException;

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
