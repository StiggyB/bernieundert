package a02;

/**
 * 
 * @author aay973
 * 
 * @param <T>
 */
public interface IStack<T> {

	/**
	 * Push legt ein Element auf dem Stack ab (w�chst nach unten).
	 * @param element
	 */
	void push(T element);

	/**
	 * Pop l�scht das zuletzt eingef�gte Element, liefert aber nichts zur�ck.
	 */
	void pop();

	/**
	 * Top liefert das oberste Element des Stacks zur�ck, l�scht ihn jedoch nicht.
	 * @return
	 */
	T top();

	/**
	 * Liefert True, wenn der Stack leer ist, andernfalls False.
	 * @return
	 */
	boolean isEmpty();

}
