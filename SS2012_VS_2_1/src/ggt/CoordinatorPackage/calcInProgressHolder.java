package ggt.CoordinatorPackage;

/**
* ggt/CoordinatorPackage/calcInProgressHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 11:26 Uhr MESZ
*/

public final class calcInProgressHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.CoordinatorPackage.calcInProgress value = null;

  public calcInProgressHolder ()
  {
  }

  public calcInProgressHolder (ggt.CoordinatorPackage.calcInProgress initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.CoordinatorPackage.calcInProgressHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.CoordinatorPackage.calcInProgressHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.CoordinatorPackage.calcInProgressHelper.type ();
  }

}