package trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearchTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="";
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		System.out.println("H�he des Baumes: " + bst.getHeight());
		System.out.println("Leerer Baum?     " + bst.isEmpty());
		System.out.println("Knotenanzahl:    " + bst.getNodeCount());
		
		try {
			s = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("4 einf�gen    " + bst.addKey(4));
		System.out.println(bst.toString());
		System.out.println("2 einf�gen    " + bst.addKey(2));
		System.out.println(bst.toString());
		System.out.println("6 einf�gen    " + bst.addKey(6));
		System.out.println(bst.toString());
		System.out.println("5 einf�gen    " + bst.addKey(5));
		System.out.println(bst.toString());
		System.out.println("7 einf�gen    " + bst.addKey(7));
		System.out.println(bst.toString());
		System.out.println("5 einf�gen    " + bst.addKey(5));
		System.out.println(bst.toString());
		
		try {
			s = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("H�he des Baumes: " + bst.getHeight());
		System.out.println("Leerer Baum?     " + bst.isEmpty());
		System.out.println("Knotenanzahl:    " + bst.getNodeCount());
		
		try {
			s = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Minimum: " + bst.findMin());
		System.out.println("Maximum: " + bst.findMax());
		System.out.println("Finde 5: " + bst.find(5));
		System.out.println("Finde 9: " + bst.find(9));
		
		try {
			s = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Preorder Traversierung:    " + bst.preOrderTraverse());
		System.out.println("Postorder Traversierung:   " + bst.postOrderTraverse());
		System.out.println("Inorder Traversierung:     " + bst.inOrderTraverse());
		System.out.println("Level-Order Traversierung: " + bst.levelOrderTraverse());
		
		try {
			s = input.readLine();
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("4 l�schen    " + bst.deleteKey(4));
		System.out.println(bst.toString());
		System.out.println("6 l�schen    " + bst.deleteKey(6));
		System.out.println(bst.toString());
		System.out.println("9 l�schen    " + bst.deleteKey(9));
		System.out.println(bst.toString());
		System.out.println("5 l�schen    " + bst.deleteKey(5));
		System.out.println(bst.toString());
		System.out.println("7 l�schen    " + bst.deleteKey(7));
		System.out.println(bst.toString());
		System.out.println("2 l�schen    " + bst.deleteKey(2));
		System.out.println(bst.toString()); 
	}

}
