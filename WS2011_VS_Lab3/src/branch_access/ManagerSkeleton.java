package branch_access;

import mware_lib.NameService;

public class ManagerSkeleton extends Manager {

	
	public ManagerSkeleton(NameService nameService, String hostName, int port) {
	}

	@Override
	public String createAccount(String owner) {
		System.out.println("remote call");
		return null;
	}

	@Override
	public boolean removeAccount(String accountID) {
		System.out.println("remote call");
		return false;
	}

}

