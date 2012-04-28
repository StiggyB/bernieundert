package ggt;


/**
* ggt/CoordinatorOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 12:30 Uhr MESZ
*/

public interface CoordinatorOperations 
{
  ggt.Starter[] getStarters ();
  void start (int minProcess, int maxProcess, int minDelay, int maxDelay, int timeout, int ggt, monitor.Monitor mntr);
  boolean shutdown ();
  boolean isCalculating ();
  void registerStarter (ggt.Starter starter) throws ggt.CoordinatorPackage.starterAlreadyExists;
  void registerProcess (ggt.ggtProcess process, String processName);
  void unregisterStarter (ggt.Starter starter) throws ggt.CoordinatorPackage.starterDoesNotExists;
  void processCalcDone (ggt.ggtProcess process);
} // interface CoordinatorOperations
