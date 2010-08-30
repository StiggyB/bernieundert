package a08;

public class Lokomotive implements ILokomotive {

	private String typ;
	private int lokLeistung;
	private int lokGewicht;

	public Lokomotive(String typ, int lokGewicht, int lokLeistung) {
		this.typ = typ;
		this.lokLeistung = lokLeistung;
		this.lokGewicht = lokGewicht;
	}

	@Override
	public int getPower() {
		return this.lokLeistung;
	}

	@Override
	public int getWeight() {
		return this.lokGewicht;
	}

	
	//Typ nicht über Interface erreichbar, daher eigene Methode
	public String getTyp() {
		return this.typ;
	}
	
	
}
