package namensdienst;

import java.util.HashMap;
import java.util.Map;

public abstract class NameService {
	
	private Map<String, ObjectRef> entries = new HashMap<String, ObjectRef>();
	private Server socket;
	private List<Threads> workerList = new ArrayList<Threads>();
	
	
	public abstract void rebind(Object servant, String name);
	   // Meldet ein Objekt (servant) beim Namensdienst an.
	   // Eine eventuell schon vorhandene Objektreferenz gleichen Namens
	   // soll überschrieben werden.
	
	public abstract Object resolve(String name);
	// Liefert die Objektreferenz (Stellvertreterobjekt) zu einem Namen.
	
	public static void main(String[] args) {
		
	}
	
}
