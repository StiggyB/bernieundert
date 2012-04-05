package server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import lagern.Fach;
import lagern.FachHelper;
import lagern.LagerPOA;
import lagern.Monitor;
import lagern.LagerPackage.exAlreadyExists;
import lagern.LagerPackage.exNotFound;

public class LagerImpl extends LagerPOA {

	private Map<String, Fach> lagerFaecher = new HashMap<String, Fach>();
	private ORB orb;
	private Thread hook;
	private NamingContextExt ncRef;
	private NameComponent[] path;
	private List<Monitor> lagerMonitore = new LinkedList<Monitor>();

	// HashMap zwar synced, aber nur die Zugriffe darauf, Rest nicht -> synchronized
	@Override
	public synchronized Fach neu(String user, String name) throws exAlreadyExists {
		
		// pruefen ob Fach bereits vorhanden ist
		if (lagerFaecher.containsKey(name)) {
			benachrichtigeMonitore(user, "neu(): Fach " + name + "existiert bereits!");
			throw new exAlreadyExists("neu(): " + name + "already exists!");
		}

		LagerfachImpl lagerfach = new LagerfachImpl(user, name);
		lagerfach.setLager(this);
		Fach neuesFach = null;
		org.omg.CORBA.Object ref = null;

		try {
			// TODO: braucht man hier auch son rootPOA aufruf oder reicht das
			// _poa? macht das einen unterschied?
			// z.B. bei Nutzung auf mehereren PCs?
			ref = _poa().servant_to_reference((Servant) lagerfach);
		} catch (ServantNotActive e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace();
		} catch (WrongPolicy e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace();
		}

		// Downcast und ablegen in Map
		neuesFach = FachHelper.narrow(ref);
		lagerFaecher.put(name, neuesFach);

		benachrichtigeMonitore(user, "neu(): Fach '" + name	+ "' erfolgreich in das Lager eingetragen.");

		return neuesFach;
	}

	public void benachrichtigeMonitore(String user, String log) {
		for (Monitor mon : lagerMonitore) {
			mon.aktion(user, log);
	}
	}

	@Override
	public Fach hole(String user, String name) throws exNotFound {
		Fach fach = lagerFaecher.get(name);
		if (fach == null) {
			throw new exNotFound("hole(): Fach '" + name + "' existiert nicht!");
		}
		
		return fach;
	}

	@Override
	public Fach[] holeLagerListe() {
		return lagerFaecher.values().toArray(new Fach[lagerFaecher.size()]);
	}

	@Override
	public synchronized void aktiviereMonitor(Monitor theMonitor) {
		System.out.println("Monitor: '" + theMonitor.hashCode() + "' hinzugefuegt");
		lagerMonitore.add(theMonitor);
	}

	@Override
	public synchronized void entferneMonitor(Monitor theMonitor) {
		if (lagerMonitore.contains(theMonitor)) {
			lagerMonitore.remove(theMonitor);
			System.out.println("Monitor: '" + theMonitor.hashCode() + "' entfernt");
		}
	}

	public void entferneAlleMonitore() {
		for (Iterator<Monitor> iterator = lagerMonitore.iterator(); iterator.hasNext();) {
			Monitor moni = iterator.next();
			try {
				moni.quit();
			} catch (Exception e) {
				System.out.println("Monitor.quit() threw: ");
				e.printStackTrace();
			} finally {
				iterator.remove();
			}
		}
	}
	
	// wird gerufen, wenn der Client als Parameter quit sendet, Hook vorher wieder entfernen!
	public void quit() {
		System.out.println("Server>Client quitted server");
		System.out.print("Server>quitting all monitors...");
		
		entferneAlleMonitore();
		
		System.out.print("OK\nServer>unbinding...");
		
		try {
			ncRef.unbind(path);
		} catch (NotFound e) {
			e.printStackTrace();
		} catch (CannotProceed e) {
			e.printStackTrace();
		} catch (InvalidName e) {
			e.printStackTrace();
		}
		
		

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.print("OK\nRemoving shutdownHook...");
				Runtime.getRuntime().removeShutdownHook(hook);
				System.out.print("OK\nServer>shutting down ORB...");
				orb.shutdown(false);
				System.out.println("OK\nServer>shutdown was successful...");
			}
		}).start();
		

	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}

	public void setHook(Thread hook) {
		this.hook = hook;
	}

	public void setNcRef(NamingContextExt ncRef, NameComponent[] path) {
		this.ncRef = ncRef;
		this.path = path;
	}

}
