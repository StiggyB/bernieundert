package a02;

import java.io.Serializable;
import java.math.BigInteger;

public class CalculateMessage implements Serializable {

	private static final long serialVersionUID = 840244832287440949L; 
	private BigInteger n;
	private long cpuTime;
	

	public CalculateMessage(BigInteger n, long CPUTime) { 
		this.n = n;
		this.cpuTime = CPUTime;
	}
	
	public BigInteger getN() { 
		return this.n;
	}
	
	@Override
	public String toString() {
		return "CalculateMessage [n = " + n + "]";
	}

	public long getCPUTime() {
		return this.cpuTime;
	}
}
