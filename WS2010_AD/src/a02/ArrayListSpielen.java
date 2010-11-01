package a02;
//package a02;
//
//import java.util.Arrays;
//
//import a01.Liste;
//
//public class ArrayList_muff_raw<T> implements Liste<T> {
//
//	private Object[] elements;
//	private int size;
//
//	public ArrayList_muff_raw(int size) {
//		elements = new Object[size];
//	}
//
//	@Override
//	public void add(int pos, T element) throws IndexOutOfBoundsException {
//		if (pos > size || pos < 0) {
//			throw new IndexOutOfBoundsException();
//		}
//
//		// um eins vergrößern
//		enlarge(size + 1);
//		// alles ab pos eins nach hinten kopieren
//		System.arraycopy(elements, pos, elements, pos + 1, size - pos);
//		elements[pos] = element;
//		size++;
//	}
//
//	@Override
//	public void add(T element) {
//		enlarge(size + 1);
//		elements[size] = element;
//		size++;
//	}
//
//	@Override
//	public T get(int pos) throws IndexOutOfBoundsException {
//		if (pos >= size) {
//			throw new IndexOutOfBoundsException();
//		}
//		return (T) elements[pos];
//	}
//
//	@Override
//	public void remove(int pos) throws IndexOutOfBoundsException {
//		if (pos >= size) {
//			throw new IndexOutOfBoundsException();
//		}
//
//		// wurde das letzte Element gelöscht? Dann muss nicht umkopiert werden
//		int elementRemoved = size - pos - 1;
//		if (elementRemoved > 0) {
//			System.arraycopy(elements, pos + 1, elements, pos, elementRemoved);
//		}
//		size--;
//		elements[size] = null;
//	}
//
//	@Override
//	public void remove(T element) throws Exception {
//		if (element == null) {
//			for (int i = 0; i < size; i++) {
//				if (elements[i] == null) {
//					remove(i);
//				}
//			}
//		} else {
//			for (int i = 0; i < size; i++) {
//				if (element.equals(elements[i])) {
//					remove(i);
//				}
//			}
//		}
//	}
//
//	public void enlarge(int newSize) {
////		int oldCapacity = elements.length;
////		if (minCapacity > oldCapacity) {
////			int newCapacity = (oldCapacity * 3) / 2 + 1;
////			if (newCapacity < minCapacity) {
////				newCapacity = minCapacity;
////			}
////			System.out.println(newCapacity);
////			elements = Arrays.copyOf(elements, newCapacity);
////		}
//		if (newSize > elements.length) {
//			elements = Arrays.copyOf(elements, newSize);
//		}
//	}
//
//	public static void main(String[] args) {
//		ArrayList_muff_raw<String> list = new ArrayList_muff_raw<String>(2);
//		list.add("bla");
//		list.add("bla");
//		list.add("bla");
//		list.add("bla");
//		list.add("bla");
//		list.add("bla");
//		list.add("bla");
//		list.add("bla");
//	}
//
//	@Override
//	public boolean isEmpty() {
//		return size == 0;
//	}
//
//	@Override
//	public int size() {
//		return size;
//	}
//}
//

