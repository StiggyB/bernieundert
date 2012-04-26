package coordinator;

import ggt.CoordinatorPOA;
import ggt.Starter;
import ggt.ggtProcess;
import ggt.CoordinatorPackage.starterAlreadyExists;
import ggt.CoordinatorPackage.starterDoesNotExists;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import monitor.Monitor;

public class CoordinatorImpl extends CoordinatorPOA {

	private Set<Starter> starters = new HashSet<Starter>();
	private ProcessStruct processes = new ProcessStruct();
	private int processCount;
	private int timeout;
	private int ggt;
	private ORB orb;
	private Thread hook;
	private NamingContextExt ncRef;
	private NameComponent[] path;

	
	@Override
	public Starter[] getStarters() {
		return starters.toArray(new Starter[starters.size()]);
	}

	@Override
	public void start(int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) {
		this.timeout = timeout;
		this.ggt = ggt;

		Random rnd = new Random();

		int processCountTmp;
		for (Starter s : starters) {
			processCountTmp = rnd.nextInt(maxProcess - minProcess) + minProcess;
			processCount += processCountTmp;
			s.createProcesses(processCountTmp);
		}


		//TODO: - Timeout beim Warten einbauen (Exception), dann alles auf Anfang, wenn bedingung nicht erfuellt wurde
		//      - Sleep einbauen, um CPU Zeit zu sparen
		while (processCount != processes.size());
		// prozesse zufällig wählen (liste shufflen?!)
		processes.shuffleProcesses();

		// ring aufbauen
		// daten setzen, nachbarn, ....
		// monitor zahlen und ringbaufbau mitteilen
		mntr.ring(processes.getProcessNames());
		mntr.startzahlen(processes.initProcesses(minDelay, maxDelay, timeout, ggt, mntr));
		ggtProcess[] startProcesses = processes.getStartProcesses();

		// berechnung starten, 3 prozesse mit kleinsten zahlen auswählen
		for (ggtProcess s : startProcesses) {
			s.start();
		}
		
		//TODO: muss hier noch was hin? wenn die Berechnung fertig ist, muss der Coord das ja irgendwie mitbekommen. Oder rennt das in anderen
		//	    Methoden ab?
		//TODO: wenn eine berechnung fertig ist, alles wieder in ursprungszustand setzen (starter, coord) fuer neue berechnung
	}

	@Override
	public void shutdown() {
		System.out.println("Coordinator>Client quitted coordinator");
		System.out.print("Coordinator>tell all starters to quit...");
		for (Starter s : starters) {
			s.shutdown();
		}

		System.out.print("OK\nCoordinator>unbinding...");

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
				System.out.print("OK\nCoordinator>Removing shutdownHook...");
				Runtime.getRuntime().removeShutdownHook(hook);
				System.out.print("OK\nCoordinator>shutting down ORB...");
				orb.shutdown(true);
				System.out.println("OK\nCoordinator>shutdown was successful...");
			}
		}).start();
	}

	@Override
	public void registerStarter(Starter starter) throws starterAlreadyExists {
		for (Starter s : starters) {
			if (s.getName().equals(starter.getName())) {
				throw new starterAlreadyExists("Starter " + starter.getName() + " is already registered");
			}
		}

		starters.add(starter);
		System.out.println("Coordinator>Starter " + starter.getName() + " was added...");
	}

	@Override
	public void registerProcess(ggtProcess process, String processName) {
		processes.add(process);
	}

	@Override
	public void unregisterStarter(Starter starter) throws starterDoesNotExists {
		
		//TODO: oder lieber auf contains prüfen und removen ohne exceptions, in dem fall eben nix tun ...?!
		if(!starters.contains(starter)){
			throw new starterDoesNotExists(starter.getName());
		}
		//TODO: wird der Code nach dem throw eigentlich noch ausgeführt?!
		starters.remove(starter);
		System.out.println("Coordinator>Starter " + starter.getName() + " unregistered");
	}
	
	public void unregisterAllStarters() {
		for (Starter s : starters) {
			s.shutdown();
		}
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