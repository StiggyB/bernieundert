package a03;

public class SlowPrimzahl {
	public boolean[] findPrimzahlen(int max) {

		boolean[] a = new boolean[max];
		Benchmark.ops = 0;

		for (int i = 2; i < max; i++) {
			a[i] = true;
			for (int j = 2; j < i; j++) {
				Benchmark.ops++;
				if (i % j == 0) {
					a[i] = false;
					break;
				}
			}
		}
		return a;
	}

}
