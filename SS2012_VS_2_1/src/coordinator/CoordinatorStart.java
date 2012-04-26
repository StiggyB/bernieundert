package coordinator;

import ggt.Coordinator;
import ggt.CoordinatorHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class CoordinatorStart {

	public static void main(String[] args) {

		try {
			System.out.println("Coordinator>starting coordinator...");
			System.out.println("Coordinator>creating and initializing the ORB");
			final ORB orb = ORB.init(args, null);

			System.out.println("Coordinator>getting reference to rootpoa");
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

			System.out.println("Coordinator>activating the POA Manager");
			rootPoa.the_POAManager().activate();

			// Servant-Objekt erstellen
			System.out.println("Coordinator>Creating the servant");
			final CoordinatorImpl coordServant = new CoordinatorImpl();

			// Objekt Referenz des Servants holen
			System.out.println("Coordinator>obtain the reference from the servant");
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(coordServant);

			// Downcast Corba-Objekt
			Coordinator href = CoordinatorHelper.narrow(ref);

			// Referenz zum Namensdiesnt (root naming context) holen
			System.out.println("Coordinator>getting the root naming context");
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			// Verwendung von NamingContextExt, ist Teil der Interoperable
			// Naming Service (INS) Spezifikation.
			System.out.println("Coordinator>using NamingContextExt to provides interoperability");
			final NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// binde die Object Reference an einen Namen
			String name = args[4];
			System.out.println("Coordinator>binding the object reference in naming with name " + name);
			final NameComponent path[] = ncRef.to_name(name);

			// Objekt href einen Namen zuweisen, unter dem es beim Naming
			// Service nachfragt werden kann
			ncRef.rebind(path, href);
			System.out.println("Coordinator>coordinator was started...");
			
			Thread hook = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("Coordinator>invoked shutdownHook");
					System.out.print("Coordinator>telling all starters to quit...");
					coordServant.unregisterAllStarters();
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
					System.out.print("OK\nCoordinator>shutting down ORB...");
					orb.shutdown(true);
					System.out.println("OK\nCoordinator>shutdown was successful...");
				}
			});
			// Hook setzen und Objekte fuer die quit() Methode uebergeben 
			Runtime.getRuntime().addShutdownHook(hook);
			coordServant.setHook(hook);
			coordServant.setOrb(orb);
			coordServant.setNcRef(ncRef, path);

			// Orb starten und auf Clients warten
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Coordinator>EOF");

	}

}
