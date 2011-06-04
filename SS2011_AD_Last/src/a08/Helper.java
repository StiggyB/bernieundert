package a08;

import java.util.List;

/**
 * Interface für die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 */
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
			System.out.println(wCard + " ");
		}
		System.out.println();
	}
	
	static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
