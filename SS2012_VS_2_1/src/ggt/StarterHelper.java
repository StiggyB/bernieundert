package ggt;


/**
* ggt/StarterHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 25. April 2012 14:07 Uhr MESZ
*/

abstract public class StarterHelper
{
  private static String  _id = "IDL:ggt/Starter:1.0";

  public static void insert (org.omg.CORBA.Any a, ggt.Starter that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ggt.Starter extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ggt.StarterHelper.id (), "Starter");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ggt.Starter read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_StarterStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ggt.Starter value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ggt.Starter narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ggt.Starter)
      return (ggt.Starter)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ggt._StarterStub stub = new ggt._StarterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ggt.Starter unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ggt.Starter)
      return (ggt.Starter)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ggt._StarterStub stub = new ggt._StarterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
