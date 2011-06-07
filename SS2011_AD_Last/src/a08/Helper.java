package a08;

import java.util.List;

/**
 * Hilfsklasse: Dies ist einen Klasse, die Hilfsmethoden zum 
 * ausgeben von Arrays und Listen bereitstellt
 * 
 * @author Tugend und Laster
 */
public class Helper {

	/**
	 * Gibt ein Array variabler Laenge auf der Konsole aus
	 * 
	 * @param arr Array zum Printen
	 */
	static void printArr(int... arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	/**
	 * Gibt ein Array von CostNode variabler Laenge auf der 
	 * Konsole aus
	 * 
	 * @param arr CostNode zum Printen
	 */
	static void printArr(CostNode... arr) {
		for (CostNode costNode : arr) {
			System.out.println(costNode + " ");
		}
		System.out.println();
	}
	
	/**
	 * Gibt ein Array 2D-Array auf der Konsole aus
	 * @param arr
	 */
	static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Gibt eine beliebige Liste auf der Konsole aus
	 * 
	 * @param list Liste zum Printen
	 */
	static void printList(List<?> list) {
		for (Object wCard : list) {
			System.out.println(wCard + " ");
		}
		System.out.println();
	}
}
