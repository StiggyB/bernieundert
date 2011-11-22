package test_user_access;

public class OnlineUserProxy extends OnlineUser {

	@Override
	public boolean doTransfer(String accOwner, String accTarget, int BSC) {
		System.out.println("remote doTransfer");
		return false;
	}

	@Override
	public String showTransactionVolume() {
		System.out.println("remote showTransactionVolume");
		return null;
	}

	@Override
	public String editOwnerData(String ownerName, String ownerAddr, String fon) {
		System.out.println("remote editOwnerData");
		return null;
	}

	
}
