package ggtCorba;


/**
* ggtCorba/ggtProcessOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Mittwoch, 2. Mai 2012 12:42 Uhr MESZ
*/

public interface ggtProcessOperations 
{

  // Name/ID des Prozess im Konstruktor
  void initProcess (ggtCorba.ggtProcess left, ggtCorba.ggtProcess right, int startValue, int delay, int timeout, monitor.Monitor mntr);
  void start ();
  void calc (int y, String msgFrom);
  void terminate (String processName, boolean isAllowed);
  String getName ();
  void kill ();
} // interface ggtProcessOperations
