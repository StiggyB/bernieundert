package a05;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Interface für unsere Implementierung des RingBuffer.
 * 
 * @param <T> Typ der Elemente der Liste.
 */

public interface IRingBuffer<T> {
	/**
	 * Fügt ein Element am Ende ein
	 * @param item Das Element, das eingefügt werden soll
	 */
	void enqueue(T item) throws RuntimeException;

	/**
	 * Entfernen des Elements am Anfang
	 * @return Erstes Element im RingBuffer
	 */
	T dequeue() throws RuntimeException;

	/**
	 * Liefert das Element am Anfang zurück
	 * @return Erstes Element im Ringpuffer
	 */
	T peek() throws RuntimeException;

	/**
	 * Liefert die Anzahl Elemente im RingBuffer
	 * @return Anzahl Elemente im RingBuffer
	 */
	int size();

	/**
	 * Liefert <b>true</b>, wenn der RingBuffer leer ist, andernfalls <b>false</b>
	 * @return <b>true</b>, wenn der RingBuffer leer ist, andernfalls <b>false</b>
	 */
	boolean isEmpty();

	/**
	 * Liefert <b>true</b>, wenn der RingBuffer voll ist, andernfalls <b>false</b>
	 * @return <b>true</b>, wenn der RingBuffer voll ist, andernfalls <b>false</b>
	 */
	boolean isFull();

}