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
	// public void quicksort(int ilinks, int irechts) {
	// boolean stopped = false;
	// int pivot, i, j;
	// if (irechts > ilinks) {
	// i = ilinks;
	// j = irechts;
	// pivot = a[irechts].key;
	// while (!stopped) {
	// while (a[i].key < pivot) {
	// i++;
	// }
	// while (a[j].key >= pivot) {
	// if(j==0)break;
	// j--;// Vorsicht: Stop-Element einbauen, z.B. mit -1 rennen
	//
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
		System.out.println("ilinks: " + ilinks + "(" + a[ilinks].key + ")"
				+ " irechts: " + irechts + "(" + a[irechts].key + ")");
		int pivot = getPivot(ilinks, irechts);
		System.out.println("pivot: " + pivot + "(" + a[pivot].key + ")");
		while (i <= j) {
			while (a[i].key < pivot) {
				i++;
				System.out.println("i: " + i);
			}

			while (a[j].key > pivot) {
				j--;
				System.out.println("j: " + j);
			}

			if (i <= j) {
				System.out.println("swap " + i + " und " + j);
				swap(i, j);
				i++;
				j--;
			}
		}
//		swap(i, irechts);

		if (ilinks < j) {
			// System.out.println("i und j: " + i + ", " + j);
			// System.out.println("linke Hälfte => ilinks: " + ilinks + "(" +
			// a[ilinks].key + ")" + " j: " + j + "(" + a[j].key + ")");
			quicksort2(ilinks, j);
		}
		if (i < irechts) {
			// System.out.println("i und j: " + i + ", " + j);
			// System.out.println("rechte Hälfte =>i: " + i + "(" + a[i].key +
			// ")" + " irechts: " + irechts + "(" + a[irechts].key + ")");
			quicksort2(i, irechts);
		}
	}
	
	//hab den algo nochmal eben nach wikipedia selber geproggt ... der funzt sogar :D
	public void quicksort3(int ilinks, int irechts){
		if(ilinks < irechts){
			int i = ilinks;
			int j = irechts-1;
			
			//funktioniert bisher nur gut mit privotmethod = 1 ... 
			//bei 2 ist es nur "fast" richtig sortiert, liegt wahrscheinlich am if median == 0 :P
			//3er geht hierbei gar nicht ...
			int pivot = getPivot(ilinks, irechts);
			
			//ok im script isses ne while mit break, das aber hässlich ...
			//is do/while ok oder besser erstmal bedingung prüfen also mit while() anfangen?!
			//dann brauch ich lediglich n abburchkriterium
			do{
				while(a[i].key <= pivot && i < irechts){
					i++;
				}
				
				while(a[j].key >= pivot && j > ilinks){
					j--;
				}
				
				if(i<j){
					swap(i, j);
				}
			}while(i<j);
			
			//wieso swappe ich aber nur wenn key > pivot ist?!
			if(a[i].key > pivot){
				swap(i, irechts);
			}
			
			quicksort3(ilinks, i-1);
			quicksort3(i+1, irechts);
		}
	}

	private void swap(int i, int j) {
		Dataset tmp;
		tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public int getPivot(int ilinks, int irechts) {
		switch (pivotMethod) {

		case 1:
			return a[irechts].key;

		case 2:
			int median = 0;
			int middle = (ilinks + irechts) / 2;
			if (a[ilinks].key < a[middle].key && a[middle].key < a[irechts].key
					|| a[ilinks].key < a[middle].key
					&& a[irechts].key > a[ilinks].key) {
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
			if (median == 0) {
				median = a[irechts].key;
			}
			return median;
		case 3:
			int i = rnd.nextInt(irechts);
			return a[i].key;
		default:
			throw new IllegalArgumentException();

		}
	}

	public void setPivotMethod(int pivotMethod) {
		this.pivotMethod = pivotMethod;
	}

	public void setA(Dataset[] a) {
		this.a = a;
	}

}
