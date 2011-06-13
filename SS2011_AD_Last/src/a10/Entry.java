package a10;

/**
 * This class represents an entry in the HashTable implementation.
 * 
 * @param <K> the type of keys maintained by the table
 * @param <V> the type of the values in the table
 * 
 * @author Tugend und Laster
 * @see IHashTable
 */
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

	@Override
	public String toString() {
		return "Entry [key=" + key + ", value=" + value + ", next=" + next
				+ ", isDeleted=" + isDeleted + "]";
	}
	
	
}