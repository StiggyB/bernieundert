package client;



import ggt.Coordinator;
import ggt.CoordinatorHelper;
import ggt.Starter;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
	
	
	public static void main(String[] args) {
		new Client().start(args);
	}

	private void start(String[] args) {
		String lagername;
		Coordinator coordRef;
		//Fach anlegen:
//		-ORBInitialPort 1051 -ORBInitialHost localhost VS1_Lager neu Dildos DildoFee
		//       0         1          2           3          4      5     6       7 
		//einlagern:
		//-ORBInitialPort 1051 -ORBInitialHost localhost VS1_Lager einlagern Dildos 1337 DildoFee
		lagername = args[4];

		try {
			System.out.println("Client>Creating and initializing the ORB");
			ORB orb = ORB.init(args, null);

			// Zum Namensdienst verbinden (Referenz holen und wandeln)
			System.out.println("Client>getting the root naming context");
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Objektreferenz mit Namen "Konto" besorgen
			System.out.println("Client>Resolving the object reference");
			org.omg.CORBA.Object obj = nc.resolve_str(lagername); // Corba
			
			// Cast Corba ref -> Java Referenz
			System.out.println("Client>getting remote object...");
			coordRef = CoordinatorHelper.narrow(obj);
			Starter[] starters = coordRef.getStarters();
			for (Starter starter : starters) {
				System.out.println(starter.getName());
			}
			
			
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
		}
		
		System.out.println("Client>exit successful ...");

	}

}
