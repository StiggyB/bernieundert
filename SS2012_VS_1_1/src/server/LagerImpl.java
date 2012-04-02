package server;

import java.util.HashMap;
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
	private static List<Monitor> lagerMonitore = new LinkedList<Monitor>();

	//TODO: muss das synchronized sein oder reicht hashmap? Die ist ja synchronized afaik...
	@Override
	public Fach neu(String user, String name) throws exAlreadyExists {

		if (lagerFaecher.containsKey(name)) {
			benachrichtigeMonitore(user, "neu(): Fach " + name + "existiert bereits!");
			throw new exAlreadyExists("neu(): " + name + "already exists!");
		}

		LagerfachImpl lagerfach = new LagerfachImpl(user, name);
		Fach neuesFach = null;
		org.omg.CORBA.Object ref = null;
		
		try {
			//TODO: braucht man hier auch son rootPOA aufruf oder reicht das _poa? macht das einen unterschied? 
			//z.B. bei Nutzung auf mehereren PCs?
			ref = _poa().servant_to_reference((Servant) lagerfach);
		} catch (ServantNotActive e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace();
		} catch (WrongPolicy e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace();
		}
		
		neuesFach = FachHelper.narrow(ref);
		lagerFaecher.put(name, neuesFach);
		
		benachrichtigeMonitore(user, "neu(): Fach '" + name + "' erfolgreich in das Lager eingetragen.");
		
		return neuesFach;
	}

	//TODO: static referenz ok fuer LagerfachImpl?? L�uft das dann auch verteilt auf mehereren PCs?
	public static void benachrichtigeMonitore(String user, String log) {
		if (!lagerMonitore.isEmpty()) {
			for (Monitor mon : lagerMonitore) {
				mon.aktion(user, log);
			}

		}
	}

	@Override
	public Fach hole(String user, String name) throws exNotFound {
		if(!lagerFaecher.containsKey(name)){
			throw new exNotFound("hole(): Fach '" + name + "' existiert nicht!");
		}
		
		return lagerFaecher.get(name);
	}

	//TODO: gibts was sexyeres? :D
	@Override
	public Fach[] holeLagerListe() {
		return lagerFaecher.values().toArray(new Fach[0]);
	}

	//TODO: linkedlist nicht synced, also methode synchronized richtig?
	@Override
	public synchronized void aktiviereMonitor(Monitor theMonitor) {
		System.out.println("Monitor: '" + theMonitor.hashCode() + "' hinzugefuegt");
		lagerMonitore.add(theMonitor);
	}

	//TODO: linkedlist nicht synced, also methode synchronized richtig?
	@Override
	public synchronized void entferneMonitor(Monitor theMonitor) {
		if (lagerMonitore.contains(theMonitor)) {
			lagerMonitore.remove(theMonitor);
			System.out.println("Monitor: '" + theMonitor.hashCode() + "' entfernt");
		}
	}

	//TODO: komisch ... ohne thread gehts ... mit strg+c ... wtf ....
	public void entferneAlleMonitore() {
		for (Monitor moni : lagerMonitore) {
			moni.quit();
		}
	}
	
	//TODO: eigenartig ... Server starten, Moni starten, Client sagt quit; Moni wird noch beendet aber Lager
	// rennt weiter; im Client gibts ne COMM Exception ... bei meheren Monis werden auch nicht zwingend alle beendet ...
	// startet man z.B. 3 geht nur einer aus und die anderen beiden + lager rennen weiter ... hmmmm
	public void quit() {
		System.out.println("Server>Client quitted server");
		System.out.print("Server>quitting all monitors...");
		entferneAlleMonitore();
		System.out.print("OK\nServer>unbinding...");
		try {
			ncRef.unbind(path);
		} catch (NotFound | CannotProceed | InvalidName e) {
			e.printStackTrace();
		}
		System.out.print("OK\nServer>shutting down ORB...");
		Runtime.getRuntime().removeShutdownHook(hook);
		orb.shutdown(false);
		System.out.println("OK\nServer>shutdown was successful...");
	}	

	//	public void quit() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("Server>quit");
//				for (Monitor moni : lagerMonitore) {
//					moni.quit();
//				}
//				Runtime.getRuntime().removeShutdownHook(hook);
//				orb.shutdown(false);
//			}
//		}).start();
//	}
//	
//	//TODO: komisch ... ohne thread gehts ... mit strg+c ... wtf ....
//	public void hookQuit() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("Server>quit");
//				for (Monitor moni : lagerMonitore) {
//					moni.quit();
//				}
//				Runtime.getRuntime().removeShutdownHook(hook);
//				orb.shutdown(false);
//			}
//		}).start();
//	}

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
