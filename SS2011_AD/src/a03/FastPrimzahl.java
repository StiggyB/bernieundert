package a03;

public class FastPrimzahl {

	public boolean[] findPrimzahlen(int max) {
		
		boolean[] a = new boolean[max];
		Benchmark.ops = 0;

		for (int i = 2; i < max; i++) {
			a[i] = true;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				Benchmark.ops++;
				if (i % j == 0) {
					a[i] = false;
					break;
				}
			}
		}
		return a;
	}

	public boolean isPrime(int p) {
		Benchmark.ops = 0;
		if (p < 2)
			return false;

		for (int i = 2; i <= Math.sqrt(p); i++) {
			Benchmark.ops++;
			if (p % i == 0) {
				return false;
			}
		}
		return true;
	}

}
