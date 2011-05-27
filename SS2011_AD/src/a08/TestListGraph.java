package a08;

import java.util.ArrayList;
import java.util.List;

public class TestListGraph {

	static void test() {
		
		ListGraph lg = new ListGraph();
		List<Edge> l = new ArrayList<Edge>();
		l.add(new Edge(new Node(2, null), 2));
		lg.adjancencyList.add(new Node(1, l));
		
		System.out.println(lg.getAdjacencys(lg.adjancencyList.get(0)));
	}
	
	public static void main(String[] args) {
		test();
	}
	
}
