package a06;

import java.util.Random;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         In dieser Klasse sind zwei Algorithmen implementiert,
 *         die den Quicksort-Algorithmus darstellen.
 *         Weiterhin ist eine Methode zur Pivotelementwahl
 *         enthalten.
 * 
 */
public class Quicksort {

	private Dataset a[];
	private int pivotMethod;
	private Random rnd;

	/**
	 * @param a
	 * @param pivotMethod
	 */
	public Quicksort(Dataset[] a, int pivotMethod) {
		this.a = a;
		this.pivotMethod = pivotMethod;
		this.rnd = new Random();
	}

	/**
	 * @param ilinks
	 * @param irechts
	 */
	public void quicksort2(int ilinks, int irechts) {
		int i = ilinks, j = irechts;
		int pivot = getPivot(ilinks, irechts);
		if (pivotMethod != 1) {
			pivot = a[pivot].key;
		}
		while (i <= j) {
			while (a[i].key < pivot) {
				Benchmark.ops++;
				i++;
			}

			while (a[j].key > pivot) {
				Benchmark.ops++;
				j--;
			}

			if (i <= j) {
				swap(i, j);
				i++;
				j--;
			}
		}

		if (ilinks < j) {
			quicksort2(ilinks, j);
		}
		if (i < irechts) {
			quicksort2(i, irechts);
		}
	}

	/**
	 * @param ilinks
	 * @param irechts
	 */
	public void quicksort3(int ilinks, int irechts) {
		if (ilinks < irechts) {
			int i = ilinks;
			int j = irechts - 1;

			int pivot = getPivot(ilinks, irechts);
			if (pivotMethod != 1) {
				swap(pivot, irechts);
				pivot = a[irechts].key;
			}

			do {
				while (a[i].key <= pivot && i < irechts) {
					Benchmark.ops++;
					i++;
				}

				while (a[j].key >= pivot && j > ilinks) {
					Benchmark.ops++;
					j--;
				}

				if (i < j) {
					swap(i, j);
				}
			} while (i < j);

			// wieso swappe ich aber nur wenn key > pivot ist?!
			if (a[i].key > pivot) {
				swap(i, irechts);
			}

			quicksort3(ilinks, i - 1);
			quicksort3(i + 1, irechts);
		}
	}

	/**
	 * @param i
	 * @param j
	 */
	private void swap(int i, int j) {
		Dataset tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param ilinks
	 * @param irechts
	 * @return
	 */
	public int getPivot(int ilinks, int irechts) {
		switch (pivotMethod) {

		case 1:
			return a[irechts].key;

		case 2:
			int median = 0;
			// besser wegen ueberlauf bei grossem idx: low+(high-low)/2
			int middle = (ilinks + irechts) / 2;

			if (a[ilinks].key < a[middle].key && a[irechts].key > a[middle].key
					|| a[irechts].key < a[middle].key
					&& a[ilinks].key > a[middle].key) {
				median = middle;
			}

			if (a[irechts].key < a[ilinks].key && a[middle].key > a[ilinks].key
					|| a[middle].key < a[ilinks].key
					&& a[irechts].key > a[ilinks].key) {
				median = ilinks;
			}

			if (a[middle].key < a[irechts].key
					&& a[ilinks].key > a[irechts].key
					|| a[ilinks].key < a[irechts].key
					&& a[middle].key > a[irechts].key) {
				median = irechts;
			}

			// sind zwei oder 3 werte gleich, nehme halt das rechte...
			if (median == 0) {
				median = irechts;
			}
			return median;
		case 3:
			return rnd.nextInt(irechts - ilinks) + ilinks;

		default:
			throw new IllegalArgumentException();

		}
	}

	/**
	 * @param pivotMethod
	 */
	public void setPivotMethod(int pivotMethod) {
		this.pivotMethod = pivotMethod;
	}

	/**
	 * @param a
	 */
	public void setA(Dataset[] a) {
		this.a = a;
	}

}
