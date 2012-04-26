package starter;



import ggt.Coordinator;
import ggt.CoordinatorHelper;
import ggt.Starter;
import ggt.StarterHelper;
import ggt.CoordinatorPackage.starterDoesNotExists;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class StarterStart {
	
	public static void main(String[] args) {
		try {
			String coordName = args[4];
			String starterName = args[5];
			System.out.println("Starter>Creating and initializing the ORB");
			final ORB orb = ORB.init(args, null);

			// Zum Namensdienst verbinden (Referenz holen und wandeln)
			System.out.println("Starter>getting the root naming context");
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Objektreferenz mit Namen "Konto" besorgen
			System.out.println("Starter>Resolving coordinator reference");
			org.omg.CORBA.Object obj = nc.resolve_str(coordName); // Corba
			
			// Cast Corba ref -> Java Referenz
			System.out.println("Starter>getting remote object...");
			final Coordinator coordRef = CoordinatorHelper.narrow(obj);
			
			// Neuen starter erzeugen
			StarterImpl starter = new StarterImpl(starterName);;
			
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();
			
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(starter);
			
			final Starter href = StarterHelper.narrow(ref);

			// Setze starter referenz
			System.out.println("Starter>adding starter to coordinator...");
			coordRef.registerStarter(href);
			
			starter.setCoordRef(coordRef);
			
			// Shutdown-Hook fuer Beenden mit strg+c
			Thread hook = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("Starter>remove Starter from Coordinator...");
					try {
						coordRef.unregisterStarter(href);
					} catch (starterDoesNotExists e) {
						e.printStackTrace();
					}
					System.out.print("OK\nStarter>quit");
					orb.shutdown(true);
				}
			});
			
			// Hook und Objektreferenzen setzen fuer quit() Methode
			Runtime.getRuntime().addShutdownHook(hook);
			starter.setHook(hook);
			starter.setOrb(orb);

			System.out.println("Starter>starter was started...");

			orb.run();
			
		} catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}

		System.out.println("Starter>EOF");
	}

	
}
