package a05;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Interface für unsere Implementierung des RingBuffer.
 * 
 */

public interface IRingBuffer<T> {

	boolean isEmpty();
	boolean isFull();
	int size();
	void enqueue(T item);
	T dequeue();
	T peek();

}