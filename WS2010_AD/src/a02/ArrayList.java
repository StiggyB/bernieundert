package a02;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Implementierung einer eigenen ArrayList mittels
 *         des vorgegebenen Interface Liste<E>
 * 
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

import a01.Liste;

public class ArrayList<T> implements Liste<T> {

	private T[] elements;
	private int size;

	public ArrayList() {
		this(10);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int length) {
		this.elements = (T[]) new Object[length];
	}

	@Override
	public void add(int pos, T element) throws IndexOutOfBoundsException {
		if (pos > this.size || pos < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			this.extendArray(this.size + 1);
			for (int i = size; i > pos; i--) {
				this.elements[i] = this.elements[i - 1];
			}
			this.elements[pos] = element;
			this.size++;
		}
	}

	@Override
	public void add(T element) {
		this.extendArray(this.size + 1);
		this.elements[this.size] = element;
		this.size++;
	}

	private void extendArray(int newsize) {
		if (this.elements.length < newsize) {
			this.elements = Arrays.copyOf(this.elements, this.size + this.size /2);
		}
	}

	@Override
	public T get(int pos) throws IndexOutOfBoundsException {
		if (pos >= this.size || pos < 0) {
			throw new IndexOutOfBoundsException();
		}
		return this.elements[pos];
	}

	@Override
	public void remove(int pos) throws IndexOutOfBoundsException {
		if (pos >= this.size || pos < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			for (int i = pos; i < this.size; i++) {
				this.elements[i] = this.elements[i + 1];
			}
			this.elements[this.size] = null; // null setzen, freigabe für den
			// gc.
			this.size--;
		}
	}

	@Override
	public void remove(T element) throws Exception {
		boolean found = false;
		if (element == null) { // damit man nur einen vergleich hat. 
			for (int i = 0; i < this.size;) {
				if (this.elements[i] == null) {
					this.remove(i);
					found = true;
				} else {
					i++; // es wird nur erhöht, wenn wir keins entfent haben.
				}
			}
		} else {
			for (int i = 0; i < this.size;) {
				if (element.equals(this.elements[i])) {
					this.remove(i);
					found = true;
				} else {
					i++;
				}
			}
		}
		if (!found) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

}
