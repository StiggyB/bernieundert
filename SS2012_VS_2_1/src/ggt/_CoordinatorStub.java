package ggt;


/**
* ggt/_CoordinatorStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 19. April 2012 20:21 Uhr MESZ
*/

public class _CoordinatorStub extends org.omg.CORBA.portable.ObjectImpl implements ggt.Coordinator
{

  public ggt.Starter[] getStarters ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getStarters", true);
                $in = _invoke ($out);
                ggt.Starter $result[] = ggt.starterListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getStarters (        );
            } finally {
                _releaseReply ($in);
            }
  } // getStarters

  public void start (int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("start", true);
                $out.write_long (minProcess);
                $out.write_long (maxProcess);
                $out.write_long (minDelay);
                $out.write_long (maxDelay);
                $out.write_long (timeout);
                $out.write_long (ggt);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                start (minProcess, maxProcess, minDelay, maxDelay, timeout, ggt        );
            } finally {
                _releaseReply ($in);
            }
  } // start

  public void shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  public void registerStarter (ggt.Starter starter) throws ggt.CoordinatorPackage.starterAlreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerStarter", true);
                ggt.StarterHelper.write ($out, starter);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:ggt/Coordinator/starterAlreadyExists:1.0"))
                    throw ggt.CoordinatorPackage.starterAlreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                registerStarter (starter        );
            } finally {
                _releaseReply ($in);
            }
  } // registerStarter

  public void registerProcess (ggt.ggtProcess process, String starterName, int id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerProcess", true);
                ggt.ggtProcessHelper.write ($out, process);
                $out.write_string (starterName);
                $out.write_long (id);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                registerProcess (process, starterName, id        );
            } finally {
                _releaseReply ($in);
            }
  } // registerProcess

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ggt/Coordinator:1.0"};

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
} // class _CoordinatorStub
