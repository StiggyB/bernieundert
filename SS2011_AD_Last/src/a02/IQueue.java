package a02;

public interface IQueue<T> {

	
	/**
	 * @return
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
	 * @return
	 */
	boolean isEmpty();
	
	
	/**
	 * @return
	 */
	boolean isFull();
}
