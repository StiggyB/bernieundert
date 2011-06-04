package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Testklasse zum Testen der Implementierung des Graphen
 * mit Adjazenz-Liste
 * 
 * @author Tugend und Laster
 */
public class TestListGraph {

	static void test() {
		
		ListGraph lg = new ListGraph();
		try {
			lg.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(lg.toString());
		
		Helper.printArr(lg.getAdjacencies(1));
		Helper.printArr(lg.getWeights(1));
		for (int i = 0; i < lg.adjacencyList.size(); i++) {
			Helper.printArr(lg.getLowestNodeWeight(i));
		}
	}
	

	
}
