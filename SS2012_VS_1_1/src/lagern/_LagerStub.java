package lagern;


/**
* lagern/_LagerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public class _LagerStub extends org.omg.CORBA.portable.ObjectImpl implements lagern.Lager
{


  //Parameter user zur Kennzeichnung, wer die Aktion aufruft. Dient zur Protokollierung im Monitor.
  public lagern.Fach neu (String user, String name) throws lagern.LagerPackage.exAlreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("neu", true);
                $out.write_string (user);
                $out.write_string (name);
                $in = _invoke ($out);
                lagern.Fach $result = lagern.FachHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:lagern/Lager/exAlreadyExists:1.0"))
                    throw lagern.LagerPackage.exAlreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return neu (user, name        );
            } finally {
                _releaseReply ($in);
            }
  } // neu

  public lagern.Fach hole (String user, String name) throws lagern.LagerPackage.exNotFound
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("hole", true);
                $out.write_string (user);
                $out.write_string (name);
                $in = _invoke ($out);
                lagern.Fach $result = lagern.FachHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:lagern/Lager/exNotFound:1.0"))
                    throw lagern.LagerPackage.exNotFoundHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return hole (user, name        );
            } finally {
                _releaseReply ($in);
            }
  } // hole

  public lagern.Fach[] holeLagerListe ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("holeLagerListe", true);
                $in = _invoke ($out);
                lagern.Fach $result[] = lagern.FachListeHelper.read ($in);
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

  public void aktiviereMonitor (lagern.Monitor theMonitor)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("aktiviereMonitor", true);
                lagern.MonitorHelper.write ($out, theMonitor);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                aktiviereMonitor (theMonitor        );
            } finally {
                _releaseReply ($in);
            }
  } // aktiviereMonitor

  public void entferneMonitor (lagern.Monitor theMonitor)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("entferneMonitor", true);
                lagern.MonitorHelper.write ($out, theMonitor);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                entferneMonitor (theMonitor        );
            } finally {
                _releaseReply ($in);
            }
  } // entferneMonitor


  //Dient zum Beenden der Lageranwendung. Sorgt dafuer, dass das Lager und alle registrierten Monitore beendet werden.
  public void quit ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("quit", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                quit (        );
            } finally {
                _releaseReply ($in);
            }
  } // quit

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
