package a06;


public class Selectionsort {
	
	private Dataset a[];

	public Selectionsort(Dataset[] a) {
		this.a = a;
	}

	public void selectionSort1() {
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

	private int findMinStartingAt(int i, Dataset[] a) {
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
	
	public void setA(Dataset[] a) {
		this.a = a;
	}
}
