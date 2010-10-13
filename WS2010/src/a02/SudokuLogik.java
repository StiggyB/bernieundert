package a02;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse, die unsere Sudokutabellen mit gültigem Inhalt füllt.
 *         Zunächst wird eine Zufallszahl zwischen 20 und 40 erzeugt, die
 *         angibt, wieviele Zufallszahlen im gesamten Feld auftauchen sollen.
 *         Anschließend wird eine zufällige Tabelle und ein darin zufälliges
 *         Feld gewählt. Nun wird wieder eine Zufallszahl generiert und es wird 
 *         geprüft, ob die Zahl akzeptabel ist nach den vorgegebenen Kriterien.
 *         Die Methode isNumberValidIn überprüft zunächst, ob die Zahl in der gleichen
 *         Tabelle bereits vorkommt, dann, ob sie in gleicher Reihe und zuletzt in 
 *         gleicher Spalte vorkommt. Ist die Zahl gültig, wird sie eingetragen, wenn nicht,
 *         wird der Vorgang so lange wiederholt, bis eine gültige Zahl gefunden wurde.
 *         Unsere eigene Methode setRandomValueAt aus SudokuTable merkt sich gleichzeitig,
 *         wo überall automatisch generierte Zahlen stehen, damit diese Felder nicht mehr
 *         editiert werden können später.
 * 
 */

import java.util.Random;

import javax.swing.JTable;

public class SudokuLogik {

	private Random rand = new Random();
	private SudokuTable[][] tables;

	public SudokuLogik(SudokuTable[][] tables) {
		this.tables = tables;
	}

	public void fillTables() {
		int numberCount = rand.nextInt(21) + 20;

		for (int i = 0; i < numberCount; i++) {
			int col = rand.nextInt(3);
			int row = rand.nextInt(3);

			SudokuTable table = tables[row][col];

			int colInTable = rand.nextInt(3);
			int rowInTable = rand.nextInt(3);
			boolean isValid = false;
			do {
				int randomNumber = rand.nextInt(9) + 1;
				isValid = isNumberValidIn(randomNumber, row, col, rowInTable,
						colInTable);
				if (isValid) {
					table
							.setRandomValueAt(randomNumber, rowInTable,
									colInTable);
				}
			} while (!isValid);
		}
	}

	public boolean isNumberValidIn(int number, int row, int col,
			int rowInTable, int colInTable) {
		JTable table = tables[row][col];
		// Zahl im gleichen Feld
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (Integer.valueOf(number).equals(
						table.getModel().getValueAt(i, j))) {
					return false;
				}
			}
		}

		// Zahl in gleicher Row
		for (int c = 0; c < 3; c++) {
			JTable t = tables[row][c];
			for (int i = 0; i < 3; i++) {
				if (Integer.valueOf(number).equals(t.getValueAt(rowInTable, i))) {
					return false;
				}
			}
		}

		// Zahl in gleicher Column
		for (int r = 0; r < 3; r++) {
			JTable t = tables[r][col];
			for (int i = 0; i < 3; i++) {
				if (Integer.valueOf(number).equals(t.getValueAt(i, colInTable))) {
					return false;
				}
			}
		}
		return true;
	}

}
