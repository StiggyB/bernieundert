package a02;

import java.util.Scanner;

/**
 * Einfache Klasse für Ein- und Ausgaben für den Osterrechner
 * 
 * @author Martin Slowikowski
 * 
 */

public class EasterInputOutput {

	// Methode, die ein Jahr zur Berechnung einliest
	public int readYear() {
		Scanner sc = new Scanner(System.in);
		int year;
		System.out.println("Dieser Osterrechner ist nur gültig für Jahre >= 1583");
		System.out.println("Bitte Jahreszahl eingeben im Format JJJJ");
		// while-Schleife die Überprüft, ob die eingegebene Jahreszahl auch
		// vierstellig ist
		// und ob der Wert auch größer/gleich 1583 ist

		while ((year = sc.nextInt()) > 9999 || year < 1583) {
			System.out.println("Bitte vierstellige Jahreszahlen verwenden!");
			System.out.println("Gültigkeitsbereich: 1583 < Jahre < 10000!!!");
		}

		return year;
	}

	// Ausgabemethode, wann Ostern wieder so früh ist, wie "year"
	public void earlyEasterNextOutput(int year, int newYear) {
		System.out.println("Das nächste mal ist Ostern " + newYear
				+ " so früh wie " + year);

	}
	
	// Ausgabemethode, wann Ostern das letzte Mal so früh war, wie "year"
	public void earlyEasterPrevOutput(int year, int newYear) {
		if(newYear < 1583){
			System.out.println("Dieser Rechner ist nur ab 1583 gueltig, das Ergebnis " +
					"für den letzten Ostertermin KOENNTE fehlerhaft sein!");
		}
		
		System.out.println("Das letze Mal war Ostern " + newYear
				+ " so früh wie " + year);
		
	}

	public void easterMenu() {
		Scanner sc2 = new Scanner(System.in);
		char selection = ' ';
		while (selection != 'e') {
			System.out.println("\n\n");
			System.out.println("neues Jahr eingeben: \t n");
			System.out.println("Programm beenden: \t e");
			System.out.println("\nWählen Sie bitte eine Aktion (n oder e)!");
			if (sc2.hasNext()) {
				selection = sc2.next().charAt(0);
			} else {
				selection = 'e';
			}
			if (selection == 'e') {
				System.exit(0);
			} else {
				if (selection == 'n') {
					EasterApp restart = new EasterApp();
					restart.runEasterApp();
				}
			}

		}
	}

}
