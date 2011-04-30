package a05;

public class PascalsTriangle {
	
	/**
	 * P(n, k) = P(n-1, k-1) + P(n-1, k)
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	static int calcTriangleRecu(int n, int k) {
//		System.out.println("\nVales: " + n + ", " + k);
		if (n < k || k < 0) {
			return 0;
		}
		if (k == 1 || k == n) {
			return 1;		
		}
		return calcTriangleRecu(n-1, k-1) + calcTriangleRecu(n-1, k);
	}
	
	/**
	 * P(n, k) = P(n-1, k-1) + P(n-1, k)
	 * 
	 */
	static long[][] calcTriangleIter(int m) {
		long [][] values = new long[m+1][m+1];
		for (int n = 0; n < values.length; n++) {
			for (int k = 0; k <= n; k++) {
				if(k == 0 || k == n) {
					values[n][k] = 1;
				} else {
					values[n][k] = values[n-1][k-1] + values[n-1][k];
				}
			}
		}
		return values;
	}
	
	/**
	 * Only for n >= k and n nonnegative
	 * (n,k) = n! / k! * (n-k)!
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	static long calcBinomial(int n, int k) {
		long res = 0;
		if(n >= k || n >= 0) {
			res = (factor(n)) / (factor(k) * factor((n-k)));
		}
		return res;
	}
	
	/**
	 * @param n
	 * @return
	 */
	static long factor(int n) {
		long res = 1;
		for (int i = 1; i <= n; i++) {
			res *= i;
		}
		return res;
	}
	
	static void printArr(long[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println();
			for (int j = 0; j < arr[1].length; j++) {
				System.out.print(arr[i][j] + "  ");
			}
		}
	}
	
	static void printArr(long[][] arr, int n) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[n][i] + "  ");
		}
	}
	
}
