package a06;

import java.util.Random;

public class QuickSortTest {

	private static final int MAX_DATA_SETS = 20;

	public static void sortAndPrint(Dataset[] a, int pivotMethod, Quicksort qs) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].key + " ");
		}
		System.out.println();

		qs.setA(a);
		qs.setPivotMethod(pivotMethod);
		qs.quicksort2(0, a.length - 1);

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
		Random rnd = new Random();

		for (int i = 0; i < testarray.length; i++) {
			testarray[i] = new Dataset(rnd.nextInt(20));
		}
		
		System.arraycopy(testarray, 0, testarrayCopy1, 0, testarray.length);
		System.arraycopy(testarray, 0, testarrayCopy2, 0, testarray.length);
		System.arraycopy(testarray, 0, testarrayCopy3, 0, testarray.length);
		
		Quicksort qs = new Quicksort(testarrayCopy1, 1);
		sortAndPrint(testarrayCopy1, 1, qs);
		sortAndPrint(testarrayCopy2, 3, qs);

	}
}
