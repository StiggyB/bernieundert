package a01;

import a01.LinkedList.Node;

public interface List<T> {
	
	/**
	 * Fügt ein Element an der Stelle pos in die Liste ein.
	 * @param pos Die Position, an der eingefügt werden soll.
	 * @param element Das Element, das eingefügt werden soll.
	 */
	void insert(Node pos, T element) throws IndexOutOfBoundsException;
	/**
	 * Fügt ein Element am Ende der Liste ein.
	 * @param element Das Element, das eingefügt werden soll.
	 */
	void insert(T element);
	/**
	 * Liefert die Position des gesuchten Elements.
	 * @param element Das gesuchte Element.
	 * @return Die Position des gesuchten Elements
	 */
	Node find(T element) throws IndexOutOfBoundsException;
	/**
	 * Entfernt das Element an der Position pos.
	 * @param pos Die Position, an der das Element entfernt werden soll
	 */
	void delete(Node pos) throws IndexOutOfBoundsException;
	/**
	 * Liefert das Element zurück an der Position pos
	 * @param pos Position des gewünschten Elements
	 */
	T retrieve(Node pos) throws Exception;
	/**
	 * Konkateniert zwei Listen miteinander
	 * @param list Die Liste, die angefügt wird.
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
