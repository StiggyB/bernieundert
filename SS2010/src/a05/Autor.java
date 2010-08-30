package a05;

/**
 * Simple Klasse, die einen Buchautor repräsentiert. Gespeichert werden Vorname,
 * Nachname, Titel und ein Array an geschriebenen Buechern.
 * 
 * @author Martin Slowikowski
 * 
 */

public class Autor {

	private String autorVorname;
	private String autorNachname;
	private String autorTitel;
	private Buch[] geschriebeneBuecher;

	public Autor(String autorVorName, String autorNachName, String autorTitel) {
		this.autorVorname = autorVorName;
		this.autorNachname = autorNachName;
		this.autorTitel = autorTitel;
		// Testweise mit der Länge 5 initialisiert, da wir noch keine
		// dynamischen Arrays hatten
		this.geschriebeneBuecher = new Buch[5];
	}

	public String getAutorVorName() {
		return autorVorname;
	}

	public String getAutorNachName() {
		return autorNachname;
	}

	public String getAutorTitel() {
		return autorTitel;
	}

	public Buch[] getGeschriebeneBuecher() {
		return geschriebeneBuecher;
	}

	public void setAutorNachName(String autorNachName) {
		this.autorNachname = autorNachName;
	}

	public void setAutorVorName(String autorVorName) {
		this.autorVorname = autorVorName;
	}

	public void setAutorTitel(String autorTitel) {
		this.autorTitel = autorTitel;
	}

	public void setGeschriebeneBuecher(Buch neuesBuch) {
		// Buch an einer freien Arraystelle ablegen, dann Schleife beenden
		for (Buch i : this.geschriebeneBuecher) {
			if (i == null) {
				i = neuesBuch;
				break;

			}

		}
	}
}
