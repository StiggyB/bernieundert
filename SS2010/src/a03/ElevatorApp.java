package a03;

/**
 * Klasse, die eine Fahrstuhlsteuerung enthält.
 * 
 * @author Martin Slowikowski
 * 
 */

public class ElevatorApp {

	// Neuer Fahrstuhlschachtarray mit der Feldlänge 1
	private ElevatorDuct[] elevatorDucts = new ElevatorDuct[1];
	
	// Neues Obj. von ElevatorIO, zur Nutzung der enthaltenen Methoden
	private ElevatorIO eleIO;

	// start-Methode, Fahrstuhl ist im Erdgeschoss, Türen offen
	public void start() {
		// Neues ElevatorIO-Objekt, zur Nutzung der Methoden
		eleIO = new ElevatorIO();
		// In den Schacht wird nun nach dem Einlesen der Stockwerke
		// ein neuer Fahrstuhl gesteckt
		elevatorDucts[0] = new ElevatorDuct(new Elevator(eleIO.readFloor()));
		eleIO.showCurrentFloor(elevatorDucts[0].getElevator().getActualFloor());

		// Nach dem Start geht der Fahrstuhl in den Dauerbetrieb
		commandLoop();
	}

	public void commandLoop() {
		// Endlosschleife (Alternative wäre while(true)), die den Dauerbetrieb des
		// Fahrstuhls simuliert, bis das Programm beendet wird
		for (;;) {
			char selection = eleIO.readCommand();

			switch (selection) {

			case 'e':
				eleIO.showAvailableFloors(elevatorDucts[0].getElevator().getFloors());
				elevatorDucts[0].getElevator().floorUpDown(eleIO.readInputFloor());
				eleIO.showCurrentFloor(elevatorDucts[0].getElevator().getActualFloor());
				break;

			case 'r':
				eleIO.showAvailableFloors(elevatorDucts[0].getElevator().getFloors());
				int destination = eleIO.readInputFloor();
				eleIO.callElevator(destination, elevatorDucts[0].getElevator().getActualFloor());
				elevatorDucts[0].getElevator().floorUpDown(destination);
				eleIO.showCurrentFloor(elevatorDucts[0].getElevator().getActualFloor());
				break;

			case 'b':
				System.exit(0);
				break;

			default:
				eleIO.showIllegalSelection();
				break;
			}
		}
	}
}
