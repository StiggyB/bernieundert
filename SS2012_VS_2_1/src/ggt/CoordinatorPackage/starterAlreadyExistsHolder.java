package ggt.CoordinatorPackage;

/**
* ggt/CoordinatorPackage/starterAlreadyExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 25. April 2012 11:40 Uhr MESZ
*/

public final class starterAlreadyExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.CoordinatorPackage.starterAlreadyExists value = null;

  public starterAlreadyExistsHolder ()
  {
  }

  public starterAlreadyExistsHolder (ggt.CoordinatorPackage.starterAlreadyExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.CoordinatorPackage.starterAlreadyExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.CoordinatorPackage.starterAlreadyExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.CoordinatorPackage.starterAlreadyExistsHelper.type ();
  }

}
