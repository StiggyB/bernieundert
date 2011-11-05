package a02;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ResultMessage implements Serializable {
	
	private static final long serialVersionUID = -6065578273626197783L;
	private List<BigInteger> results = new ArrayList<BigInteger>();
	
	public ResultMessage(List<BigInteger> result) {
		this.results = result;
	}
	
	public List<BigInteger> getResults() {
		return this.results; 
	}

	@Override
	public String toString() {
		return "ResultMessage [result=" + results + "]";
	}
	
}
