package lagern;


/**
* lagern/_LagerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* den 25 oktober 2010 kl 15:09 CEST
*/

public class _LagerStub extends org.omg.CORBA.portable.ObjectImpl implements lagern.Lager
{

  public lagern.Lagerfach neu (String besitzer, String name) throws lagern.LagerPackage.alreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("neu", true);
                $out.write_string (besitzer);
                $out.write_string (name);
                $in = _invoke ($out);
                lagern.Lagerfach $result = lagern.LagerfachHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:lagern/Lager/alreadyExists:1.0"))
                    throw lagern.LagerPackage.alreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return neu (besitzer, name        );
            } finally {
                _releaseReply ($in);
            }
  } // neu

  public lagern.Lagerfach hole (String name) throws lagern.LagerPackage.notFound
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("hole", true);
                $out.write_string (name);
                $in = _invoke ($out);
                lagern.Lagerfach $result = lagern.LagerfachHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:lagern/Lager/notFound:1.0"))
                    throw lagern.LagerPackage.notFoundHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return hole (name        );
            } finally {
                _releaseReply ($in);
            }
  } // hole

  public String[] holeLagerListe ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("holeLagerListe", true);
                $in = _invoke ($out);
                String $result[] = lagern.LagerfaecherListeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return holeLagerListe (        );
            } finally {
                _releaseReply ($in);
            }
  } // holeLagerListe

  public void aktiviereMonitor (String user, lagern.Monitor theMonitor)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("aktiviereMonitor", true);
                $out.write_string (user);
                lagern.MonitorHelper.write ($out, theMonitor);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                aktiviereMonitor (user, theMonitor        );
            } finally {
                _releaseReply ($in);
            }
  } // aktiviereMonitor

  public void entferneMonitor (String user, lagern.Monitor theMonitor)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("entferneMonitor", true);
                $out.write_string (user);
                lagern.MonitorHelper.write ($out, theMonitor);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                entferneMonitor (user, theMonitor        );
            } finally {
                _releaseReply ($in);
            }
  } // entferneMonitor

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:lagern/Lager:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _LagerStub
