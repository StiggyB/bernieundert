package a05;

public class Helper {

	static long time1;
	static long time2;
	
	public static void printOps(int n) {
		
		System.out.println("Operations & Duration of the algorithms: \n");
			
		// --------------------------------------------------------------
		System.out.println("Complexity N: ");
		for (int i = 1; i <= n; i++) {
			System.out.print(i + " ");
		}
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle recursiv: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < 25; i++) {
			Benchmark.ops = 0;
			PascalsTriangle.calcTriangleRecu(i);
			System.out.print(Benchmark.ops + " ");
		}	
		System.out.println();
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle iterativ: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			Benchmark.ops = 0;
			PascalsTriangle.calcTriangleIter(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle iterativ Embedded-Sys optimal: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			Benchmark.ops = 0;	
			PascalsTriangle.pascalIterativeOpt(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle binomial - max N: 136:: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			Benchmark.ops = 0;
			PascalsTriangle.pascalBinomialGreaterThan20(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle binomial: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			Benchmark.ops = 0;
			PascalsTriangle.calcBinomial(i);	
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle special binomial: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			Benchmark.ops = 0;
			PascalsTriangle.pascalSpecial(i);
			System.out.print(Benchmark.ops + " ");
		}
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
	}	

	public static void printTriangles(int n) {

		System.out.println("\n\nPascals Triangles with the nth row: \n");
		
		// --------------------------------------------------------------
		System.out.println("Pascals Triangle recursiv: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		PascalsTriangle.printArr(PascalsTriangle.calcTriangleRecu(n));
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle iterativ: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		PascalsTriangle.printArr(n, PascalsTriangle.calcTriangleIter(n));
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle iterativ faster: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		PascalsTriangle.printArr(PascalsTriangle.pascalIterativeOpt(n));
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle binomial - max N: 136: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		PascalsTriangle.printArr(PascalsTriangle.pascalBinomialGreaterThan20(n));
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle binomial: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		PascalsTriangle.printArr(PascalsTriangle.calcBinomial(n));
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
		
		// --------------------------------------------------------------
		System.out.println("\n\nPascals Triangle special binomial: ");
		Benchmark.ops = 0;
		time1 = System.currentTimeMillis();
		PascalsTriangle.pascalSpecial(n);
		time2 = System.currentTimeMillis();
		System.out.println("\nDuration: " + (time2 - time1));
	}
	
}
