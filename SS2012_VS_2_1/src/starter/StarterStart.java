package starter;



import ggt.Coordinator;
import ggt.CoordinatorHelper;
import ggt.Starter;
import ggt.StarterHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class StarterStart {
	
	public static void main(String[] args) {
		try {
			String lagername = args[4];
			System.out.println("Monitor>Creating and initializing the ORB");
			final ORB orb = ORB.init(args, null);

			// Zum Namensdienst verbinden (Referenz holen und wandeln)
			System.out.println("Monitor>getting the root naming context");
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Objektreferenz mit Namen "Konto" besorgen
			System.out.println("Monitor>Resolving the object reference");
			org.omg.CORBA.Object obj = nc.resolve_str(lagername); // Corba
			
			// Cast Corba ref -> Java Referenz
			System.out.println("Monitor>getting remote object...");
			final Coordinator coordRef = CoordinatorHelper.narrow(obj);
			
			// Neuen Monitor erzeugen
			StarterImpl starter = new StarterImpl();;
			
			//TODO: Wieso? ...
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();
			
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(starter);
			
			Starter href = StarterHelper.narrow(ref);

			// Setze monitor referenz
			System.out.println("Monitor>adding Monitor to Lager...");
			coordRef.registerStarter(href);

			System.out.println("Monitor>Monitor was started...");

			orb.run();
			
		} catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}

		System.out.println("Monitor>EOF");
	}

	
}
