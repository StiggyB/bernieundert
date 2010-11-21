package a08;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Hilfsklasse für die Auswahl eines neuen Dirs zum Listen.
 * 
 */

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class ExplorerIO {

	public File loadDir() throws IOException {

		JFileChooser fc = new JFileChooser();

		fc.setDialogTitle("Verzeichnis wählen...");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

		}
		return fc.getSelectedFile();

	}

}