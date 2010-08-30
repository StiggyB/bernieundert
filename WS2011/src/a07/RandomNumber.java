package a07;

/**
 * Eine Klasse, die eine vierstellige Zufallszahl
 * generiert. Keine Zahlen doppelt.
 * 
 * 
 * @author Bernie und Ert
 *
 */

import java.util.Arrays;
import java.util.Random;

public class RandomNumber {

	private Random random = new Random();

	/**
	 * Parsed die Zahl in das richtige Format returned diese als Array.
	 * 
	 * @return ein Array mit der Zufallszahl im Array Format.
	 */
	public int[] getNumber() {
		int[] number = new int[4];

		number[0] = getRandomDigit(new int[] {});
		number[1] = getRandomDigit(new int[] { number[0] });
		number[2] = getRandomDigit(new int[] { number[0], number[1] });
		number[3] = getRandomDigit(new int[] { number[0], number[1], number[2] });

		return number;
	}

	/**
	 * 
	 * @param forbiddenNumbers
	 * @return eine Zufallszahl
	 */
	private int getRandomDigit(int[] forbiddenNumbers) {
		Arrays.sort(forbiddenNumbers);
		int i;
		do {
			i = random.nextInt(10);
		} while (Arrays.binarySearch(forbiddenNumbers, i) >= 0);
		return i;
	}

}