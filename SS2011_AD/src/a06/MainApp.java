package a06;

public class MainApp {
	
	private static final int MAX_EMPIRICAL_NUM = 20;
	private static final int MAX_DATA_SETS = 500;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Quicksort qs = new Quicksort(null, 1);
		int [] arr = new int[50];	
		
		for (int i = 0; i < MAX_DATA_SETS; i+=10) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int y = 0; y < MAX_DATA_SETS; y++) {
			System.out.println();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = 0;
			}
		for (int x = 0; x < MAX_EMPIRICAL_NUM; x++) {
			int c = 0;	
			for (int i = 0; i < MAX_DATA_SETS; i+=10) {
				Dataset[] testarray = new Dataset[i];
				Dataset[] testarrayCopy1 = new Dataset[i];
				Dataset[] testarrayCopy2 = new Dataset[i];
				Dataset[] testarrayCopy3 = new Dataset[i];
	//			Dataset[] testarrayCopy4 = new Dataset[i];
		
				//Hier werden die verschiedenen Testfälle ausgewählt
//				initWorstCase(testarray);
				QuickSortTest.initBestCase(testarray);
//				initAvgCase(testarray);
		
				System.arraycopy(testarray, 0, testarrayCopy1, 0, testarray.length);
				System.arraycopy(testarray, 0, testarrayCopy2, 0, testarray.length);
				System.arraycopy(testarray, 0, testarrayCopy3, 0, testarray.length);
	//			System.arraycopy(testarray, 0, testarrayCopy4, 0, testarray.length);
		
				//Hier werden die verschiedenen Pivotsuchverfahren und Suchverfahren
				QuickSortTest.quickSortAndPrint(testarrayCopy1, 1, qs);
//				quickSortAndPrint(testarrayCopy2, 2, qs);
//				quickSortAndPrint(testarrayCopy3, 3, qs);
				arr[c++] = Benchmark.ops;
	
				//Da bei einer Problemgroesse <= 10 Selectionsort sinnvoller
	//			selectionSortAndPrint(testarrayCopy4, ss);
	//			Selectionsort ss = new Selectionsort(testarrayCopy4);
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
		}
		//Hier wird der Durchschnitt errechnet
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print((arr[i] / MAX_EMPIRICAL_NUM) + " ");
//		}
		}
	}
}
