
package a03;



public class Main {

	
	public static void main(String[] args) {
		SlowPrimzahl slow = new SlowPrimzahl();
		FastPrimzahl fast = new FastPrimzahl();
		EratosthenesPrimzahl eratosthenes = new EratosthenesPrimzahl();
		
		boolean[] slowPrimzahlen = slow.findPrimzahlen();
		boolean[] fastPrimzahlen = fast.findPrimzahlen();
		boolean[] eratosthenesPrimzahlen = eratosthenes.findPrimzahlen(100);
		
		System.out.println(eratosthenes.isPrimzahl(97));
		for (int i = 1; i < eratosthenesPrimzahlen.length; i++) {
			if (eratosthenesPrimzahlen[i]) {
				System.out.print(i + "\t");
			}
		}
		
	}
}
