package a02;

import java.io.Serializable;
import java.math.BigInteger;

public class CheckMessage implements Serializable {

	private static final long serialVersionUID = 840244832287440949L; 
	private BigInteger n;


	public CheckMessage(BigInteger n) { 
		this.n = n;
	}
	
	public BigInteger getN() { 
		return n;
	}
}
