package a05;

public class PascalschesDreieck {

	// volles Dreieck und lahmarschig
	public static void pascalIterativeSlow(int n) {
		long triangle[][] = new long[n][];

		for (int i = 0; i < triangle.length; i++) {
			triangle[i] = new long[i + 1];

			for (int j = 0; j <= i; j++) {
				if ((j == 0) || (j == i))
					triangle[i][j] = 1;
				else
					triangle[i][j] = triangle[i - 1][j - 1]
							+ triangle[i - 1][j];

				System.out.print(" " + triangle[i][j]);
			}
			System.out.println();
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

	public static void main(String[] args) {

		System.out.println("PascaleIterativeOpt:");
		long time1 = System.currentTimeMillis();
		pascalIterativeOpt(66);
		long time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));

		System.out.println("PascaleBinomialGreaterThan20:");
		time1 = System.currentTimeMillis();
		pascalBinomialGreaterThan20(66);
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		System.out.println();

		System.out.println("PascaleBinomialMax20:");
		time1 = System.currentTimeMillis();
		pascalBinomialMax20(20);
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		System.out.println(Factorial.factorialMax20iterative(20));
	}
}
