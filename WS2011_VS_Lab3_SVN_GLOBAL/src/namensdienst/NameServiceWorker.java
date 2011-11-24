package namensdienst;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import messages.InvokeMessage;
import messages.RebindMessage;
import messages.ResolveMessage;
import messages.ResultMessage;
import messages.UnbindMessage;
import tcp_advanced.Connection;

public class NameServiceWorker implements Runnable {

	private Connection connection;
	private NameServiceServer NSServer;
	private Object remoteResult;

	public NameServiceWorker(Connection connection, NameServiceServer NSServer) {
		this.connection = connection;
		this.NSServer = NSServer;
		this.remoteResult = null;
	}

	@Override
	public void run() {
		Object message = null;
		try {
			message = connection.receive();
			if (message instanceof RebindMessage) {
				System.out.println("REBINDMSG: " + message);
				RebindMessage rbMsg = (RebindMessage)message;
				NSServer.put(rbMsg.getRemoteName(), rbMsg.getType()); 
			} else if (message instanceof ResolveMessage) {
				System.out.println("RESOLVEMSG: " + message);
				ResolveMessage resMsg = (ResolveMessage)message;
				Object resultObj = NSServer.get(resMsg.getRemoteName());
				System.out.println("WORKER RESULTMESSAGE: " + resultObj);
				Serializable serialObj = null;
				if (resultObj instanceof Serializable) {
					serialObj = (Serializable) resultObj;
				}
				ResultMessage resuMsg = new ResultMessage(serialObj);
				connection.send(resuMsg);
			} else if (message instanceof UnbindMessage) {
				UnbindMessage ubMsg = (UnbindMessage)message;
				String key = ubMsg.getRemoteName();
				System.out.println("REMOVE THIS ACCID: " + key);
				if(NSServer.getRemoteEntries().containsKey(key)) {
					NSServer.remove(key);
					UnbindMessage ubMsg2 = new UnbindMessage(key);
					connection.send(ubMsg2);
//					localNS.getDeleteList().add(key);
//					localNS.getMwCom().sendSync(key);
					System.out.println("UNBIND MAP: " + NSServer.getRemoteEntries());
				}
				System.out.println("UNIX: " + NSServer.getRemoteEntries());
//			} else if (message instanceof String) {
//				String name = (String) message;
//				if (NSServer.getRemoteEntries().containsKey(name)) {
//					RemoteObject remoteObj = new RemoteObject(name,
//							new BigInteger(6, new SecureRandom()), NSServer.get(
//									name).getClass());
//					System.out.println("LOCAL RemoteOBJ: " + NSServer.get(name));
//					connection.send(remoteObj);
//				}
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

	
	
}
