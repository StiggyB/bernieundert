package a06;

//http://www.vogella.de/articles/JavaAlgorithmsQuicksort/article.html#quicksort
//http://www.inf.fh-flensburg.de/lang/algorithmen/sortieren/quick/quicken.htm
//http://www.stefan-baur.de/cs.algo.quicksort.html

import java.util.Random;

public class QuickSortTest {


	private static final int MAX_RANDOM_NUM = 100000;


	/**
	 * @param a
	 * @param pivotMethod
	 * @param qs
	 */
	public static void quickSortAndPrint(Dataset[] a, int pivotMethod,
			Quicksort qs) {
		//Ausgabe des Arrayinhalts
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i].key + " ");
//		}
//		System.out.println();

		qs.setA(a);
		qs.setPivotMethod(pivotMethod);
		Benchmark.ops = 0;
		qs.quicksort3(0, a.length - 1);

		//Ausgabe des Arrayinhalts
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i].key + " ");
//		}
//		System.out.println();
	}

	/**
	 * @param a
	 * @param ss
	 */
	public static void selectionSortAndPrint(Dataset[] a, Selectionsort ss) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].key + " ");
		}
		System.out.println();

		ss.selectionSort1();

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].key + " ");
		}
		System.out.println();
	}
	
	/**
	 * @param a
	 */
	public static void initWorstCase(Dataset[] a)  {
		for (int i = 0; i < a.length; i++) {
			a[i] = new Dataset(i);
		}
	}
	
	/**
	 * @param a
	 */
	public static void initBestCase(Dataset[] a) {
		Random rnd = new Random();
		for (int i = 0; i < a.length; i++) {
			if(i < a.length /2) {
				a[i] = new Dataset(rnd.nextInt(a.length/2));
			} else if (i == a.length-1) {
				a[i] = new Dataset(a.length / 2);
			} else if(i >= a.length/2){
				a[i] = new Dataset(rnd.nextInt(a.length/2) + (a.length/2));
			}
		}
//		printArr(a);
	}
	
	/**
	 * @param a
	 */
	public static void initAvgCase(Dataset[] a) {
		Random rnd = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = new Dataset(rnd.nextInt(MAX_RANDOM_NUM));
		}
	}
	
	/**
	 * @param arr
	 */
	public static void printArr(Dataset... arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i].key + " ");
		}
	}


}
