package a01;

/**
 * Eine Klasse zur Umrechnung von Temperaturen. Es wird ein Wert in verschiedene
 * Temperatureinheiten gewandelt.
 * 
 * @author Martin Slowikowski
 * 
 */

public class Temperature implements ITemperature {

	// Attribut des Typs double für unseren Temperaturwert
	private final double temp;

	// Default Konstruktor, ruft den parametrisierten Konstruktor auf und
	// initialisiert mit 0
	public Temperature() {
		this(0);
	}

	// parametrisierter Konstruktor
	public Temperature(double temp) {
		this.temp = temp;
	}

	// Methode zur Umrechnung von Celsius nach Kelvin
	public double getKelvin() {
		return (this.temp + 273.15);
	}

	// Methode zur Umrechnung von Celsius nach Reaumur
	public double getReaumur() {
		return (this.temp * 0.8);
	}

	// Methode zur Umrechnung von Celsius nach Fahrenheit
	public double getFahrenheit() {
		return (this.temp * 1.8 + 32);
	}

	// Methode, die den ursprünglichen Temperaturwert zurückliefert
	public double showTemp() {
		return this.temp;
	}

}
