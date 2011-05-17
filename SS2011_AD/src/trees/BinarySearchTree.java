package trees;

import java.util.ArrayDeque;
import java.util.Queue;

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
		if(this.key == key)  {
			if (left == null && right == null) {
				// kein sohn
				kickIt(this);
				return true;
			} else if (left != null && right == null) {
				// nur ein linker sohn
				if(left.right != null) {
					shift(this, left.right);
					left.right = null;
				} else {
					shift(this, left);
					left = null;
				}
			} else if (right != null && left == null) {
				// nur ein rechter sohn
				if(right.left != null)  {
					shift(this, right.left);
					right.left = null;
				} else {
					shift(this, right);
					right = null;
				}
			} else {
				// zwei S�hne
				BinarySearchTree<E> currentTree = left;
				BinarySearchTree<E> parentTree = this;
				while (currentTree.right != null) {
					parentTree = currentTree;
					currentTree = currentTree.right;
				}
				this.key = currentTree.key;
				parentTree.right = null;
			}
		} else if (key.compareTo(this.key) < 0) {
			if (left != null) {
				left.deleteKey(key);
			}
		} else if (key.compareTo(this.key) > 0) {
			if (right != null) {
				right.deleteKey(key);
			}
		}
		return false;
	}
	
	private void kickIt(BinarySearchTree<E> accTree)  {
		accTree = null;
	}
	
	private void shift(BinarySearchTree<E> accTree, BinarySearchTree<E> nextTree)  {
		accTree.key = nextTree.key;
		//Warum hier call by value?!
//		nextTree = null;
	}
	
	//@Override
	public boolean deleteKey2(E key) {
		if(this.key == key){
			//links unten
		}
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
				// zwei S�hne
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
				// zwei S�hne
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
			if (left != null) {
				left.deleteKey(key);
			}
		} else if (key.compareTo(this.key) > 0) {
			if (right != null) {
				right.deleteKey(key);
			}
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
//	Die H�he eines Wurzelbaums ist die maximal auftretende Tiefe. 
//	Viele Autoren setzen sie aber um 1 h�her, da man so dem leeren 
//	Baum die H�he 0 und dem nur aus der Wurzel bestehenden Baum 
//	die H�he 1 geben kann, was gewisse rekursive Definitionen k�rzer 
//	zu fassen gestattet.
	@Override
	public int getHeight() {
		int linksHeight = 0;
		int rechtsHeight = 0;
		if (!isEmpty()) {
			linksHeight++;
			rechtsHeight++;
		}
		if (left != null) {
			linksHeight += left.getHeight();
		}
		if (right != null) {
			rechtsHeight += right.getHeight();
		}
		return Math.max(linksHeight,rechtsHeight);
	}		

	@Override
	public String preOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		if (!isEmpty()) {
			sb.append(key.toString() + " ");
			if (key != null) {
				if (left != null) {
					sb.append(left.preOrderTraverse());
				}
				if (right != null) {
					sb.append(right.preOrderTraverse());
				}
			}
		}

		return sb.toString();
	}

	@Override
	public String postOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		if (!isEmpty()) {
			if (key != null) {
				if (left != null) {
					sb.append(left.postOrderTraverse());
				}
				if (right != null) {
					sb.append(right.postOrderTraverse());
				}
				sb.append(key.toString() + " ");
			}
		}
		return sb.toString();
	}

	@Override
	public String levelOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		if (!isEmpty()) {
			Queue<BinarySearchTree<E>> q = new ArrayDeque<BinarySearchTree<E>>();
			q.add(this);
			while (!q.isEmpty()) {
				BinarySearchTree<E> t = q.remove();
				if (t.left != null) {
					q.add(t.left);
				}
				if (t.right != null) {
					q.add(t.right);
				}
				sb.append(t.key.toString() + " ");
			}
		}
		return sb.toString();
	}
	
	@Override
	public boolean isLeaf() {
		return left == null && right == null;
	}

	//mehrere returns?!
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
		if (!isEmpty()) {
			if (key != null) {
				if (left != null) {
					sb.append(left.inOrderTraverse());
				}
				sb.append(key.toString() + " ");
				if (right != null) {
					sb.append(right.inOrderTraverse());
				}
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		if (key != null) {
			if (left != null) {
				sb.append(left.toString());
			}
			sb.append(key.toString());
			if (right != null) {
				sb.append(right.toString());
			}
		}
		sb.append(")");
		return sb.toString();
	}

}
