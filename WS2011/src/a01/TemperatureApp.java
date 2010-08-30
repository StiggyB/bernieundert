package a01;
/**
 * Klasse zum Start der Temperatur-Anwendung
 * @author Martin Slowikowski
 *
 */

public class TemperatureApp {

	public static void main(String[] args) {
		// Neues Obejtk erstellen vom Typ TemperaturePrint mit Namen tempView
		TemperaturePrint tempView = new TemperaturePrint();
		
		// Aufruf der Methode run(); der Klasse TemperaturePrint
		tempView.run();

	}

}
