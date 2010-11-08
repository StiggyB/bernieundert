package a06ws;

public class ElevatorController {
	private static ElevatorController SINGLETON;
	private Elevator[] elevators;
	
	private ElevatorController() {}
	
	public static final ElevatorController getInstance() {
		if (null == SINGLETON) {
			synchronized (ElevatorController.class) {
				if (null == SINGLETON) {
					SINGLETON = new ElevatorController();
				}
			}
		}
		
		return SINGLETON;
	}
	public void init() {
		elevators = new Elevator[AppMain.COUNT_ELEVATOR];
		                           
		for (int i = 0; i < AppMain.COUNT_ELEVATOR; i++) {
			elevators[i] = new Elevator();
		}
	}
	
	public void down(int floor) {
		
	}
	
	public void up(int floor) {
		
	}
}
