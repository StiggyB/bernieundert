package lagern;


/**
* lagern/LagerfachHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* den 25 oktober 2010 kl 15:09 CEST
*/

abstract public class LagerfachHelper
{
  private static String  _id = "IDL:lagern/Lagerfach:1.0";

  public static void insert (org.omg.CORBA.Any a, lagern.Lagerfach that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static lagern.Lagerfach extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (lagern.LagerfachHelper.id (), "Lagerfach");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static lagern.Lagerfach read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_LagerfachStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, lagern.Lagerfach value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static lagern.Lagerfach narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof lagern.Lagerfach)
      return (lagern.Lagerfach)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      lagern._LagerfachStub stub = new lagern._LagerfachStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static lagern.Lagerfach unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof lagern.Lagerfach)
      return (lagern.Lagerfach)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      lagern._LagerfachStub stub = new lagern._LagerfachStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
