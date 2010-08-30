package a09;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse, welche eine Addition ermöglicht und von Abstract Class
 *         'Command' erbt.
 * 
 */

public class Addition extends Command {

	public Addition(double value) {
		super(value);
	}

	@Override
	public double execute(double currentValue) {
		return currentValue + value;
	}

	@Override
	public Command getReverse() {
		return new Subtraktion(value);
	}

}
