package lagern;

/**
* lagern/MonitorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* den 25 oktober 2010 kl 15:09 CEST
*/

public final class MonitorHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.Monitor value = null;

  public MonitorHolder ()
  {
  }

  public MonitorHolder (lagern.Monitor initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.MonitorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.MonitorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.MonitorHelper.type ();
  }

}