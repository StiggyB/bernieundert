package ggt.CoordinatorPackage;


/**
* ggt/CoordinatorPackage/starterDoesNotExists.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 25. April 2012 11:30 Uhr MESZ
*/

public final class starterDoesNotExists extends org.omg.CORBA.UserException
{
  public String s = null;

  public starterDoesNotExists ()
  {
    super(starterDoesNotExistsHelper.id());
  } // ctor

  public starterDoesNotExists (String _s)
  {
    super(starterDoesNotExistsHelper.id());
    s = _s;
  } // ctor


  public starterDoesNotExists (String $reason, String _s)
  {
    super(starterDoesNotExistsHelper.id() + "  " + $reason);
    s = _s;
  } // ctor

} // class starterDoesNotExists
