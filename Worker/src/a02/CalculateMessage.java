package a02;

import java.io.Serializable;
import java.math.BigInteger;

public class CalculateMessage implements Serializable {

	private static final long serialVersionUID = 840244832287440949L; 
	private BigInteger n;
	private int nThreads;
	
	public int getnThreads() {
		return nThreads;
	}

	public CalculateMessage(BigInteger n, int nThreads) { 
		this.n = n;
		this.nThreads = nThreads;
	}
	
	public BigInteger getN() { 
		return n;
	}

	@Override
	public String toString() {
		return "CalculateMessage [n=" + n + ", nThreads=" + nThreads + "]";
	}
	
}
