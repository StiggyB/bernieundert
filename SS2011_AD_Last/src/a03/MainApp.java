package a03;

public class MainApp {
	
	private static final int N = 1000;
	
	public static void main(String[] args) {
//		Primes.printArray(Primes.findPrimeSlow(N));
//		Primes.printArray(Primes.findPrimeFaster(N));
//		Primes.printArray(Primes.findPrimeBySieve(N));
		
		
//		System.out.println(Primes.isPrim(998));
		
		System.out.println("Langsames Primzahlen suchen:");
		for (int i = 0; i <= N; i++) {
			Primes.findPrimeSlow(i);
			System.out.println("Problemgroesse: " + i + "\tAufwand: " + Benchmark.count);
		}
		System.out.println("Schnelles Primzahlen suchen:");
		for (int i = 0; i <= N; i++) {
			Primes.findPrimeFaster(i);
			System.out.println("Problemgroesse: " + i + "\tAufwand: " + Benchmark.count);
		}
		System.out.println("Sieb des Eratostenes suchen:");
		for (int i = 0; i <= N; i++) {
			Primes.findPrimeBySieve(i);
			System.out.println("Problemgroesse: " + i + "\tAufwand: " + Benchmark.count);
		}
		
		System.out.println("Primzahleigenschaft feststellen:");
		for (int i = 0; i <= N; i++) {
			Primes.isPrime(i);
			System.out.println("Problemgroesse: " + i + "\tAufwand: " + Benchmark.count);
		}
		
		
	}
	
	
}
