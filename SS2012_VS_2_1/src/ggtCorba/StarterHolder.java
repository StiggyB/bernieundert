package ggtCorba;

/**
* ggtCorba/StarterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 17:00 Uhr MESZ
*/

public final class StarterHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.Starter value = null;

  public StarterHolder ()
  {
  }

  public StarterHolder (ggtCorba.Starter initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.StarterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.StarterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.StarterHelper.type ();
  }

}
