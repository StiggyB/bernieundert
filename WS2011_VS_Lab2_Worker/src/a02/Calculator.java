package a02;

/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 02<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import java.math.BigInteger;
import java.util.Random;

public class Calculator implements Runnable {

	private boolean isRunning;
	private Worker worker;
	private BigInteger a = new BigInteger(new Random().nextInt(1000),
			new Random());
	private BigInteger prime;

	private BigInteger result;

	public Calculator(Worker worker, BigInteger n) {
		this.isRunning = true;
		this.worker = worker;
		this.prime = n;
		this.result = BigInteger.ONE;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public BigInteger getPrime() {
		return prime;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < worker.getMaxIterations() && isRunning; i++) {
			result = pollard(prime);
			if (result == null) {
				isRunning = false;
				if (!worker.getFactorList().isEmpty()) {
					worker.pollardFinished(prime, System.currentTimeMillis()
							- startTime);
				} else {
					worker.pollardFinished(result, System.currentTimeMillis()
							- startTime);
				}
			} else if (isPrime(result)) {
				isRunning = false;
				worker.pollardFinished(result, System.currentTimeMillis()
						- startTime);
			}
		}
	}

	synchronized public static boolean isPrime(BigInteger number) {
		BigInteger two = new BigInteger("2");
		if (number.compareTo(two) < 0) {
			return false;
		} else if (number.equals(two)) {
			return true;
		} else {
			if (number.mod(two).equals(BigInteger.ZERO)) {
				return false;
			}
			BigInteger sqrt = sqrt(number);
			for (BigInteger i = new BigInteger("3"); i.compareTo(sqrt) <= 0; i = i
					.add(two)) {
				if (number.mod(i).equals(BigInteger.ZERO)) {
					return false;
				}
			}
			return true;
		}
	}

	synchronized private static BigInteger sqrt(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8"))
				.toString());
		while (b.compareTo(a) >= 0) {
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
			if (mid.multiply(mid).compareTo(n) > 0)
				b = mid.subtract(BigInteger.ONE);
			else
				a = mid.add(BigInteger.ONE);
		}
		return a.subtract(BigInteger.ONE);
	}

	synchronized private BigInteger pollard(BigInteger n) {
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
