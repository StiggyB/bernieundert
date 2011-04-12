package a03;


public class EratosthenesPrimzahl {

	public boolean[] findPrimzahlen(int max) {
		boolean[] a = new boolean[max];
		
		for (int i = 2; i < max; i++) {
			a[i] = true;
		}
		
		for (int i = 2; i < Math.sqrt(max); i++) {
			if (a[i]) {
				for (int j = 2; i * j < max; j++) {
					a[i*j] = false;
				}
			}
		}
		return a;
	}

	public boolean isPrimzahl(int i) {
		return findPrimzahlen(i + 1)[i];
	}
	
}
