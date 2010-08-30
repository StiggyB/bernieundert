package a08;

import java.util.Comparator;

public class WagonComparator implements Comparator<IWagon> {

	@Override
	public int compare(IWagon o1, IWagon o2) {
		// o1.gewichtGesamt() - o2.gewichtGesamt() == 0 -> gleiches Gewicht, muss nicht geändert werden
		//o1.gewichtGesamt() - o2.gewichtGesamt() < 0   -> o2 ist schwerer, also o1 nach vorne
		// o1.gewichtGesamt() - o2.gewichtGesamt() > 0  -> o1 is schwerer, also o2 nach vorne
		return o1.gewichtGesamt() - o2.gewichtGesamt();

		// Alternative, mit allerdings 3 return Statements.
		// if (o1.gewichtGesamt() == o2.gewichtGesamt()) {
		// return 0;
		// } else if (o1.gewichtGesamt() > o2.gewichtGesamt()) {
		// return 1;
		// } else {
		// return -1;
		// }
	}

}
