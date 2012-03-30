package client;

/* Verteilte Systeme Praktikum
 * Aufgabe 1
 * Praktikumsgruppe 2
 * 
 * ------Teammitglieder-----
 * - Falk Böschen  1940016 -
 * - Laura Knetter 1938302 -
 * -------------------------
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import lagern.*;
import lagern.LagerPackage.alreadyExists;
import lagern.LagerPackage.notFound;
import lagern.LagerfachPackage.invalidCount;
import lagern.LagerfachPackage.notEnoughPieces;

public class Client{

private static String name;
	public static void main(String[] args) 
	{
		try 
		{
				Properties props = new Properties();
	//			props.put("org.omg.CORBA.ORBInitialPort", "1050");
	//			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
				ORB orb = ORB.init(args, props);
				// NamingContext besorgen
				NamingContextExt nc = NamingContextExtHelper.narrow(
				orb.resolve_initial_references("NameService"));
				// Objektreferenz mit Namen "Lager" besorgen
				org.omg.CORBA.Object obj = nc.resolve_str("Lager");
				// Down-Cast
				Lager lager = LagerHelper.narrow (obj);
				// und aufrufen
				run(lager);
		} 
		catch (Exception ex) 
		{
				System.err.println (ex);
				System.exit(1);
		}
	}

	public static void run(Lager lager) 
	{
		// Initialization
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean running = true; 
		int input = 0;
		// Get User Name
		System.out.println("Guten Mahlzeit");
		System.out.println("Wie lautet ihr Name?");
		try 
		{
			name = in.readLine();
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
		try
		{
			while(running)
			{
			// Show possibilities	
				System.out.println("\n\nSie haben folgende Auswahlmoeglichkeiten : \n" +
								   "[1] Lagerfach neu anlegen \n" +
								   "[2] Liste der Lagerfaecher ausgeben \n" +
								   "[3] Einlagern von Teilen in ein Lagerfach \n" +
								   "[4] Auslagern von Teilen aus einem Lagerfach \n" +
								   "[5] Beenden" +
								   "\n\n" +
								   "Bitte geben sie nun die Nummer der gewuenschten Aktion ein.");
				// get choice
				try 
				{
					input = Integer.parseInt(in.readLine());
				} 
				catch (IOException e) 
				{
					System.err.println(e.getMessage());
				}
				switch(input)
				{
				// call functionality
					case 1 : anlegen(lager);									break;
					case 2 : ausgeben(lager);									break;
					case 3 : einlagern(lager);									break;
					case 4 : auslagern(lager);									break;
					case 5 : running = false;									break;
					default : System.out.println("Nicht auswertbare Eingabe");	break;
				}
			}
		}
		catch(Exception E)
		{
			System.out.println("lager unavailable; exiting");
		}
	}

	private static void auslagern(Lager lager) 
	{
	// Initialization
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int input;
		String eingabe = "";
		Lagerfach fach = null;
	// Get Storage name
		System.out.println("Bitte den Namen des Lagerfachs angeben : ");

		try 
		{
			eingabe = in.readLine();
	// Get Storage reference from Warehouse
			fach = lager.hole(eingabe);
		} 
		catch (notFound e) 
		{
			System.out.println("Ungueltiger Name.");
				return;
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
	
	// Get number of parts to remove and do it
		System.out.println("Bitte Anzahl der auszulagernden Teile angeben : ");
		try 
		{
			input = Integer.parseInt(in.readLine());
			fach.auslagern(name, input);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
			return;
		}
		catch (invalidCount e) 
		{
			System.out.println("Inkorrekte Zahl eingegeben. -> Abbruch");
			return;
		} 
		catch (notEnoughPieces e) 
		{
			System.out.println("Lagerfach enthält zu wenig Teile. -> Abbruch");
			return;
		}
		System.out.println(input + " Teile aus Lagerfach " + eingabe + " ausgelagert.");
	}

	private static void einlagern(Lager lager) 
	{
	// Initialization
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int input,runs;
		String eingabe = "";
		Lagerfach fach = null;
	// Get Storage name
		System.out.println("Bitte den Namen des Lagerfachs angeben : ");
			
		try 
		{
			eingabe = in.readLine();
	// Get Storage reference from Warehouse
			fach = lager.hole(eingabe);
		} 
		catch (notFound e) 
		{
			System.out.println("Ungueltiger Name.");
				return;
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
		
	// Get number of parts to insert
		System.out.println("Bitte Anzahl der einzulagernden Teile angeben : ");
		try 
		{
			input = Integer.parseInt(in.readLine());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
			return;
		}
	// Get number of loops
		System.out.println("Bitte Anzahl der Durchfuehrungen angeben : ");
		try 
		{
			runs = Integer.parseInt(in.readLine());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
			return;
		}
		for(int i = 0; i < runs; i ++)
		{
	// Insert parts
			try 
			{
				fach.einlagern(name, input);
			} 
			catch (invalidCount e) 
			{
				System.out.println("Inkorrekte Zahl eingegeben. -> Abbruch");
				return;
			}
		}
		System.out.println(input + " Teile aus Lagerfach " + eingabe + " eingelagert.");
	}

	private static void ausgeben(Lager lager) 
	{
	// Print all Storages
		String[] ausgabe = lager.holeLagerListe();
		for(int i = 0; i < ausgabe.length; i++)
			System.out.println(ausgabe[i]);	
	}

	private static void anlegen(Lager lager) 
	{
	// Initialization
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Lagerfach fach = null;
		String eingabe = "";
	// Get Storage name
		System.out.println("Bitte den Namen des Lagerfachs angeben : ");
		try 
		{
			eingabe = in.readLine();
	// Create Storage at Warehouse
			fach = lager.neu(name, eingabe);
		} 
		catch (IOException e1) 
		{
			System.err.println(e1.getMessage());
		}

		catch (alreadyExists e) 
		{
			System.out.println("Fehlerhafte Eingabe --> Abbruch");
			return;
		}
		System.out.println("Lagerfach mit dem Namen " + eingabe + " erfolgreich angelegt.");
	// Give User the possibility to insert directly
		System.out.println("Wollen sie direkt etwas einlagern (y)? ");
		try 
		{
			eingabe = in.readLine();
			if(eingabe.equals("y") || eingabe.equals("Y") )
				einlagern(fach);
		} 
		catch (IOException e1) 
		{
			System.err.println(e1.getMessage());
		}
	}

	private static void einlagern(Lagerfach fach) 
	{
	// Initialization
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int input,runs;
	// Get number of parts to insert
		System.out.println("Bitte Anzahl der einzulagernden Teile angeben : ");
		try 
		{
			input = Integer.parseInt(in.readLine());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
			return;
		}
	// Get number of loops
		System.out.println("Bitte Anzahl der Durchfuehrungen angeben : ");
		try 
		{
			runs = Integer.parseInt(in.readLine());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error");
			return;
		}
		for(int i = 0; i < runs; i ++)
		{
	// Insert parts
			try 
			{
				fach.einlagern(name, input);
				System.out.println(input + " Teile in Lagerfach eingelagert.");
			} 
			catch (invalidCount e) 
			{
				System.out.println("Inkorrekte Zahl eingegeben. -> Abbruch");
				return;
			}
		}
		
	}

}

