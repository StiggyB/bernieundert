package lagern.LagerPackage;

/**
* lagern/LagerPackage/exNotFoundHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public final class exNotFoundHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.LagerPackage.exNotFound value = null;

  public exNotFoundHolder ()
  {
  }

  public exNotFoundHolder (lagern.LagerPackage.exNotFound initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.LagerPackage.exNotFoundHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.LagerPackage.exNotFoundHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.LagerPackage.exNotFoundHelper.type ();
  }

}
