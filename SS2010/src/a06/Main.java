package a06;

/**
 * 
 * @author Martin Slowikowski
 * 
 */

public class Main {
	public static void main(String[] args) {

		Fara fara = new Fara("Fara", "Fu");
		Fu fu = fara.getFu();

		System.out.println(fara.getName()); // Fara
		System.out.println(fu.getName()); // Fu

		System.out.println(fu.getFara().getName()); // Fara
		System.out.println(fara.getFu().getName()); // Fu

	}
}
