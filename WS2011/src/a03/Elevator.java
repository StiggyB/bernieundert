package a03;

/**
 * Klasse, die einen Fahrstuhl repräsentiert.
 * 
 * @author Martin Slowikowski
 * 
 */

public class Elevator {

	// Anzahl der Stockwerke, final, da nicht mehr änderbar nach Start
	private final int floors;

	// Hier wird das Stockwerk gespeichert, wo sich der Fahrstuhl aktuell
	// befindet
	private int actualFloor;

	public Elevator(int floors) {
		this.floors = floors;
	}

	// Methode, die den Fahrstuhl Stockwerke auf- oder abwärts fahren lässt.
	public void floorUpDown(int newFloor) {
		// Aufgabenstellung: Wenn wir uns im gewählten Stockwerk bereits
		// befinden,
		// soll das kund getan werden!
		if (this.actualFloor == newFloor) {
			System.out.println("Wir befinden uns bereits im gewaehlten Stockwerk!");
		} else {
			if (newFloor > this.floors) {
				System.out.println("Ungültiges Stockwerk, es gibt nur "
						+ this.floors);
			} else {

				// aufwärts...
				if (this.actualFloor < newFloor) {
					closeDoors();
					do {
						actualFloor++;
						System.out.println("Fahre ... " + actualFloor
								+ ". Stock");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} while (actualFloor < newFloor);
					openDoors();

					// abwärts
				} else {
					closeDoors();
					do {
						actualFloor--;
						System.out.println("Fahre ... " + actualFloor
								+ ". Stock");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (actualFloor > newFloor);
					openDoors();
				}
			}
		}

	}

	// Methode zur Simulation des Türöffnens
	public void openDoors() {
		System.out.print("Türen öffnen sich ");
		for (int i = 0; i < 5; i++) {
			System.out.print(". ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// Methode zur Simulation des Türschließens
	public void closeDoors() {
		System.out.print("Türen schließen sich ");
		for (int i = 0; i < 5; i++) {
			System.out.print(". ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// Methode gibt aktuelles Stockwerk zurück
	public int getActualFloor() {
		return this.actualFloor;
	}

	// Methode gibt zurück, mit wieviel Stockwerken der Fahrstuhl
	// initialisiert wurde
	public int getFloors() {
		return this.floors;
	}

}
