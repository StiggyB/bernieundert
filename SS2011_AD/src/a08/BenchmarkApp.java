package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Benchmarkklasse fuer die Aufwandsanalyse des Dijkstraalgorithmus mit
 * inkrementell wachsenden Graphen.
 * 
 * @author Tugend und Laster
 */
public class BenchmarkApp {

	/**
	 * Benchmarkmethode, welche die Aufwandsanalyse vornimmt.
	 * 
	 */
	public static void runBenchmark() {
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

			System.out.println("Graph " + i + "x, Knoten: " + lb.getOrder());
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

			// Ops resetten
			lb.resetOps();
			mb.resetOps();

			// Gewicht fuer Knoten ermiteln
			System.out.println("Aufwandszaehler ausgeben getWeights()");
			lb.getWeights(0);
			mb.getWeights(0);

			// Ops ausgeben
			System.out.println("Listen OPs: " + lb.ops);
			System.out.println("Matrix OPs: " + mb.ops);

			// Ops resetten
			lb.resetOps();
			mb.resetOps();

			// Hoehe fuer Graphen ermiteln
			System.out.println("Aufwandszaehler ausgeben getHeight()");
			lb.getHeight();
			mb.getHeight();

			// Ops ausgeben
			System.out.println("Listen OPs: " + lb.ops);
			System.out.println("Matrix OPs: " + mb.ops);
			System.out.println("\n\n");
		}
	}
}
