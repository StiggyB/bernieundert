package a03;

public class SlowPrimzahl {
	private static final int MAX = 100;

	public boolean[] findPrimzahlen() {
		boolean[] a = new boolean[MAX];
		
		for (int i = 2; i < MAX; i++) {
			a[i] = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					a[i] = false;
					break;
				}
			}
		}
		return a;
	}
	
}
