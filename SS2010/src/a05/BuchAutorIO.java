package a05;

/**
 * Simple Klasse, um Buecher und Autoren anzulegen, weiterhin
 * kann man fertige Buecher ausgeben.
 * 
 * @author Martin Slowikowski
 *
 */

import java.util.Scanner;

public class BuchAutorIO {

	private Scanner sc = new Scanner(System.in);
	private Autor[] autor;

	// ein neues Objekt vom Typ Buch erstellen
	public Buch buchErstellen() {
		System.out.println("Buchtitel eingeben: ");
		String titel = sc.nextLine();
		System.out.println("Wieviele Autoren hat das Buch (in Ganzzahlwerten)?: ");
		int anzahlAutoren = sc.nextInt();
		sc.nextLine();
		System.out.println("Bitte ISBN eingeben: ");
		String isbn = sc.nextLine();
		System.out.println("Bitte Verlag eingeben: ");
		String verlag = sc.nextLine();
		System.out.println("Bitte Auflage eingeben (in Ganzzahlwerten): ");
		int auflage = sc.nextInt();
		System.out.println("Bitte Seitenanzahl eingeben (in Ganzzahlwerten): ");
		int seitenzahl = sc.nextInt();
		System.out.println("Bitte Erscheinungsjahr eingeben (in der Form yyyy): ");
		int erscheinungsjahr = sc.nextInt();

		Buch neuesBuch = new Buch(titel, anzahlAutoren, isbn, verlag, auflage,
				seitenzahl, erscheinungsjahr);

		return neuesBuch;
	}

	// für das neu erstellte Buch Autoren erstellen
	public Autor[] autorErstellen(Buch neuesBuch) {
		if (neuesBuch.getAnzahlAutoren() == 0) {
			this.autor = new Autor[1];
			this.autor[0] = new Autor("Autor", "vorhanden", "kein");

		} else {
			this.autor = new Autor[neuesBuch.getAnzahlAutoren()];
			for (int i = 0; i < neuesBuch.getAnzahlAutoren(); i++) {
				System.out.println("Vorname des Autors: ");
				String vorname = sc.next();
				System.out.println("Nachname des Autors: ");
				String nachname = sc.next();
				System.out.println("Titel des Autors: ");
				String titel = sc.next();
				this.autor[i] = new Autor(vorname, nachname, titel);

			}
		}
		return autor;
	}

	// Das erstellte Buch samt den neu hinzugefügten Autoren ausgeben
	public void buchAusgeben(Buch neuesbuch) {
		System.out.println("Titel: " + neuesbuch.getTitel());
		for (Autor i : autor) {
			System.out.println("Autor: " + i.getAutorTitel() + " "
					+ i.getAutorVorName() + " "
					+ i.getAutorNachName());
		}
		System.out.println("ISBN: " + neuesbuch.getIsbn());
		System.out.println("Verlag: " + neuesbuch.getVerlag());
		System.out.println("Auflage: " + neuesbuch.getAuflage() + ".");
		System.out.println("Erscheinungsjahr: "
				+ neuesbuch.getErscheinungsjahr());
		System.out.println("Seitenzahl: " + neuesbuch.getSeitenzahl());
	}

}
