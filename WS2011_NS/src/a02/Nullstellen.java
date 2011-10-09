package a02;

public class Nullstellen {

	
	public static void main(String[] args) {
		
	//TODO Wie sollte man bei allen Verfahren a und b waehlen? 	
	
	/**
	 * 4.
	 * 
	 */
	Polynom p = new Polynom(12, 4, -15, -5, 3, 1);
	double x = 0;
	int fx = (int)Math.pow(x, 5);
	}
	
	public static double bisektion(double x) {
		double a = 0;
		double b = 0;
		
		if (x < 0) {
			a = x;
		} else {
			b = x;
		}
		
		while (a > 0 & b < 0) {
			if ((((a + b) / 2) * a) <= 0) {
				b = (a + b) / 2;
			} else {
				a = (a + b) / 2;
			}
		}
		return a;
	}
	
	
	public static double regulafalsi(double fx) {
		double a = -1;
		double b = 1;
		double c = 0;
		double fa = 0;
		double fb = 0;
		double fc = 0;
		double x = 0;
		
		
		
//		x = ((fb - fa) / b - a) * (x - a) + fa;
		while (x < 0/*crap*/) {
			x = a - ((b - a) * fa) / (fb - fa);
			if (fc < 0) {
				a = c;
			} else {
				b = c;
			}
			
			
		}
		return 0;
	}
	
}
