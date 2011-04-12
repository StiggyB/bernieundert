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
	 * @param n
	 * @param e
	 * @return
	 */
	void insert(Node<E> targetNode, E e);
	
	
	/**
	 * @param e
	 */
	void append(E e);
	
	
	/**
	 * @param targetNode
	 * @return
	 */
	void delete(Node<E> targetNode);
	
	
	/**
	 * @param e
	 * @return
	 */
	public abstract Node<E> find(E e);
	
	
	/**
	 * @param targetNode
	 * @return
	 */
	public abstract E retrieve(Node<E> targetNode);
	
	
	/**
	 * @param list
	 * @return
	 */
	void concat(IList<E> list);
	
	
	/**
	 * @return
	 */
	Node<E> getHead();
	
	
	/**
	 * @return
	 */
	Node<E> getTail();
	
	
	/**
	 * @return
	 */
	int size();
	
	
	/**
	 * @return
	 */
	boolean isEmpty();
}
