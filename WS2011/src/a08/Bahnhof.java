package a08;

import java.util.Random;

public class Bahnhof {

	public static void main(String[] args) throws Exception {

		// Random Objekt für die Ladung
		Random random = new Random();

		// Ausgabeobjekt von ZugAusgabe, für die Wagonausgabe
		ZugAusgabe zugAusgeben = new ZugAusgabe();

		// Lokomotiven erstellen nach Details aus eisenbahndaten.txt
		Lokomotive lokbr184 = new Lokomotive("BR184", 60000, 3240);
		Lokomotive lokbr185 = new Lokomotive("BR185", 70000, 4200);
		Lokomotive lokbr189 = new Lokomotive("BR189", 80000, 6400);

		// Zuege mit je einer der drei erstellen Loks konstruieren
		Zug zug1 = new Zug(lokbr184);
		Zug zug2 = new Zug(lokbr185);
		Zug zug3 = new Zug(lokbr189);

		System.out.println("Wagons beladen und Zuegen Wagons zufügen wird gestartet ...");

		// Wagons mit Ladungen befüllen und den Zuegen zuordnen
		try {
			for (;;) {
				IWagon rungenwagenKbs = new Wagon("RungenwagenKbs", 5000, 33000);
				IWagon sgnss600 = new Wagon("Sgnss600", 20000, 90000);
				try {
					rungenwagenKbs.add(new Ladung(random.nextInt(28001)));
					sgnss600.add(new Ladung(random.nextInt(70001)));
					zug1.addWagon(rungenwagenKbs);
					zug2.addWagon(sgnss600);
					zug3.addWagon(rungenwagenKbs);
					zug3.addWagon(sgnss600);
				} catch (WagonOverloadException e) {
					System.out.println("Wagon wird zu schwer, Ladung wird nicht hinzugefügt!\n");
				}
			}

		} catch (OverloadException e) {
			System.out.println("Zug vollständig, keine weiteren Wagons/Ladungen -> fährt los\n");
		}

		// Wagons der Zuege erst unsortiert, dann sortiert ausgeben
		zugAusgeben.ausgabeUnsortiert(zug1);
		zugAusgeben.ausgabeSortiert(zug1);
		zugAusgeben.ausgabeUnsortiert(zug2);
		zugAusgeben.ausgabeSortiert(zug2);
		zugAusgeben.ausgabeUnsortiert(zug3);
		zugAusgeben.ausgabeSortiert(zug3);

	}

}
