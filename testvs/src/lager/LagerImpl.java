package lager;

/* Verteilte Systeme Praktikum
 * Aufgabe 1
 * Praktikumsgruppe 2
 * 
 * ------Teammitglieder-----
 * - Falk Böschen  1940016 -
 * - Laura Knetter 1938302 -
 * -------------------------
 */

import java.util.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import lagern.*;
import lagern.LagerPackage.alreadyExists;
import lagern.LagerPackage.notFound;


public class LagerImpl extends LagerPOA {

	List<LagerfachImpl> lagerf = new ArrayList<LagerfachImpl>();
	static List<Monitor> monitore = new ArrayList<Monitor>();
	
	private static POA rootPoa;
	private static ORB orb;
	private static NamingContextExt ncRef;
	
	public static void main(String[] args) 
	{
		try 
		{
			  // ORB Eigenschaftensetzen
			  Properties props = new Properties();
			  orb = ORB.init(args , props );
			  // get ref. of rootPOA und activate POA Manager
		      rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		      rootPoa.the_POAManager().activate();

		      // create servant and register it with the ORB
		      LagerImpl lager = new LagerImpl ();

		      // get object reference from the servant
		      org.omg.CORBA.Object ref = rootPoa.servant_to_reference(lager);
		      Lager href = LagerHelper.narrow(ref);

		      // get the root naming context
		      org.omg.CORBA.Object objRef =	orb.resolve_initial_references("NameService");
		      // Use NamingContextExt which is part of the Interoperable
		      // Naming Service (INS) specification.
		      ncRef = NamingContextExtHelper.narrow(objRef);

		      // bind the Object Reference in Naming
		      String name = "Lager";
		      NameComponent path[] = ncRef.to_name( name );
		      ncRef.rebind(path, href);

		      System.out.println("LagerServer ready and waiting ...");

		      // initialize shutdownhook
		      lager.new ShutdownHook();
		      // wait for invocations from clients
		      orb.run();
		 }
	    catch (Exception e) 
	    {
	    	System.err.println("ERROR: " + e);
	    	e.printStackTrace(System.out);
	    	System.exit(0);
	    }
	    System.out.println("LagerServer Exiting ...");
	}

	@Override
	public synchronized void aktiviereMonitor(String user, Monitor theMonitor) 
	{
	// add monitor to list
		monitore.add(theMonitor);
		System.out.println("Monitor " + user + " wurde aktiviert.");
	}

	@Override
	public synchronized void entferneMonitor(String user, Monitor theMonitor) 
	{
	// remove monitor from list
		if(!monitore.remove(theMonitor))
			System.err.println("Monitor " + user + " konnte nicht entfernt werden.");
		System.out.println("Monitor " + user + " wurde entfernt.");
	}

	@Override
	public Lagerfach hole(String name) throws notFound 
	{
	// check whole list of storages for name
		for(LagerfachImpl tmp : lagerf)
		{
			if( tmp.getName().equals(name))
			{
	// when name found return storage
				try 
				{
					return LagerfachHelper.narrow(rootPoa.servant_to_reference((Servant)tmp));
				} 
				catch (ServantNotActive e) 
				{
					System.err.println("ERROR: " + e);
					e.printStackTrace();
				} 
				catch (WrongPolicy e) 
				{
					System.err.println("ERROR: " + e);
					e.printStackTrace();
				};
			}
		}
		throw new notFound("Error");
	}

	@Override
	public String[] holeLagerListe() 
	{
	// return array of strings of all Storages
		String[] list = new String[lagerf.size()];
		int i = 0;
		for(LagerfachImpl tmp : lagerf)
		{
			list[i++] = tmp.toString();
		}
		return list;
	}

	@Override
	public synchronized Lagerfach neu(String besitzer, String name) throws alreadyExists 
	{
	// Initialization
		LagerfachImpl fachimpl = null;
		Lagerfach fach = null;
	// Check if name already exists or is a emtpy string
		if( name.equals(""))
			throw new alreadyExists("Error");
		for(LagerfachImpl tmp : lagerf)
		{
			if( tmp.getName().equals(name))
				throw new alreadyExists("Error");
		}
	// If not create Storage and return reference
	    try 
	    {
	    	fachimpl = new LagerfachImpl( name , besitzer);
			fach = LagerfachHelper.narrow(rootPoa.servant_to_reference((Servant)fachimpl));
		} 
	    catch (ServantNotActive e) 
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace();
		} 
		catch (WrongPolicy e) 
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace();
		}
	// add new Storage to list and signal monitors
		lagerf.add(fachimpl);
		signalMonitor(besitzer," hat Lagerfach " + name + " angelegt.");
		
		return fach;
	}
	
	public static synchronized void signalMonitor(String user, String log)
	{
		for(Monitor tmp : monitore)
		{
	// Signal all monitors in list 
			tmp.aktion(user, log);
		}
	}
	
	public class ShutdownHook implements Runnable 
	{ 
			  public ShutdownHook () 
			  {  
				  // Register a shutdown hook for this class.  
				  // A shutdown hook is an initialized but not started thread, which will get up and run  
				  // When the JVM is about to exit. This is used for short clean up tasks.  
				  Runtime.getRuntime().addShutdownHook (new Thread (this));  
			  }  

			  public void run () 
			  {  
				  this.cleanUp ();  
			  } 
			  private void cleanUp()
			  {
				  // Quit all monitors
				  for(Monitor tokill : monitore)
				  {
					  tokill.quit();
				  }
				  
			      try 
			      {
			    	  String name = "Lager";
			    	  NameComponent path[] = ncRef.to_name( name );
			    	  ncRef.unbind(path);
			      } 
			      catch (NotFound e) 
			      {
			    	  e.printStackTrace();
			      } 
			      catch (CannotProceed e) 
			      {
			    	  e.printStackTrace();
			      } 
			      catch (InvalidName e) 
			      {
			    	  e.printStackTrace();
			      }
			      finally
			      {
			    	  orb.shutdown(false);
			    	  orb.destroy();
			      }
			  }
	}

}
