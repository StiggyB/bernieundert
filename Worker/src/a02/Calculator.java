package a02;

import java.math.BigInteger;
import java.util.Random;

public class Calculator implements Runnable {
	
	public static final int MAX_ITERATIONS = 1000;
	
	private boolean found = false;
	private Worker worker;
	private BigInteger n;
	private BigInteger a = new BigInteger(new Random().nextInt(1000), new Random());
	private BigInteger prime;
	private BigInteger factor;
	private BigInteger result;

	public Calculator(Worker worker, BigInteger n) {
		this.worker = worker;
		this.n = n;
		this.prime = n;
		this.factor = null;
		this.result = BigInteger.ONE;
	}
	
	@Override
	public void run() {
		
//		 do{
//			   b = b.divide(sl.factor);
//			   sl = PollardMethod(b,BigInteger.valueOf(3));
//			   System.out.println(sl.toString());
//			   System.out.println(isPrime(sl.factor));
//			  }while(b != sl.factor);
		
			do {
				prime = prime.divide(result);
				result = pollard(prime);
				System.out.println(result);
			} while (prime != result && result != null);
			System.out.println(prime);
		
		
//		for(int i = 0; i < MAX_ITERATIONS && !(found); i++) {
//			factor = pollard(n);
//			System.out.println("Factor: " + factor);
//			if (factor == null) {
//				found = true;
//			} else {
//				if(this.worker.getFactorList().contains(factor)) {
//					if(isCompletePrim()) {
//						found = true;
//					} else {
//						a = new BigInteger(new Random().nextInt(1000), new Random());
//					}
//				} else if (isPrime(factor)){ // TODO Without isPrime more efficient
//					worker.add(factor);
//				}
//			}
//		}
//		this.worker.pollardFinished();
	}

	// TODO Accept duplicated factors! (e.i. 29 * 29)
	// Maybe more than the searched factors in the factorList...
	private boolean isCompletePrim() {
		boolean bool = false;
		
		for (BigInteger factor : this.worker.getFactorList()) {
			result = result.multiply(factor); 
		}
		if(result.equals(prime)) {
			System.out.println("COMPLETE!");
			bool = true;
		}
		return bool;
	}
	
	private boolean isPrime(BigInteger factor) {
		for (BigInteger i = new BigInteger("2"); factor.compareTo(i) > 0; i = i.add(BigInteger.ONE)) {
			if(factor.mod(i).equals(BigInteger.ZERO)) {
				return false;
			}
		}
		return true;
	}

	private BigInteger pollard(BigInteger n) {
		
		if (n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
			return null;
		}
		
		Random rand = new Random();
		BigInteger x;
		do {
			x = new BigInteger(n.bitCount(), rand);
		} while (x.compareTo(n) > 0);
		BigInteger y = x;
		BigInteger p = BigInteger.ONE;
		BigInteger d = BigInteger.ZERO;
		do {
			x = (x.multiply(x).add(a).mod(n));
			y = (y.multiply(y).add(a).mod(n));
			y = (y.multiply(y).add(a).mod(n));
			d = (y.subtract(x).mod(n));
			p = n.gcd(d);
		} while (p.equals(BigInteger.ONE));

		if (!(p.equals(n))) {
			return p;
		}
		return null;
	}
}
