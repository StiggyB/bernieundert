package test_user_access;

public abstract class OnlineUser {

	public abstract boolean doTransfer(String accOwner, String accTarget, int BSC);
	
	public abstract String showTransactionVolume();
	
	public abstract String editOwnerData(String ownerName, String ownerAddr, String fon);
	
}
