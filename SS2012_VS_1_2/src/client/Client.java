package client;


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
	
	private Lager lagerRef;
	
	public static void main(String[] args) {
		new Client().start(args);
	}

	private void start(String[] args) {
		String lagername;
		String lagerfachname;
		String aktion;
		String username;
		int anzahl;

		//Fach anlegen:
//		-ORBInitialPort 1051 -ORBInitialHost localhost VS1_Lager neu Dildos DildoFee
		//       0         1          2           3          4      5     6       7 
		//einlagern:
		//-ORBInitialPort 1051 -ORBInitialHost localhost VS1_Lager einlagern Dildos 1337 DildoFee
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
				} else if(aktion.equals("liste")) {
					liste();
				} else if(aktion.equals("lagerloop")) {
					anzahl = Integer.parseInt(args[6]);
					lagerTestLoop(anzahl);
				} else if(aktion.equals("neu")){
					lagerfachname = args[6];
					username = args[7];
					neu(lagerfachname, username);
				} else if(aktion.equals("einlagern")) {
					lagerfachname = args[6];
					anzahl = Integer.parseInt(args[7]);
					username = args[8];
					einlagern(username, lagerfachname, anzahl);
				} else if(aktion.equals("auslagern")) {
					lagerfachname = args[6];
					anzahl = Integer.parseInt(args[7]);
					username = args[8];
					auslagern(username, lagerfachname, anzahl);
				}
				
				System.out.println("Client>executed chosen parameter...");
				
			} catch (Exception e) {
				System.out.println(e.getClass());
			}

			
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(1);
		}
		
		System.out.println("Client>exit successful ...");

	}

	private void lagerTestLoop(int anzahl) {
		String lagerfachname = "lagertestloop";
		String username = "lagertest";
		int loopAnzahl = 0;
		try {
			lagerRef.neu(username, lagerfachname);
		} catch (exAlreadyExists e) {
			System.out.println("neu(): Fach '" + lagerfachname	+ "' existiert bereits!");
			System.err.println(e.getMessage());
		}

		Fach fach = null;

		try {
			
			fach = lagerRef.hole(username, lagerfachname);
			for (int i = 1; i < anzahl+1; i++) {
				fach.einlagern(username, i);
				loopAnzahl+=i;
			}
		} catch (exNotFound e) {
			System.out.println("einlagern(): Fach '" + lagerfachname + "' existiert nicht!");
			System.err.println(e.getMessage());
		} catch (exInvalidCount e1) {
			System.out.println("einlagern(): Ungueltige Anzahl!");
			System.err.println(e1.getMessage());
		}

		System.out.println("einlagern(): '" + loopAnzahl + "' Teile in '" + lagerfachname + "' eingelagert!");
	}
	
	

	private void quit() {
		lagerRef.quit();
	}

	private void liste() {
		Fach[] faecher = lagerRef.holeLagerListe();
		System.out.println("liste(): Fachname:\t\t\tAnzahl Teile:");
		System.out.println("liste(): ============================================");
		for (Fach fach : faecher) {
			System.out.printf("liste(): %-27s -> %-15s\n", fach.name(),
					fach.anzahl());
		}
	}

	private void neu(String lagerfachname, String username) {
		try {
			lagerRef.neu(username, lagerfachname);
		} catch (exAlreadyExists e) {
			System.out.println("neu(): Fach '" + lagerfachname + "' existiert bereits!");
			System.err.println(e.getMessage());
		}
	}

	private void auslagern(String username, String lagerfachname, int anzahl) {
		Fach fach = null;
		
		try {
			fach = lagerRef.hole(username, lagerfachname);
			fach.auslagern(username, anzahl);
			System.out.println("auslagern(): '" + anzahl + "' Teile aus '" + lagerfachname + "' ausgelagert!");
			
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
		
	}

	private void einlagern(String username, String lagerfachname, int anzahl) {
		Fach fach = null;

		try {
			fach = lagerRef.hole(username, lagerfachname);
			fach.einlagern(username, anzahl);
			System.out.println("einlagern(): '" + anzahl + "' Teile in '" + lagerfachname + "' eingelagert!");
			
		} catch (exNotFound e) {
			System.out.println("einlagern(): Fach '" + lagerfachname + "' existiert nicht!");
			System.err.println(e.getMessage());
		} catch (exInvalidCount e1) {
			System.out.println("einlagern(): Ungueltige Anzahl!");
			System.err.println(e1.getMessage());
		}
		
	}
}
