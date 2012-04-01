package monitor;

import org.omg.CORBA.ORB;

import lagern.MonitorPOA;

public class MonitorImpl extends MonitorPOA{

	private Thread hook;
	private ORB orb;

	@Override
	public void aktion(String user, String log) {
		System.out.println("Monitor>Action from user: " + user + " \t\t\tLog-Entry: " + log);
	}

	@Override
	public void quit() {
		Runtime.getRuntime().removeShutdownHook(hook);
		orb.shutdown(false);
	}

	public void setHook(Thread hook) {
		this.hook = hook;
	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}

}
