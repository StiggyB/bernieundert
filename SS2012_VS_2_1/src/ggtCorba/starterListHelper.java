package ggtCorba;


/**
* ggtCorba/starterListHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 14:46 Uhr MESZ
*/

abstract public class starterListHelper
{
  private static String  _id = "IDL:ggtCorba/starterList:1.0";

  public static void insert (org.omg.CORBA.Any a, ggtCorba.Starter[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ggtCorba.Starter[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = ggtCorba.StarterHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (ggtCorba.starterListHelper.id (), "starterList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ggtCorba.Starter[] read (org.omg.CORBA.portable.InputStream istream)
  {
    ggtCorba.Starter value[] = null;
    int _len0 = istream.read_long ();
    value = new ggtCorba.Starter[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = ggtCorba.StarterHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ggtCorba.Starter[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      ggtCorba.StarterHelper.write (ostream, value[_i0]);
  }

}
