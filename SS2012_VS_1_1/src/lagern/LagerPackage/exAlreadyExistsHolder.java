package lagern.LagerPackage;

/**
* lagern/LagerPackage/exAlreadyExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public final class exAlreadyExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.LagerPackage.exAlreadyExists value = null;

  public exAlreadyExistsHolder ()
  {
  }

  public exAlreadyExistsHolder (lagern.LagerPackage.exAlreadyExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.LagerPackage.exAlreadyExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.LagerPackage.exAlreadyExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.LagerPackage.exAlreadyExistsHelper.type ();
  }

}
