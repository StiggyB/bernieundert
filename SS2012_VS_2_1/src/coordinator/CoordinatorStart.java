package coordinator;

import ggt.Coordinator;
import ggt.CoordinatorHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
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
			CoordinatorImpl coordServant = new CoordinatorImpl();

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
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// binde die Object Reference an einen Namen
			String name = args[4];
			System.out.println("Coordinator>binding the object reference in naming with name " + name);
			NameComponent path[] = ncRef.to_name(name);

			// Objekt href einen Namen zuweisen, unter dem es beim Naming
			// Service nachfragt werden kann
			ncRef.rebind(path, href);
			System.out.println("Coordinator>coordinator was started...");

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