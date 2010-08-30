package a03;

/**
 * Klasse, die einen Fahrstuhlschacht enthält,
 * die Klasse steht in Verbidung mit der 
 * Fahestuhlklasse (Elevator)
 * 
 * @author Martin Slowikowski
 *
 */

public class ElevatorDuct {

	private Elevator elevator;

	public ElevatorDuct(Elevator elevator) {
		this.elevator = elevator;
	}

	public Elevator getElevator() {
		return elevator;
	}
	
}
