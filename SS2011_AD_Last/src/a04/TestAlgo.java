package a04;

public class TestAlgo {

	private final static int N = 3;
	
	
	/**
	 * 1 + 2 + ... + n
	 * 
	 * @param n
	 * @return
	 */
	static int sumIter(int n) {
		int res = 0;
		for (int i = 1; i <= n; i++) {
			res += i;
		}
		return res;
	}
	
	static int res = 0;
	/**
	 * n * (n + 1) / 2
	 * 
	 * @param n
	 */
	static void sumRecu(int n) {
		if(n > 0) {
			res += n--;
			sumRecu(n);
		}
	}
	
	static int cnt = 0;
	/**
	 * Loesung aus der Vorlesung (falsch) 
	 * 
	 * @param i
	 */
	static void recu(int i)  {
		if(i <= N) {
			recu(i-1);
		}
		cnt++;
	}
	
	public static void main(String[] args) {
		System.out.println("Iterativ :" + sumIter(3));
		sumRecu(3);
		System.out.println("Rekursiv: " + res);
//		recu(3);
//		System.out.println(cnt);
	}
	
}
