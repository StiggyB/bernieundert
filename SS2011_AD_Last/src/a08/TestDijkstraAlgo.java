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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Helper.printArr(da.initCostNodes(graph));
		da.getShortestPath(graph, 0);
	}
	
}
