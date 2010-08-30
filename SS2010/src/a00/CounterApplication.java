package a00;
/**
 * Eine ganz einfache Klasse, die die Counter-Anwendung startet.
 * 
 * @author Bernd Kahlbrandt
 * 
 */
public class CounterApplication {
	/**
	 * @param args
	 *            Keine
	 */
	public static void main(String[] args) {
		CounterConsoleView counterView = new CounterConsoleView();
		counterView.start();
	}
}
