package ggtCorba.CoordinatorPackage;

/**
* ggtCorba/CoordinatorPackage/noStartersHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 19:19 Uhr MESZ
*/

public final class noStartersHolder implements org.omg.CORBA.portable.Streamable
{
  public ggtCorba.CoordinatorPackage.noStarters value = null;

  public noStartersHolder ()
  {
  }

  public noStartersHolder (ggtCorba.CoordinatorPackage.noStarters initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggtCorba.CoordinatorPackage.noStartersHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggtCorba.CoordinatorPackage.noStartersHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggtCorba.CoordinatorPackage.noStartersHelper.type ();
  }

}
