package a06;

import java.util.Random;



public class Quicksort {

	final static int SIZE = 10;
	static int PIVOT_NO = 0;
	
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
			pivot = selectPivot(PIVOT_NO, left, right);
			if(PIVOT_NO == 2) {
				swap(pivot, right);
			}
			do {
				while (a[i].key < pivot) {
					i++;
				}
				while (a[j].key >= pivot && j > left) {
					j--;
				}
				if(i < j) {
					swap(i, j);
				}
			} while (i < j);
			
			swap(i, right);
			quicksort(left, i - 1);
			quicksort(i + 1, right);
		}
	}

	/**
	 * @param iLeft
	 * @param iRight
	 */
	public static void quicksortScript(int iLeft, int iRight) {
		int pivot, j, i;
		if (iRight > iLeft) {
			i = iLeft;
			j = iRight - 1;
			pivot = selectPivot(PIVOT_NO, iLeft, iRight);
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
			int pivot = selectPivot(PIVOT_NO, left, right);
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
	 * @return
	 */
	private static int selectPivot(int pivotNo, int left, int right) {
		int pivot = 0;
		switch(pivotNo) {
		case 0: pivot = selectPivotLast(right);
		break;
		case 1: pivot = selectPivotRandom(right);
		break;		
		case 2: pivot = selectPivotMiddle(7, 9);
		break;
		}
		return pivot;
	}
	
	/**
	 * @param right
	 * @return
	 */
	private static int selectPivotLast(int right) {
		return a[right].key;
	}
	
	/**
	 * @param right
	 * @return
	 */
	private static int selectPivotRandom(int right) {
		return a[rnd.nextInt(right)].key;
	}
	
	/**
	 * @param off
	 * @param len
	 * @return
	 * @see java.util.Arrays
	 */
	private static int selectPivotMiddle(int off, int len) {
		System.out.println(off + ", " + len);

		// Choose a partition element, v
		int m = off + (len >> 1);       // Small arrays, middle element

	    int l = off;
	    int n = off + len - 1;	//Failure n > a.length-1!
	    if (len > 40) {        // Big arrays, pseudomedian of 9
			int s = len/8;
			l = med3(a, l,     l+s, l+2*s);
			m = med3(a, m-s,   m,   m+s);
			n = med3(a, n-2*s, n-s, n);
	    }
	    m = med3(a, l, m, n); // Mid-size, med of 3
		return a[m].key;
	}
	
    /**
     * Returns the index of the median of the three indexed integers.
     * 
     * @see java.util.Arrays
     */
    private static int med3(Data x[], int a, int b, int c) {
    	System.out.println(a + ", " + b + ", " + c);
    	return (x[a].key < x[b].key ?
    			(x[b].key < x[c].key ? b : x[a].key < x[c].key ? c : a) :
    				(x[b].key > x[c].key ? b : x[a].key > x[c].key ? c : a));
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
	
	private static void initSorted() {
		for (int i = 0; i < a.length; i++) {
			a[i] = new Data(i);
		}
	}
	
	private static void initRandom() {
		for (int i = 0, j = rnd.nextInt(); i < a.length; i++, j = rnd.nextInt()) {
			a[i] = new Data(j);
		}
	}
	
	private static void initReverse() {
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
