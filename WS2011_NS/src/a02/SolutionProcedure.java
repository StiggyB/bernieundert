package a02;


class Function1 implements Function {
	@Override
	public double f(double x) {
		return (Math.pow(x, 5) + 3 * Math.pow(x, 4) - 5 * Math.pow(x, 3) - 15
				* Math.pow(x, 2) + 4 * x + 12);
	}
	
	@Override
	public String toString(){
		return "xâ�µ + 3xâ�´ - 5xÂ³ - 15xÂ² + 4x + 12";
	}
}

class Function2 implements Function {
	@Override
	public double f(double x) {
		return (Math.sin(2*x) * Math.cos(x/2.));
	}
	
	@Override
	public String toString(){
		return "sin(2x) * cos(x/2)\t";
	}	
}

class Function3 implements Function {
	@Override
	public double f(double x) {
		return (((1/3) * Math.pow(x, 2)) - 2 * x + 1.5);
	}
	
	@Override
	public String toString(){
		return "1/3xÂ² - 2x + 1.5\t";
	}	
}

class Function4 implements Function {
	@Override
	public double f(double x) {
		return (-0.3 * Math.pow(x, 2) + 0.5);
	}
	
	@Override
	public String toString(){
		return "-0.3xÂ² + 0.5\t\t";
	}	
}

public class SolutionProcedure {

	public final static int MAX_INTERV = 100;
	
	public static int iterations = 0;
	

	public static double bisection(double a, double b, Function func) {
		double fValue = 1;
		double mid = 0;
		
		for (int i = 0; Math.abs(a - b) > calcEpsilon() * 2 && i < MAX_INTERV; i++) {
			mid = (a + b) / 2.0;
			fValue = func.f(mid) * func.f(a);
			if (fValue <= 0) {
				b = mid;
			} else { 
				a = mid;
			}
			iterations++;
		}
		return mid;
	}

	public static double regulafalsi(double a, double b, Function func) {
		double x = 0;
		
		for (int i = 0; Math.abs(a - b) >= calcEpsilon() && i < MAX_INTERV; i++) {
			x = a - ((b - a) * func.f(a)) / (func.f(b) - func.f(a));
			if (func.f(x) < 0) {
				a = x;
			} else {
				b = x;
			}
			iterations++;
		}
		return x;
	}
	
	public static double secant(double xn2, double xn1, Function func) {
		double xn3 = 0;
		
		for (int i = 0; Math.abs(xn1 - xn2) >= calcEpsilon() &&  i < MAX_INTERV; i++) {
			xn3 = xn1 - ((xn1 - xn2) / (func.f(xn1) - func.f(xn2))) * func.f(xn1);
			xn2 = xn1;
			xn1 = xn3;
			iterations++;
		}	
		return xn3;
	}
	
    public static double fixpoint(double a, double b, Function f) {
        double x = a;
        
        for (int i = 0; Math.abs(f.f(x)) > 2 * calcEpsilon() && i < MAX_INTERV; i++) {
            x = f.f(x) + x;
            iterations++;
        }
        return x;
    }
    
    private static double calcEpsilon() {
    	double val = 1;
    	double eps = 0;
    	
    	while(val + 1 != 1) {
    		eps = val;
    		val = val / 2;
    	}
    	return eps;
    }
	
}
