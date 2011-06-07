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

		// Benchmark-Objekte erzeugen
		ListBenchmark lb;
		MatrixBenchmark mb;
		DijkstraAlgorithm da;

		for (int i = 1; i <= 6; i++) {
			lb = new ListBenchmark();
			mb = new MatrixBenchmark();
			da = new DijkstraAlgorithm();
			// Graph einlesen 2x
			try {
				lb.readXML(new File("src/a08/graph" + i + ".xml"));
				mb.readXML(new File("src/a08/graph" + i + ".xml"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			System.out.println("Graph " + i + "x");
			System.out.println("==============");
			
			// kuerzesten Pfad berechnen nach Dijkstra
			System.out.println("Dijkstra: getShortestPath()");
			Helper.printArr(da.getShortestPath(lb, 0));
			Helper.printArr(da.getShortestPath(mb, 0));
			
			// Ops ausgeben
			System.out.println("Aufwandszaehler ausgeben getShortestPath()");
			System.out.println("Listen OPs: " + lb.ops);
			System.out.println("Matrix OPs: " + mb.ops);
			
			// Ops resetten
			lb.resetOps();
			mb.resetOps();
			
			// Nachbarn fuer Knoten ermiteln
			System.out.println("Aufwandszaehler ausgeben getAdjacencies()");
			lb.getAdjacencies(0);
			mb.getAdjacencies(0);
			
			// Ops ausgeben
			System.out.println("Listen OPs: " + lb.ops);
			System.out.println("Matrix OPs: " + mb.ops);
			System.out.println("\n\n");
		}
	}
}
