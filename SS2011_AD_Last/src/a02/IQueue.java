package a02;

public interface IQueue<T> {

	
	/**
	 * 
	 */
	T front();
	
	
	/**
	 * @param element
	 */
	void enqueue(T element);
	
	
	/**
	 * 
	 */
	void dequeue();
	
	
	/**
	 *
	 */
	boolean isEmpty();
	
	
	/**
	 * 
	 */
	int size();
}
