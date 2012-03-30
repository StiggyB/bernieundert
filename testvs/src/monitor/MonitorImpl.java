package monitor;

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
import java.io.InputStreamReader;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import lagern.*;


public class MonitorImpl extends MonitorPOA {
	
	private String name;
	private Lager lager;
	private static Monitor href;
	private static ShutdownHook hook;
	private static ORB orb;
	public MonitorImpl(String name, Lager lager)
	{
		this.name = name;
		this.lager = lager;
	}
	public static void main(String[] args) 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String user = "";
		try 
		{
				Properties props = new Properties();
	//			props.put("org.omg.CORBA.ORBInitialPort", "1050");
	//			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
				orb = ORB.init(args, props);
				// NamingContext besorgen
				NamingContextExt nc = NamingContextExtHelper.narrow(
				orb.resolve_initial_references("NameService"));
				// Objektreferenz mit Namen "Lager" besorgen
				org.omg.CORBA.Object obj = nc.resolve_str("Lager");
				// Down-Cast
				Lager lager = LagerHelper.narrow (obj);
				// und aufrufen
				System.out.println("Name?");
				user = in.readLine();
				POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			    rootPoa.the_POAManager().activate();

	    // activate monitor at warehouse
			    MonitorImpl mon = new MonitorImpl(user, lager);
			    
			    org.omg.CORBA.Object ref = rootPoa.servant_to_reference(mon);
			    href = MonitorHelper.narrow(ref);
		// initialize shutdownhook
				lager.aktiviereMonitor(user, href);
				hook = mon.new ShutdownHook();
				orb.run();
		} 
		catch (Exception ex) 
		{
				System.err.println (ex);
				System.exit(1);
		}
	}

	@Override
	public void aktion(String user, String log) 
	{
	// print log
		System.out.println(user + log);
	}

	@Override
	public void quit() 
	{
	// exit without standard ShutdownHook
		hook.remove();
		new ShutdownM();

	}

	private class ShutdownM implements Runnable
	{

		@Override
		public void run() 
		{
			orb.shutdown(true);
		}
		public ShutdownM()
		{
			Thread s = new Thread(this);
			s.start();
		}
		
	}
	
	public class ShutdownHook implements Runnable 
	{ 
		Thread sdh;
			  public ShutdownHook () 
			  {  
				  // Register a shutdown hook for this class.  
				  // A shutdown hook is an initialized but not started thread, which will get up and run  
				  // When the JVM is about to exit. This is used for short clean up tasks.  
				  sdh = new Thread(this);
				  Runtime.getRuntime().addShutdownHook (sdh);  
			  }  

			  public void run () 
			  {  
				  this.cleanUp ();  
			  } 
			  private void cleanUp()
			  {
			  //  unsubscribe monitor at warehouse
				  lager.entferneMonitor(name, href);
			  }
			  public void remove()
			  {
				  Runtime.getRuntime().removeShutdownHook(sdh);
			  }
	}
}
