package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Interface f�r die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
public class MainApp {
	//TODO implement AOP Benchmark & JavaDoc
	public static void main(String[] args) {
		
//		TestListGraph.test();
//		TestMatrixGraph.test();
//		TestDijkstraAlgo.test();
		
		
		DijkstraAlgorithm da = new DijkstraAlgorithm();
		IGraph graph = new MatrixGraph();
		try {
			graph.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		MatrixBenchmark mb = new MatrixBenchmark(da);
		Helper.printArr(mb.getShortestPath(graph, 0));
		System.out.println(mb.ops);
	}
}
