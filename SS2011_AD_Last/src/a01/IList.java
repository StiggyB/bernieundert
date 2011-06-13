package a01;

/**
 * This List interface is generic and
 * provides 5 methods for access to the List.
 * 
 * 
 * @author Administrator
 *
 * @param <E>
 */
public interface IList<E> {
	

	/**
	 * @param targetNode
	 * @param e
	 */
	void insert(Node<E> targetNode, E e);
	

	/**
	 * @param e
	 */
	void append(E e);
	
	
	/**
	 * @param targetNode
	 */
	void delete(Node<E> targetNode);
	
	
	/**
	 * @param e
	 */
	public abstract Node<E> find(E e);
	
	
	/**
	 * @param targetNode
	 */
	public abstract E retrieve(Node<E> targetNode);
	
	
	/**
	 * @param list
	 */
	void concat(IList<E> list);
	
	
	/**
	 * 
	 */
	Node<E> getHead();
	
	
	/**
	 * 
	 */
	Node<E> getTail();
	
	
	/**
	 * 
	 */
	void setHead(Node<E> node);
	
	/**
	 * 
	 */
	int size();
	
	
	/**
	 * 
	 */
	boolean isEmpty();
}
