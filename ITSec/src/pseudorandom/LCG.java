package pseudorandom;

public class LCG {

	// Infos
	// http://www.mathematik.uni-ulm.de/stochastik/lehre/ss03/markov/skript/node26.html
	// http://maettig.com/?page=Studium/Zufallszahlen

	// Chiang, Hwang, Kao
	private final long a = 19496;
	private final long b = 0;
	private final long N = (long) (Math.pow(2., 15.) - (long) 19);

	// Berkeley UNIX Pascal
	// private final long a = 62605;
	// private final long b = 113218009;
	// private final long N = (long) Math.pow(2., 29.);

	private long x0;

	public LCG(long seed) {
		x0 = seed;
	}

	public double nextValue() {
		x0 = (a * x0 + b) % N;
		return (double) x0 / N;
	}

}
