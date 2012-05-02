package ggtCorba;


/**
* ggtCorba/CoordinatorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 23:06 Uhr MESZ
*/

public abstract class CoordinatorPOA extends org.omg.PortableServer.Servant
 implements ggtCorba.CoordinatorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getStarters", new java.lang.Integer (0));
    _methods.put ("start", new java.lang.Integer (1));
    _methods.put ("shutdown", new java.lang.Integer (2));
    _methods.put ("isCalculating", new java.lang.Integer (3));
    _methods.put ("registerStarter", new java.lang.Integer (4));
    _methods.put ("registerProcess", new java.lang.Integer (5));
    _methods.put ("unregisterStarter", new java.lang.Integer (6));
    _methods.put ("processCalcDone", new java.lang.Integer (7));
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
       case 0:  // ggtCorba/Coordinator/getStarters
       {
         ggtCorba.Starter $result[] = null;
         $result = this.getStarters ();
         out = $rh.createReply();
         ggtCorba.starterListHelper.write (out, $result);
         break;
       }

       case 1:  // ggtCorba/Coordinator/start
       {
         try {
           int minProcess = in.read_long ();
           int maxProcess = in.read_long ();
           int minDelay = in.read_long ();
           int maxDelay = in.read_long ();
           int timeout = in.read_long ();
           int ggt = in.read_long ();
           monitor.Monitor mntr = monitor.MonitorHelper.read (in);
           this.start (minProcess, maxProcess, minDelay, maxDelay, timeout, ggt, mntr);
           out = $rh.createReply();
         } catch (ggtCorba.CoordinatorPackage.noStarters $ex) {
           out = $rh.createExceptionReply ();
           ggtCorba.CoordinatorPackage.noStartersHelper.write (out, $ex);
         } catch (ggtCorba.CoordinatorPackage.calculationInProgress $ex) {
           out = $rh.createExceptionReply ();
           ggtCorba.CoordinatorPackage.calculationInProgressHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // ggtCorba/Coordinator/shutdown
       {
         boolean $result = false;
         $result = this.shutdown ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 3:  // ggtCorba/Coordinator/isCalculating
       {
         boolean $result = false;
         $result = this.isCalculating ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 4:  // ggtCorba/Coordinator/registerStarter
       {
         try {
           ggtCorba.Starter starter = ggtCorba.StarterHelper.read (in);
           this.registerStarter (starter);
           out = $rh.createReply();
         } catch (ggtCorba.CoordinatorPackage.starterAlreadyExists $ex) {
           out = $rh.createExceptionReply ();
           ggtCorba.CoordinatorPackage.starterAlreadyExistsHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // ggtCorba/Coordinator/registerProcess
       {
         ggtCorba.ggtProcess process = ggtCorba.ggtProcessHelper.read (in);
         String processName = in.read_string ();
         this.registerProcess (process, processName);
         out = $rh.createReply();
         break;
       }

       case 6:  // ggtCorba/Coordinator/unregisterStarter
       {
         try {
           ggtCorba.Starter starter = ggtCorba.StarterHelper.read (in);
           this.unregisterStarter (starter);
           out = $rh.createReply();
         } catch (ggtCorba.CoordinatorPackage.starterDoesNotExists $ex) {
           out = $rh.createExceptionReply ();
           ggtCorba.CoordinatorPackage.starterDoesNotExistsHelper.write (out, $ex);
         }
         break;
       }

       case 7:  // ggtCorba/Coordinator/processCalcDone
       {
         ggtCorba.ggtProcess process = ggtCorba.ggtProcessHelper.read (in);
         this.processCalcDone (process);
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
    "IDL:ggtCorba/Coordinator:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Coordinator _this() 
  {
    return CoordinatorHelper.narrow(
    super._this_object());
  }

  public Coordinator _this(org.omg.CORBA.ORB orb) 
  {
    return CoordinatorHelper.narrow(
    super._this_object(orb));
  }


} // class CoordinatorPOA
