package lagern.LagerPackage;


/**
* lagern/LagerPackage/exNotFound.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public final class exNotFound extends org.omg.CORBA.UserException
{
  public String s = null;

  public exNotFound ()
  {
    super(exNotFoundHelper.id());
  } // ctor

  public exNotFound (String _s)
  {
    super(exNotFoundHelper.id());
    s = _s;
  } // ctor


  public exNotFound (String $reason, String _s)
  {
    super(exNotFoundHelper.id() + "  " + $reason);
    s = _s;
  } // ctor

} // class exNotFound
