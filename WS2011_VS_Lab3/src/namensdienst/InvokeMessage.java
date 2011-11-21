package namensdienst;

import java.io.Serializable;

public class InvokeMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128014230850201840L;
	private String className;
	private String invMethod;
	//TODO Force Serializable Objects with Serializable[]
	private Object[] methodArgs;
	
	public InvokeMessage(String className, String invMethod, Object... methodArgs) {
		this.className = className;
		this.invMethod = invMethod;
		this.methodArgs = methodArgs;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return invMethod;
	}

	public Object[] getMethodArgs() {
		return methodArgs;
	}
	
}
