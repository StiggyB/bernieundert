package a10;

public class MainApp {

	public static void main(String[] args) {

		FileIO io = new FileIO();

		// Systemroots holen und direkt ausgeben
		io.printRoots(io.getSystemRoots());

		// Dirname einlesen und dir im relativen Pfad erstellen
		String reldirname = io.readRelativeDirName();
		String path = io.createDirRelativePath(reldirname);

		// gewünschten Dateinamen einlesen und leere Datei erzeugen
		String filename = io.readFileName();
		io.createFileWithPath(path, filename);

		// absoluten Pfad einlesen
		String absdirpath = io.readAbsoluteDirName();
		io.printDirContent(io.getDirContent(absdirpath));

	}

}
