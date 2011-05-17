package trees;

public class Main {
	
	public static void main(String[] args) {
		IBinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.addKey(4);
		bst.addKey(2);
		bst.addKey(3);
//		bst.addKey(1);
//		bst.addKey(8);
//		bst.addKey(12);
//		bst.addKey(5);
		
//		bst.addKey(20);
//		bst.addKey(10);
//		bst.addKey(1);
//		bst.addKey(5);
//		bst.addKey(4);
//		bst.addKey(7);
		
		System.out.println(bst.findMin());
		System.out.println(bst.findMax());
		System.out.println("count: " + bst.getNodeCount());
		System.out.println("height: " + bst.getHeight());
		System.out.println("find 8: " + bst.find(8));
		System.out.println("find 15: " + bst.find(15));
		System.out.println("find 12: " + bst.find(12));
		System.out.println("tostring: " + bst);
		System.out.println("inorder: " + bst.inOrderTraverse());
		System.out.println("postorder: " + bst.postOrderTraverse());
		System.out.println("preorder: " + bst.preOrderTraverse());
		System.out.println("levelorder: " + bst.levelOrderTraverse());
		System.out.println(bst.deleteKey(4));
		System.out.println(bst);
		
//		http://www.java-tips.org/java-se-tips/java.lang/binary-search-tree-implementation-in-java.html
//		http://www-lehre.inf.uos.de/~ainf/2005/Vorlesung/SuchBaum.java
//		http://www.javaworld.com/javaworld/jw-11-1996/indepth/BinarySearchTree.java
//		http://www.informatiktreff.de/materialien/sek_ii/algorithmen/baum/baum.htm
	}

}
