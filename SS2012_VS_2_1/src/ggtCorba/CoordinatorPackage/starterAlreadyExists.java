package ggtCorba.CoordinatorPackage;


/**
* ggtCorba/CoordinatorPackage/starterAlreadyExists.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 19:19 Uhr MESZ
*/

public final class starterAlreadyExists extends org.omg.CORBA.UserException
{
  public String s = null;

  public starterAlreadyExists ()
  {
    super(starterAlreadyExistsHelper.id());
  } // ctor

  public starterAlreadyExists (String _s)
  {
    super(starterAlreadyExistsHelper.id());
    s = _s;
  } // ctor


  public starterAlreadyExists (String $reason, String _s)
  {
    super(starterAlreadyExistsHelper.id() + "  " + $reason);
    s = _s;
  } // ctor

} // class starterAlreadyExists
