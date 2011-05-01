package a05;

import java.math.BigInteger;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         Diese Klasse beinhaltet diverse Algorithmen zu Darstellung der n-ten
 *         Zeile des Pascalschen Dreiecks. Aufgrund von Datentyp-
 *         beschraenkungen wurden (auch in der Factorial-Klasse) mehrere
 *         Implementationen gewaehlt. iterativ mit long geht bis ca. n = 66 mit
 *         Binomialkoeffizienten je nach genutzter Fakultaetsfunktion bis ca. n
 *         = 136
 * 
 */
public class PascalsTriangle {

	/**
	 * Diese Methode berechnet die n-te Zeile des Dreiecks rekursiv.
	 * 
	 * @param n
	 *            Welche Zeile ausgegeben werden soll
	 */
	static long[] calcTriangleRecu(int n) {
		n++;
		long[] values = new long[n];
		for (int i = 0; i < n; i++) {
			values[i] = calcTriangleRecu(n, i + 1);
		}
		return values;
	}

	/**
	 * Diese Methode berechnet den Wert an der uebergebenen Position (n/k) im
	 * Pascalschen Dreieck (n = Zeile, k = Spalte). P(n, k) = P(n-1, k-1) +
	 * P(n-1, k)
	 * 
	 * @param n
	 *            Zeile
	 * @param k
	 *            Spalte
	 * @return Wert an der Position in der Zeile n in der Spalte k
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
	 * Diese Methode berechnet die n-te Zeile des Pascalschen Dreiecks iterativ.
	 * P(n, k) = P(n-1, k-1) + P(n-1, k)
	 * 
	 * @param m
	 *            Welche Zeile ausgegeben werden soll
	 * @return 2D Array mit n Zeilen des Pascalschen Dreiecks
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
	 * Diese Methode berechnet die n-te Zeile des Pascalschen Dreiecks iterativ.
	 * Da kein 2D-Array benutzt wurde und immer nur zwei Zeilen benutzt werden,
	 * vergeht zwar mehr Zeit zur Berechnung, aber es wird wesentlich weniger
	 * Speichert benoetigt (embedded).
	 * 
	 * @param n
	 *            Welche Zeile ausgegeben werden soll
	 * @return letzte Zeile des Dreiecks als long-Array
	 */
	public static long[] pascalIterativeOpt(int n) {
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
				Benchmark.ops++;
			}
			lastLine = currentLine;
		}
		return currentLine;
	}

	/**
	 * Diese Methode berechnet die n-te Zeile des Pascalschen Dreiecks mittels
	 * Binomialkoeffizienten.
	 * 
	 * @param n
	 *            Welche Zeile ausgegeben werden soll
	 * @return letzte Zeile des Dreiecks als long-Array
	 */
	static long[] calcBinomial(int n) {
		long[] values = new long[n + 1];
		for (int i = 0; i <= n; i++) {
			values[i] = calcBinomial(n, i);
			Benchmark.ops++;
		}
		return values;
	}

	/**
	 * Diese Methode berechnet einen Wert im Pascalschen Dreieck (n ueber k)
	 * Only for n >= k and n nonnegative (n,k) = n! / k! * (n-k)!
	 * 
	 * @param n
	 *            Zeile
	 * @param k
	 *            Spalte
	 * @return Wert an der Position n ueber k
	 */
	static long calcBinomial(int n, int k) {
		long res = 0;
		if (n >= 0 || n >= k) {
			Benchmark.ops++;
			res = (Factorial.factorial(n))
					/ (Factorial.factorial(k) * Factorial.factorial((n - k)));
		}
		return res;
	}

	/**
	 * Diese Methode berechnet die n-te Zeile des Pascalschen Dreiecks mit
	 * Binomialkoeffizienten. Die Berechnung funktioniert bis n=136
	 * 
	 * @param n
	 *            Welche Zeile ausgegeben werden soll
	 * @return letzte Zeile des Dreiecks als BigInteger-Array
	 */
	public static BigInteger[] pascalBinomialGreaterThan20(int n) {
		BigInteger[] values = new BigInteger[n + 1];
		for (int i = 0; i <= n; i++) {
			values[i] = Factorial.factorialGreaterThan20recursive(n).divide(
					Factorial.factorialGreaterThan20recursive(i).multiply(
							Factorial.factorialGreaterThan20recursive(n - i)));
			Benchmark.ops++;
		}
		return values;
	}

	/**
	 * Diese Methode berechnet die n-te Zeile des Pascalschen Dreiecks nach
	 * einer "interessanten" Formel :) Die Berechnung klappt bis ca. n = 60
	 * 
	 * @param n
	 */
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

	/**
	 * Hilfsmethode zur Ausgabe
	 * 
	 * @param arr
	 *            2D array
	 */
	static void printArr(long[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println();
			for (int j = 0; j < arr[1].length; j++) {
				System.out.print(arr[i][j] + "  ");
			}
		}
	}

	/**
	 * Hilfsmethode zur Ausgabe
	 * 
	 * @param arr
	 *            2D array
	 * @param n
	 */
	static void printArr(int n, long[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[n][i] + "  ");
		}
	}

	/**
	 * Hilfsmethode zur Ausgabe
	 * 
	 * @param arr
	 *            2D array
	 * @param n
	 */
	static void printArr(int n, BigInteger[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[n][i] + "  ");
		}
	}

	/**
	 * Hilfsmethode zur Ausgabe eines Array
	 * 
	 * @param arr
	 */
	static void printArr(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}

	/**
	 * Hilfsmethode zur Ausgabe eines Array
	 * 
	 * @param arr
	 */
	static void printArr(BigInteger[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}

}
