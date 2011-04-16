package a03;

public class Primes {
	
	public static boolean[] findPrimeSlow(int n) {
		Benchmark.count = 0;
		boolean arr[] = new boolean[n];
		for (int i = 2; i < n; i++) {
			arr[i] = true;
			for (int j = 2; j < n ; j++) {
				if((i % j == 0) && (j != i)) {
					arr[i] = false;
				}
				Benchmark.count++;
			}
		}
		return arr;
	}
	
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
	
	public static boolean[] findPrimeFaster(int n) {
		Benchmark.count = 0;
		boolean arr[] = new boolean[n];
		if(n > 2) {
		arr[2] = true;
		}
		for (int i = 3; i < n-1; i += 2) {
			arr[i] = true;
			arr[i+1] = false;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				Benchmark.count++;
				if(i % j == 0) {
					arr[i] = false;
					break;
				}
			}
		}
		return arr;
	}
	
	public static boolean[] findPrimeBySieve(int n) {
		Benchmark.count = 0;
		boolean[] arr = new boolean[n];
		int i;
		for (i = 2; i < n; i++) {
			arr[i] = true;
		}
		for(i = 2; i < Math.sqrt(n); i++) {
			if(arr[i] == true) {
				for(int j = 2; i * j < n; j++) {
					arr[i * j] = false;
					Benchmark.count++;
				}
			}
		}
		
		return arr;
	}
	
	public static boolean isPrime(int n) {
		Benchmark.count = 0;
		boolean res = true;
		if(n < 2) {
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
	
	
	
	public static void printArray(boolean... arr) {
		int i = 0;
		for (boolean b : arr) {
			System.out.println(i++ + ": "+ "[" + b + "] ");
		}
	}
}
