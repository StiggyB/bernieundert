package trees;

public interface IBinarySearchTree<E extends Comparable<E>> extends ISearchTree<E>{
	//**********************************************************************
	// inOrderTraverse() liefert eine Stringrepräsentation der Elemente
	//                   eines Baumes in Inorder. Elemente werden durch
	//                   Leerzeichen getrennt
	//**********************************************************************
	public String inOrderTraverse();
}
