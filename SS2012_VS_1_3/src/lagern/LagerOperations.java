package lagern;


/**
* lagern/LagerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Donnerstag, 29. M�rz 2012 20:04 Uhr MESZ
*/

public interface LagerOperations 
{

  //Parameter user zur Kennzeichnung, wer die Aktion aufruft. Dient zur Protokollierung im Monitor.
  lagern.Fach neu (String user, String name) throws lagern.LagerPackage.exAlreadyExists;
  lagern.Fach hole (String user, String name) throws lagern.LagerPackage.exNotFound;
  lagern.Fach[] holeLagerListe ();
  void aktiviereMonitor (lagern.Monitor theMonitor);
  void entferneMonitor (lagern.Monitor theMonitor);

  //Dient zum Beenden der Lageranwendung. Sorgt dafuer, dass das Lager und alle registrierten Monitore beendet werden.
  void quit ();
} // interface LagerOperations
