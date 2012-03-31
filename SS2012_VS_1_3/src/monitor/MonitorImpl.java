package monitor;

import lagern.MonitorPOA;

public class MonitorImpl extends MonitorPOA{

	@Override
	public void aktion(String user, String log) {
		System.out.println("Monitor>Action from user: " + user + " \t\t\tLog-Entry: " + log);
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}
