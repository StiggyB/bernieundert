package a09;

/**
 * Translate this shit!
 * 
 * @author Tugend und Laster
 * 
 * @param <K>
 * @param <V>
 */
public interface IHashTable<K, V> {

	/**
	 * Diese Methode fuegt ein neues Element in die HashTable ein.
	 * 
	 * @param key
	 * @param value
	 */
	V put(K key, V value);

	/**
	 * Diese Methode loescht ein Element aus der HashTable.
	 * 
	 * @param key
	 */
	V remove(K key);

	/**
	 * Diese Methode gibt ein Element zu einem bestimmten Schluessel zurueck.
	 * 
	 * @param key
	 * @return Element vom Schluessel
	 */
	V get(K key);

	/**
	 * Diese Methode prueft ob der Schluessel in der HashTable vorhanden ist.
	 * 
	 * @param key
	 * @return true wenn Schluessel vorhanden
	 */
	boolean containsKey(K key);

	/**
	 * Diese Methode prueft ob das Element in der HashTable vorhanden ist.
	 * 
	 * @param vaule
	 * @return true wenn Element vorhanden
	 */
	boolean containsValue(V value);

	/**
	 * Diese Methode leert die HashTable.
	 */
	void clear();

	/**
	 * Diese Methode prueft ob die HashTable leer ist.
	 * 
	 * @return true wenn HashTable leer ist
	 */
	boolean isEmpty();

	/**
	 * Diese Mathode gibt die Groesse der HashTable zurueck.
	 * 
	 * @return groesse der HashTable
	 */
	int size();

}
