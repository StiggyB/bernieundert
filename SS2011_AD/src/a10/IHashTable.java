package a10;

import java.util.Hashtable;
import java.util.List;

/**
 * This interface provides elemental operations for a <tt>HashTable<tt>.
 * 
 * @param <K> the type of keys maintained by this table
 * @param <V> the type of the values in the table
 * 
 * @author Tugend und Laster
 * @see Hashtable
 */
public interface IHashTable<K, V> {

	/**
	 * This method fills a new Element in the <tt>HashTable<tt>.
	 * 
	 * @param key
	 * @param value
	 * 
	 * @return the filled value.
	 */
	V put(K key, V value);

	/**
	 * This method sets a delete-flag in the data structure.
	 * 
	 * @param key
	 * 
	 * @return the value with delete-flag
	 */
	V remove(K key);

	/**
	 * This method returns a element with a specific key.
	 * 
	 * @param key
	 * @return element from associated key
	 */
	List<V> get(K key);

	/**
	 * This method searches the specific key in the <tt>HashTable<tt>.
	 * 
	 * @param key
	 * @return <tt>true<tt> if key in table, otherwise false
	 */
	boolean containsKey(K key);

	/**
	 * This method searches the specific value in the <tt>HashTable<tt>.
	 * 
	 * @param value
	 * @return <tt>true<tt> if value in table, otherwise false
	 */
	boolean containsValue(V value);

	/**
	 * This method deletes all entries in the table.
	 */
	void clear();

	/**
	 * Returns <tt>true</tt> if this table contains no key-values.
	 * 
	 * @return <tt>true</tt> if this table contains no key-values
	 */
	boolean isEmpty();

	/**
	 * Returns the number of key-values in this table.
	 * 
	 * @return the number of key-values in this table
	 */
	int size();

}
