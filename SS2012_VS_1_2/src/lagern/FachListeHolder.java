package lagern;


/**
* lagern/FachListeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public final class FachListeHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.Fach value[] = null;

  public FachListeHolder ()
  {
  }

  public FachListeHolder (lagern.Fach[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.FachListeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.FachListeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.FachListeHelper.type ();
  }

}
