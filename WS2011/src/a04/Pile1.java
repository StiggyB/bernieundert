package a04;

public class Pile1 implements IPile {

	private int[] a;
	private final int n;

	public Pile1(int pile) {

		// Hier könnte man auch mit '<<' potenzieren...
		this.n = (int) Math.pow(2, pile);

		// Unser Array a mit der Länge der eben errechneten Potenz init.
		this.a = new int[this.n];

		// mit zahlen füllen
		for (int i = 0; i < this.a.length; i++) {
			this.a[i] = i;
		}
	}

	@Override
	public int get(int i) {
		return this.a[i];
	}

	@Override
	public int leftChild(int i) {
		int leftChild = this.a[i] << 1;

		if ((leftChild > 0 && i > 0) && (leftChild < n && i < n)) {
			return leftChild;
		} else {
			return 0;
		}

	}

	@Override
	public int parent(int i) {

		return i > 1 ? this.a[i] >> 1 : 0;
	}

	@Override
	public int rightChild(int i) {

		int rightChild = (this.a[i] << 1) + 1;

		if ((rightChild > 0 && i > 0) && (rightChild < n && i < n)) {
			return rightChild;
		} else {
			return 0;
		}
	}

	@Override
	public void set(int i, int value) {
		this.a[i] = value;

	}

	@Override
	public int sibling(int i) {

		return this.a[i] ^ 1;
	}

	public void inorder(int j) {

		// Damit es kein ArrayOutOfBound gibt, hier rechzeitig prüfen ...
		if (j > this.a.length - 1) {
			System.out.println("Außerhalb des Arrays!");
			System.out.println("Programm wird beendet!");
			System.exit(0);

			// Ansonsten weiter ausführen
		} else {

			System.out.println("Pile1.inorder(" + j + ")");
			int leftChild = leftChild(j);
			int rightChild = rightChild(j);
			if (leftChild != 0) {
				inorder(leftChild);
			}
			visit(j);
			if (rightChild != 0) {
				inorder(rightChild);
			}
		}
	}

	public void visit(int j) {
		System.out.println("Wert im Feld " + j + ": " + this.a[j]);

	}

}
