package a10;

public class Entry<K ,V> {

	K key;
	V value;
	
	/**
	 * @param key
	 * @param value
	 */
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
}