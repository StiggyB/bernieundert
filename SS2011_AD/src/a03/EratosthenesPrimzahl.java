package a03;

public class EratosthenesPrimzahl {

	public boolean[] findPrimzahlen(int max) {
		boolean[] a = new boolean[max];
		Benchmark.ops = 0;

		for (int i = 2; i < max; i++) {
			a[i] = true;
		}

		for (int i = 2; i < Math.sqrt(max); i++) {
			if (a[i]) {
				for (int j = 2; i * j < max; j++) {
					Benchmark.ops++;
					a[i * j] = false;
				}
			}
		}
		return a;
	}

}
