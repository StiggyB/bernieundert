package a10;

public class Entry<K ,V> {

	K key;
	V value;
	Entry<K, V> next;
	boolean isDeleted;
	
	/**
	 * @param key
	 * @param value
	 */
	public Entry(K key, V value, Entry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
		this.isDeleted = false;
	}
}