package client;

//import java.util.Properties;

import lagern.Fach;
import lagern.Lager;
import lagern.LagerHelper;
import lagern.FachPackage.exInvalidCount;
import lagern.FachPackage.exNotEnoughPieces;
import lagern.LagerPackage.exAlreadyExists;
import lagern.LagerPackage.exNotFound;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {
	
	private static Lager lagerRef;
	private static String lagername;
	private static String lagerfachname;
	private static String aktion;
	private static String username;
	private static int anzahl;
	
	public static void main(String[] args) {
		
		lagername = args[4];
		aktion = args[5];

		try {
			// Zugang zum Namensdienst festlegen (ORB init)
//			Properties props = new Properties();
//			props.put("org.omg.CORBA.ORBInitialPort", "1051");
//			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			
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
			lagerRef = LagerHelper.narrow(obj);
			
			try {
				if(aktion.equals("quit")){
					quit();
				} else if(aktion.equals("liste")){
					liste();
				}
				
				lagerfachname = args[6];
				
				if(aktion.equals("neu")){
					username = args[7];

					neu();
				} else if(aktion.equals("einlagern")){
					anzahl = Integer.parseInt(args[7]);
					username = args[8];
					
					einlagern();
				} else if(aktion.equals("auslagern")){
					anzahl = Integer.parseInt(args[7]);
					username = args[8];
					
					auslagern();
				}
				
			} catch (Exception e) {
				System.out.println(e.getClass());
			}

			
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
		}

	}

	private static void quit() {
		// TODO Auto-generated method stub
		
	}

	private static void liste() {
		Fach[] faecher = lagerRef.holeLagerListe();
		for (Fach fach : faecher) {
			//TODO: wie an die faecher infos kommen?
		}
	}

	private static void neu() {
		try {
			lagerRef.neu(username, lagerfachname);
		} catch (exAlreadyExists e) {
			System.out.println("neu(): Fach '" + lagerfachname + "' existiert bereits!");
			System.err.println(e.getMessage());
		}
	}

	private static void auslagern() {
		Fach fach = null;
		
		try {
			fach = lagerRef.hole(username, lagerfachname);
			fach.auslagern(username, anzahl);
			
		} catch (exNotFound e) {
			System.out.println("einlagern(): Fach '" + lagerfachname + "' existiert nicht!");
			System.err.println(e.getMessage());
		} catch (exInvalidCount e1) {
			System.out.println("einlagern(): Ungueltige Anzahl!");
			System.err.println(e1.getMessage());
		} catch (exNotEnoughPieces e2) {
			System.out.println("einlagern(): Nicht genuegend Teile fuer Anfrage!");
			System.err.println(e2.getMessage());
		}
		
		System.out.println("auslagern(): '" + anzahl + "' Teile aus '" + lagerfachname + "' ausgelagert!");
	}

	private static void einlagern() {
		Fach fach = null;

		try {
			fach = lagerRef.hole(username, lagerfachname);
			fach.einlagern(username, anzahl);
			
		} catch (exNotFound e) {
			System.out.println("einlagern(): Fach '" + lagerfachname + "' existiert nicht!");
			System.err.println(e.getMessage());
		} catch (exInvalidCount e1) {
			System.out.println("einlagern(): Ungueltige Anzahl!");
			System.err.println(e1.getMessage());
		}
		
		//wieso fuehrt er dies noch aus, obwohl ne exception kam?!
		System.out.println("einlagern(): '" + anzahl + "' Teile in '" + lagerfachname + "' eingelagert!");
	}
}
