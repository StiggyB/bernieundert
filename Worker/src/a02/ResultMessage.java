package a02;

import java.io.Serializable;

public class ResultMessage implements Serializable {
	
	private static final long serialVersionUID = -6065578273626197783L;
	private Integer result;
	
	public ResultMessage(Integer result) {
		this.result = result; 
	}
	
	public Integer getResult() {
		return this.result; 
	}

	@Override
	public String toString() {
		return "ResultMessage [result=" + result + "]";
	}
	
}
