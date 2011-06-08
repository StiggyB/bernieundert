package a09;

public interface IHashMap<K, V> {

	/**
	 * Diese Methode fuegt ein neues Element in die HashMap ein.
	 * 
	 * @param key
	 * @param value
	 */
	void insert(K key, V value);
	
	/**
	 * Diese Methode loescht ein Element aus der HashMap.
	 * 
	 * @param key
	 */
	void delete(K key);
	
	/**
	 * Diese Methode gibt ein Element zu 
	 * einem bestimmten Schluessel zurueck.
	 * 
	 * @param key
	 * @return Element vom Schluessel
	 */
	V get(K key);
	
	/**
	 * Diese Methode prueft ob der Schluessel 
	 * in der HashMap vorhanden ist.
	 * 
	 * @param key
	 * @return true wenn Schluessel vorhanden
	 */
	boolean containsKey(K key);
	
	/**
	 * Diese Methode prueft ob das Element 
	 * in der HashMap vorhanden ist.
	 * 
	 * @param vaule
	 * @return true wenn Element vorhanden
	 */
	boolean containsValue(V value);
	
	/**
	 * Diese Methode leert die HashMap.
	 */
	void clear();
	
	/**
	 * Diese Methode prueft ob die HashMap leer ist.
	 * 
	 * @return true wenn HashMap leer ist
	 */
	boolean isEmpty();
	
	/**
	 * Diese Mathode gibt die Groesse der HashMap zurueck.
	 * 
	 * @return groesse der HashMap
	 */
	int size();
	
	
	//Wird bei verschiedenen Hashtables genutzt -- ist das nötig?
//	void hash();
	
	
}
