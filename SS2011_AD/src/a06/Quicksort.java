package a06;

import java.util.Random;

public class Quicksort {

	private Dataset a[];
	private int pivotMethod;
	private Random rnd;

	public Quicksort(Dataset[] a, int pivotMethod) {
		this.a = a;
		this.pivotMethod = pivotMethod;
		this.rnd = new Random();
	}

	// uni crap algo
	// void quicksort(int ilinks, int irechts) {
	// boolean stopped = false;
	// int pivot, i, j;
	// if (irechts > ilinks) {
	// i = ilinks;
	// j = irechts - 1;
	// pivot = a[irechts].key;
	// while (!stopped) {
	// while (a[i].key < pivot) {
	// i++;
	// }
	// while (a[j].key >= pivot) {
	// j--;// Vorsicht: Stop-Element einbauen, z.B. mit -1 rennen
	// // ...
	// }
	// if (i >= j) {
	// stopped = true; // in der Mitte getroffen
	// }
	// swap(i, j);// vertauschen
	// }
	// swap(i, irechts);// Pivotelement in die Mitte tauschen
	// quicksort(ilinks, i - 1);
	// quicksort(i + 1, irechts);
	// }
	// }

	public void quicksort2(int ilinks, int irechts) {
		int i = ilinks, j = irechts;
		// int pivot = a[low + (high - low) / 2].key;
		// int pivot = a[irechts].key;
		System.out.println("ilinks: " + ilinks + "(" + a[ilinks].key + ")" + " irechts: " + irechts + "(" + a[irechts].key + ")");
		int pivot = getPivot(ilinks, irechts);
		System.out.println("pivot: " + pivot + "(" + a[pivot].key + ")");
		while (i <= j) {
			while (a[i].key < pivot) {
				i++;
			}

			while (a[j].key > pivot) {
				j--;
			}

			if (i <= j) {
				System.out.println("swap " + i + " und " + j);
				swap(i, j);
				i++;
				j--;
			}
		}

		if (ilinks < j)
//			System.out.println(i + "," + j);
//			System.out.println("linke Hälfte => ilinks: " + ilinks + "(" + a[ilinks].key + ")" + " j: " + j + "(" + a[j].key + ")");
			quicksort2(ilinks, j);
		if (i < irechts)
//			System.out.println("rechte Hälfte =>i: " + i + "(" + a[i].key + ")" + " irechts: " + irechts + "(" + a[irechts].key + ")");
			quicksort2(i, irechts);
	}

	private void swap(int i, int j) {
		Dataset tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public int getPivot(int ilinks, int irechts) {
		switch (pivotMethod) {

		case 2:
			int median = 0;
			int middle = (ilinks + irechts) / 2;

			if (a[ilinks].key < a[middle].key && a[middle].key < a[irechts].key
					|| a[ilinks].key < a[middle].key && irechts > ilinks) {
				median = a[middle].key;
			}

			if (a[ilinks].key < a[middle].key && a[irechts].key < a[ilinks].key
					|| a[ilinks].key > a[middle].key
					&& a[irechts].key > a[ilinks].key) {
				median = a[ilinks].key;
			}

			if (a[middle].key < a[irechts].key
					&& a[irechts].key < a[ilinks].key
					|| a[ilinks].key < a[irechts].key
					&& a[irechts].key < a[middle].key) {
				median = a[irechts].key;
			}
			return median;
		case 3:
			return rnd.nextInt(irechts);
		default:
			return a[irechts].key;

		}
	}

	public void setPivotMethod(int pivotMethod) {
		this.pivotMethod = pivotMethod;
	}

	public void setA(Dataset[] a) {
		this.a = a;
	}

}
