package namensdienst;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tcp_advanced.Connection;

public class NameServiceWorker implements Runnable {

	private Connection connection;
	private LocalNameService localNS;
	private Object remoteResult;
	private boolean isRunning;

	public NameServiceWorker(Connection connection, LocalNameService localNS) {
		this.connection = connection;
		this.localNS = localNS;
		this.remoteResult = null;
		this.isRunning = true;
	}

	@Override
	public void run() {

		while (isRunning) {
			Object message = null;
			try {
				message = connection.receive();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (message instanceof InvokeMessage) {
				InvokeMessage iMsg = (InvokeMessage) message;
				RemoteObject remoteObj = localNS.get(
						iMsg.getClassName());
				Method invokeMeth = iMsg.getInvMethod();
				try {
					remoteResult = invokeMeth.invoke(remoteObj.getObjRef(),
							iMsg.getMethodArgs());
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}

				ResultMessage rMsg = new ResultMessage(remoteResult);
				try {
					connection.send(rMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
