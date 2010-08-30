package a08;

import java.util.ArrayList;
import java.util.List;

public class Wagon implements IWagon {

	private String typ;
	private List<ILadung> ladungen = new ArrayList<ILadung>();
	private int tara;
	private int brutto;

	public Wagon(String typ, int tara, int brutto) {
		this.typ = typ;
		this.tara = tara;
		this.brutto = brutto;
	}

	@Override
	public void add(ILadung ladung) throws WagonOverloadException {

		this.ladungen.add(ladung);
		if (gewichtGesamt() > this.brutto) {
			throw new WagonOverloadException();
		}
	}

	@Override
	public int gewichtGesamt() {

		return gewichtLadung() + this.tara;
	}

	@Override
	public int gewichtLadung() {
		int gewichtLadungen = 0;
		for (ILadung ladung : this.ladungen) {
			gewichtLadungen += ladung.getGewicht();
		}
		return gewichtLadungen;
	}

	// Diese Methode existiert nur, damit typ keine Warning wirft,
	// da es von keiner Methode aus dem Interface genutzt wird.
	public String getTyp() {
		return typ;
	}

}
