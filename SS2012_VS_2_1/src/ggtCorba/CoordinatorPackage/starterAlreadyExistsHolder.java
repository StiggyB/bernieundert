package ggtCorba.CoordinatorPackage;

/**
* ggtCorba/CoordinatorPackage/starterAlreadyExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 12:42 Uhr MESZ
*/

public final class starterAlreadyExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.CoordinatorPackage.starterAlreadyExists value = null;

  public starterAlreadyExistsHolder ()
  {
  }

  public starterAlreadyExistsHolder (ggtCorba.CoordinatorPackage.starterAlreadyExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.CoordinatorPackage.starterAlreadyExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.CoordinatorPackage.starterAlreadyExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.CoordinatorPackage.starterAlreadyExistsHelper.type ();
  }

}
