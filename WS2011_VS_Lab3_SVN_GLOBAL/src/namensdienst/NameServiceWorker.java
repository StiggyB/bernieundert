package namensdienst;

import java.io.IOException;

import messages.RebindMessage;
import messages.ResolveMessage;
import messages.UnbindMessage;
import tcp_advanced.Connection;

public class NameServiceWorker implements Runnable {

	//TODO Exceptions for the bank!!!
	
	private Connection connection;
	private NameServiceServer NSServer;

	public NameServiceWorker(Connection connection, NameServiceServer NSServer) {
		this.connection = connection;
		this.NSServer = NSServer;
	}

	@Override
	public void run() {
		Object message = null;
		try {
			message = connection.receive();
			if (message instanceof RebindMessage) {
				RebindMessage rbMsg = (RebindMessage)message;
				System.out.println("REBINDMSG1: " + rbMsg.getRemoteName() + ", "+ rbMsg.getRemoteInfo().getType());
				NSServer.put(rbMsg.getRemoteName(), rbMsg.getRemoteInfo()); 
			} else if (message instanceof ResolveMessage) {
				ResolveMessage resMsg = (ResolveMessage)message;
				System.out.println("RESOLVEMSG: " + resMsg.getRemoteName());
				System.out.println("MAP: " + NSServer.getRemoteEntries());
				Object remoteInfo = NSServer.get(resMsg.getRemoteName());
				System.out.println("WORKER RESULTMESSAGE: " + remoteInfo);
				connection.send(remoteInfo);
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
