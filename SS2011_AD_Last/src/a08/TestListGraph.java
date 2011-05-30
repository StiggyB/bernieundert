package a08;

import java.util.ArrayList;
import java.util.List;

public class TestListGraph {

	static void test() {
		
		ListGraph lg = new ListGraph();
		List<Edge> l = new ArrayList<Edge>();
		l.add(new Edge(new Node(2, null), 2));
		lg.adjancencyList.add(new Node(1, l));
		
		Helper.printArr(lg.getAdjacencys(0));
	}
	

	
}
