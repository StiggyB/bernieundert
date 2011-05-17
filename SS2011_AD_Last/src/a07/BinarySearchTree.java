package a07;

import java.util.AbstractList;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> implements IBinarySearchTree<E> {

	Node<E> head;
	Node<E> nullNode;
	BinarySearchTree<E> left;
	BinarySearchTree<E> right;
	
	int nodeCount;
	
	public BinarySearchTree(Node<E> head, Node<E> nullNode) {
		super();
		this.head = head;
		this.nullNode = nullNode;
		this.nodeCount = 0;
	}

	@Override
	public int getNodeCount() {
		return this.nodeCount;
	}

	@Override
	public boolean isEmpty() {
		return this.nodeCount == 0;
	}

	
	
	@Override
	public int getHeight() {
		int height = 0;
		return this.recuHeight(height);
	}
	
	private int recuHeight(int height) {
//			return !(this.isLeaf()) ? this.left.getHeight() > this.right.getHeight() ? this.left.getHeight() : this.right.getHeight() : height++;
			return this.isEmpty() ? 0 : 1 + Math.max(this.left.getHeight(), this.right.getHeight());
	}

	@Override
	public String preOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.head.key.toString() + " ");
		if(!(this.left.isLeaf())) {
			this.left.preOrderTraverse();
		} if (!(this.right.isLeaf())) {
			this.right.postOrderTraverse(); 
		}
		return sb.toString();
	}
	
	@Override
	public String inOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		if(!(this.left.isLeaf())) {
			this.left.preOrderTraverse();
		}
		sb.append(this.head.key.toString() + " ");
		if (!(this.right.isLeaf())) {
			this.right.postOrderTraverse(); 
		}
		
		return sb.toString();
	}

	@Override
	public String postOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		if(!(this.left.isLeaf())) {
			this.left.postOrderTraverse();
		} 
		if (!(this.right.isLeaf())) {
			this.right.postOrderTraverse();
		}
		sb.append(this.head.key.toString() + " ");
		return sb.toString();
	}

	/**
	 * 
	 * @see a07.ITree#levelOrderTraverse()
	 */
	@Override
	public String levelOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		AbstractList<BinarySearchTree<E>> stack = new Stack<BinarySearchTree<E>>();
		stack.add(this.left);
		while(!stack.isEmpty()) {
			BinarySearchTree<E> tree = stack.remove(0);
			if(!(this.left.isLeaf())) {
				stack.add(tree.left);
			}
			if (!(this.right.isLeaf())) {
				stack.add(tree.right);
			}
		}
		sb.append(this.head.key.toString());
		return sb.toString();
	}
	
	boolean visited(Node<E> accNode) {
		return this.head.equals(accNode);
	}

	@Override
	public boolean isLeaf() {
			return this.head.left != null ? this.head.left.left == null ? this.head.right.right == null ? true : false : false : false;
	}

	@Override
	public boolean find(E key) {
		boolean result = false;
		
		if(key.compareTo(this.head.left.key) < 0) {
			if(this.left.isLeaf()) {
				result = false;
			} else {
				result = this.left.find(key);
			}
		} if(key.compareTo(this.head.right.key) > 0) {
			if(this.right.isLeaf()) {
				result = false;
			} else {
				result = this.right.find(key);
			}
		} else {
			result = true;
		}
		return result;
		
//		Node<E> accNode = recufind(key);
//		return  accNode.equals(this.head) ? false : true;
	}
	
	private Node<E> recufind(E key) {
		Node<E> accNode = this.head;
		//Beginne bei head falls accNode equals head -> nicht im Baum.
		if(key.compareTo(accNode.left.key) < 0 ) {
			this.left.recufind(key);
		}
		if(key.compareTo(accNode.left.key) > 0) {
			this.right.recufind(key);
		} else {
			accNode = this.head.left;
		}
		return accNode;
	}

	@Override
	public boolean addKey(E key) {//throws Exception {
		boolean result = false;
		if(this.isEmpty()) {
			this.head.left = new Node<E>(key, this.head, this.head.left, this.head.right);
		}
		
		if(key.compareTo(this.head.left.key) < 0) {
			if(this.left.isLeaf()) {
				this.head.left = new Node<E>(key, this.head, this.head.left, this.head.right);
				result = true;
			} else {
				this.left.addKey(key);
			}
		} if(key.compareTo(this.head.right.key) > 0) {
			if(this.right.isLeaf()) {
				this.head.right = new Node<E>(key, this.head, this.head.left, this.head.right);
				result = true;
			} else {
				this.right.addKey(key);
			}
		} else {
			//throw new Exception();
			result = false;
		}
		return result;
		
//		Node<E> accNode = recufind(key); //Wenn passender Parentknoten gefunden
//		return  accNode.equals(this.head) ? false : true;
	}
	
	private Node<E> recuAddKey(E key) throws Exception {
		Node<E> accNode = this.head;
		if(this.isEmpty()) {
			this.head.left = new Node<E>(key, accNode, accNode.left, accNode.right);
		}
		
		if(key.compareTo(accNode.left.key) < 0) {
			if(this.left.isLeaf()) {
				this.head.left = new Node<E>(key, accNode, accNode.left, accNode.right);
			} else {
				this.left.recuAddKey(key);
			}
		} if(key.compareTo(accNode.right.key) > 0) {
			if(this.right.isLeaf()) {
				this.head.right = new Node<E>(key, accNode, accNode.left, accNode.right);
			} else {
				this.right.recuAddKey(key);
			}
		} else {
			throw new Exception();
		}
		return accNode;
	}

	@Override
	//TODO zu implementieren!
	public boolean deleteKey(E key) {
		boolean result = false;
		if(this.isEmpty()) {
			this.head.left = nullNode;
		}
		if(key.compareTo(this.head.left.key) < 0) {
			if(this.left.isLeaf()) {
				this.head.left = nullNode;
				result = true;
			} else {
				this.left.deleteKey(key);
			}
		} if(key.compareTo(this.head.right.key) > 0) {
			if(this.right.isLeaf()) {
				this.head.right = nullNode;
				result = true;
			} else {
				this.right.deleteKey(key);
			}
		} else {
			//throw new Exception();
			result = false;
		}
		return result;
	}

	@Override
	public E findMin() {
		return (this.head.left.key.compareTo(this.head.right.key) < 0) ? this.left.findMin() : this.right.findMin();
	}

	@Override
	public E findMax() {
		return (this.head.left.key.compareTo(this.head.right.key) > 0) ? this.left.findMin() : this.right.findMin();
	}

}
