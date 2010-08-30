package a07;


/**
 * Diese Klasse überprüft, dass es keine zwei gleichen
 * Ziffern in einem Array geben darf.
 * 
 * @author Bernie und Ert
 * 
 */

import java.util.HashSet;
import java.util.Set;


public class CheckNumber {

	/**
	 * @param numberToCheck => Array, mit der zu prüfenden Zahl
	 * @return einen Boolean, welcher angibt, ob die Zahl den Kriterien entspricht, oder nicht.
	 * 
	 * Es darf keine Ziffern doppelt geben.
	 */
	public boolean isValid(int[] numberToCheck) {
		Set<Integer> forbiddenDigits = new HashSet<Integer>();
		forbiddenDigits.add(numberToCheck[0]);
		for (int i = 1; i < 4; i++) {
			if (!forbiddenDigits.add(numberToCheck[i])) {
				return false;
			}
		}
		return true;
	}

}
