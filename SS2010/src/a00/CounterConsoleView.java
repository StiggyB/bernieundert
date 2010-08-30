package a00;

import java.util.Locale;
import java.util.Scanner;
/**
 * Eine einfache Konsol-Anzeige f�r einen Z�hler.
 * 
 * Die Anwendung gibt in einer while-Schleife ein Men� aus und verarbeitet die
 * Eingabe bis die Auswahl 'e' f�r Ende aufgerufen wird.
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
		System.out.println("Starten eines Z�hlers");
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
			System.out.printf(Locale.GERMAN, "%s%,d\n", "Z�hlerstand: ",
					this.counter.show());
			System.out.println("Z�hler erh�hen: \t i");
			System.out.println("Z�hler verringern: \t d");
			System.out.println("Z�hler zur�cksetzen:\t r");
			System.out.println("beenden: \t\t e");
			System.out.println("W�hlen Sie bitte ein Aktion!");
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
				System.out.println("W�hlen Sie bitte ein Aktion: i,d,r oder e!");	
				break;
			}
			
		} 

	}
}
