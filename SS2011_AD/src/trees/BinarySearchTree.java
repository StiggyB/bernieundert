package trees;

public class BinarySearchTree<E extends Comparable<E>> implements IBinarySearchTree<E> {

	Knoten<E> root;
	private int nodeCount;

	@Override
	public boolean addKey(E key) {
		if (root == null) {
			root = new Knoten<E>(key);
		}
		return addKey(key, root);
	}

	public boolean addKey(E key, Knoten<E> node) {
		if (key.compareTo(node.key) < 0) {
			if (node.links == null) {
				Knoten<E> newNode = new Knoten<E>(key);
				node.links = newNode;
				return true;
			} else {
				return addKey(key, node.links);
			}
		} else if (key.compareTo(node.key) > 0) {
			if (node.rechts == null) {
				Knoten<E> newNode = new Knoten<E>(key);
				node.rechts = newNode;
				return true;
			} else {
				return addKey(key, node.rechts);
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteKey(E key) {
		root = deleteKey(key, root);
		return true;
	}

	private Knoten<E> deleteKey(E key, Knoten<E> node) {
		if (node == null) {
//			throw new ItemNotFoundException(key.toString());
		}
		if (key.compareTo(node.key) < 0) {
			node.links = deleteKey(key, node.links);
		} else if (key.compareTo(node.key) > 0) {
			node.rechts = deleteKey(key, node.rechts);
		} else if (node.links != null && node.rechts != null) // Two children
		{
			node.key = getMin(node.rechts).key;
			node.rechts = removeMin(node.rechts);
		} else
			node = (node.links != null) ? node.links : node.rechts;
		return node;
	}

	private Knoten<E> removeMin(Knoten<E> node) {
		if (node == null) {
//			throw new ItemNotFoundException(null);
			return null;
		} else if (node.links != null) {
			node.links = removeMin(node.links);
			return node;
		} else
			return node.rechts;
	}

	private Knoten<E> getMin(Knoten<E> node) {
		if (node != null)
			while (node.links != null)
				node = node.links;

		return node;
	}

	@Override
	public E findMin() {
		Knoten<E> node = null;
		if (root != null)
			while (root.links != null)
				node = node.links;

		return node.key;
	}

	@Override
	public E findMax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNodeCount() {
		return nodeCount;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
		// return nodeCount == 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
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
		return root != null && root.links == null && root.rechts == null;
	}

	@Override
	public boolean find(E key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String inOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		inOrderTraverse(root, sb);
		return sb.toString();
	}

	private void inOrderTraverse(Knoten<E> node, StringBuilder sb) {
		sb.append("(");
		if (node != null) {
			if (node.links != null) {
				inOrderTraverse(node.links, sb);
			}
			sb.append(node.key.toString());
			if (node.rechts != null) {
				inOrderTraverse(node.rechts, sb);
			}
		}
		sb.append(")");
	}
	
	@Override
	public String toString() {
		return inOrderTraverse();
	}

}
