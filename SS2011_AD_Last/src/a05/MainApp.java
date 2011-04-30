package a05;

public class MainApp {

	final static int N = 20;
	final static int M = 20;
	
	public static void main(String[] args) {		
		long time1;
		long time2;
		
		// --------------------------------------------------------------
		System.out.println("Pascals Triangle recursiv: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		
		for (int i = 0; i < M; i++) {
			Benchmark.ops = 0;
//				System.out.print(PascalsTriangle.calcTriangleRecu(N+1, j) + "  ");
				PascalsTriangle.calcTriangleRecu(i);
			System.out.print(Benchmark.ops + " ");
		}	
		System.out.println();
		for (int i = 0; i <= M; i++) {
			System.out.print(i + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nZeit: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle iterativ: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			Benchmark.ops = 0;
//			PascalsTriangle.printArr(PascalsTriangle.calcTriangleIter(N), N);
			PascalsTriangle.calcTriangleIter(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nZeit: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle iterativ faster: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			Benchmark.ops = 0;
//			PascalsTriangle.pascalIterativeSlow(N);
			PascalsTriangle.pascalIterativeFaster(i);
			System.out.print(Benchmark.ops + " ");
			
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nZeit: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle binomial Tugend: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i <= M; i++) {
			Benchmark.ops = 0;
//			PascalsTriangle.pascalBinomialMax20(N);
			PascalsTriangle.pascalBinomialGreaterThan20(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nZeit: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle binomial: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			Benchmark.ops = 0;
			for (int j = 0; j <= i; j++) {
//				System.out.print(PascalsTriangle.calcBinomial(N, j) + "  ");
				PascalsTriangle.calcBinomial(i, j);	
			}
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nZeit: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle special binomial: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			Benchmark.ops = 0;
//			PascalsTriangle.pascalSpecial(N);
			PascalsTriangle.pascalSpecial(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nZeit: " + (time2 - time1));
	}
}
