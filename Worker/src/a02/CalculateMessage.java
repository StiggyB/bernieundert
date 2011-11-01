package a02;

import java.io.Serializable;
import java.math.BigInteger;

public class CalculateMessage implements Serializable {

	private static final long serialVersionUID = 840244832287440949L; 
	private BigInteger b;
	private BigInteger a;
	
	public CalculateMessage(BigInteger a, BigInteger b) { 
		this.b = b;
		this.a = a;
	}
	
	public BigInteger getB() { 
		return b;
	}
	
	public BigInteger getA() { 
		return a;
	}
}
