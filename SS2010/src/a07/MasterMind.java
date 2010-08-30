package a07;

import java.util.ArrayList;

/**
 * Diese Klasse enthält die Logik für das Mastermind-Spiel
 * 
 * @author Bernie und Ert
 * 
 */

public class MasterMind {

	private MasterMindIO mastermindio;
	private RandomNumber randomnumber;
	private CheckNumber checknumber;

	
/**
 * Diese Methode wertet die Eingabe im Menue aus und startet
 * die entsprechende Spielvariante
 */
	public void start() {
		while (true) {
			int choice = mastermindio.mainmenu();

			switch (choice) {
			case 1:
				humanVsComputer();
				break;

			case 2:
				computerVsHuman();
				break;

			case 3:
				System.exit(0);

			}
		}

	}

	/**
	 * Diese Methode startet das Spiel fuer Mensch gegen Maschine.
	 */
	private void humanVsComputer() {
		int[] randomNumber = randomnumber.getNumber();
		String[] lastUserTips = new String[10];
		String[] lastUserHits = new String[10];
		int roundCounter = 1;

		for (int i = 0; i < 10; i++) {
			mastermindio.printRoundCounter(roundCounter);
			mastermindio.printLastUserTipsAndHits(lastUserTips, lastUserHits);

			int[] userTip = mastermindio.readUserTip(checknumber);
			int[] hits = compareNumbers(randomNumber, userTip);
			mastermindio.printHits(hits);
			if (hits[0] == 4) {
				mastermindio.printVictory();
				break;
			}

			if (roundCounter == 10) {
				mastermindio.printLost(randomNumber);
			}
			roundCounter++;
			lastUserTips[i] = mastermindio.userTipToString(userTip);
			lastUserHits[i] = mastermindio.userHitsToString(hits);
		}

	}

	/**
	 * Diese Methode starte das Spiel fuer Maschine gegen Mensch.
	 * Die Methode grenzt eine Menge (in Form einer ArrayList) immer weiter
	 * ein, solange, bis die richtige Zahl gefunden worden ist.
	 */
	private void computerVsHuman() {
		int[] toGuess = mastermindio.readUserTipToGuess(checknumber);
		
		int tryCount = 1;
		System.out.println("Versuch: " + tryCount);

		int[] firstTip = randomnumber.getNumber();
		int[] hits = this.compareNumbers(firstTip, toGuess);
		
		for (Integer element : firstTip) {
			System.out.print(element);
		}
		System.out.println("");

		ArrayList<int[]> allPossibilities = new ArrayList<int[]>();
		allPossibilities = this.generatePossibilities();

		ArrayList<int[]> newPossibilities = allPossibilities;
		int[] newTip = firstTip;

		tryCount++;
		for (int i = 2; i <= 10; i++) {
			System.out.println("Versuch: " + tryCount);

			newPossibilities = narrow(newTip, hits, newPossibilities);
			newTip = newPossibilities.get(newPossibilities.size() / 2);
			hits = compareNumbers(newTip, toGuess);

			for (Integer element : newTip) {
				System.out.print(element);
			}
			if (hits[0] == 4) {
				System.out.println("");
				System.out.println("GEKNACKT!!!111eins");
				break;
			}
			System.out.println();
			tryCount++;
		}
	}

	/**
	 * @param firstTip
	 * @param hitsFirstRound
	 * @param allPossibilities
	 * @return gibt eine ArrayList zurück, welche die verbliebenen
	 *         Restmöglichkeiten beinhaltet.
	 */
	private ArrayList<int[]> narrow(int[] firstTip, int[] hitsFirstRound,
			ArrayList<int[]> allPossibilities) {
		ArrayList<int[]> tmpArray = new ArrayList<int[]>();
		for (int[] e : allPossibilities) {
			int[] newHits = this.compareNumbers(firstTip, e);
			if (newHits[0] == hitsFirstRound[0]
					&& newHits[1] == hitsFirstRound[1]) {
				tmpArray.add(e);
			}
		}
		return tmpArray;
	}

	/**
	 * Diese Methoe vergleicht, inwieweit die eingegebene Zahl mit einer
	 * zufällig generierten Zahl uebereinstimmt und gibt die Anzahl der 
	 * Treffer zurück.
	 * 
	 * @param randomNumber
	 * @param userInputNumber
	 * @return
	 */
	private int[] compareNumbers(int[] randomNumber, int[] userInputNumber) {
		int hits[] = new int[4];
		int directHits = 0;
		int indirectHits = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (randomNumber[i] == userInputNumber[j]) {
					if (i == j)
						directHits++;
					else
						indirectHits++;
				}
			}
		}
		hits[0] = directHits;
		hits[1] = indirectHits;

		return hits;
	}

	/**
	 * Diese Methode speichert sämtliche möglichen Kombinationen, 
	 * die es in MasterMind gibt in einer ArrayList.
	 * 
	 * @return eine ArrayList mit allen Möglichkeiten.
	 */
	
	private ArrayList<int[]> generatePossibilities() {

		ArrayList<int[]> possibilities = new ArrayList<int[]>();

		int mirrorFromK = 0;
		for (int k = 123; k <= 9876; k++) {

			// Weise k++ dem mirror zu
			mirrorFromK = k;

			// Schreibe drei zahlen an vier Stellen in das tempArray
			int[] tempArray = new int[4];
			for (int i = 3; i >= 0; i--) {

				tempArray[i] = mirrorFromK % 10;
				mirrorFromK = mirrorFromK / 10;
			}
			if (checknumber.isValid(tempArray)) {
				possibilities.add(tempArray);
			}

		}

		return possibilities;
	}

	/**
	 * Methode zur Initialisierung eines Feldes in der Klasse MasterMind
	 * Ein Objekt der Klasse MasterMindIO wird übergeben. Dient zur Verknüpfung 
	 * der Klassen.
	 * 
	 * @param mastermindio
	 */
	public void setMastermindio(MasterMindIO mastermindio) {
		this.mastermindio = mastermindio;
	}

	/**
	 * Methode zur Initialisierung eines Feldes in der Klasse MasterMind
	 * Ein Objekt der Klasse RandomNumber wird übergeben. Dient zur Verknüpfung
	 * der Klassen.
	 * 
	 * @param randomnumber
	 */

	public void setRandomnumber(RandomNumber randomnumber) {
		this.randomnumber = randomnumber;
	}

	/**
	 * Methode zur Initialisierung eines Feldes in der Klasse MasterMind
	 * Ein Objekt der Klasse CheckNumber wird übergeben. Dient zur Verknüpfung
	 * der Klassen.
	 * 
	 * @param checknumber
	 */

	public void setChecknumber(CheckNumber checknumber) {
		this.checknumber = checknumber;
	}
}
