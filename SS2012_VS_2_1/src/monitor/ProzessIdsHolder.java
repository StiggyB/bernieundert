package monitor;


/**
* monitor/ProzessIdsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 23:06 Uhr MESZ
*/

public final class ProzessIdsHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public ProzessIdsHolder ()
  {
  }

  public ProzessIdsHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = monitor.ProzessIdsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    monitor.ProzessIdsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return monitor.ProzessIdsHelper.type ();
  }

}
