package monitor;


/**
* monitor/MonitorHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 19. April 2012 20:21 Uhr MESZ
*/

abstract public class MonitorHelper
{
  private static String  _id = "IDL:monitor/Monitor:1.0";

  public static void insert (org.omg.CORBA.Any a, monitor.Monitor that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static monitor.Monitor extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (monitor.MonitorHelper.id (), "Monitor");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static monitor.Monitor read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_MonitorStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, monitor.Monitor value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static monitor.Monitor narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof monitor.Monitor)
      return (monitor.Monitor)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      monitor._MonitorStub stub = new monitor._MonitorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static monitor.Monitor unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof monitor.Monitor)
      return (monitor.Monitor)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      monitor._MonitorStub stub = new monitor._MonitorStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
