package namensdienst;

import java.io.Serializable;
import java.lang.reflect.Method;

public class InvokeMessage implements Serializable{

	private String className;
	private Method invMethod;
	private Object[] methodArgs;
	
	public InvokeMessage(String className, Method methodName, Object[] methodArgs) {
		this.className = className;
		this.invMethod = methodName;
		this.methodArgs = methodArgs;
	}

	public String getClassName() {
		return className;
	}

	public Method getInvMethod() {
		return invMethod;
	}

	public Object[] getMethodArgs() {
		return methodArgs;
	}
	
}
