package ggtCorba;


/**
* ggtCorba/ggtProcessOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggt.idl
* Samstag, 28. April 2012 16:31 Uhr MESZ
*/

public interface ggtProcessOperations 
{

  // Name/ID des Prozess im Konstruktor
  void initProcess (ggtCorba.ggtProcess left, ggtCorba.ggtProcess right, int startValue, int delay, int timeout, monitor.Monitor mntr);
  void start ();
  void calc (int y);
  boolean terminate (String processName);
  String getName ();
  void kill ();
} // interface ggtProcessOperations
