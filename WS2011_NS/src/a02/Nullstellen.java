package a02;

import java.text.DecimalFormat;

class Functions implements Function {
	public double f(double x) {
		return (Math.pow(x, 5) + 3 * Math.pow(x, 4) - 5 * Math.pow(x, 3) - 15
				* Math.pow(x, 2) + 4 * x + 12);
	}
}

class Function2 implements Function {
	public double f(double x) {
		return (Math.sin(2*x) * Math.cos(x/2.));
	}
	
}

class Function3 implements Function {
	public double f(double x) {
		return (((1/3) * Math.pow(x, 2)) - 2 * x + 1.5);
	}
	
}

class Function4 implements Function {
	public double f(double x) {
		return (-0.3 * Math.pow(x, 2) + 0.5);
	}
	
}

public class Nullstellen {

	public final static int MAX_INTERV = 1000;
	
	public static void main(String[] args) {

		Function f1 = new Functions();
		Function f2 = new Functions();
		Function f3 = new Functions();
		Function f4 = new Functions();
		DecimalFormat df = new DecimalFormat("#0.0000000000");
		System.out.println("Bisektion:\n");
		System.out.print("Funktion\t\t\t\t\tIntervall\tNullstelle\t\tIterationen:\n");
		System.out.print("x^5 + 3x^4 - 5x^3 - 15x^2 + 4x +12\t");
		System.out.println("\t[test; test]\t" + df.format(bisektion(3.0, 3.5, 0.0000000001, f1)));
		System.out.println("\nregulafalsi: " + df.format(regulafalsi(1.5, 2.6, 0.0000000001, f2)));
		System.out.println("sekanten: " + df.format(sekanten(1.5, 2.7, 0.0000000001, f3)));
		System.out.println("sekanten: " + df.format(sekanten(0.5, 1.0, 0.0000000001, f4)));
	}

	public static double bisektion(double a, double b, double eps, Function func) {
		double fValue = 1;
		double mid = 0;
		while (Math.abs(a - b) >= eps) {
			mid = (a + b) / 2.0;
			fValue = func.f(mid) * func.f(a);
			if (fValue <= 0) {
				b = mid;
			} else {
				a = mid;
			}
		}
		return mid;
	}

	public static double regulafalsi(double a, double b, double eps, Function func) {
		double x = 0;
		for (int i = 0; Math.abs(a - b) >= eps && MAX_INTERV > i; i++) {
			x = a - ((b - a) * func.f(a)) / (func.f(b) - func.f(a));
			if (func.f(x) < 0) {
				a = x;
			} else {
				b = x;
			}
		}
		return x;
	}
	
	public static double sekanten(double xn2, double xn1, double eps, Function func) {
		double xn3 = 0;
		while (Math.abs(xn1 - xn2) >= eps) {
			xn3 = xn1 - ((xn1 - xn2) / (func.f(xn1) - func.f(xn2))) * func.f(xn1);
			xn2 = xn1;
			xn1 = xn3;
		}	
		return xn3;
	}
	
	public static double fixpoint(int bla){
		return 0;
	}
	
	
}
