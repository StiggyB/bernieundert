package a06;

import java.util.Random;



public class Quicksort {

	final static int SIZE = 10;
	static int pivotNo = 2;
	
	static Data[] a = new Data[SIZE];
	static Random rnd = new Random();
	
	static {
//		initSorted();
		initReverse();
//		initRandom();
	}

	/**
	 * @param left
	 * @param right
	 */
	public static void quicksort(int left, int right) {

		int i;
		int j;
		int pivot;
		if (left < right) {
			i = left;
			j = right - 1;
			pivot = selectPivot(pivotNo, left, right);
			if (pivotNo != 0) {
				swap(pivot, right);
				Benchmark.ops++;
				pivot = a[right].key;
			}
			do {
				while (a[i].key < pivot) {
					i++;
					Benchmark.ops++;
				}
				while (a[j].key >= pivot && j > left) {
					j--;
					Benchmark.ops++;
				}
				if(i < j) {
					swap(i, j);
					Benchmark.ops++;
				}
			} while (i < j);
			
			swap(i, right);
			Benchmark.ops++;
			quicksort(left, i - 1);
			quicksort(i + 1, right);
		}
	}

	/**
	 * This method does not run correctly.
	 * 
	 * @param iLeft
	 * @param iRight
	 */
	public static void quicksortScript(int iLeft, int iRight) {
		int pivot, j, i;
		if (iRight > iLeft) {
			i = iLeft;
			j = iRight - 1;
			pivot = selectPivot(pivotNo, iLeft, iRight);
			while (true) {
				while (a[i].key < pivot && i < iRight) {
					i++;
				}
				while (a[j].key >= pivot && j > iLeft) {
					j--;
				}
				if (i >= j) {
					break;
				}
				swap(i, j);
			}
			swap(i, iRight);
			quicksortScript(iLeft, i - 1);
			quicksortScript(i + 1, iRight);
		}
	}
	
	/**
	 * @param left
	 * @param right
	 */
	public static void quicksort2(int left, int right) {
		if(left < right) {
			int mid = (left + right) >> 2;
			if(a[mid].key < a[left].key) {
				swap(left, mid);
			}
			if(a[right].key < a[left].key) {
				swap(left, right);
			}
			if(a[right].key < a[mid].key) {
				swap(mid, right);
			}
			swap(mid, right - 1);
			int pivot = selectPivot(pivotNo, left, right);
			int i;
			int j;
			for(i = left, j = right - 1;;) {
				while(a[++i].key < pivot);
				while(a[--j].key > pivot);
				if( i >= j) {
					break;
				}
				swap(i, j);
			}
			swap(i, right - 1);
			quicksort(left, i - 1);
			quicksort(i + 1, right);
		}
	}
	
	/**
	 * @param pivotNo
	 * @param left
	 * @param right
	 */
	private static int selectPivot(int pivotNo, int left, int right) {
		int pivot = 0;
		switch(pivotNo) {
		case 0: pivot = selectPivotLast(right);
		break;
		case 1: pivot = selectPivotRandom(left, right);
		break;		
		case 2: pivot = selectPivotMiddle(left, right);
		break;
		}
		return pivot;
	}
	
	/**
	 * @param right
	 */
	private static int selectPivotLast(int right) {
		return a[right].key;
	}
	
	/**
	 * @param right
	 */
	private static int selectPivotRandom(int left, int right) {
		return rnd.nextInt(right - left) + left;
	}

	/**
	 * @param left
	 * @param right
	 */
	private static int selectPivotMiddle(int left, int right) {
		int median = 0;
		// besser wegen ueberlauf bei grossem idx: low+(high-low)/2
		int middle = (left + right) / 2;
		System.out.println("link: " + a[left].key + " rechts: "
				+ a[right].key + " mitte:" + a[middle].key);

		if (a[left].key < a[middle].key && a[right].key > a[middle].key
				|| a[right].key < a[middle].key
				&& a[left].key > a[middle].key) {
			median = middle;
		}

		if (a[right].key < a[left].key && a[middle].key > a[left].key
				|| a[middle].key < a[left].key
				&& a[right].key > a[left].key) {
			median = left;
		}

		if (a[middle].key < a[right].key
				&& a[left].key > a[right].key
				|| a[left].key < a[right].key
				&& a[middle].key > a[right].key) {
			median = right;
		}

		// sind zwei oder 3 werte gleich, nehme halt das rechte...
		if (median == 0) {
			median = right;
		}
		return median;
	}
	
	/**
	 * @param i
	 * @param j
	 */
	private static void swap(int i, int j) {
		Data tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	static void initSorted() {
		for (int i = 0; i < a.length; i++) {
			a[i] = new Data(i);
		}
	}
	
	static void initRandom() {
		for (int i = 0, j = rnd.nextInt(); i < a.length; i++, j = rnd.nextInt()) {
			a[i] = new Data(j);
		}
	}
	
	static void initReverse() {
		for (int i = 0, j = a.length-1; i < a.length; i++, j--) {
			a[i] = new Data(j);
		}
	}
		
	/**
	 * @param arr
	 */
	public static void printArr(Data... arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].key);
		}
	}

}
