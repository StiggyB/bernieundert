package a08;

public class TestMatrixGraph {

	static void test() {
		MatrixGraph mg = new MatrixGraph();
		int[][] matrix = {  { 0, 0, 1 }, 
							{ 4, 5, 6 }, 
							{ 7, 8, 9 }  };
		mg.adjancencyMatrix = matrix;
		printArr(mg.adjancencyMatrix);
		System.out.println("#######");
		int[] arr = mg.getAdjacencys(1);
		printArr(arr);
		System.out.println("#######");
		arr = mg.getWeights(1);
		printArr(arr);
		
		System.out.println("Nodes: " + mg.getOrder());
		
		System.out.println("Edges: " + mg.getHeight());
	}

	public static void main(String[] args) {
		test();
	}
	
	static void printArr(int...arr) {
		for (int i : arr) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
	static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}
}
