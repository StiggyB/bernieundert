package monitor;

import org.omg.CORBA.ORB;

import lagern.MonitorPOA;

public class MonitorImpl extends MonitorPOA {

	private Thread hook;
	private ORB orb;

	@Override
	public void aktion(String user, String log) {
		System.out.println("Monitor>Action from user: " + user + " \t\t\tLog-Entry: " + log);
	}

	// quit() Methode braucht eigenen Thread, da sonst nach orb.shutdown() die Verbindung weg ist,
	// fuehrt sonst zu corba-exceptions; Methode wird vom Lager gerufen, wenn es beendet wird.
	public void quit() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Monitor>quit");
				Runtime.getRuntime().removeShutdownHook(hook);
				orb.shutdown(false);
			}
		}).start();
	}

	public void setHook(Thread hook) {
		this.hook = hook;
	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}

}
