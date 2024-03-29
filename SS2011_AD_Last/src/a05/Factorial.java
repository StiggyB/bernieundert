package a05;

import java.math.BigInteger;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         Dies ist eine Helferklasse, die verschiedene Algorithmen zum
 *         berechnen der Fakultaet bereitstellt.
 * 
 */
public class Factorial {

	/**
	 * Diese Methode ist geeignet zur Berechnung von Fakultaeten >20 Ohne
	 * Rekursion geht es schneller, da nicht alle Rekursionsstufen gespeichert
	 * werden muessen. Durch die Nutzung der Klasse BigInteger kann sich die
	 * Laufzeit vergroessern.
	 * 
	 * @param n
	 *            Zu welchem n die Fakultaet berechnet werden soll
	 * @return Wert der Fakultaet der uebergebenen Zahl.
	 */
	public static BigInteger factorialGreaterThan20iterative(int n) {
		BigInteger big = BigInteger.ONE;
		if (n == 0 || n == 1) {
			return big;
		}
		if (n < 0) {
			return BigInteger.valueOf(-1);
		}
		if (n > 1) {
			for (int i = 1; i <= n; i++)
				big = big.multiply(BigInteger.valueOf(i));
		}
		return big;

	}

	/**
	 * Diese Methode ist geeignet zur Berechnung von Fakultaeten bis max. 20
	 * Ohne Rekursion geht es schneller, da nicht alle Rekursionsstufen
	 * gespeichert werden muessen.
	 * 
	 * @param n
	 *            Zu welchem n die Fakultaet berechnet werden soll
	 * @return Wert der Fakultaet der uebergebenen Zahl.
	 */
	public static long factorialMax20iterative(int n) {
		if (n == 0 || n == 1) {
			Benchmark.ops++;
			return 1;
		}
		if (n < 0) {
			return -1;
		}
		long result = 1;
		if (n > 1) {
			for (int i = 1; i <= n; i++) {
				Benchmark.ops++;
				result *= i;
			}
		}
		return result;
	}

	/**
	 * Diese Methode ist geeignet zur Berechnung von Fakultaeten bis max. 20 Die
	 * Berechnung erfolgt Rekursiv, was langsamer als iterativ passiert.
	 * 
	 * @param n
	 *            Zu welchem n die Fakultaet berechnet werden soll
	 * @return Wert der Fakultaet der uebergebenen Zahl.
	 */
	public static long factorialMax20recursive(long n) {
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			return 1;
		} else {
			return n * factorialMax20recursive(n - 1);
		}
	}

	/**
	 * Diese Methode ist geeignet zur Berechnung von Fakultaeten >20 Allerdings
	 * langsamer, als die iterative Variante. Durch die Nutzung der Klasse
	 * BigInteger kann sich die Laufzeit vergroessern.
	 * 
	 * @param n
	 *            Zu welchem n die Fakultaet berechnet werden soll
	 * @return Wert der Fakultaet der uebergebenen Zahl.
	 */
	public static BigInteger factorialGreaterThan20recursive(int n) {
		if (n <= 1) {
			return BigInteger.ONE;
		}
		BigInteger bi = BigInteger.valueOf(n);
		return bi.multiply(factorialGreaterThan20recursive(n - 1));
	}

	/**
	 * Diese Methode berechnet die Fakultaet bis max. 20!
	 * 
	 * @param n
	 *            Zu welchem n die Fakultaet berechnet werden soll
	 * @return Wert der Fakultaet der uebergebenen Zahl.
	 */
	static long factorial(int n) {
		if (n < 0) {
			return -1;
		}
		long res = 1;
		for (int i = 1; i <= n; i++) {
			Benchmark.ops++;
			res *= i;
		}
		return res;
	}

}
