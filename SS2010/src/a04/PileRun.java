package a04;

public class PileRun {

	public static void main(String[] args) {

		PileIO output = new PileIO();

		// Endlos-loop
		for (;;) {

			System.out.println("\nPile1:");

			// Neues Objekt der Klasse Pile1, direkt mit einlesen
			// und übergeben der Potenz
			Pile1 pile1 = new Pile1(output.readPower());

			// Methode inorder, gewünschtes j wird bei Aufruf
			// eingelesen
			pile1.inorder(output.readInorder());

			System.out.println("\nPile2:");
			Pile2 pile2 = new Pile2(output.readPower());
			pile2.inorder(output.readInorder());

			// Wenn in der readExit Methode 'e' eingetippt wird,
			// wird das programm beendet, sonst weiter geloopt
			if (output.readExit() == 'e') {
				System.exit(0);
			}

		}
	}

}
