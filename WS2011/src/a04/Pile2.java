package a04;

public class Pile2 implements IPile {

	private int[] a;
	private final int n;

	public Pile2(int pile) {
		this.n = (int) Math.pow(2, pile);

		this.a = new int[this.n];
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

		return i % 2 == 0 ? this.a[i] - ((i & -i) >> 1) : 0;
	}

	@Override
	public int parent(int i) {

		return this.a[(i - (i & -i) | (i & -i) << 1)];

	}

	@Override
	public int rightChild(int i) {

		return i % 2 == 0 ? this.a[i] + ((i & -i) >> 1) : 0;
	}

	@Override
	public void set(int i, int value) {
		this.a[i] = value;

	}

	@Override
	public int sibling(int i) {

		int temp = (i ^ ((i & -i) << 1));
		if (temp > this.a.length) {
			return 0;
		} else
			return temp;

	}

	public void inorder(int j) {
		if (j > this.a.length - 1) {
			System.out.println("Auﬂerhalb des Arrays!");
			System.out.println("Programm wird beendet!");
			System.exit(0);
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
