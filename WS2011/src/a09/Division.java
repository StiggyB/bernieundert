package a09;
/**
 * 
 * @author Bernie und Ert
 *	
 * Klasse, welche eine Division ermöglicht 
 * und von Abstract Class 'Command' erbt.
 *
 */
public class Division extends Command {
	public Division(double value) {
		super(value);
	}

	@Override
	public double execute(double currentValue) {
		return currentValue / value;
	}

	@Override
	public Command getReverse() {
		return new Multiplikation(value);
	}
}
