package appl;

import cash_access.Account;

public class AccountImpl extends Account {


	@Override
	public void deposit(double amount) {
		// TODO Auto-generated method stub
		System.out.println("deposit");
	}

	@Override
	public void withdraw(double amount) throws OverdraftException {
		// TODO Auto-generated method stub
		System.out.println("withdraw");
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		System.out.println("getBalance");
		return 0;
	}

}
