package a01;

/**
 * Ein Interface mit den wesentlichen Listenoperationen.
 * Die Fehlerbehandlung ist von der Implementierung zu definieren.
 * @author Bernd Kahlbrandt
 * 
 * @param <E> Typ der Elemente der Liste.
 */
public interface Liste<E> {
	/**
	 * Fügt ein Element an der Stelle pos in die Liste ein.&nbsp;Das Element an dieser Stelle und alle anderen werden um eine Position verschoben.
	 * @param pos Die Position, an der eingefügt werden soll.
	 * @param element Das Element, das eingefügt werden soll.
	 */
	void add(int pos, E element) throws IndexOutOfBoundsException;
	/**
	 * Fügt ein Element am Ende der Liste ein.
	 * @param element Das Element, das eingefügt werden soll.
	 */
	void add(E element);
	/**
	 * Liefert das Element an der Position pos.
	 * @param pos Die Position, deren Element zurückgeliefert wird.
	 * @return Das Element an der Position pos.
	 */
	E get(int pos) throws IndexOutOfBoundsException;
	/**
	 * Entfernt das Element an der Position pos.
	 * @param pos Die Position, an der das Element entfernt werden soll
	 */
	void remove(int pos) throws IndexOutOfBoundsException;
	/**
	 * Entfernt alle Element aus der Liste, die gleich dem übergebenen Element sind.
	 * @param element Das Element, dessen Exemplare aus der Liste entfernt werden sollen.
	 */
	void remove(E element) throws Exception;
	/**
	 * Liefert <b>true</b>, wenn die Liste leer ist,  anderfalls <b>false</b>
	 * @return <b>true</b>, wenn die Liste leer ist,  anderfalls <b>false</b>
	 */
	boolean isEmpty();
	/**
	 * Liefert die Anzahl Elemente in der Liste. 
	 * @return Anzahl Elemente in der Liste.
	 */
	int size();
}
