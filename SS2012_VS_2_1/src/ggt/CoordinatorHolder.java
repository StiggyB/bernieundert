package ggt;

/**
* ggt/CoordinatorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 26. April 2012 17:40 Uhr MESZ
*/

public final class CoordinatorHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.Coordinator value = null;

  public CoordinatorHolder ()
  {
  }

  public CoordinatorHolder (ggt.Coordinator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.CoordinatorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.CoordinatorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.CoordinatorHelper.type ();
  }

}
