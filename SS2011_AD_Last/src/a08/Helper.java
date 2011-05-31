package a08;

import java.util.List;

public class Helper {

	static void printArr(int... arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	static void printArr(CostNode... arr) {
		for (CostNode costNode : arr) {
			System.out.println(costNode + " ");
		}
		System.out.println();
	}
	
	static void printArr(List<?> list) {
		for (Object wCard : list) {
			System.out.println(wCard);
		}
		System.out.println();
	}
	
//	static void printArr(List<Node> arr) {
//		for (Node i : arr) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
//	}
	
	static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
