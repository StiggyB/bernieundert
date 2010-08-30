package a01;

//
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Klasse zur Ausgabe umgerechneter Temeratureinheiten.
 * 
 * @author Martin Slowikowski
 * 
 * 
 */

public class TemperaturePrint {

	// Attribut des Typs Scanner für Texteingabe
	private Scanner sc;

	// Attribut des Typs Temperature für unsere Temperatur
	private Temperature temperature;

	// Attribut des Typs DecimalFormat für die Formatierung der Werte in der
	// Tabelle
	private DecimalFormat df;

	// Attribut des Typs double für unseren Celsius Endwert
	private double endwert;

	// Attribut des Typs Integer für die Schrittweite in der Tabelle
	private int schrittweite;

	// Konstruktor der Klasse TemperaturePrint
	public TemperaturePrint() {
		this.sc = new Scanner(System.in);
		this.temperature = new Temperature();
		this.df = new DecimalFormat("0.00");
		}

	// Mehtode zum Erstellen der Tabellenausgabe
	public void run() {
		System.out.println("Bitte den Celsius-Startwert eingeben:");
		// Temperatur Startwert als Typ double von der Console einlesen
		this.temperature = new Temperature(sc.nextDouble());

		System.out.println("Bitte den Celsius-Endwert eingeben:");
		// Temperatur Endwert als Typ double von der Console einlesen
		// und mittels while auf Gültigkeit prüfen
		while ((this.endwert = sc.nextDouble()) < temperature.showTemp()) {
			System.out.println("Endwert bitte => Anfangswert");
			System.out.println("Bitte den Celsius Endwert eingeben:");
		}

		System.out.println("Bitte die Schrittweite fuer die Tabelle eingeben:");
		// Schrittweite für die for-schleife (Tabellenerstellung) als Integer
		this.schrittweite = sc.nextInt();

		// Tabellenkopf
		System.out.println("°Celsius\t°Kelvin\t\t°Fahrenheit\t°Reaumur");
		System.out
				.println("========================================================");

		// for-schleife mit eingelesenen Werten von der Tastatur
		for (double i = temperature.showTemp(); i < endwert + 1; i += schrittweite) {

			Temperature newTemp = new Temperature(i);
			
			System.out.print(df.format(newTemp.showTemp()) + " ");

			// Prüfung, ob die Temp < als 0° K sind
			if (newTemp.getKelvin() < 0) {
				System.out.print("\t\t°K < 0!");
			} else {
				System.out.print("\t\t" + df.format(newTemp.getKelvin()) + " ");
			}

			System.out.print("\t\t" + df.format(newTemp.getFahrenheit())
					+ " ");

			System.out.println("\t\t" + df.format(newTemp.getReaumur()));

		}

	}

}
