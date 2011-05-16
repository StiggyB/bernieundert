package a07;

public interface ITree <E>{
	//**********************************************************************
	// getNodeCount() liefert die Anzahl der Knoten im Baum
	//**********************************************************************
	public int getNodeCount();
	
	//**********************************************************************
	// isEmpty() liefert true, wenn der Baum leer ist, andernfalls false
	//**********************************************************************
	public boolean isEmpty();
	
	//**********************************************************************
	// getHeight() liefert die H�he des Baumes
	//**********************************************************************
	public int getHeight();
	
	//**********************************************************************
	// preOrderTraverse() liefert eine Stringrepr�sentation der Elemente
	//                    eines Baumes in Preorder. Elemente werden durch 
	//                    Leerzeichen getrennt
	//**********************************************************************
	public String preOrderTraverse();
	
	//**********************************************************************
	// postOrderTraverse() liefert eine Stringrepr�sentation der Elemente
	//                     eines Baumes in Postorder. Elemente werden durch 
	//                     Leerzeichen getrennt
	//**********************************************************************
	public String postOrderTraverse();
	
	//**********************************************************************
	// levelOrderTraverse() liefert eine Stringrepr�sentation der Elemente
	//                      eines Baumes in Level-Order. Elemente werden  
	//                      durch Leerzeichen getrennt
	//**********************************************************************
	public String levelOrderTraverse();
	
	//**********************************************************************
	// isLeaf() liefert true, wenn der Wurzel-Knoten ein Blatt ist, 
	//          andernfalls false
	//**********************************************************************
	public boolean isLeaf();
	
	//**********************************************************************
	// find(E key) liefert true, wenn das Element key im Baum vorhanden ist, 
	//             andernfalls false
	//**********************************************************************
	public boolean find(E key);
}
