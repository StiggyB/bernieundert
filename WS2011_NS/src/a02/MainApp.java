package a02;

import java.text.DecimalFormat;

public class MainApp {

	public static void main(String[] args) {

		Function[] f = new Function[4];
		f[0] = new Function1();
		f[1] = new Function2();
		f[2] = new Function3();
		f[3] = new Function4();
		
		String funcs[][] = new String[4][2];
		funcs[0][0] = f[0].toString();
		funcs[0][1] = "[1.5;2.5]";
		funcs[1][0] = f[1].toString();
		funcs[1][1] = "[-3;1.5]";
		funcs[2][0] = f[2].toString();
		funcs[2][1] = "[-3;1.5]";
		funcs[3][0] = f[3].toString();
		funcs[3][1] = "[-3;1.5]";
		
		DecimalFormat df = new DecimalFormat("#0.0000000000");
		//TODO FINISH IT!
		System.out.println("Bisektion:\n");
		System.out.print("Funktion\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i][0] + "\t\t" + funcs[i][1] + "\t" + df.format(SolutionProcedure.bisektion(3.0, 3.5, f[i])));
		}
		
		System.out.println("\n\nSekanten:\n");
		System.out.print("Funktion\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i][0] + "\t\t" + funcs[i][1] + "\t" + df.format(SolutionProcedure.sekanten(3.0, 3.5, f[i])));
		}
		
		System.out.println("\n\nRegula Falsi:\n");
		System.out.print("Funktion\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i][0] + "\t\t" + funcs[i][1] + "\t" + df.format(SolutionProcedure.regulafalsi(3.0, 3.5, f[i])));
		}
		
		System.out.println("\n\nFixpunkt:\n");
		System.out.print("Funktion\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		for (int i = 0; i < f.length; i++) {
			System.out.println(funcs[i][0] + "\t\t" + funcs[i][1] + "\t" + df.format(SolutionProcedure.fixpoint(1.5, 2.5, f[i])));
		}
	}
	
}
