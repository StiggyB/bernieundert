package a06;

public class Selectionsort {

	public void selectionSort1(Dataset[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i].key > a[j].key) {
					swap(i, j, a);
				}
			}
		}
	}

	public void selectionSort2(Dataset[] a) {
		int minimum = 0;
		for (int i = 1; i <= a.length - 1; i++) {
			minimum = findMinStartingAt(i, a);
			swap(i, minimum, a);
		}
	}

	public int findMinStartingAt(int i, Dataset[] a) {
		int min = i;
		for (int j = i + 1; j <= a.length; j++) {
			if (a[j].key < a[min].key)
				min = j;
		}
		return min;
	}

	private void swap(int i, int j, Dataset[] a) {
		Dataset tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;

	}
}
