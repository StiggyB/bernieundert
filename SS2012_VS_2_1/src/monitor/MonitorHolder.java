package monitor;

/**
* monitor/MonitorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 19:19 Uhr MESZ
*/

public final class MonitorHolder implements org.omg.CORBA.portable.Streamable
{
  public monitor.Monitor value = null;

  public MonitorHolder ()
  {
  }

  public MonitorHolder (monitor.Monitor initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = monitor.MonitorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    monitor.MonitorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return monitor.MonitorHelper.type ();
  }

}
