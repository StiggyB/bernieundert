package a02;

import java.math.BigInteger;
import java.util.Random;

public class Calculator implements Runnable {
	private boolean running = false;
	private BigInteger n;
	private BigInteger a;
	
	public Calculator(BigInteger n, BigInteger a) {
		this.n = n;
		this.a = a;
	}

	private Integer calculate(BigInteger n, BigInteger a) {
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		
		if (n.mod(new BigInteger("2")).equals(zero)) {
			return null;
		}
		
		Random rand = new Random();
		BigInteger x = new BigInteger(n.bitCount(), rand);
		System.out.println("Radnom value: " + x);
		BigInteger y = x;
		BigInteger p = one;
		BigInteger d = zero;
		do {
			x = (x.multiply(x).add(a).mod(n));
			y = (y.multiply(y).add(a).mod(n));
			y = (y.multiply(y).add(a).mod(n));
			d = (y.subtract(x).mod(n));
			p = ggt(d, n);
		} while (p.equals(one));

		if (!(p.equals(n))) {
			return p.intValue();
		}
		return null;
	}

	private BigInteger ggt(BigInteger d, BigInteger n) {
		BigInteger bi = new BigInteger(("" + d));
		return bi.gcd(new BigInteger("" + n));
	}

	@Override
	public void run() {

		while(!running) {
			if(calculate(n, a) == null) {
				running = true;
			}
		}
		
	}
}
