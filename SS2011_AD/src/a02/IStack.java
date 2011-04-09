package a02;

/**
 * Interface für die Implementierung eines Stack nach dem TI3-AD_Script.
 * 
 * @author Tugend und Laster
 * @param <T> Typ der Elemente des Stack.
 */
public interface IStack<T> {

	/**
	 * TODO: Exception needed?! Push legt ein Element auf dem Stack ab (waechst
	 * nach unten).
	 * 
	 * @param element
	 */
	void push(T element);

	/**
	 * Pop loescht das zuletzt eingefuegte Element.
	 */
	void pop();

	/**
	 * Top liefert das oberste Element des Stacks zurueck (nicht destruktiv)
	 * 
	 * @return Oberstes Element vom Stack
	 */
	T top();

	/**
	 * Liefert True, wenn der Stack leer ist, andernfalls False.
	 * 
	 * @return True, wenn der Stack leer ist, andernfalls False.
	 */
	boolean isEmpty();

}
