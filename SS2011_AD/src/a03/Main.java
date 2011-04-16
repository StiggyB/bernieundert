package a03;

public class Main {

	private static final int MAX = 100;

	public static void main(String[] args) {
		SlowPrimzahl slow = new SlowPrimzahl();
		FastPrimzahl fast = new FastPrimzahl();
		EratosthenesPrimzahl eratosthenes = new EratosthenesPrimzahl();

		for (int i = 0; i < 5001; i += 47) {
			System.out.print(i + " ");

		}
		System.out.println();

		for (int i = 0; i < 5001; i += 47) {
			slow.findPrimzahlen(i);
			// fast.findPrimzahlen(i);
			// eratosthenes.findPrimzahlen(i);
			System.out.print(Benchmark.ops + " ");
		}

		System.out.println();

		for (int i = 1; i < 7001; i += 41) {
			System.out.print(i + " ");
		}

		System.out.println();

		for (int i = 1; i < 7001; i += 41) {
			fast.isPrime(i);
			System.out.print(Benchmark.ops + " ");
		}
	}
}

/*
plot(c,d)
ylabel('Anzahl der Operationen: T(N)')
xlabel('Problemgröße: N')
title('Einzelabfrage, ob N eine Primzahl ist')
grid on
*/