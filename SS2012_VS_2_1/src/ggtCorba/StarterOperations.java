package ggtCorba;


/**
* ggtCorba/StarterOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Donnerstag, 10. Mai 2012 16:13 Uhr MESZ
*/

public interface StarterOperations 
{
  String getName ();
  void createProcesses (int count);
  void killProcesses ();
  void shutdown ();
} // interface StarterOperations
