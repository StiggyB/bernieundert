package ggtCorba;


/**
* ggtCorba/StarterPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 19:19 Uhr MESZ
*/

public abstract class StarterPOA extends org.omg.PortableServer.Servant
 implements ggtCorba.StarterOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getName", new java.lang.Integer (0));
    _methods.put ("createProcesses", new java.lang.Integer (1));
    _methods.put ("killProcesses", new java.lang.Integer (2));
    _methods.put ("shutdown", new java.lang.Integer (3));
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
       case 0:  // ggtCorba/Starter/getName
       {
         String $result = null;
         $result = this.getName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // ggtCorba/Starter/createProcesses
       {
         int count = in.read_long ();
         this.createProcesses (count);
         out = $rh.createReply();
         break;
       }

       case 2:  // ggtCorba/Starter/killProcesses
       {
         this.killProcesses ();
         out = $rh.createReply();
         break;
       }

       case 3:  // ggtCorba/Starter/shutdown
       {
         this.shutdown ();
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
    "IDL:ggtCorba/Starter:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Starter _this() 
  {
    return StarterHelper.narrow(
    super._this_object());
  }

  public Starter _this(org.omg.CORBA.ORB orb) 
  {
    return StarterHelper.narrow(
    super._this_object(orb));
  }


} // class StarterPOA
