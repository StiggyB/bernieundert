package monitor;

import java.util.Properties;

import lagern.Lager;
import lagern.LagerHelper;
import lagern.MonitorHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Monitor {
	
	public static void main(String[] args) {
		try {
			// Zugang zum Namensdienst festlegen (ORB init)
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1051");
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			
			System.out.println("Monitor>Creating and initializing the ORB");
			ORB orb = ORB.init(args, props);

			// Zum Namensdienst verbinden (Referenz holen und wandeln)
			System.out.println("Monitor>getting the root naming context");
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			// Objektreferenz mit Namen "Konto" besorgen
			System.out.println("Monitor>Resolving the object reference");
			org.omg.CORBA.Object obj = nc.resolve_str("VS1_Lager"); // Corba
			
			// Cast Corba ref -> Java Referenz
			System.out.println("Monitor>getting remote object...");
			Lager lagerRef = LagerHelper.narrow(obj);
			
			// Neuen Monitor erzeugen
			MonitorImpl monitor = new MonitorImpl();
			
			
			//TODO: Wieso? ...
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate();
			
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(monitor);
			
			lagern.Monitor href = MonitorHelper.narrow(ref);

			// Setze monitor referenz in KontoListe
			lagerRef.aktiviereMonitor(href);

			orb.run();
			
		} catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}

		System.out.println("Monitor>EOF");
	}

}
