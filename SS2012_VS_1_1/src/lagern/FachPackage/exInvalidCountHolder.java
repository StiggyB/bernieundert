package lagern.FachPackage;

/**
* lagern/FachPackage/exInvalidCountHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public final class exInvalidCountHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.FachPackage.exInvalidCount value = null;

  public exInvalidCountHolder ()
  {
  }

  public exInvalidCountHolder (lagern.FachPackage.exInvalidCount initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.FachPackage.exInvalidCountHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.FachPackage.exInvalidCountHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.FachPackage.exInvalidCountHelper.type ();
  }

}
