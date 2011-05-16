package trees;

public class MuffisTree<E extends Comparable<E>> implements IBinarySearchTree<E> {

	
	private MuffisTree<E> links;
	private MuffisTree<E> rechts;
	private E key;
	
	
	public MuffisTree(E key) {
		this.key = key;
	}

	public MuffisTree() {
	}

	@Override
	public boolean addKey(E key) {
		if (isEmpty()) {
			this.key = key;
			return true;
		} else {
			if (key.compareTo(this.key) < 0) {
				if (links == null) {
					links = new MuffisTree<E>(key);
					return true;
				} else {
					return links.addKey(key);
				}
			} else if (key.compareTo(this.key) > 0) {
				if (rechts == null) {
					rechts = new MuffisTree<E>(key);
					return true;
				} else {
					return rechts.addKey(key);
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean deleteKey(E key) {
		if (links != null && links.key == key) {
			if (links.links == null && links.rechts == null) {
				// kein sohn
				links = null;
				return true;
			} else if (links.links != null && links.rechts == null) {
				// nur ein linker sohn
				MuffisTree<E> newLinks = links.links;
				links.links = null;
				links = newLinks;
			} else if (links.rechts != null && links.links == null) {
				// nur ein rechter sohn
				MuffisTree<E> newLinks = links.rechts;
				links.rechts = null;
				links = newLinks;
			} else {
				// zwei Söhne
				MuffisTree<E> currentTree = links;
				while (currentTree.rechts != null) {
					currentTree = currentTree.rechts;
				}
				if (links.rechts != currentTree) {
					currentTree.rechts = links.rechts;
				}
				currentTree.links = links.links;
				links = currentTree;
			}
		} else if (rechts != null && rechts.key == key) {
			if (rechts.links == null && rechts.rechts == null) {
				// kein sohn
				rechts = null;
				return true;
			} else if (rechts.links != null && rechts.rechts == null) {
				// nur ein linker sohn
				MuffisTree<E> newLinks = rechts.links;
				rechts.links = null;
				rechts = newLinks;
			} else if (rechts.rechts != null && rechts.links == null) {
				// nur ein rechter sohn
				MuffisTree<E> newLinks = rechts.rechts;
				rechts.rechts = null;
				rechts = newLinks;
			} else {
				// zwei Söhne
				MuffisTree<E> currentTree = rechts;
				while (currentTree.rechts != null) {
					currentTree = currentTree.rechts;
				}
				if (rechts.rechts != currentTree) {
					currentTree.rechts = links.rechts;
				}
				currentTree.links = rechts.links;
				rechts = currentTree;
			}
		} else if (key.compareTo(this.key) < 0) {
			links.deleteKey(key);
		} else if (key.compareTo(this.key) > 0) {
			rechts.deleteKey(key);
		}
		return false;
	}

	@Override
	public E findMin() {
		if (links != null) {
			return links.findMin();
		} 
		return key;
	}

	@Override
	public E findMax() {
		if (rechts != null) {
			return rechts.findMax();
		} 
		return key;
	}

	@Override
	public int getNodeCount() {
		int count = 0;
		if (!isEmpty()) {
			count = 1;
		}
		if (links != null) {
			count += links.getNodeCount();
		}
		if (rechts != null) {
			count += rechts.getNodeCount();
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return key == null;
	}

	@Override
	public int getHeight() {
		int linksHeight = 0;
		int rechtsHeight = 0;
		if (!isEmpty()) {
			linksHeight++;
			rechtsHeight++;
		}
		if (links != null) {
			linksHeight += links.getHeight();
		}
		if (rechts != null) {
			rechtsHeight += rechts.getHeight();
		}
		return Math.max(linksHeight, rechtsHeight);
		
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
		return !isEmpty() && links == null && rechts == null;
	}

	@Override
	public boolean find(E key) {
		if (isEmpty()) {
			return false;
		} else if (key.compareTo(this.key) < 0) {
			if (links == null) {
				return false;
			}
			return links.find(key);
		} else if (key.compareTo(this.key) > 0) {
			if (rechts == null) {
				return false;
			}
			return rechts.find(key);
		} else {
			return true;
		}
	}

	@Override
	public String inOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		if (key != null) {
			if (links != null) {
				sb.append(links.inOrderTraverse());
			}
			sb.append(key.toString());
			if (rechts != null) {
				sb.append(rechts.inOrderTraverse());
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
