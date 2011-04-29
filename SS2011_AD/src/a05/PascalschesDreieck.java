package a05;

public class PascalschesDreieck {

	public static void pascalIterativ(int n) {
		int dreieck[][] = new int[n][];

		for (int i = 0; i < dreieck.length; i++) {
			dreieck[i] = new int[i + 1];

			for (int j = 0; j <= i; j++) {
				if ((j == 0) || (j == i))
					dreieck[i][j] = 1;
				else
					dreieck[i][j] = dreieck[i - 1][j - 1] + dreieck[i - 1][j];

				System.out.printf("%2d ", dreieck[i][j]);
			}
			System.out.println();
		}
	}

	public static int pascalRekursiv(int zeile, int spalte) {
		if (spalte == 0 || spalte == zeile)
			return 1;
		return pascalRekursiv(zeile - 1, spalte) + pascalRekursiv(zeile - 1, spalte - 1);
	}

	public static void main(String[] args) {
		pascalIterativ(8);
	}
}
