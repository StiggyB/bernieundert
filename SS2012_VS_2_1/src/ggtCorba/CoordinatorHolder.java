package ggtCorba;

/**
* ggtCorba/CoordinatorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 16:35 Uhr MESZ
*/

public final class CoordinatorHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.Coordinator value = null;

  public CoordinatorHolder ()
  {
  }

  public CoordinatorHolder (ggtCorba.Coordinator initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.CoordinatorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.CoordinatorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.CoordinatorHelper.type ();
  }

}
