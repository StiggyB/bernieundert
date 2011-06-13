package a02;

/**
 * Interface für die Implementierung einer Queue nach dem TI3-AD_Script.
 * Das Interface wurde um die Methode size() erweitert.
 * 
 * @author Tugend und Laster
 * @param <T> Typ der Elemente der Queue.
 */
public interface IQueue<T> {

	/**
	 * Liefert das Element head zurueck ohne es aus der Queue zu loeschen (nicht
	 * destruktiv)
	 * 
	 * @return Liefert das erste Element (head) aus der Queue
	 */
	T front() throws RuntimeException;

	/**
	 * Fuegt neues Element am Ende der Queue ein (rear)
	 * 
	 * @param element Element zum Einfuegen
	 */
	void enqueue(T element) throws RuntimeException;

	/**
	 * Loescht das erste Element (head)
	 */
	void dequeue() throws RuntimeException;

	/**
	 * Liefert <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 * 
	 * @return <b>true</b>, wenn die Queue leer ist, andernfalls <b>false</b>
	 */
	boolean isEmpty();

	/**
	 * Liefert die Anzahl der in der Queue befindlichen Elemente
	 * @return Anzahl der Elemente in der Queue
	 */
	int size();

}
