package monitor;

import lagern.MonitorPOA;

public class MonitorImpl extends MonitorPOA{

	@Override
	public void aktion(String user, String log) {
		System.out.println("Monitor>Action from user: " + user + " \t\t\tLog-Entry: " + log);
	}

	//TODO: Wird vom lager gerufen, wenn es beendet wird, alle Monitore entfernen und quitten,
	//Unterscheidung, ob Lager geschlossen wird oder ein Monitor; wenn nur ein Monitor geclosed
	//wird, muss nur der eine Monitor aus der LagerMonitor Liste entfernt werden.
	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}
