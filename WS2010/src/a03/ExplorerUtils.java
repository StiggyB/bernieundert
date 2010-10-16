package a03;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Helferklasse: Diese Klasse wird nur benötigt, um eine vernünftige
 *         Formatierung der Dateigrößen beim Listen von Verzeichnissen
 *         zu erlangen.
 *         Code stamm ursprünglilch von: 
 *         http://kickjava.com/src/org/apache/commons/io/FileUtils.java.htm
 *         Wurde lediglich leicht abgewandelt, wurde auch bereits einmal
 *         in Aufgabe a10 im SS2010 PR1 verwendet :)
 * 
 */

public class ExplorerUtils {

	public static final long ONE_KB = 1024;
	public static final long ONE_MB = ONE_KB * ONE_KB;
	public static final long ONE_GB = ONE_KB * ONE_MB;

	public static String byteCountToDisplaySize(long size) {
		String displaySize;

		if (size / ONE_GB > 0) {
			displaySize = String.valueOf(size / ONE_GB) + " GB";
		} else if (size / ONE_MB > 0) {
			displaySize = String.valueOf(size / ONE_MB) + " MB";
		} else if (size / ONE_KB > 0) {
			displaySize = String.valueOf(size / ONE_KB) + " KB";
		} else {
			displaySize = String.valueOf(size) + " MB";
		}
		return displaySize;
	}
}
