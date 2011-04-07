package a01;

/**
 * 
 * @author Team TugendUndLaster
 * 
 *         Interface einer Liste mit den wesentlichen Optionen, 
 *         wie im AD-Skript auf Seite 10 angegeben.
 *         Erg�nzt um die Methodendeklarationen isEmpty() und
 *         insert(T element) zum Einf�gen am Ende der Liste. 
 * 
 */

public interface List<T> {
	
	/**
	 * F�gt ein Element an der Stelle pos in die Liste ein.
	 * @param pos Die Position, an der eingef�gt werden soll.
	 * @param element Das Element, das eingef�gt werden soll.
	 */
	void insert(Node<T> pos, T element) throws IndexOutOfBoundsException;
	/**
	 * F�gt ein Element am Ende der Liste ein.
	 * @param element Das Element, das eingef�gt werden soll.
	 */
	void append(T element);
	/**
	 * Liefert die Position des gesuchten Elements.
	 * @param element Das gesuchte Element.
	 * @return Die Position des gesuchten Elements
	 */
	Node<T> find(T element) throws IndexOutOfBoundsException;
	/**
	 * Entfernt das Element an der Position pos.
	 * @param pos Die Position, an der das Element entfernt werden soll
	 */
	void delete(Node<T> pos) throws IndexOutOfBoundsException;
	/**
	 * Liefert das Element zur�ck an der Position pos
	 * @param pos Position des gew�nschten Elements
	 */
	T retrieve(Node<T> pos) throws Exception;
	/**
	 * Konkateniert zwei Listen miteinander
	 * @param list Die Liste, die angef�gt wird.
	 */
	void concat(List<T> list) throws Exception;
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
