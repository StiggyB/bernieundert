package lagern;


/**
* lagern/LagerfachOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* den 25 oktober 2010 kl 15:09 CEST
*/

public interface LagerfachOperations 
{
  String besitzer ();
  void besitzer (String newBesitzer);
  int anzahl ();
  void einlagern (String user, int anzahl) throws lagern.LagerfachPackage.invalidCount;
  void auslagern (String user, int anzahl) throws lagern.LagerfachPackage.invalidCount, lagern.LagerfachPackage.notEnoughPieces;
} // interface LagerfachOperations