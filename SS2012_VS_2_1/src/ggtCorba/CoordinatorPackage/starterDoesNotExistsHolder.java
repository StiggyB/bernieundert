package ggtCorba.CoordinatorPackage;

/**
* ggtCorba/CoordinatorPackage/starterDoesNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 16:31 Uhr MESZ
*/

public final class starterDoesNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.CoordinatorPackage.starterDoesNotExists value = null;

  public starterDoesNotExistsHolder ()
  {
  }

  public starterDoesNotExistsHolder (ggtCorba.CoordinatorPackage.starterDoesNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.CoordinatorPackage.starterDoesNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.CoordinatorPackage.starterDoesNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.CoordinatorPackage.starterDoesNotExistsHelper.type ();
  }

}
