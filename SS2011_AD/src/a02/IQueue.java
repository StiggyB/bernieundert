package a02;

public interface IQueue<T> {

	/**
	 * Liefert das Element head zurueck ohne es aus der Queue zu loeschen (nicht destruktiv)
	 * @return
	 */
	T front();

	/**
	 * Fuegt neues Element hinten ein (rear)
	 * @param element
	 */
	void enqueue(T element);

	/**
	 * Loescht das Element head
	 */
	void dequeue();

	/**
	 * true falls die Queue leer ist , false sonst.
	 * @return
	 */
	boolean isEmpty();

}
