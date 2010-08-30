package a07;

/**
 * Diese Klasse regelt Ein- und Ausgaben für das
 * MasterMind-Spiel
 * 
 * @author Bernie und Ert
 * 
 */

import java.util.Scanner;

public class MasterMindIO {

	private Scanner sc = new Scanner(System.in);

	
	/**
	 * Methode zur Anzeige der Treffer auf der Konsole.
	 * @param hits Array, welches die in/-direkten Treffer übergibt.
	 */
	public void printHits(int[] hits) {
		System.out.println("Direkte Treffer: " + hits[0]);
		System.out.println("Indirekte Treffer: " + hits[1]);
	}

	
	/**
	 * Ausgabe auf der Konsole bei erraten der richtigen Kombination durch den User.
	 */
	public void printVictory() {
		System.out.println("===============================");
		System.out.println("Richtige Kombination, gewonnen!");
		System.out.println("===============================");
	}

	
	/**
	 * Diese Methode gibt die zu erratende Zahl aus, wenn der User die Zahl nach
	 * 10 Runden nicht gefunden hat.
	 * 
	 * @param randomNumber ein Array mit der gesuchten Nummer
	 */
	public void printLost(int[] randomNumber) {
		System.out.println("===============================");
		System.out.println("Sie haben verloren.... ");
		System.out.print("Die Gesuchte Zahl war: ");
		for (Integer element : randomNumber) {
			System.out.print(element);
		}
		System.out.println("");
		System.out.println("===============================");
	}

	/**
	 * Diese Methode gibt aus, in welcher Runde sich der Benutzer befindet.
	 * 
	 * @param roundCounter ein Integer welcher die Rundenzahl beinhaltet
	 */
	public void printRoundCounter(int roundCounter) {
		if (roundCounter != 10) {
			System.out.println("===============================");
			System.out.println("Sie spielen Runde " + roundCounter);
		} else {
			System.out.println("Sie spielen die LETZTE runde");
		}
		System.out.println("===============================");
	}

	/**
	 * Eine Methode, welche die letzten Tips anzeigt, die der User gemacht hat.
	 * Dient der Uebersicht.
	 * 
	 * @param userTips die bisher geratenen Zahlen.
	 * @param userHits ein String Array fuer die Treffer durch den User.
	 */
	public void printLastUserTipsAndHits(String[] userTips, String[] userHits) {
		System.out.println("Ihre letzten Tipps:");
		for (int i = 0; i < 9; i++) {
			System.out.println(userTips[i] + "(" + userHits[i] + ")");
		}

	}

	/**
	 * Eine Methode, welche die Zahl von der Tastatur einliest.
	 * Player vs. Computer.
	 * 
	 * @param checknumber ein Objekt 
	 * @return ein geparstes Int Array welches den User Tipp beinhaltet.
	 */
	public int[] readUserTip(CheckNumber checknumber) {

		System.out.println("=================================================");
		System.out.println("Bitte geben Sie ihren vierstelligen Tipp ein:");
		System.out.println("Bitte im Format xxxx aus ganzen Zahlen.");
		System.out
				.println("Nur eine führende 0 erlaubt, keine Zahlen doppelt!\n");

		int userInputValue;
		int[] userTip = new int[4];
		String userInputString = null;

		boolean firstRun = true;

		do {
			if (!firstRun) {
				System.out.println("Zahl ungültig");
			}
			userInputString = sc.next();
			userInputValue = Integer.parseInt(userInputString);

			for (int i = 3; i >= 0; i--) {
				userTip[i] = userInputValue % 10;
				userInputValue = userInputValue / 10;
			}
			firstRun = false;
		} while (!checknumber.isValid(userTip) || userInputString.length() != 4);

		return userTip;

	}
	
	/**
	 * Eine Methode, welche die zu erratende Zahl für den Computer eingibt.
	 * Computer vs. Player
	 * 
	 * @param checknumber
	 * @return ein Int Array mit dem User Tip.
	 */
	public int[] readUserTipToGuess(CheckNumber checknumber) {

		System.out.println("=================================================");
		System.out.println("Bitte geben Sie ihre vierstellige Geheimzahl ein:");
		System.out.println("Bitte im Format xxxx aus ganzen Zahlen.");
		System.out
				.println("Nur eine führende 0 erlaubt, keine Zahlen doppelt!\n");

		int userInputValue;
		int[] userTip = new int[4];
		String userInputString = null;

		boolean firstRun = true;

		do {
			if (!firstRun) {
				System.out.println("Zahl ungültig");
			}
			userInputString = sc.next();
			userInputValue = Integer.parseInt(userInputString);

			for (int i = 3; i >= 0; i--) {
				userTip[i] = userInputValue % 10;
				userInputValue = userInputValue / 10;
			}
			firstRun = false;
		} while (!checknumber.isValid(userTip) || userInputString.length() != 4);

		return userTip;

	}

	/**
	 * Diese Methode zeigt ein Menü für das Spiel auf der Konsole.
	 * 
	 * @return einen Integer mit der Auswahl.
	 */
	public int mainmenu() {

		char selection = ' ';
		while (true) {
			System.out
					.println("Willkommen bei Mastermind made by Bernie und Ert!\n\n\n");
			System.out.println("Bitte wählen Sie zwischen folgenden Optionen:");
			System.out.println("1. <only you> vs. our incredible machine");
			System.out.println("2. Deep Thought vs. <you>");
			System.out.println("3. Angst? Programm beenden ...");
			System.out.println("Nun lieber User musst du dich entscheiden"
					+ ", wähle 1,2 oder 3");
			System.out
					.println("===========================================================");

			if (sc.hasNext()) {
				selection = sc.next().charAt(0);
			} else {
				selection = '3';
			}

			switch (selection) {
			case '1':
				return 1;

			case '2':
				return 2;

			case '3':
				return 3;

			default:
				System.out.println("Wählen Sie bitte ein Aktion: 1,2 oder 3!");
				break;
			}

		}

	}

	/**
	 * Diese Methode parsed einzelne Elemente eines Int Arrays als String und gibt Sie zurück.
	 * 
	 * @param userTip
	 * @return einzelne Inhalte des Arrays UserTip als String.
	 */
	public String userTipToString(int[] userTip) {
		return String.valueOf(userTip[0]) + String.valueOf(userTip[1])
				+ String.valueOf(userTip[2]) + String.valueOf(userTip[3]);
	}

	/**
	 * Gibt einen String mit den direkten und indirekten Treffern zurück.
	 * 
	 * @param userHits
	 * @return einen String mit den bisherigen Treffern.
	 */
	public String userHitsToString(int[] userHits) {
		return (" [" + userHits[0] + "] direkte Treffer und [" + userHits[1] + "] indirekte Treffer");
	}

}
