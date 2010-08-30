package a08;

public class Ladung implements ILadung {

	private int gewicht;

	public Ladung(int gewicht) {
		super();
		this.gewicht = gewicht;
	}

	@Override
	public int getGewicht() {

		return this.gewicht;
	}

}
