package ggtCorba.CoordinatorPackage;


/**
* ggtCorba/CoordinatorPackage/calculationInProgress.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 10. Mai 2012 16:13 Uhr MESZ
*/

public final class calculationInProgress extends org.omg.CORBA.UserException
{
  public String s = null;

  public calculationInProgress ()
  {
    super(calculationInProgressHelper.id());
  } // ctor

  public calculationInProgress (String _s)
  {
    super(calculationInProgressHelper.id());
    s = _s;
  } // ctor


  public calculationInProgress (String $reason, String _s)
  {
    super(calculationInProgressHelper.id() + "  " + $reason);
    s = _s;
  } // ctor

} // class calculationInProgress
