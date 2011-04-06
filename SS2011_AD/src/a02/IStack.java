package a02;

/**
 * 
 * @author aay973
 * 
 * @param <T>
 */
public interface IStack<T> {

	/**
	 * Push legt ein Element auf dem Stack ab (wächst nach unten).
	 * @param element
	 */
	void push(T element);

	/**
	 * Pop löscht das zuletzt eingefügte Element, liefert aber nichts zurück.
	 */
	void pop();

	/**
	 * Top liefert das oberste Element des Stacks zurück, löscht ihn jedoch nicht.
	 * @return
	 */
	T top();

	/**
	 * Liefert True, wenn der Stack leer ist, andernfalls False.
	 * @return
	 */
	boolean isEmpty();

}
