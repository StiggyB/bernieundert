package ggt.CoordinatorPackage;

/**
* ggt/CoordinatorPackage/starterDoesNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 19. April 2012 20:21 Uhr MESZ
*/

public final class starterDoesNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.CoordinatorPackage.starterDoesNotExists value = null;

  public starterDoesNotExistsHolder ()
  {
  }

  public starterDoesNotExistsHolder (ggt.CoordinatorPackage.starterDoesNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.CoordinatorPackage.starterDoesNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.CoordinatorPackage.starterDoesNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.CoordinatorPackage.starterDoesNotExistsHelper.type ();
  }

}
