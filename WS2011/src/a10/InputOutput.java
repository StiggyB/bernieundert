package a10;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InputOutput {

	public final long one_kb = 1024;
	public final long one_mb = one_kb * one_kb;
	public final long one_gb = one_kb * one_mb;

	Scanner sc = new Scanner(System.in);

	public String byteCountToDisplaySize(long size) {
		String displaySize;

		if (size / one_gb > 0) {
			displaySize = String.valueOf(size / one_gb) + " GB";
		} else if (size / one_mb > 0) {
			displaySize = String.valueOf(size / one_mb) + " MB";
		} else if (size / one_kb > 0) {
			displaySize = String.valueOf(size / one_kb) + " KB";
		} else {
			displaySize = String.valueOf(size) + " MB";
		}
		return displaySize;
	}

	public String readRelativeDirName() {
		System.out.println(">>>Neues Verzeichnis anlegen<<<");
		System.out.println("\nLegt ein Verzeichnis im derzeitigen Arbeitsverzeichnis an!");
		System.out.println("Bitte gewuenschten Verzeichnisnamen eingeben, ohne Leerzeichen!");
		return sc.next();
	}

	public String readFileName() {
		System.out.println(">>>Neue Datei anlegen<<<");
		System.out.println("Bitte gewuenschten Dateinamen angeben:");
		return sc.next();
	}

	public String readAbsoluteDirName() {
		System.out.println(">>>Verzeichnisinhalt ausgeben<<<");
		System.out.println("Bitte Verzeichnispfad angeben:");
		System.out.println("z.B. C:\\tolles\\dir");
		return sc.next();
	}

	public void printWriteAccessError() {
		System.err.println("Keine Schreibrechte!");
	}

	public void printAlreadyExistError() {
		System.err.println("Verzeichnis/Datei existiert bereits!");
	}

	public void printDirError() {
		System.err
				.println("Verzeichnis existiert nicht oder ist kein Verzeichnis!");
	}

	public void printRoots(File[] roots) {
		System.out.println("Laufwerk   Total           Frei      Belegt");
		System.out.println("-------------------------------------------");

		for (File e : roots)
			System.out.printf("%s   %10s     %10s  %10s%n", e,
					byteCountToDisplaySize(e.getTotalSpace()),
					byteCountToDisplaySize(e.getFreeSpace()),
					byteCountToDisplaySize((e.getTotalSpace() - e.getFreeSpace())));
	}

	// Alte Methode, alternativ zur obigen ohne Anwendung der static-Methode
	// public void printRoots(File[] roots) {
	// System.out.println("Laufwerk   Total           Frei      Belegt");
	// System.out.println("-------------------------------------------");
	//
	// for (File e : roots)
	// System.out.printf("%s   %7d MB     %7d MB  %7d MB%n", e, e
	// .getTotalSpace()
	// / (1024 * 1024), e.getFreeSpace() / (1024 * 1024), (e
	// .getTotalSpace() - e.getFreeSpace())
	// / (1024 * 1024));
	// }

	public void printDirContent(File[] dirlist) {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		if (dirlist == null) {
			printDirError();
		} else {

			System.out.printf("%24s %6s \t%9s \t%s", "Name", "Typ", "Groeße",
					"Letzte Aenderung");
			System.out.println("");
			System.out
					.println("========================================================================");

			for (File file : dirlist) {
				if (file.isDirectory()) {
					System.out.printf("%24s  <dir>", file.getName());
					System.out.println("");
				}
			}

			for (File file : dirlist) {
				if (file.isFile()) {

					System.out.printf("%24s <file>\t\t %8s", 
							file.getName(),
							byteCountToDisplaySize(file.length()), 
							sdf.format(file.lastModified()));
					System.out.println("    " + sdf.format(file.lastModified()));

				}

			}
		}

	}

}
