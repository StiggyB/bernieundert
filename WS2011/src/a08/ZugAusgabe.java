package a08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZugAusgabe {

	//Diese Methode gibt Lokdetails und die Details der einzelnen Wagons aus,
	//in der Reihenfolge, wie sie hinzugefuegt wurden.
	public void ausgabeUnsortiert(Zug zug) {
		
		System.out.println("Details zur Lokomotive");
		System.out.println("Loktyp: " + zug.getLokomotive().getTyp());
		System.out.println("Gewicht: " + zug.getLokomotive().getWeight() + " kg");
		System.out.println("Leistung: " + zug.getLokomotive().getPower() + " kw");
		
		System.out.println("\nWagons in der Folge, wie sie hinzugefügt wurden: ");
		
		int wagonzaehler = 1;
		
		for (IWagon e : zug.getWagons()) {
			System.out.print(wagonzaehler + ". Wagon: ");
			System.out.print("Nettogewicht: " + e.gewichtLadung() + " kg"); // netto
			System.out.print(" \tTaragewicht: "
					+ (e.gewichtGesamt() - e.gewichtLadung()) + " kg"); // tara
			System.out.print(" \tBruttogewicht: " + e.gewichtGesamt() + " kg"); // brutto
			System.out.println("");
			wagonzaehler++;
		}

	}

	//Diese Methode gibt Lokdetails und die Details der einzelnen Wagons aus,
	//nach dem Gesamtgewicht (aufsteigend).
	public void ausgabeSortiert(Zug zug) {
		List<IWagon> wagonsZugSortiert = new ArrayList<IWagon>(zug.getWagons());
		Collections.sort(wagonsZugSortiert, new WagonComparator());
		
		System.out.println("Details zur Lokomotive");
		System.out.println("Loktyp: " + zug.getLokomotive().getTyp());
		System.out.println("Gewicht: " + zug.getLokomotive().getWeight() + " kg");
		System.out.println("Leistung: " + zug.getLokomotive().getPower() + " kw");

		System.out.println("\nWagons sortiert nach Gesamtgewicht: ");
		
		int wagonzaehler = 1;
		
		for (IWagon e : wagonsZugSortiert) {
			System.out.print(wagonzaehler + ". Wagon: ");
			System.out.print("Nettogewicht: " + e.gewichtLadung() + " kg"); // netto
			System.out.print(" \tTaragewicht: "
					+ (e.gewichtGesamt() - e.gewichtLadung()) + " kg"); // tara
			System.out.print(" \tBruttogewicht: " + e.gewichtGesamt() + " kg"); // brutto
			System.out.println("");
			wagonzaehler++;
		}
	}

}
