package a05;

/**
 * Einfache Buchklasse, die wichtige Daten eines Buches speichert.
 * Unter anderem ein Autor-Array, zum, speichern der Autoren.
 * 
 * @author Martin Slowikowski
 * 
 */

public class Buch {

	private String titel;
	private Autor[] autor;
	private String isbn;
	private String verlag;
	private int auflage;
	private int seitenzahl;
	private int erscheinungsjahr;

	// anzahlAutoren wird als Hilfe benutzt, um bereits ein Array in der
	// gew�nschten Gr��e erstellen zu k�nnen
	private int anzahlAutoren;

	public Buch(String titel, int anzahlAutoren, String isbn, String verlag,
			int auflage, int seitenzahl, int erscheinungsjahr) {
		this.anzahlAutoren = anzahlAutoren;
		this.titel = titel;

		// Array mit der �bergebenen Anzahl an Autoren initialisieren, wird mit
		// null belegt, sp�ter ge�ndert im Programmablauf
		this.autor = new Autor[this.anzahlAutoren];
		this.isbn = isbn;
		this.verlag = verlag;
		this.auflage = auflage;
		this.seitenzahl = seitenzahl;
		this.erscheinungsjahr = erscheinungsjahr;

	}

	public String getTitel() {
		return titel;
	}

	public Autor[] getAutor() {
		return autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getVerlag() {
		return verlag;
	}

	public int getAuflage() {
		return auflage;
	}

	public int getSeitenzahl() {
		return seitenzahl;
	}

	public int getErscheinungsjahr() {
		return erscheinungsjahr;
	}

	public int getAnzahlAutoren() {
		return anzahlAutoren;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public void setAutor(Autor[] autor) {
		this.autor = autor;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setVerlag(String verlag) {
		this.verlag = verlag;
	}

	public void setAuflage(int auflage) {
		this.auflage = auflage;
	}

	public void setSeitenzahl(int seitenzahl) {
		this.seitenzahl = seitenzahl;
	}

	public void setErscheinungsjahr(int erscheinungsjahr) {
		this.erscheinungsjahr = erscheinungsjahr;
	}
}
