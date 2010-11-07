package a03;

import java.util.List;

import a01.Liste;

public class ListeAdapter<T> implements Liste<T> {

	private final List<T> list;

	public ListeAdapter(List<T> list) {
		this.list = list;
	}
	
	@Override
	public void add(int pos, T element) throws IndexOutOfBoundsException {
		list.add(pos, element);
	}

	@Override
	public void add(T element) {
		list.add(element);
	}

	@Override
	public T get(int pos) throws IndexOutOfBoundsException {
		return list.get(pos);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void remove(int pos) throws IndexOutOfBoundsException {
		list.remove(pos);
	}

	@Override
	public void remove(T element) throws Exception {
		list.remove(element);
	}

	@Override
	public int size() {
		return list.size();
	}
}
