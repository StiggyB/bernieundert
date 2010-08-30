package a09;
/**
 * 
 * @author Bernie und Ert
 *	
 * Klasse, welche eine Multiplikation erm�glicht 
 * und von Abstract Class 'Command' erbt.
 *
 */
public class Multiplikation extends Command {
	public Multiplikation(double value) {
		super(value);
	}

	@Override
	public double execute(double currentValue) {
		return currentValue * value;
	}

	@Override
	public Command getReverse() {
		return new Division(value);
	}
}
