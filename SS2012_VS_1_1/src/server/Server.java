package server;

import java.util.Properties;

import lagern.Lager;
import lagern.LagerHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server {

	public static void main(String[] args) {

		try {

			// orb konfigurieren
			Properties props = new Properties();
			
			// nur fuer Testzwecke! geht spaeter ueber cmd-Parameter
			props.put("org.omg.CORBA.ORBInitialPort", "1051");
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");

			System.out.println("Server>starting server...");
	        System.out.println("Server>creating and initializing the ORB");
			ORB orb = ORB.init(args, props);

			System.out.println("Server>getting reference to rootpoa");
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			
			System.out.println("Server>activating the POA Manager");
			rootPoa.the_POAManager().activate();
			
			// Servant-Objekt erstellen
	        System.out.println("Server>Creating the servant");
			LagerImpl lagerServant = new LagerImpl();
			
			// Objekt Referenz des Servants holen
			System.out.println("Server>obtain the reference from the servant");
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(lagerServant);
			
			// Downcast Corba-Objekt -> MyKonto
			Lager href = LagerHelper.narrow(ref);
			
			// Referenz zum Namensdiesnt (root naming context) holen
		    System.out.println("Server>getting the root naming context");
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// Verwendung von NamingContextExt, ist Teil der Interoperable
			// Naming Service (INS) Spezifikation.
			System.out.println("Server>using NamingContextExt to provides interoperability");  
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// binde die Object Reference an einen Namen
			String name = "VS1_Lager";
			System.out.println("Server>binding the object reference in naming with name "+ name);
			NameComponent path[] = ncRef.to_name(name);

			// Objekt href einen Namen zuweisen, unter dem es beim Naming Service nachfragt werden kann
			ncRef.rebind(path, href);
			System.out.println("Server>Server was started...");
			
			// fuer shutdown
			lagerServant.setOrb(orb);
			
			// Orb starten und auf Clients warten
			orb.run();
			
			// Server beenden
			System.out.println("Server>shutdown of server is in progress...");
			ncRef.unbind(path);
	        System.out.println("Server>shutdown was successful...");

		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		
		System.out.println("Server>EOF");

	}

}
