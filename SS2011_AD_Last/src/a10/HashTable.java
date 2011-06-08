package a10;

public class HashTable<K, V> implements IHashTable<K, V> {

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	//interne Methoden -- nicht fuer den User bestimmt!
	
	/**
	 * Diese Methode berechnet den Index fuer einen Schluessel 
	 * in der Datenstruktur der HashTable.
	 */
	void hash() {
		
	}
	
	/**
	 * Diese Methode laesst die Datenstruktur entsprechend
	 * des load factors wachsen bzw. schrumpfen.
	 */
	void resize() {
		
	}
	
}
