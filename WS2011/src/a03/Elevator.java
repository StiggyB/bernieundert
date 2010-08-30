package a03;

/**
 * Klasse, die einen Fahrstuhl repr�sentiert.
 * 
 * @author Martin Slowikowski
 * 
 */

public class Elevator {

	// Anzahl der Stockwerke, final, da nicht mehr �nderbar nach Start
	private final int floors;

	// Hier wird das Stockwerk gespeichert, wo sich der Fahrstuhl aktuell
	// befindet
	private int actualFloor;

	public Elevator(int floors) {
		this.floors = floors;
	}

	// Methode, die den Fahrstuhl Stockwerke auf- oder abw�rts fahren l�sst.
	public void floorUpDown(int newFloor) {
		// Aufgabenstellung: Wenn wir uns im gew�hlten Stockwerk bereits
		// befinden,
		// soll das kund getan werden!
		if (this.actualFloor == newFloor) {
			System.out.println("Wir befinden uns bereits im gewaehlten Stockwerk!");
		} else {
			if (newFloor > this.floors) {
				System.out.println("Ung�ltiges Stockwerk, es gibt nur "
						+ this.floors);
			} else {

				// aufw�rts...
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

					// abw�rts
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

	// Methode zur Simulation des T�r�ffnens
	public void openDoors() {
		System.out.print("T�ren �ffnen sich ");
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

	// Methode zur Simulation des T�rschlie�ens
	public void closeDoors() {
		System.out.print("T�ren schlie�en sich ");
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

	// Methode gibt aktuelles Stockwerk zur�ck
	public int getActualFloor() {
		return this.actualFloor;
	}

	// Methode gibt zur�ck, mit wieviel Stockwerken der Fahrstuhl
	// initialisiert wurde
	public int getFloors() {
		return this.floors;
	}

}
