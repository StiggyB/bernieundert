package a06;

public class Quicksort<T> {

	Datensatz<T> a[];
	boolean stopped = false;

	void quicksort(int ilinks, int irechts) {
		int pivot, i, j;
		if (irechts > ilinks) {
			i = ilinks;
			j = irechts - 1;
			pivot = a[irechts].key;
			while (!stopped) {
				while (a[i].key < pivot) {
					i++;
				}
				while (a[j].key >= pivot) {
					j--;// Vorsicht: Stop-Element einbauen, z.B. mit -1 rennen ...
				}
				if (i >= j) {
					stopped = true;	// in der Mitte getroffen
				}
				swap(i, j);// vertauschen
			}
			swap(i, irechts);// Pivotelement in die Mitte tauschen
			quicksort(ilinks, i - 1);
			quicksort(i + 1, irechts);
		}
	}

	private void swap(int i, int j) {
		Datensatz<T> tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;

	}

}
