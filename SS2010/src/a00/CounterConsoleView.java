package a00;

import java.util.Locale;
import java.util.Scanner;
/**
 * Eine einfache Konsol-Anzeige für einen Zähler.
 * 
 * Die Anwendung gibt in einer while-Schleife ein Menü aus und verarbeitet die
 * Eingabe bis die Auswahl 'e' für Ende aufgerufen wird.
 * @author Bernd Kahlbrandt
 *
 */
public class CounterConsoleView {
	private Counter counter;
	private Scanner sc;

	public CounterConsoleView() {
		this.sc = new Scanner(System.in);
		this.counter = new Counter();
	}

	public void start() {
		System.out.println("Starten eines Zählers");
				System.out.println("Geben Sie bitte den Startwert ein:");
				if (sc.hasNextInt()) {
					this.counter = new Counter(sc.nextInt());
				} else {
					this.counter = new Counter();
				}
				run();
			} 

	public void run() {
		char selection=' ';
		while (selection!='e') {			
			System.out.printf(Locale.GERMAN, "%s%,d\n", "Zählerstand: ",
					this.counter.show());
			System.out.println("Zähler erhöhen: \t i");
			System.out.println("Zähler verringern: \t d");
			System.out.println("Zähler zurücksetzen:\t r");
			System.out.println("beenden: \t\t e");
			System.out.println("Wählen Sie bitte ein Aktion!");
			if(this.sc.hasNext()){
				selection = this.sc.next().charAt(0);
				}
				else{
					selection = 'e';
				}

			switch (selection) {
			case 'i':
				this.counter.increment();
				break;
			case 'd':
				this.counter.decrement();
				break;
			case 'r':
				this.counter.reset();
				break;
			case 'e':
				System.exit(0);
			default:
				System.out.println("Wählen Sie bitte ein Aktion: i,d,r oder e!");	
				break;
			}
			
		} 

	}
}
