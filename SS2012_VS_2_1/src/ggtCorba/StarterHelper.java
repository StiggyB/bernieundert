package ggtCorba;


/**
* ggtCorba/StarterHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 12:42 Uhr MESZ
*/

abstract public class StarterHelper
{
  private static String  _id = "IDL:ggtCorba/Starter:1.0";

  public static void insert (org.omg.CORBA.Any a, ggtCorba.Starter that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ggtCorba.Starter extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ggtCorba.StarterHelper.id (), "Starter");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ggtCorba.Starter read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_StarterStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ggtCorba.Starter value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ggtCorba.Starter narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ggtCorba.Starter)
      return (ggtCorba.Starter)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ggtCorba._StarterStub stub = new ggtCorba._StarterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ggtCorba.Starter unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ggtCorba.Starter)
      return (ggtCorba.Starter)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ggtCorba._StarterStub stub = new ggtCorba._StarterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
