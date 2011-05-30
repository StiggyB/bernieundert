package a08;

public class TestMatrixGraph {

	static void test() {
		MatrixGraph mg = new MatrixGraph();
		int[][] matrix = {  { 0, 0, 1 }, 
							{ 4, 5, 6 }, 
							{ 7, 8, 9 }  };
		mg.adjancencyMatrix = matrix;
		Helper.printArr(mg.adjancencyMatrix);
		System.out.println("#######");
		int[] arr = mg.getAdjacencys(1);
		Helper.printArr(arr);
		System.out.println("#######");
		arr = mg.getWeights(1);
		Helper.printArr(arr);
		
		System.out.println("Nodes: " + mg.getOrder());
		
		System.out.println("Edges: " + mg.getHeight());
	}

	public static void main(String[] args) {
		TestListGraph.test();
	}
	

}
