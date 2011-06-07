package a09;

public interface IHashMap<K, V> {

	/**
	 * Diese Methode fuegt
	 * 
	 * @param key
	 * @param value
	 */
	void insert(K key, V value);
	
	/**
	 * @param key
	 */
	void delete(K key);
	
	/**
	 * @param key
	 * @return
	 */
	boolean containsKey(K key);
	
	/**
	 * @param vaule
	 * @return
	 */
	boolean containsValue(V vaule);
	
	/**
	 * 
	 */
	void clear();
	
	/**
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * @return
	 */
	int size();
	
	
	//Wird bei verschiedenen Hashtables genutzt -- ist das nötig?
//	void hash();
	
	
}
