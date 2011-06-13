package a02;

public interface IStack<T> {

	/**
	 * 
	 */
	void create();
	
	/**
	 * @param elem
	 */
	void push(T elem);
	
	
	/**
	 * 
	 */
	void pop();
	
	
	/**
	 * 
	 */
	T top();
	
	
	/**
	 * 
	 */
	boolean isEmpty();
	
}
