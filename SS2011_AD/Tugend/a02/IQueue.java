package a02;

public interface IQueue<T> {

	/**
	 * TODO: wirft EmptyQueueException, wenn die Liste leer ist. 
	 * Liefert das Element head zurueck ohne es aus der Queue 
	 * zu loeschen (nicht destruktiv)
	 * 
	 * @return
	 */
	T front();

	/**
	 * TODO: Wirft OverflowException, wenn Queue voll ist. 
	 * Fuegt neues Element hinten ein (rear)
	 * 
	 * @param element
	 */
	void enqueue(T element);

	/**
	 * TODO: Wirft sowas wie NoSuchElementException, wenn Queue leer ist 
	 * Loescht das Element head
	 */
	void dequeue();

	/**
	 * true falls die Queue leer ist , false sonst.
	 * 
	 * @return
	 */
	boolean isEmpty();

}
