package trees;

class Knoten<T> {
	public Knoten(T key) {
		this.key = key;
	}

	T key;
	Knoten<T> links, rechts, vater;
}