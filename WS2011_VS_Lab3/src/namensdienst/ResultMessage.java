package namensdienst;

import java.io.Serializable;

public class ResultMessage implements Serializable{

	private Object result;
	private Exception[] remoteExceptions;

	public ResultMessage(Object result) {
		super();
		this.result = result;
	}
	
	public Object getResult() {
		return result;
	}
	
	public Exception[] getRemoteExceptions() {
		return remoteExceptions;
	}
}
