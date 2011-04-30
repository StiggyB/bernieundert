package a05;

public class MainApp {

	final static int N = 20;
	final static int M = 200;
	
	public static void main(String[] args) {		
		long time1;
		long time2;
		
		System.out.println("\nPascals Triangle recursiv: \n");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(PascalsTriangle.calcTriangleRecu(N+1, j) + "  ");
			}
			System.out.println();
		}	
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		
		System.out.println("\n\nPascals Triangle iterativ: ");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			PascalsTriangle.printArr(PascalsTriangle.calcTriangleIter(N), N);
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
		
		System.out.println("\n\nPascals Triangle binomial: ");
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j <= N; j++) {
				System.out.print(PascalsTriangle.calcBinomial(N, j) + "  ");
			}
			System.out.println();
		}
		time2 = System.currentTimeMillis();
		System.out.println();
		System.out.println("Zeit: " + (time2 - time1));
	}
}
