package coordinator;

import ggtCorba.CoordinatorPOA;
import ggtCorba.Starter;
import ggtCorba.ggtProcess;
import ggtCorba.CoordinatorPackage.calculationInProgress;
import ggtCorba.CoordinatorPackage.noStarters;
import ggtCorba.CoordinatorPackage.starterAlreadyExists;
import ggtCorba.CoordinatorPackage.starterDoesNotExists;

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
	private ProcessManager processes = new ProcessManager();
	private int processCount;
	private ORB orb;
	private NamingContextExt ncRef;
	private NameComponent[] path;
	private boolean isCalculating = false;

	//Liefert Array über alle angemeldeten Starter zurück, z.B. fuer Ausgabe beim Client
	@Override
	public Starter[] getStarters() {
		return starters.toArray(new Starter[starters.size()]);
	}

	@Override
	public void start(int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, Monitor mntr) throws calculationInProgress, noStarters {
		if (isCalculating) {
			throw new calculationInProgress("Exception: Can't terminate, Calculation in Progress ...");
		} else if (starters.isEmpty()) {
			throw new noStarters("Exception: No Starters registered, can't start calculation");
		}
		isCalculating = true;
		processCount = 0;

		Random rnd = new Random();

		int processCountTmp;
		for (Starter s : starters) {
			processCountTmp = rnd.nextInt(maxProcess - minProcess) + minProcess;
			processCount += processCountTmp;
			s.createProcesses(processCountTmp);
		}

		// Auf alle gestarteten Prozesse warten
		// TODO: - Timeout beim Warten, falls Prozesse/Starter sich nicht melden.
		// TODO: - CPU Zeit sparen, also bspw. in der while-Schleife sleep() einbauen.
		while (processCount != processes.size());

		// Prozesse in zufaellige Reihenfolge bringen
		processes.shuffleProcesses();

		// Monitor Ringaufbau mitteilen
		mntr.ring(processes.getProcessNames());
		// Prozesse initialisieren
		int startValues[] = processes.initProcesses(minDelay, maxDelay, timeout, ggt, mntr);
		// Monitor Startzahlen mitteilen
		mntr.startzahlen(startValues);
		// Die drei Startprozesse auswaehlen
		final ggtProcess[] startProcesses = processes.getStartProcesses();

		// Berechnung starten
		for (ggtProcess s : startProcesses) {
			s.start();
		}

		// Damit Aufruf von start asynchron, Thread starten fuer weiteren Programmablauf
		new Thread(new Runnable() {

			@Override
			public void run() {

				// Warten, bis alle Prozesse processCalcDone() aufgerufen haben und damit geloescht wurden
				while (processes.size() != 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// Allen Startern mitteilen, ihre Prozesse nun zu beenden
				for (Starter s : starters) {
					s.killProcesses();
				}

				// neue Berechnung kann gestartet werden
				isCalculating = false;
				System.out.println("Coordinator>Ready for new calculation");

			}
		}).start();

	}

	// Methode ist fuer Aufruf durch Client gedacht, wenn eine Berechnung in Gange ist, liefert die Methode false, ist keine Berechnung in Gange,
	// werden alle Starter beendet und das System heruntergefahren.
	@Override
	public boolean shutdown() {
		System.out.println("Coordinator>Client quitted coordinator");
		if (isCalculating) {
			System.out.println("Coordinator>Calculation running, cant quit now ...");
			return false;
		}
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
				System.out.print("OK\nCoordinator>shutting down ORB...");
				orb.shutdown(true);
				System.out.println("OK\nCoordinator>shutdown was successful...");
			}
		}).start();
		return true;
	}

	// Neue Starter anmelden
	@Override
	public void registerStarter(Starter starter) throws starterAlreadyExists, calculationInProgress {
		if(isCalculating){
			throw new calculationInProgress("Exception: Calculation in progress ... try again later!");
		}
		for (Starter s : starters) {
			if (s.getName().equals(starter.getName())) {
				throw new starterAlreadyExists("Exception: Starter " + starter.getName() + " is already registered");
			}
		}

		starters.add(starter);
		System.out.println("Coordinator>Starter " + starter.getName() + " was added...");
	}

	// Wird von allen Prozessen im Konstruktor aufgerufen == Anmeldung der Prozesse am Koordinator
	@Override
	public void registerProcess(ggtProcess process, String processName) {
		processes.add(process);
	}

	// Abmelden von Startern, wenn eine Berechnung in Gange ist, kann der Starter sich nicht abmelden; siehe SigHandler im Starter
	@Override
	public void unregisterStarter(Starter starter) throws starterDoesNotExists {
		if (!starters.contains(starter)) {
			throw new starterDoesNotExists("Exception: Starter " + starter.getName() + " does not exist!");
		}
		starters.remove(starter);
		System.out.println("Coordinator>Starter " + starter.getName() + " unregistered");
	}

	@Override
	public boolean isCalculating() {
		return isCalculating;
	}

	// Alle Starter samt Prozesse beenden, beendet laufende Berechnung; siehe SigHandler Coordinator
	public void unregisterAllStarters() {
		for (Starter s : starters) {
			s.shutdown();
		}
	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}

	public void setNcRef(NamingContextExt ncRef, NameComponent[] path) {
		this.ncRef = ncRef;
		this.path = path;
	}

	// Signalisiert, dass ein Prozess mit der Berechnung abgeschlossen hat
	@Override
	public void processCalcDone(ggtProcess process) {
		System.out.println("Coordinator>Process " + process.getName() + " has finished calculation");
		processes.remove(process);
	}

}