package namensdienst;

import java.io.Serializable;

public class InvokeMessage implements Serializable{

	private String methodName;
	private Object[] methodArgs;
	
	public InvokeMessage(String methodName, Object[] methodArgs) {
		super();
		this.methodName = methodName;
		this.methodArgs = methodArgs;
	}
}
