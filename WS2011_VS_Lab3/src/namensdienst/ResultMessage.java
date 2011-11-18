package namensdienst;

import java.io.Serializable;

public class ResultMessage implements Serializable{

	private Object result;


	public ResultMessage(Object result) {
		super();
		this.result = result;
	}
	
	public Object getResult() {
		return result;
	}
}
