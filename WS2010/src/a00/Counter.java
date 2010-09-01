package a00;
/**
 * Folgendes Template gilt für unsere Projekte:
 *
 * @author Jan-Tristan Rudat / 2007852 / Team Bernie und Ert
 * @version 31. August 2010 / 01:39
 * 
 * @TODO Hier muss der Zähler ausimplementiert werden.
 * 
 * Es fehlen Kommentare zu Methode XY, da ich heute keine Lust hatte Kommentare
 * zu schreiben.
 * 
 * Machst Du die bitte fertig, Tristan? Ich habe Dir den Arbeitsbereich XY 
 * freigegeben und XY gesperrt. Danke.
 *
 */


public class Counter {
	private int value;							// Ein Kommentar zu einem Attribut
	
	private final int resetValue;
	public Counter(){							// Ein Kommentar zum Konstruktor
		this(0);
	}
	public Counter(int value){					// Ein Kommentar zum Konstruktor
		this.value = value;
		this.resetValue = value;
	}
	
	
	/**
	 * Diese Methode dient dazu...
	 * 
	 * @return den Wert des Counters
	 */
	public int show(){
		return this.value;
	}

	
	/**
	 * Diese Methode dient dazu.. 
	 * .. und benutzt dabei...
	 * 
	 * @param einen Wert für einen Counter
	 */
	
	
	public void increment(){
		this.value++;
	}
	
		
	public void decrement(){
		this.value--;
	}
	public void reset(){
		this.value = this.resetValue;
	}
	
}