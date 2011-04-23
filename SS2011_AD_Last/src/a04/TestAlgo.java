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
	
	
	static int res = 1;
	/**
	 * 
	 * @param n
	 */
	static void sumRecu(int n) {
		if(n > 1) {
			res += n;
			sumRecu(n-1);
		}
	}
	
	//TODO Fehler bei N = 3!
	/**
	 * Gauss-Summenformel iterative  
	 * 
	 * @param n
	 * @return
	 */
	static int gaussSum(int n) {
		int res = 0;
		for (int i = 1; i < n; i++, n--) {
			res += n + i;
		}
		return res;
	}
	
	static int gaussRes = 0;
	static int i = 1;
	//TODO Fehler bei N = 3!
	/**
	 * Gauss-Summenformel recursive 
	 * 
	 * n * (n + 1) / 2
	 * 1 + n + 2 + (n -1) + 3 + (n -2) + ...
	 * 
	 * @param n
	 */
	static void gaussRecu(int n) {
		if(n > i) {
			gaussRes += n + i; //1+3 -> 4+2 = 6
			i++;
			gaussRecu(n-1);
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
	
	static int cnt2 = 0;
	static void recu2(int i)  {
		if(i+1 >= N) {
			recu2(i-1);
		}
		cnt2++;
	}
	
	public static void main(String[] args) {
		System.out.println("Iterativ :" + sumIter(N));
		sumRecu(N);
		System.out.println("Rekursiv: " + res);
		System.out.println("Iterativ Gauss: " + gaussSum(N));
		gaussRecu(N);
		System.out.println("Rekursiv Gauss: " + gaussRes);
//		recu(N);
//		System.out.println(cnt);
		recu2(N);
		System.out.println(cnt2);
	}
	
}
