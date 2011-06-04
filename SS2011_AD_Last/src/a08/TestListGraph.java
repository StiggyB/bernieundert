package a08;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Interface für die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
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
