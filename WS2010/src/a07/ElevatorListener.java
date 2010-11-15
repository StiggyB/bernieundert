package a07;

public interface ElevatorListener {

	void elevatorChangedFloor(int elevatorNr, int floor);
	void arrivedOnTargetFloor(int elevatorNr);
	void leavingFloor(int elevatorNr);
}
