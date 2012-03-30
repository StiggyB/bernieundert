package lagern;


/**
* lagern/LagerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* den 25 oktober 2010 kl 15:09 CEST
*/

public abstract class LagerPOA extends org.omg.PortableServer.Servant
 implements lagern.LagerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("neu", new java.lang.Integer (0));
    _methods.put ("hole", new java.lang.Integer (1));
    _methods.put ("holeLagerListe", new java.lang.Integer (2));
    _methods.put ("aktiviereMonitor", new java.lang.Integer (3));
    _methods.put ("entferneMonitor", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // lagern/Lager/neu
       {
         try {
           String besitzer = in.read_string ();
           String name = in.read_string ();
           lagern.Lagerfach $result = null;
           $result = this.neu (besitzer, name);
           out = $rh.createReply();
           lagern.LagerfachHelper.write (out, $result);
         } catch (lagern.LagerPackage.alreadyExists $ex) {
           out = $rh.createExceptionReply ();
           lagern.LagerPackage.alreadyExistsHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // lagern/Lager/hole
       {
         try {
           String name = in.read_string ();
           lagern.Lagerfach $result = null;
           $result = this.hole (name);
           out = $rh.createReply();
           lagern.LagerfachHelper.write (out, $result);
         } catch (lagern.LagerPackage.notFound $ex) {
           out = $rh.createExceptionReply ();
           lagern.LagerPackage.notFoundHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // lagern/Lager/holeLagerListe
       {
         String $result[] = null;
         $result = this.holeLagerListe ();
         out = $rh.createReply();
         lagern.LagerfaecherListeHelper.write (out, $result);
         break;
       }

       case 3:  // lagern/Lager/aktiviereMonitor
       {
         String user = in.read_string ();
         lagern.Monitor theMonitor = lagern.MonitorHelper.read (in);
         this.aktiviereMonitor (user, theMonitor);
         out = $rh.createReply();
         break;
       }

       case 4:  // lagern/Lager/entferneMonitor
       {
         String user = in.read_string ();
         lagern.Monitor theMonitor = lagern.MonitorHelper.read (in);
         this.entferneMonitor (user, theMonitor);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:lagern/Lager:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Lager _this() 
  {
    return LagerHelper.narrow(
    super._this_object());
  }

  public Lager _this(org.omg.CORBA.ORB orb) 
  {
    return LagerHelper.narrow(
    super._this_object(orb));
  }


} // class LagerPOA
