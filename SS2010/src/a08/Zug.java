package a08;

import java.util.ArrayList;
import java.util.List;

public class Zug implements IZug {

	private Lokomotive lokomotive;
	private List<IWagon> wagons = new ArrayList<IWagon>();
	private int gewichtWagons;

	public Zug(Lokomotive lokomotive) {
		this.lokomotive = lokomotive;
	}

	@Override
	public void addWagon(IWagon wagon) throws OverloadException {
		this.wagons.add(wagon);
		if (!losfahren()) {
			throw new OverloadException();
		}

	}

	@Override
	public int gewichtGesamt() {
		int gewichtGesamt = 0;
		for (IWagon wagon : this.wagons) {
			gewichtGesamt += wagon.gewichtGesamt();
		}
		gewichtGesamt += lokomotive.getWeight();
		return gewichtGesamt;
	}

	@Override
	public int gewichtWagons() {
		for (IWagon wagon : this.wagons) {
			this.gewichtWagons += wagon.gewichtGesamt();
		}
		return this.gewichtWagons;
	}

	@Override
	public boolean losfahren() {
		int maxGewicht = lokomotive.getPower() * Constants.KG_PER_KW;
		// return gewichtGesamt() > maxGewicht ? false : true;
		return gewichtGesamt() <= maxGewicht;
	}

	public List<IWagon> getWagons() {
		return wagons;
	}

	public Lokomotive getLokomotive() {
		return lokomotive;
	}

}
