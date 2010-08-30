package a04;

import java.util.Scanner;

public class PileIO {

	Scanner sc;

	// Methode zum einlesen der gewünschten Potenz
	public int readPower() {
		this.sc = new Scanner(System.in);
		System.out.println("Bitte die Potenz zur Basis 2 angeben (Arraylänge):");
		return sc.nextInt();

	}

	// Methode zum einlesen des gewünschten j 
	public int readInorder() {
		this.sc = new Scanner(System.in);
		System.out.println("Bitte das gewuenschte j angeben:");
		return sc.nextInt();

	}

	// Methode, die einen chat einliest (für programmende)
	public char readExit() {
		this.sc = new Scanner(System.in);
		System.out.println("\nProgramm beenden: e druecken"
				+ ", weitermachen <any key>");
		return sc.next().charAt(0);
	}

}
