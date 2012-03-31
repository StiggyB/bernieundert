package client;

import java.util.Properties;

import lagern.Lager;
import lagern.LagerHelper;
import lagern.Monitor;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
	public static void main(String[] args) {

		try {
			// Zugang zum Namensdienst festlegen (ORB init)
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1051");
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			
			System.out.println("Client>Creating and initializing the ORB");
			ORB orb = ORB.init(args, props);

			// Zum Namensdienst verbinden (Referenz holen und wandeln)
			System.out.println("Client>getting the root naming context");
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Objektreferenz mit Namen "Konto" besorgen
			System.out.println("Client>Resolving the object reference");
			org.omg.CORBA.Object obj = nc.resolve_str("VS1_Lager"); // Corba
			
			// Cast Corba ref -> Java Referenz
			System.out.println("Client>getting remote object...");
			Lager lagerRef = LagerHelper.narrow(obj);

			
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
		}

	}
}
