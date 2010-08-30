package a07;


/**
 * Diese Klasse startet das MasterMind-Spiel
 * 
 * @author Bernie und Ert
 * 
 */

public class MasterMindApp {

	
	/**
	 * 
	 * Diese Methode wird vom Compiler als erstes aufgerufen.
	 * @param Übergabeparameter zum starten der MainMethode.
	 * 
	 */ 
	public static void main(String[] args) {
		
		MasterMindIO mastermindio = new MasterMindIO();
		RandomNumber randomnumber = new RandomNumber();
		CheckNumber checknumber = new CheckNumber();
		
		MasterMind mastermind = new MasterMind();
        mastermind.setMastermindio(mastermindio);
        mastermind.setRandomnumber(randomnumber);
        mastermind.setChecknumber(checknumber);

         
        mastermind.start();

	}






}
