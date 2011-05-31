package a08;

import java.io.File;
import java.io.FileNotFoundException;

public class TestMatrixGraph {

	static void test() {
		MatrixGraph mg = new MatrixGraph();
		
		try {
			mg.readXML(new File("src/a08/graph.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Helper.printArr(lg.adjancencyList);
		System.out.println(mg.toString());
//		int[][] matrix = {  { 0, 0, 1 }, 
//							{ 4, 5, 6 }, 
//							{ 7, 8, 9 }  };
//		mg.adjacencyMatrix = matrix;
//		Helper.printArr(mg.adjacencyMatrix);
//		System.out.println("#######");
//		int[] arr = mg.getAdjacencys(1);
//		Helper.printArr(arr);
//		System.out.println("#######");
//		arr = mg.getWeights(1);
//		Helper.printArr(arr);
//		
//		System.out.println("Nodes: " + mg.getOrder());
//		
//		System.out.println("Edges: " + mg.getHeight());
	}
}
