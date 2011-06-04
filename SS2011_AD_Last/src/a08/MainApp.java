package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Startklasse fuer die Implementierung eines Graphen mit Adjazenz Matrix und
 * Liste, ausserdem fuer Dijkstra.
 * 
 * @author Tugend und Laster
 */
public class MainApp {

	public static void main(String[] args) {

		// TestListGraph.test();
		// TestMatrixGraph.test();
		// TestDijkstraAlgo.test();

		//Benchmark-Objekte erzeugen
		ListBenchmark lb = new ListBenchmark();
		MatrixBenchmark mb = new MatrixBenchmark();
		DijkstraAlgorithm da = new DijkstraAlgorithm();

		//Graph einlesen 2x
		try {
			lb.readXML(new File("src/a08/graph.xml"));
			mb.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//kuerzesten Pfad berechnen nach Dijkstra
		Helper.printArr(da.getShortestPath(lb, 0));
		Helper.printArr(da.getShortestPath(mb, 0));
		//Ops ausgeben
		System.out.println("Listen OPs: " + lb.ops);
		System.out.println("Matrix OPs: " + mb.ops);
		//Ops resetten
		lb.resetOps();
		mb.resetOps();
		//Nachbarn fuer Knoten ermiteln
		Helper.printArr((lb.getAdjacencies(0)));
		Helper.printArr((mb.getAdjacencies(0)));
		//Ops ausgeben
		System.out.println("Listen OPs: " + lb.ops);
		System.out.println("Matrix OPs: " + mb.ops);
	}
}
