package ggt;


/**
* ggt/CoordinatorOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 25. April 2012 10:34 Uhr MESZ
*/

public interface CoordinatorOperations 
{
  ggt.Starter[] getStarters ();
  void start (int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, monitor.Monitor mntr);
  void shutdown ();
  void registerStarter (ggt.Starter starter) throws ggt.CoordinatorPackage.starterAlreadyExists;
  void registerProcess (ggt.ggtProcess process, String starterName, int id);
} // interface CoordinatorOperations
