package ggt;


/**
* ggt/ggtProcessPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 25. April 2012 14:07 Uhr MESZ
*/

public abstract class ggtProcessPOA extends org.omg.PortableServer.Servant
 implements ggt.ggtProcessOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("initProcess", new java.lang.Integer (0));
    _methods.put ("start", new java.lang.Integer (1));
    _methods.put ("calc", new java.lang.Integer (2));
    _methods.put ("terminate", new java.lang.Integer (3));
    _methods.put ("getName", new java.lang.Integer (4));
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

  // Name/ID des Prozess im Konstruktor
       case 0:  // ggt/ggtProcess/initProcess
       {
         ggt.ggtProcess left = ggt.ggtProcessHelper.read (in);
         ggt.ggtProcess right = ggt.ggtProcessHelper.read (in);
         int startValue = in.read_long ();
         int delay = in.read_long ();
         int timeout = in.read_long ();
         monitor.Monitor mntr = monitor.MonitorHelper.read (in);
         this.initProcess (left, right, startValue, delay, timeout, mntr);
         out = $rh.createReply();
         break;
       }

       case 1:  // ggt/ggtProcess/start
       {
         this.start ();
         out = $rh.createReply();
         break;
       }

       case 2:  // ggt/ggtProcess/calc
       {
         int mi = in.read_long ();
         this.calc (mi);
         out = $rh.createReply();
         break;
       }

       case 3:  // ggt/ggtProcess/terminate
       {
         String processName = in.read_string ();
         boolean $result = false;
         $result = this.terminate (processName);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 4:  // ggt/ggtProcess/getName
       {
         String $result = null;
         $result = this.getName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ggt/ggtProcess:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ggtProcess _this() 
  {
    return ggtProcessHelper.narrow(
    super._this_object());
  }

  public ggtProcess _this(org.omg.CORBA.ORB orb) 
  {
    return ggtProcessHelper.narrow(
    super._this_object(orb));
  }


} // class ggtProcessPOA
