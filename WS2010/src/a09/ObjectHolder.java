package a09;

/**
 * Der ObjectHolder enthält ein zu inspizierendes Objekt und implementiert Runnable, 
 * so können wir den Vorgang als Thread laufen lassen.
 * in der run() Methode ändern wir laufend den Wert einer Variablen, allerdings müssen wir
 * vorher prüfen, ob es sich um unser Testobjekt der DummyClass handelt. 
 * Unser ObjectHolder ruft die objectChanged() Methode bei einer Objektänderung auf
 * und der Tree wird neu aufgebaut. So kriegen wir die Änderungen mit und im Browser angezeigt.
 * Das gilt in unserem Beispiel aber nur statisch für ein Objekt unserer DummyClass.
 * Unser Problem war nun nur noch, dass sich immer der komplette Baum neu aufbaut und man den
 * Status verliert, in welchem Subnode man gerade war ...
 * 
 * @author Bernie und Ert         
 * @version 0.02beta
 */
public class ObjectHolder implements Runnable {

	private final Object object;
	private ObjectChangedListener objectChangedListener;

	/**
	 * Konstruktor für den ObjectHolder.
	 * @param object Zu inspizierendes Objekt
	 */
	public ObjectHolder(Object object) {
		this.object = object;
	}

	// public void set(Field field, Object value) throws Exception {
	// field.set(object, value);
	// objectChangedListener.objectChanged();
	// }
	
	/**
	 * Getter für das zu insp. Objekt 
	 * @return object - zu insp. Objekt
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * run Methode für den ObjectHolder, wenn er ein DummyClass Objekt erhält, 
	 * wird der Wert von publicInteger inkrementiert, bei anderen Objekten 
	 * wartet er lediglich ab.
	 * Das SuppressWarnings("deprecation") dient nur der Entfernung der "deprecated-Warning",
	 * die zu Testzwecken in der DummyClass gesetzt wurde.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			while (true) {
				if (object instanceof DummyClass) {
					DummyClass dummyClass = (DummyClass) object;
					dummyClass
							.setPublicInteger(dummyClass.getPublicInteger() + 1);
					Thread.sleep(5000);
					objectChangedListener.objectChanged();
				} else {
					synchronized (this) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * set-Methode für den objectChangedListener
	 * @param objectChangedListener
	 */
	public void setObjectChangedListener(
			ObjectChangedListener objectChangedListener) {
		this.objectChangedListener = objectChangedListener;
	}

}
