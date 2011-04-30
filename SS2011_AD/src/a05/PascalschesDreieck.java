package a05;

public class PascalschesDreieck {

	// volles Dreieck und lahmarschig
	public static void pascalIterativeSlow(int n) {
		long triangle[][] = new long[n + 1][];

		for (int i = 0; i <= n; i++) {
			triangle[i] = new long[i + 1];

			for (int j = 0; j <= i; j++) {
				if ((j == 0) || (j == i))
					triangle[i][j] = 1;
				else
					triangle[i][j] = triangle[i - 1][j - 1]
							+ triangle[i - 1][j];

				// System.out.print(" " + triangle[i][j]);
			}
			// System.out.println();
		}
		for (int i = 0; i < triangle.length; i++) {
			System.out.print(triangle[n][i] + " ");
			;
		}
	}

	// sparsamer und wesentlich schneller, geht bis n=66
	public static void pascalIterativeOpt(int n) {
		long lastLine[] = null;
		long currentLine[] = null;
		for (int i = 0; i <= n; i++) {
			currentLine = new long[i + 1];
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					currentLine[j] = 1;
				} else {
					currentLine[j] = lastLine[j - 1] + lastLine[j];
				}
			}
			lastLine = currentLine;
		}
		for (int i = 0; i < currentLine.length; i++) {
			System.out.print(currentLine[i] + " ");
		}
	}

	// max n=20
	public static void pascalBinomialMax20(int n) {
		for (int i = 0; i <= n; i++) {
			System.out.print(Factorial.factorialMax20iterative(n)
					/ (Factorial.factorialMax20iterative(i) * Factorial
							.factorialMax20iterative(n - i)) + " ");
		}

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
			System.out.print(x + " ");
			x = x * (n - j) / (j + 1);
		}
		System.out.println();

	}

	public static void pascalRecursive(int n) {
		n++;
		for (int i = n; i > 0; i--) {
			System.out.print(pascalRecursive(n, i) + " ");
		}
	}

	private static int pascalRecursive(int row, int column) {
		if (column == 1 || column == row) {
			return 1;
		}
		return pascalRecursive(row - 1, column - 1)
				+ pascalRecursive(row - 1, column);
	}

	public static void main(String[] args) {

		int N = 200;
		for (int i = 0; i < 100000; i++) {

		}
		long time1 = 0;
		long time2 = 0;

		System.out.println("PascaleIterativeSloooow:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalIterativeSlow(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		// --------------------------------------------------------------
		System.out.println("PascaleIterativeSloooow:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalIterativeSlow(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		// --------------------------------------------------------------
		System.out.println("PascaleIterativeOpt:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalIterativeOpt(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		// --------------------------------------------------------------
		System.out.println("PascaleIterativeOpt:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalIterativeOpt(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		// --------------------------------------------------------------
		System.out.println("PascaleRecursive:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalRecursive(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		System.out.println();
		// --------------------------------------------------------------
		System.out.println("PascaleRecursive:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalRecursive(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		System.out.println();
		// --------------------------------------------------------------
		System.out.println("PascaleBinomialGreaterThan20:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalBinomialGreaterThan20(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		System.out.println();
		// --------------------------------------------------------------
		System.out.println("PascaleBinomialMax20:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalBinomialMax20(20);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		System.out.println();
		// --------------------------------------------------------------
		System.out.println("PascaleSpecialFormula:");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pascalSpecial(20);
		}
		time2 = System.currentTimeMillis();
		System.out.println("Zeit: " + (time2 - time1));

	}
}
