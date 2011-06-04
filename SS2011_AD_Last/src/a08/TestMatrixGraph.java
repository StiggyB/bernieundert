package a08;

/**
 * Testklasse zum Testen der Implementierung des Graphen
 * mit Adjazenz-Matrix
 * 
 * @author Tugend und Laster
 */
public class TestMatrixGraph {

	static void test() {
		MatrixGraph mg = new MatrixGraph();
		int[][] matrix = {  { 3, 0, 1 }, 
							{ 4, 5, 6 }, 
							{ 7, 8, 9 }  };
		mg.adjacencyMatrix = matrix;
		Helper.printArr(mg.adjacencyMatrix);
		System.out.println("####### getAdjacencies #######");
		int[] arr = mg.getAdjacencies(1);
		Helper.printArr(arr);
		System.out.println("####### getWeights #######");
		arr = mg.getWeights(1);
		Helper.printArr(arr);
		System.out.println("####### getLowestNodeWeight #######");
		Helper.printArr(mg.getLowestNodeWeight(0));
		
		System.out.println("Nodes: " + mg.getOrder());
		
		System.out.println("Edges: " + mg.getHeight());
	}
}
