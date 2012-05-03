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
	private ProcessStruct processes = new ProcessStruct();
	private int processCount;
	private ORB orb;
	private NamingContextExt ncRef;
	private NameComponent[] path;
	private boolean isCalculating = false;

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

		// TODO: - Timeout beim Warten einbauen (Exception), dann alles auf
		// Anfang, wenn bedingung nicht erfuellt wurde
		// - Sleep einbauen, um CPU Zeit zu sparen
		// - wenn nicht alle Prozesse bis Timeout da sind, prozesse killen,
		// prozess liste leeren, isCalculating = false;
		while (processCount != processes.size())
			;
		// prozesse zuf�llig w�hlen (liste shufflen?!)
		processes.shuffleProcesses();

		// ring aufbauen
		// daten setzen, nachbarn, ....
		// monitor zahlen und ringbaufbau mitteilen
		mntr.ring(processes.getProcessNames());
		int startValues[] = processes.initProcesses(minDelay, maxDelay, timeout, ggt, mntr);
		mntr.startzahlen(startValues);
		final ggtProcess[] startProcesses = processes.getStartProcesses();

		// berechnung starten, 3 prozesse mit kleinsten zahlen ausw�hlen
		for (ggtProcess s : startProcesses) {
			s.start();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (processes.size() != 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// TODO: wenn eine berechnung fertig ist, alles wieder in
				// ursprungszustand setzen (starter, coord) fuer neue berechnung

				for (Starter s : starters) {
					s.killProcesses();
				}
				// jetzt muesste die prozesstruktur wieder leer sein ... also
				// neue Berechnung kann gestartet werden
				isCalculating = false;

			}
		}).start();

	}

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

	@Override
	public void registerStarter(Starter starter) throws starterAlreadyExists {
		for (Starter s : starters) {
			if (s.getName().equals(starter.getName())) {
				throw new starterAlreadyExists("Exception: Starter " + starter.getName() + " is already registered");
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
		// TODO: wenn isCalculating == true ist abfangen? was w�re sinnvoll bei
		// laufender berechnung? alles beenden oder cleanup und dann neue
		// Berechnung m�glich, wenn Berechnung rennt unregister nicht zulassen?!
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

	@Override
	public void processCalcDone(ggtProcess process) {
		System.out.println("Coordinator>Process " + process.getName() + " has finished calculation");
		processes.remove(process);
	}

}