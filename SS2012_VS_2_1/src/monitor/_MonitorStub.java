package monitor;


/**
* monitor/_MonitorStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 14:46 Uhr MESZ
*/

public class _MonitorStub extends org.omg.CORBA.portable.ObjectImpl implements monitor.Monitor
{


  /*
       * Bekanntgabe der Ringzusammensetzung. 
       * Ring wird als String-Array uebergeben.
       * In dem Array sind die IDs der beteiligten Prozesse anzugeben.
       * Die Ids sollen sich zusammensetzen aus dem Namen des Starters 
       * und einer vom Starter vergebenen fortlaufenden Nummer.
       */
  public void ring (String[] prozessIds)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("ring", true);
                monitor.ProzessIdsHelper.write ($out, prozessIds);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                ring (prozessIds        );
            } finally {
                _releaseReply ($in);
            }
  } // ring


  /*
       * Bekanntgabe der Startzahlen der beteiligten Prozesse.
       * Zahlen werden in einem Integer-Array uebergeben.
       * Laenge des Startzahlen-Arrays muss mit der Laenge des Prozessids-Arrays uebereinstimmen.
       */
  public void startzahlen (int[] startzahlen)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("startzahlen", true);
                monitor.StartzahlenHelper.write ($out, startzahlen);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                startzahlen (startzahlen        );
            } finally {
                _releaseReply ($in);
            }
  } // startzahlen


  /*
       * Mitteilung an den Monitor, dass von einem Prozess eine neue Zahl empfangen wurde.
       * Mitteilung soll auch gesendet werden, wenn die Zahl vom Prozess nicht bearbeitet wurde, 
       * weil sie zu gross war.
       * Parameter enthalten die Angaben, welcher Prozess die Zahl empfangen hat, von welchem
       * Prozess sie gesendet wurde und welche Zahl uebertragen wurde.
       */
  public void rechnen (String prozessId, String prozessIdAbsender, int num)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("rechnen", true);
                $out.write_string (prozessId);
                $out.write_string (prozessIdAbsender);
                $out.write_long (num);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                rechnen (prozessId, prozessIdAbsender, num        );
            } finally {
                _releaseReply ($in);
            }
  } // rechnen


  /*
       * Mitteilung an den Monitor, dass von einem Prozess eine Terminierungsanfrage empfangen wurde.
       * Parameter enthalten die Angaben, welcher Prozess die Anfrage empfangen hat, von wem sie
       * urspruenglich gesendet wurde und ob terminiert werden soll.
       */
  public void terminieren (String prozessId, String prozessIdAbsender, boolean terminiere)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("terminieren", true);
                $out.write_string (prozessId);
                $out.write_string (prozessIdAbsender);
                $out.write_boolean (terminiere);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                terminieren (prozessId, prozessIdAbsender, terminiere        );
            } finally {
                _releaseReply ($in);
            }
  } // terminieren


  /*
       * Mit diesem Aufruf soll ein terminierender Prozess das Ergebnis seiner Berechnung an den
       * Monitor uebergeben.
       */
  public void ergebnis (String prozessId, int num)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("ergebnis", true);
                $out.write_string (prozessId);
                $out.write_long (num);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                ergebnis (prozessId, num        );
            } finally {
                _releaseReply ($in);
            }
  } // ergebnis

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:monitor/Monitor:1.0"};

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
} // class _MonitorStub
