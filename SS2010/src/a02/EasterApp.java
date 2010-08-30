package a02;

import java.text.DateFormat;
import java.util.Calendar;

/*
 * Einfache Klasse, zum Start der Osterdatums-suche
 * @author Martin Slowikowski
 */

public class EasterApp {
	
	// Main Methode: Hier wird die Methode runEasterApp aufgerufen,
	// um das Programm zu starten. Nach dem ersten Durchlauf wird
	// das Menue gestartet.
	public static void main(String[] args) {
		// Neues Objekt vom Typ EasterApp
		EasterApp start = new EasterApp();
		// Programmstart
		start.runEasterApp();
		// Neues Objekt vom Typ EasterInputOutput
		EasterInputOutput menu = new EasterInputOutput();
		// Menueaufruf
		menu.easterMenu();
	}

	// Methode, die den Applikationsablauf und die Aurufe zur
	// Berechnung enthält
	public void runEasterApp() {
		// Neues Objekt vom Typ EasterDate für die Berechnungen
		EasterCalc newDate1 = new EasterCalc();
		// Neues Objekt vom Typ EasterInputOutput zur entsprechnenden
		// Ein- und Ausgabe auf der Console
		EasterInputOutput newInput = new EasterInputOutput();

		// Hier wird ein Jahr von der Konsole eingelesen und im Feld
		// year gespeichert
		int year = newInput.readYear();
		
		// Methodenaufruf zur Berechnung des Osterdatums für das
		// eingelesene Jahr, das Rückgabeobjekt vom Typ Calender wird im 
		// lokalen Feld cal vom Typ Calendar gespeichert.
		Calendar cal = newDate1.easterDateCalc(year);
		System.out.println("Ostern fiel im Jahr " + year + " auf das Datum: "
				+ DateFormat.getDateInstance().format(cal.getTime()));

		// Berechnung, wann Ostern wieder so früh ist wie beim eingelesenen Jahr
		int newNextYear = newDate1.earlyEasterNext(cal);
		newInput.earlyEasterNextOutput(year, newNextYear);
		
		// Berechnung, wann Ostern das letzte Mal so früh wie beim 
		// eingelesenen Jahr
		int newLastYear = newDate1.earlyEasterPrev(cal);
		newInput.earlyEasterPrevOutput(year, newLastYear);

	}

}
