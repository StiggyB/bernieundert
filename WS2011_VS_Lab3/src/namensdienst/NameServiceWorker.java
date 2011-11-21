package namensdienst;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import tcp_advanced.Connection;

public class NameServiceWorker implements Runnable {

	private Connection connection;
	private LocalNameService localNS;
	private Object remoteResult;

	public NameServiceWorker(Connection connection, LocalNameService localNS) {
		this.connection = connection;
		this.localNS = localNS;
		this.remoteResult = null;
	}

	@Override
	public void run() {

		Object message = null;
		try {
			message = connection.receive();
			if (message instanceof InvokeMessage) {
				InvokeMessage iMsg = (InvokeMessage) message;
				System.out.println("TESTTEST");
				// TODO sync local Map with remoteObj & localObj (AccImpl)
				Object remoteObj = localNS.get(iMsg.getClassName());
				System.out.println("KeyName: " + iMsg.getClassName());
				System.out.println("RemoteObject: " + remoteObj);
				Method invokeMeth;
				try {
					Class<?> [] argArray = null;
					if(iMsg.getMethodArgs() != null) {
						List <Class<?>> methodArgs = new ArrayList<Class<?>>();
						
						for (Object type : iMsg.getMethodArgs()) {
							System.out.println("PARAMETERVALUE: " + type);
							methodArgs.add(unboxType(type.getClass()));
						}
						argArray = new Class<?>[methodArgs.size()];
						for (int i = 0; i < argArray.length; i++) {
							argArray[i] = methodArgs.get(i);
						}
					}
					invokeMeth = remoteObj.getClass().getMethod(iMsg.getMethodName(), argArray);
					remoteResult = invokeMeth.invoke(remoteObj,
							iMsg.getMethodArgs());
					System.out.println("TEST IT!");
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
				System.out.println("REMOTERESULT: " + remoteResult);
				if(remoteResult != null) {
					ResultMessage rMsg = new ResultMessage(remoteResult);
					connection.send(rMsg);
				}
			} else if (message instanceof String) {
				String name = (String) message;
				if (localNS.getRemoteEntries().containsKey(name)) {
					RemoteObject remoteObj = new RemoteObject(name,
							new BigInteger(6, new SecureRandom()),
							localNS.get(name).getClass());
					//TODO Maybe difference between local AccImpl and remote AccProxy!!
					System.out.println("LOCAL RemoteOBJ: " + localNS.get(name));
					connection.send(remoteObj);
				}
			} else if (message instanceof RemoteObject) {
				RemoteObject remoteObj = (RemoteObject) message;
				System.out.println("Worker RemoteObj: " + message);
				localNS.put(remoteObj.getRemoteName(),
						localNS.generateObjectRef(remoteObj, remoteObj.getRemoteName()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Class<?> unboxType(Class<?> type) {
		System.out.println("TYPE: " + type);
		if (type.equals(Byte.class)) {
			return byte.class;
		} else if (type.equals(Short.class)) {
			return short.class;
		} else if (type.equals(Integer.class)) {
			return int.class;
		} else if (type.equals(Float.class)) {
			return float.class;
		} else if (type.equals(Double.class)) {
			return double.class;
		} else if (type.equals(Character.class)) {
			return char.class;
		}
		return type;
	}
}
