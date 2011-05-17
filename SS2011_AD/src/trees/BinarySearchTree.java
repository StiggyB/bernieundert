package trees;

public class BinarySearchTree<E extends Comparable<E>> implements IBinarySearchTree<E> {

	
	private BinarySearchTree<E> left;
	private BinarySearchTree<E> right;
	private E key;
	
	
	public BinarySearchTree(E key) {
		this.key = key;
	}

	public BinarySearchTree() {
	}

	@Override
	public boolean addKey(E key) {
		if (isEmpty()) {
			this.key = key;
			return true;
		} else {
			if (key.compareTo(this.key) < 0) {
				if (left == null) {
					left = new BinarySearchTree<E>(key);
					return true;
				} else {
					return left.addKey(key);
				}
			} else if (key.compareTo(this.key) > 0) {
				if (right == null) {
					right = new BinarySearchTree<E>(key);
					return true;
				} else {
					return right.addKey(key);
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean deleteKey(E key) {
		if (left != null && left.key == key) {
			if (left.left == null && left.right == null) {
				// kein sohn
				left = null;
				return true;
			} else if (left.left != null && left.right == null) {
				// nur ein linker sohn
				BinarySearchTree<E> newLinks = left.left;
				left.left = null;
				left = newLinks;
			} else if (left.right != null && left.left == null) {
				// nur ein rechter sohn
				BinarySearchTree<E> newLinks = left.right;
				left.right = null;
				left = newLinks;
			} else {
				// zwei Söhne
				BinarySearchTree<E> currentTree = left;
				while (currentTree.right != null) {
					currentTree = currentTree.right;
				}
				if (left.right != currentTree) {
					currentTree.right = left.right;
				}
				currentTree.left = left.left;
				left = currentTree;
			}
		} else if (right != null && right.key == key) {
			if (right.left == null && right.right == null) {
				// kein sohn
				right = null;
				return true;
			} else if (right.left != null && right.right == null) {
				// nur ein linker sohn
				BinarySearchTree<E> newLinks = right.left;
				right.left = null;
				right = newLinks;
			} else if (right.right != null && right.left == null) {
				// nur ein rechter sohn
				BinarySearchTree<E> newLinks = right.right;
				right.right = null;
				right = newLinks;
			} else {
				// zwei Söhne
				BinarySearchTree<E> currentTree = right;
				while (currentTree.right != null) {
					currentTree = currentTree.right;
				}
				if (right.right != currentTree) {
					currentTree.right = left.right;
				}
				currentTree.left = right.left;
				right = currentTree;
			}
		} else if (key.compareTo(this.key) < 0) {
			left.deleteKey(key);
		} else if (key.compareTo(this.key) > 0) {
			right.deleteKey(key);
		}
		return false;
	}

	@Override
	public E findMin() {
		if (left != null) {
			return left.findMin();
		} 
		return key;
	}

	@Override
	public E findMax() {
		if (right != null) {
			return right.findMax();
		} 
		return key;
	}

	@Override
	public int getNodeCount() {
		int count = 0;
		if (!isEmpty()) {
			count = 1;
		}
		if (left != null) {
			count += left.getNodeCount();
		}
		if (right != null) {
			count += right.getNodeCount();
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return key == null;
	}

//	Tiefe eines Knotens ist die Anzahl der Kanten bis zur Wurzel
//	Die Höhe eines Wurzelbaums ist die maximal auftretende Tiefe. 
//	Viele Autoren setzen sie aber um 1 höher, da man so dem leeren 
//	Baum die Höhe 0 und dem nur aus der Wurzel bestehenden Baum 
//	die Höhe 1 geben kann, was gewisse rekursive Definitionen kürzer 
//	zu fassen gestattet.
	@Override
	public int getHeight() {
		if(isEmpty()){
			return 0;
		} else {
//			
//		
//				int linksHeight = 0;
//		int rechtsHeight = 0;
//		if (!isEmpty()) {
//			linksHeight++;
//			rechtsHeight++;
//		}
//		if (left != null) {
//			linksHeight += left.getHeight();
//		}
//		if (right != null) {
//			rechtsHeight += right.getHeight();
//		}
//		return 1+Math.max(linksHeight, rechtsHeight);
		return 1+Math.max(left.getHeight(), right.getHeight());
		}
		
	}

	@Override
	public String preOrderTraverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postOrderTraverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String levelOrderTraverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		return left == null && right == null;
	}

	@Override
	public boolean find(E key) {
		if (isEmpty()) {
			return false;
		} else if (key.compareTo(this.key) < 0) {
			if (left == null) {
				return false;
			}
			return left.find(key);
		} else if (key.compareTo(this.key) > 0) {
			if (right == null) {
				return false;
			}
			return right.find(key);
		} else {
			return true;
		}
	}

	@Override
	public String inOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		if (key != null) {
			if (left != null) {
				sb.append(left.inOrderTraverse());
			}
			sb.append(key.toString());
			if (right != null) {
				sb.append(right.inOrderTraverse());
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return inOrderTraverse();
	}

}
