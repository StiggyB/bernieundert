package a09;
/**
 * 
 * @author Bernie und Ert
 *	
 * Abstrakte Klasse, die an vier Grundrechenarten weitervererbt, 
 * welche diese Methoden ausimplementieren müssen.
 * 
 * Die toString Methode wurde bereits überschrieben für alle
 * erbenden Klassen.  
 * 
 * 
 */

public abstract class Command {
	protected double value;

	public Command(double value) {
		this.value = value;
	}

	public abstract double execute(double currentValue);

	public abstract Command getReverse();

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + value + "]";
	}
}
