package a02;


class Function1 implements Function {
	@Override
	public double f(double x) {
		return (Math.pow(x, 5) + 3 * Math.pow(x, 4) - 5 * Math.pow(x, 3) - 15
				* Math.pow(x, 2) + 4 * x + 12);
	}
	
	@Override
	public String toString(){
		return "x^5 + 3x^4 - 5x^3 - 15x^2 + 4x + 12";
	}
}

class Function2 implements Function {
	@Override
	public double f(double x) {
		return (Math.sin(2*x) * Math.cos(x/2.));
	}
	
	@Override
	public String toString(){
		return "sin(2x) * cos(x/2)\t\t";
	}	
}

class Function3 implements Function {
	@Override
	public double f(double x) {
		return (((1/3) * Math.pow(x, 2)) - 2 * x + 2);
	}
	
	@Override
	public String toString(){
		return "1/3x^2 - 2x + 2\t\t";
	}	
}

class Function4 implements Function {
	@Override
	public double f(double x) {
		return (-0.3 * Math.pow(x, 2) + 0.5);
	}
	
	@Override
	public String toString(){
		return "-0.3x^2 + 0.5\t\t\t";
	}	
}

class Function5 implements Function {
	@Override
	public double f(double x) {
		return (Math.pow(x, 2) - 4);
	}
	
	@Override
	public String toString(){
		return "x^2 - 4";
	}
}

public class SolutionProcedure {

	public final static int MAX_INTERV = 100;
	
	public static int iterations = 0;
	

	public static double bisection(double a, double b, Function func) {
		double fValue = 1;
		double mid = 0;
		
		for (int i = 0; Math.abs(b - a) > calcEpsilon() * 2 && i < MAX_INTERV; i++) {
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
		
		for (int i = 0; Math.abs(b - a) >= calcEpsilon() * 2 && i < MAX_INTERV; i++) {
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
	
	public static double secant(double bIntervall, double aIntervall, Function func) {
//		double xn3 = 0;
//		
//		for (int i = 0; Math.abs(xn1 - xn2) > calcEpsilon() * 2 &&  i < MAX_INTERV; i++) {
//			xn3 = xn1 - ((xn1 - xn2) / (func.f(xn1) - func.f(xn2))) * func.f(xn1);
//			xn2 = xn1;
//			xn1 = xn3;
//			iterations++;
//		}	
//		return xn1;
        double[] x = new double[2];
        double next;
        double eps;
        int i = 0;
            
        x[0] = aIntervall;
        x[1] = bIntervall;
        eps = calcEpsilon();
        
        while(Math.abs(x[1]-x[0]) > eps*2 && i < MAX_INTERV) {
            next = x[1]-((x[1]-x[0])/(func.f(x[1])-func.f(x[0]))*func.f(x[1]));
            
            x[0] = x[1];
            x[1] = next;
            i++;
        }
        
        
        return x[1];
	}
	
    public static double fixpoint(double a, double b, Function func) {
        double x = a;
        
        for (int i = 0; Math.abs(func.f(x)) > calcEpsilon() * 2 && i < MAX_INTERV; i++) {
            x = func.f(x) + x;
            iterations++;
        }
        return x;
    }
    
    private static double calcEpsilon() {
    	double val = 1;
    	double eps = 1;
    	
    	while(val + 1 != 1) {
    		eps = val;
    		val = val / 2;
    	}
    	return eps;
    }
	
}
