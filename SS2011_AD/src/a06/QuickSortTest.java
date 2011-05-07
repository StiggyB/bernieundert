package a06;
//http://www.vogella.de/articles/JavaAlgorithmsQuicksort/article.html#quicksort
//http://www.inf.fh-flensburg.de/lang/algorithmen/sortieren/quick/quicken.htm
//http://www.stefan-baur.de/cs.algo.quicksort.html


import java.util.Random;

public class QuickSortTest {

	private static final int MAX_DATA_SETS = 20;

	public static void quickSortAndPrint(Dataset[] a, int pivotMethod, Quicksort qs) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].key + " ");
		}
		System.out.println();

		qs.setA(a);
		qs.setPivotMethod(pivotMethod);
		qs.quicksort3(0, a.length - 1);

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].key + " ");
		}
		System.out.println();
	}
	
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

	public static void main(String[] args) {

		Dataset[] testarray = new Dataset[MAX_DATA_SETS];
		Dataset[] testarrayCopy1 = new Dataset[MAX_DATA_SETS];
		Dataset[] testarrayCopy2 = new Dataset[MAX_DATA_SETS];
		Dataset[] testarrayCopy3 = new Dataset[MAX_DATA_SETS];
		Dataset[] testarrayCopy4 = new Dataset[MAX_DATA_SETS];
		Random rnd = new Random();

		for (int i = 0; i < testarray.length; i++) {
			testarray[i] = new Dataset(rnd.nextInt(20));
		}
		
		System.arraycopy(testarray, 0, testarrayCopy1, 0, testarray.length);
		System.arraycopy(testarray, 0, testarrayCopy2, 0, testarray.length);
		System.arraycopy(testarray, 0, testarrayCopy3, 0, testarray.length);
		System.arraycopy(testarray, 0, testarrayCopy4, 0, testarray.length);
		
		Quicksort qs = new Quicksort(testarrayCopy1, 1);
		Selectionsort ss = new Selectionsort(testarrayCopy4);
//		Aufpassen in Zeile 21!!!!!!!
//		qs.quicksort3(0, a.length - 1); <--- anpassen, welcher algo genutzt werden soll, 1,2 oder 3 ... ;)
//		quickSortAndPrint(testarrayCopy1, 1, qs);
		quickSortAndPrint(testarrayCopy2, 2, qs);
//		quickSortAndPrint(testarrayCopy3, 3, qs);
//		selectionSortAndPrint(testarrayCopy4, ss);
		

	}
}
