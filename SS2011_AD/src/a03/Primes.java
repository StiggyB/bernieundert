package a03;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         In dieser Klasse befinden sich vier Algorithmen zum
 *         Primzhalberechnen, weiterhin eine Methode um die Primzhaleigenschaft
 *         einer einzelnen Zahl zu pruefen.
 * 
 */
public class Primes {

	/**
	 * Diese Methode stellt den langsamsten Primzahlalgorithmus dar
	 * 
	 * @param n		Problemgroesse, die die zu berechnenden Primzahlen vor gibt
	 *         
	 * @return Liefert ein bool-Array, anhand des Laufindex ist markiert, welche
	 *         Zahlen Primzahlen sind
	 */
	public static boolean[] findPrimeSlow(int n) {
		Benchmark.count = 0;
		boolean arr[] = new boolean[n];
		for (int i = 2; i < n; i++) {
			arr[i] = true;
			for (int j = 2; j < n; j++) {
				if ((i % j == 0) && (j != i)) {
					arr[i] = false;
				}
				Benchmark.count++;
			}
		}
		return arr;
	}

	/**
	 * In dieser Methode ist der Primzahlalgorithmus bereits leicht verbessert,
	 * die innere Schleife laeuft nur noch bis zum Laufindex der aeusseren
	 * Schleife
	 * 
	 * @param n		Problemgroesse, die die zu berechnenden Primzahlen vor gibt
	 *           
	 * @return Liefert ein bool-Array, anhand des Laufindex ist markiert, welche
	 *         Zahlen Primzahlen sind
	 */
	public static boolean[] findPrimeMiddleFast(int n) {
		boolean[] arr = new boolean[n];
		Benchmark.count = 0;

		for (int i = 2; i < n; i++) {
			arr[i] = true;
			for (int j = 2; j < i; j++) {
				Benchmark.count++;
				if (i % j == 0) {
					arr[i] = false;
					break;
				}
			}
		}
		return arr;
	}

	/**
	 * In dieser Methode ist der Primzahlalgorithmus weiter verbessert, die
	 * innere Schleife laeuft nur noch bis zur Wurzel der aeusseren Schleife und
	 * weiterhin werden in der aeusseren Schleife nur ungerade Zahlen
	 * durchlaufen.
	 * 
	 * @param n		Problemgroesse, die die zu berechnenden Primzahlen vor gibt
	 *           
	 * @return Liefert ein bool-Array, anhand des Laufindex ist markiert, welche
	 *         Zahlen Primzahlen sind
	 */
	public static boolean[] findPrimeFaster(int n) {
		Benchmark.count = 0;
		boolean arr[] = new boolean[n];
		if (n > 2) {
			arr[2] = true;
		}
		for (int i = 3; i < n - 1; i += 2) {
			arr[i] = true;
			arr[i + 1] = false;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				Benchmark.count++;
				if (i % j == 0) {
					arr[i] = false;
					break;
				}
			}
		}
		return arr;
	}

	/**
	 * Diese Methode stellt den Primzahlalgorithmus "Das Sieb des Eratosthenes"
	 * dar. Hier werden alle vielfachen einer gefundenen Primzahl gestrichen
	 * (false).
	 * 
	 * @param n 	Problemgroesse, die die zu berechnenden Primzahlen vor gibt
	 * @return Liefert ein bool-Array, anhand des Laufindex ist markiert, welche
	 *         Zahlen Primzahlen sind
	 */
	public static boolean[] findPrimeBySieve(int n) {
		Benchmark.count = 0;
		boolean[] arr = new boolean[n];
		int i;
		for (i = 2; i < n; i++) {
			arr[i] = true;
		}
		for (i = 2; i < Math.sqrt(n); i++) {
			if (arr[i] == true) {
				for (int j = 2; i * j < n; j++) {
					arr[i * j] = false;
					Benchmark.count++;
				}
			}
		}

		return arr;
	}

	/**
	 * Diese Methode prueft fuer ein uebergebenes n, ob die Zahl eine Primzahl ist.
	 *  
	 * @param n Zu pruefende Zahl
	 * @return true, wenn die Zahl eine Primzahl ist, andernfalls false
	 */
	public static boolean isPrime(int n) {
		Benchmark.count = 0;
		boolean res = true;
		if (n < 2) {
			res = false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			Benchmark.count++;
			if (n % i == 0) {
				res = false;
				break;
			}
		}
		return res;
	}

	/**
	 * Dies ist lediglich eine Hilfsmethode zum Ausgeben eines bool-Array
	 * 
	 * @param arr Auszugebenes Array vom Typ bool 
	 */
	public static void printArray(boolean... arr) {
		int i = 0;
		for (boolean b : arr) {
			System.out.println(i++ + ": " + "[" + b + "] ");
		}
	}
}
