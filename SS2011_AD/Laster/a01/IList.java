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
	public abstract void insert(Node<E> targetNode, E e);
	
	
	/**
	 * @param targetNode
	 * @return
	 */
	public abstract void delete(Node<E> targetNode);
	
	
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
	public abstract void concat(IList<E> list);
	
	
	/**
	 * @return
	 */
	public abstract Node<E> getHead();
	
	
	/**
	 * @return
	 */
	public abstract Node<E> getTail();
}
