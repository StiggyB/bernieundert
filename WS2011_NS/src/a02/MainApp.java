package a02;

import java.text.DecimalFormat;

public class MainApp {

	public static void main(String[] args) {

		Function[] f = new Function[4];
		f[0] = new Function1();
		f[1] = new Function2();
		f[2] = new Function3();
		f[3] = new Function4();
		
		String funcs[] = new String[4];
		double borders[][] = new double[4][2];
		funcs[0] = f[0].toString();
		borders[0][0] = -4.;
		borders[0][1] = 3.;
		funcs[1] = f[1].toString();
		borders[1][0] = (2*Math.PI + 1.0/4.0 * Math.PI);
		borders[1][1] = (2*Math.PI - 1.0/4.0 * Math.PI);
		funcs[2] = f[2].toString();
		borders[2][0] = 5.;
		borders[2][1] = 5.3;
		funcs[3] = f[3].toString();
		borders[3][0] = 1.;
		borders[3][1] = 1.5;
		
		DecimalFormat df = new DecimalFormat("#0.0000000000");
		DecimalFormat dfinter = new DecimalFormat("#0.00");
		//TODO FINISH IT!
		System.out.println("Bisektion:\n");
		System.out.print("Funktion\t\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i] + "\t\t" + dfinter.format(borders[i][0]) + "; " + dfinter.format(borders[i][1]) + "\t" + df.format(SolutionProcedure.bisection(borders[i][0], borders[i][1], f[i])) + "\t\t" + SolutionProcedure.iterations);
			SolutionProcedure.iterations = 0;
		}
		
		System.out.println("\n\nSekanten:\n");
		System.out.print("Funktion\t\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i] + "\t\t" + dfinter.format(borders[i][0]) + "; " + dfinter.format(borders[i][1]) + "\t" + df.format(SolutionProcedure.secant(borders[i][0], borders[i][1], f[i])) + "\t\t" + SolutionProcedure.iterations);
			SolutionProcedure.iterations = 0;
		}
		
		System.out.println("\n\nRegula Falsi:\n");
		System.out.print("Funktion\t\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i] + "\t\t" + dfinter.format(borders[i][0]) + "; " + dfinter.format(borders[i][1]) + "\t" + df.format(SolutionProcedure.regulafalsi(borders[i][0], borders[i][1], f[i])) + "\t\t" + SolutionProcedure.iterations);
			SolutionProcedure.iterations = 0;
		}
		
		System.out.println("\n\nFixpunkt:\n");
		System.out.print("Funktion\t\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i] + "\t\t" + dfinter.format(borders[i][0]) + "; " + dfinter.format(borders[i][1]) + "\t" + df.format(SolutionProcedure.fixpoint(borders[i][0], borders[i][1], f[i])) + "\t\t" + SolutionProcedure.iterations);
			SolutionProcedure.iterations = 0;
		}
	}
	
}
