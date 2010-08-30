package a09;
/**
 * 
 * @author Bernie und Ert
 *	
 * Klasse, welche eine Subtraktion ermöglicht 
 * und von Abstract Class 'Command' erbt.
 *
 */
public class Subtraktion extends Command {
	public Subtraktion(double value) {
		super(value);
	}

	@Override
	public double execute(double currentValue) {
		return currentValue - value;
	}

	@Override
	public Command getReverse() {
		return new Addition(value);
	}
}
