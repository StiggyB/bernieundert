package ggt;

/**
* ggt/ggtProcessHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 25. April 2012 10:34 Uhr MESZ
*/

public final class ggtProcessHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.ggtProcess value = null;

  public ggtProcessHolder ()
  {
  }

  public ggtProcessHolder (ggt.ggtProcess initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.ggtProcessHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.ggtProcessHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.ggtProcessHelper.type ();
  }

}
