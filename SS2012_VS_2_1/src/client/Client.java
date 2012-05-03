package client;

import ggtCorba.Coordinator;
import ggtCorba.CoordinatorHelper;
import ggtCorba.Starter;
import ggtCorba.CoordinatorPackage.calculationInProgress;
import ggtCorba.CoordinatorPackage.noStarters;

import monitor.Monitor;
import monitor.MonitorHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {

	public static void main(String[] args) {
		new Client().start(args);
	}

	private void start(String[] args) {
		String coordName = args[4];
		String aktion = args[5];

		Coordinator coordRef;
		Monitor monitorRef;

		try {
			System.out.println("Client>Creating and initializing the ORB");
			ORB orb = ORB.init(args, null);

			// Zum Namensdienst verbinden (Referenz holen und wandeln)
			System.out.println("Client>getting the root naming context");
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Objektreferenz mit Namen "Konto" besorgen
			System.out.println("Client>Resolving coordinator reference");
			org.omg.CORBA.Object coordObj = nc.resolve_str(coordName); // Corba

			// Cast Corba ref -> Java Referenz
			System.out.println("Client>getting remote object...");
			coordRef = CoordinatorHelper.narrow(coordObj);

			if (aktion.equals("start")) {
				String monitorName = args[6];
				System.out.println("Client>Resolving monitor reference");
				org.omg.CORBA.Object monitorObj = nc.resolve_str(monitorName); // Corba

				System.out.println("Client>getting remote object...");
				monitorRef = MonitorHelper.narrow(monitorObj);

				startGgt(args, coordRef, monitorRef);
			} else if (aktion.equals("quit")) {
				quit(coordRef);
			} else if (aktion.equals("starters")) {
				getAndPrintStarters(coordRef);
			}

			System.out.println("Client>executed chosen parameter...");

		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(1);
		}

		System.out.println("Client>exit successful ...");

	}

	private void startGgt(String[] args, Coordinator coordRef, Monitor monitorRef) {
		int minProcess = Integer.parseInt(args[7]);
		int maxProcess = Integer.parseInt(args[8]);
		int minDelay = Integer.parseInt(args[9]);
		int maxDelay = Integer.parseInt(args[10]);
		int timeout = Integer.parseInt(args[11]);
		int ggt = Integer.parseInt(args[12]);

		try {
			coordRef.start(minProcess, maxProcess, minDelay, maxDelay, timeout, ggt, monitorRef);
		} catch (noStarters e) {
			System.out.println(e.getMessage());
			System.out.println(e.s);
		} catch (calculationInProgress e) {
			System.out.println(e.getMessage());
			System.out.println(e.s);
		}
	}

	private void getAndPrintStarters(Coordinator coordRef) {
		Starter[] starters = coordRef.getStarters();
		System.out.println("Angemeldete Starter:");
		for (Starter starter : starters) {
			System.out.println(starter.getName());
		}
	}

	private void quit(Coordinator coordRef) {
		if (coordRef.shutdown()) {
			System.out.println("Client>exit successful ...");
		} else {
			System.out.println("Client>Calculation running, exit NOT successful ...");
			System.out.println("Client>try again later");
		}
	}

}
