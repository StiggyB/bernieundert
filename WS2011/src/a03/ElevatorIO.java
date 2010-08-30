package a03;

/**
 * Klasse, die Methoden zur Ein- und Ausgabe beinhaltet. 
 * 
 * @author Martin Slowikowski
 * 
 */

import java.util.Scanner;

public class ElevatorIO {

	// Methode zum Einlesen, wieviele Stockwerke es gibt
	public int readFloor() {
		System.out.println("Wieviele Stockwerke soll es geben?");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	// Aus- und Eingaben für das Fahrstuhlprogramm
	public char readCommand() {
		System.out.println("Welche Aktion wird gewünscht?");
		System.out.println("\nEinsteigen und Stockwerk wählen:\t\te");
		System.out.println("Fahrstuhl in ein bestimmtes Stockwerk rufen: \tr");
		System.out.println("Fahrstuhlsimulation beenden: \t\t\tb");
		Scanner sc = new Scanner(System.in);
		char selection = sc.next().charAt(0);
		return selection;
	}

	// Methode zum Einlesen, in welches Stockwerk es gehen soll
	public int readInputFloor() {
		Scanner sc = new Scanner(System.in);

		String next = sc.next();
		if (next.equals("e")) {
			return 0;
		} else {
			return Integer.parseInt(next);
		}
	}

	// Methode die ausgibt, in welchem Stock sich der Fahrstuhl befindet
	public void showCurrentFloor(int actualFloor) {
		if (actualFloor == 0) {
			System.out.println("Fahrstuhl ist im Erdgeschoss (0)");
		} else {
			System.out.println("Fahrstuhl ist im " + actualFloor + ". Stock\n");
		}
	}

	// Methode die auflistet, welche Stockwerke gewählt werden können
	public void showAvailableFloors(int floors) {
		System.out.println("In welches Stockwerk soll's gehen?");
		System.out.print("E ");
		for (int i = 1; i <= floors; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	// Methode zur Fehlerausgabe bei eine falschen Eingabe
	public void showIllegalSelection() {
		System.out.println("Bitte eine gültige option wählen");
	}

	// Methode zum Rufen des Fahrstuhls
	public void callElevator(int destination, int actualFloor) {
		System.out.println("\nDer Fahrstuhl kommt nun zu ihnen!");
		System.out.println("Fahrstuhl ist im " + actualFloor + ". Stock");
		System.out.println("Sie rufen vom " + destination + ".Stock\n");
	}

}
