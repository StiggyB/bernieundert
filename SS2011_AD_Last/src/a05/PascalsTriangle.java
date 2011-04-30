package a05;

public class PascalsTriangle {

	/**
	 * @param n
	 */
	static void calcTriangleRecu(int n) {
		n++;
		for (int i = 1; i < n; i++) {
			calcTriangleRecu(n, i);
		}
	}

	/**
	 * P(n, k) = P(n-1, k-1) + P(n-1, k)
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	static int calcTriangleRecu(int n, int k) {
		Benchmark.ops++;
		if (n < k || k < 0) {
			return 0;
		}
		if (k == 1 || k == n) {
			return 1;
		}
		return calcTriangleRecu(n - 1, k - 1) + calcTriangleRecu(n - 1, k);
	}

	/**
	 * P(n, k) = P(n-1, k-1) + P(n-1, k)
	 * 
	 */
	static long[][] calcTriangleIter(int m) {
		long[][] values = new long[m + 1][m + 1];
		for (int n = 0; n < values.length; n++) {
			for (int k = 0; k <= n; k++) {
				Benchmark.ops++;
				if (k == 0 || k == n) {
					values[n][k] = 1;
				} else {
					values[n][k] = values[n - 1][k - 1] + values[n - 1][k];
				}
			}
		}
		return values;
	}

	/**
	 * Von der Tugend
	 * 
	 * @param n
	 */
	public static void pascalIterativeFaster(int n) {
		long triangle[][] = new long[n + 1][];

		for (int i = 0; i <= n; i++) {
			triangle[i] = new long[i + 1];

			for (int j = 0; j <= i; j++) {
				Benchmark.ops++;
				if ((j == 0) || (j == i))
					triangle[i][j] = 1;
				else
					triangle[i][j] = triangle[i - 1][j - 1]
							+ triangle[i - 1][j];

				// System.out.print(" " + triangle[i][j]);
			}
			// System.out.println();
		}
		// for (int i = 0; i < triangle.length; i++) {
		// System.out.print(triangle[n][i] + " ");
		// ;
		// }
	}

	/**
	 * Only for n >= k and n nonnegative (n,k) = n! / k! * (n-k)!
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	static long calcBinomial(int n, int k) {
		long res = 0;
		if (n >= k || n >= 0) {
			Benchmark.ops++;
			res = (Factorial.factor(n))
					/ (Factorial.factor(k) * Factorial.factor((n - k)));
		}
		return res;
	}
	
	// geht bis n=136
	public static void pascalBinomialGreaterThan20(int n) {
		for (int i = 0; i <= n; i++) {
			System.out.print(Factorial.factorialGreaterThan20recursive(n)
					.divide(Factorial.factorialGreaterThan20recursive(i)
							.multiply(
									Factorial.factorialGreaterThan20recursive(n
											- i)))
					+ " ");
		}

	}

	// n = 60 ca.
	public static void pascalSpecial(int n) {
		int j = 0;
		long x = 1;
		for (j = 0, x = 1; j <= n; j++) {
			// System.out.print(x + " ");
			x = x * (n - j) / (j + 1);
			Benchmark.ops++;
		}
		// System.out.println();

	}

	static void printArr(long[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println();
			for (int j = 0; j < arr[1].length; j++) {
				System.out.print(arr[i][j] + "  ");
			}
		}
	}

	static void printArr(long[][] arr, int n) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[n][i] + "  ");
		}
	}

}
