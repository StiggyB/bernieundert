package ggtCorba.CoordinatorPackage;

/**
* ggtCorba/CoordinatorPackage/calculationInProgressHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 16:31 Uhr MESZ
*/

public final class calculationInProgressHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.CoordinatorPackage.calculationInProgress value = null;

  public calculationInProgressHolder ()
  {
  }

  public calculationInProgressHolder (ggtCorba.CoordinatorPackage.calculationInProgress initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.CoordinatorPackage.calculationInProgressHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.CoordinatorPackage.calculationInProgressHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.CoordinatorPackage.calculationInProgressHelper.type ();
  }

}
