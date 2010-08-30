package a05;

/**
 * Startklasse für das Buecherprogramm.
 * 
 * @author Martin Slowikowski
 * 
 */

public class Konsole {

	public static void main(String[] args) {

		BuchAutorIO start = new BuchAutorIO();

		// ein neues Buch erstellen, unter dem Namen neuesBuch speichern
		Buch neuesBuch = start.buchErstellen();

		// Methode autorErstellen aufrufen, für unser neuesBuch
		Autor[] autor = start.autorErstellen(neuesBuch);

		// Autor-Array dem Buch zuweisen
		neuesBuch.setAutor(autor);

		// Den erstellten Autoren das Buch zuweisen
		for (Autor i : autor) {
			i.setGeschriebeneBuecher(neuesBuch);
		}

		// neuesBuch ausgeben
		start.buchAusgeben(neuesBuch);

	}

}
