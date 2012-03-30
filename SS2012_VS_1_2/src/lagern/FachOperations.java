package lagern;


/**
* lagern/FachOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public interface FachOperations 
{
  int anzahl ();

  //Anzahl gelagerte Teile
  String name ();

  //Parameter user zur Kennzeichnung, wer die Aktion aufruft. Dient zur Protokollierung im Monitor.
  void einlagern (String user, int anzahl) throws lagern.FachPackage.exInvalidCount;
  void auslagern (String user, int anzahl) throws lagern.FachPackage.exInvalidCount, lagern.FachPackage.exNotEnoughPieces;
} // interface FachOperations
