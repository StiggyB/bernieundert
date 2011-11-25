package branch_access;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import messages.InvokeMessage;
import messages.ResultMessage;
import tcp_advanced.Connection;

public class ManagerWorker implements Runnable {

	private Connection connection;
	private Object remoteResult;
	private InvokeMessage iMsg;
	private Manager manager;
	private Thread thread;

	public ManagerWorker(Connection c, InvokeMessage iMsg, Manager manager) {
		this.thread = new Thread(this);
		this.iMsg = iMsg;
		this.manager = manager;
		this.connection = c;
	}

	// TODO clear method
	@Override
	public void run() {
		ResultMessage rMsg = null;
		try {
			System.out.println("KeyName: " + iMsg.getClassName());
			System.out.println("RemoteObject: " + manager);
			Method invokeMeth;
			try {
				Class<?>[] argArray = null;
				if (iMsg.getMethodArgs() != null) {
					List<Class<?>> methodArgs = new ArrayList<Class<?>>();
					for (Object type : iMsg.getMethodArgs()) {
						System.out.println("PARAMETERVALUE: " + type);
						methodArgs.add(unboxType(type.getClass()));
					}
					argArray = new Class<?>[methodArgs.size()];
					for (int i = 0; i < argArray.length; i++) {
						argArray[i] = methodArgs.get(i);
					}
				}
				invokeMeth = manager.getClass().getMethod(
						iMsg.getMethodName(), argArray);
				remoteResult = invokeMeth.invoke(manager,
						(Object[]) iMsg.getMethodArgs());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// e1.printStackTrace();
				e1.getCause().printStackTrace();
			}
			System.out.println("REMOTERESULT: " + remoteResult);
			if (remoteResult != null && remoteResult instanceof Serializable) {
				Serializable serialResult = (Serializable) remoteResult;
				rMsg = new ResultMessage(serialResult);
				connection.send(rMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		this.thread.start();
	}

	private Class<?> unboxType(Class<?> type) {
		Class<?> primType = type;
		if (type.equals(Byte.class)) {
			primType = byte.class;
		} else if (type.equals(Short.class)) {
			primType = short.class;
		} else if (type.equals(Integer.class)) {
			primType = int.class;
		} else if (type.equals(Float.class)) {
			primType = float.class;
		} else if (type.equals(Double.class)) {
			primType = double.class;
		} else if (type.equals(Character.class)) {
			primType = char.class;
		}
		return primType;
	}

}
