package a08;

public class Helper {

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
