package a08;

import java.io.File;
import java.io.FileNotFoundException;

public class TestListGraph {

	static void test() {
		
		ListGraph lg = new ListGraph();
		try {
			lg.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Helper.printArr(lg.adjancencyList);
		System.out.println(lg.toString());
		
//		List<Edge> l = new ArrayList<Edge>();
//		l.add(new Edge(new Node(2, null), 2));
//		lg.adjancencyList.add(new Node(1, l));
		
		Helper.printArr(lg.getAdjacencies(1));
		Helper.printArr(lg.getWeights(1));
		for (int i = 0; i < lg.adjacencyList.size(); i++) {
			Helper.printArr(lg.getLowestNodeWeight(i));
		}
	}
	

	
}
