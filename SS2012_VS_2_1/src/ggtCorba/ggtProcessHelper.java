package ggtCorba;


/**
* ggtCorba/ggtProcessHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 10. Mai 2012 16:13 Uhr MESZ
*/

abstract public class ggtProcessHelper
{
  private static String  _id = "IDL:ggtCorba/ggtProcess:1.0";

  public static void insert (org.omg.CORBA.Any a, ggtCorba.ggtProcess that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ggtCorba.ggtProcess extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ggtCorba.ggtProcessHelper.id (), "ggtProcess");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ggtCorba.ggtProcess read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ggtProcessStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ggtCorba.ggtProcess value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ggtCorba.ggtProcess narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ggtCorba.ggtProcess)
      return (ggtCorba.ggtProcess)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ggtCorba._ggtProcessStub stub = new ggtCorba._ggtProcessStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ggtCorba.ggtProcess unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ggtCorba.ggtProcess)
      return (ggtCorba.ggtProcess)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ggtCorba._ggtProcessStub stub = new ggtCorba._ggtProcessStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
