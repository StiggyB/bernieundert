package ggtCorba;


/**
* ggtCorba/starterListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 17:00 Uhr MESZ
*/

public final class starterListHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.Starter value[] = null;

  public starterListHolder ()
  {
  }

  public starterListHolder (ggtCorba.Starter[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.starterListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.starterListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.starterListHelper.type ();
  }

}
