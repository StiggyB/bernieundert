package lagern;

/**
* lagern/LagerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public final class LagerHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.Lager value = null;

  public LagerHolder ()
  {
  }

  public LagerHolder (lagern.Lager initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.LagerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.LagerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.LagerHelper.type ();
  }

}
