package namensdienst;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tcp_advanced.Client;

public class NameServiceWorker implements Runnable {

	private Client client;
	private LocalNameService localNS;
	private Object remoteResult;
	private boolean isRunning;

	public NameServiceWorker(Client client, LocalNameService lnService) {
		this.client = client;
		this.localNS = lnService;
		this.remoteResult = null;
		this.isRunning = false;
	}

	@Override
	public void run() {

		while (isRunning) {
			Object message = null;
			try {
				message = client.receive();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (message instanceof InvokeMessage) {
				InvokeMessage iMsg = (InvokeMessage) message;
				RemoteObject remoteObj = localNS.getRemoteEntries().get(
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
					client.send(rMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
