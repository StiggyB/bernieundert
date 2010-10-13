package a02;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse die JTable erweitert. Die Methode isCellEditable musste 
 *         überschrieben werden. Mit der eigenen Methode setRandomVaueAt
 *         wird der Wert in der Zelle gesetzt und anschließend im 
 *         Bool-Array das entsprechende Kästchen auf true gesetzt.
 *         Wird nun die Methode isCellEditable gecallt, gibt diese
 *         das Gegenteil des Inhalts aus der entsprechenden Bool-Array-Stelle
 *         aus. Ist ein Feld mit einem random-Wert belegt, erhält es den Wert
 *         true, wird gefragt, ob es editierbar ist, sagt die Methode folglich
 *         !true, also false. Diese Version ist kürzer als ein längerer ternärer
 *         Operator, wäre aber genausomöglich und beim ersten Hinsehen vlt
 *         einfacher zu verstehen. 
 *        
 * 
 */

import java.io.Serializable;

import javax.swing.JTable;

class SudokuTable extends JTable implements Serializable {

	private static final long serialVersionUID = 1L;
	boolean randomSetFields[][] = new boolean[3][3];

	public SudokuTable(int numRows, int numColumns) {
		super(numRows, numColumns);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return !randomSetFields[row][column];
	}

	public void setRandomValueAt(int randomNumber, int row, int column) {
		super.setValueAt(randomNumber, row, column);
		randomSetFields[row][column] = true;
	}

}