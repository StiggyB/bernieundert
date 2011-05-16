package trees;

public interface ISearchTree<E extends Comparable<E>> extends ITree<E> {
	//**********************************************************************
	// addKey(E key) fügt das Element key in den Baum ein. Liefert true, 
	//               wenn die Operation erfolgreich war, andernfalls (wenn
	//               key bereits existiert) false
	//**********************************************************************
	public boolean addKey(E key);
	
	//**********************************************************************
	// deleteKey(E key) löscht das Element key aus dem Baum. Liefert true, 
	//                  wenn die Operation erfolgreich war, andernfalls 
	//                  (wenn key nicht existiert) false
	//**********************************************************************
	public boolean deleteKey(E key);
	
	//**********************************************************************
	// findMin() liefert das minimale Element im Baum bzw. null bei einem
	//           leeren Baum.
	//**********************************************************************
	public E findMin();
	
	//**********************************************************************
	// findMax() liefert das maximale Element im Baum bzw. null bei einem
	//           leeren Baum.
	//**********************************************************************
	public E findMax();
}
