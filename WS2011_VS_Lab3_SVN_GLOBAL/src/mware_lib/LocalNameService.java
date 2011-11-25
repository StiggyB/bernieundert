package mware_lib;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import messages.RebindMessage;
import messages.ResolveMessage;
import tcp_advanced.Client;
import test_user_access.OnlineUser;
import test_user_access.OnlineUserProxy;
import branch_access.Manager;
import branch_access.ManagerProxy;
import branch_access.ManagerSkeleton;
import cash_access.Account;
import cash_access.AccountProxy;
import cash_access.AccountSkeleton;

public class LocalNameService extends NameService {

	// TODO implement specific exceptions...

	private String host;
	private int port;
	private Client client;
	private static int skeletonPort = 2500;
	
	public LocalNameService(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void rebind(Object servant, String name) {
		System.out.println("REBIND OBJECT: " + servant + " NAME: " + name);
		try {
			Class<?> objType = (generateSkeletons(servant, name));
			RemoteInfo rInfo = new RemoteInfo(InetAddress
						.getLocalHost().getHostAddress(), skeletonPort, objType);
			RebindMessage rbMsg = new RebindMessage(rInfo, name);
			System.out.println("MSG REBIND: " + rbMsg);
			client = new Client(this.host, this.port);
			client.send(rbMsg);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				skeletonPort++;
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}	

	@Override
	public Object resolve(String name) {
		Object resultObj = null;
		try {
			ResolveMessage resMsg = new ResolveMessage(name);
			client = new Client(this.host, this.port);
			Thread.sleep(500);
			client.send(resMsg);
			resultObj = client.receive();
			System.out.println("RESOLVE RECEIVE: " + resultObj);
			if(resultObj instanceof RemoteInfo) {
				RemoteInfo rMsg = (RemoteInfo)resultObj;
				System.out.println("GENERATEOBJ: " + rMsg.getType());
				resultObj = generateObjectRef(rMsg.getType(), name, rMsg.getHost(), rMsg.getPort());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultObj;
	}
	
	public Class<?> generateSkeletons (Object remoteObj, String name) {
		Class<?> remoteType = remoteObj.getClass();
		Class<?> resultObj = null;
		if (Account.class.equals(remoteType.getSuperclass())) {
			resultObj = Account.class;
			AccountSkeleton accSkel = new AccountSkeleton(skeletonPort, (Account)remoteObj);
			accSkel.start();
		} else if (Manager.class.equals(remoteType.getSuperclass())) {
			resultObj = Manager.class;
			ManagerSkeleton manSkel;
			try {
				manSkel = new ManagerSkeleton(skeletonPort, (Manager)remoteObj);
				manSkel.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("SKELETON TYPE: " + resultObj);
		return resultObj;
	}
	
	public Object generateObjectRef(Class<?> remoteType, String name, String skelHost, int skelPort) {
		Object resultObj = null;
		if (Account.class.equals(remoteType)) {
			resultObj = new AccountProxy(skelHost, skelPort, name);
		} else if (Manager.class.equals(remoteType)) {
			resultObj = new ManagerProxy(skelHost, skelPort, name);
		}
		System.out.println("PROXY TYPE: " + resultObj);
		return resultObj;
	}

}
