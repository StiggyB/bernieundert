package a02;

import java.io.Serializable;
import java.math.BigInteger;

public class CalculateMessage implements Serializable {

	private static final long serialVersionUID = 840244832287440949L; 
	private BigInteger n;
	
	public CalculateMessage(BigInteger n) { 
		this.n = n;
	}
	
	public BigInteger getN() { 
		return n;
	}
}
