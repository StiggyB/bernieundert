package server;

import lagern.Fach;
import lagern.LagerPOA;
import lagern.Monitor;
import lagern.LagerPackage.exAlreadyExists;
import lagern.LagerPackage.exNotFound;

public class LagerImpl extends LagerPOA{

	@Override
	public Fach neu(String user, String name) throws exAlreadyExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fach hole(String user, String name) throws exNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fach[] holeLagerListe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aktiviereMonitor(Monitor theMonitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entferneMonitor(Monitor theMonitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

}
