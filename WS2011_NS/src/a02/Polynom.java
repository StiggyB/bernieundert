package a02;

public class Polynom {

	private int koeff[];
	
	public Polynom(int ...x) {
		for (int i = 0; i < x.length; i++) {
			koeff[i] = (int) Math.pow(x[i], i);
		}
	}
	
}
