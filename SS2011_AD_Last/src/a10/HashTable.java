package a10;

import java.util.ArrayList;
import java.util.List;

/**
 * The class HashTable based on the <tt>IHashTable<tt> interface. This 
 * implementation provides a hash table with an array as data structure 
 * and works with open addressing and chaining with equal entries.
 * 
 * @param <K> the type of keys maintained by this table
 * @param <V> the type of the values in the table
 * 
 * @author 	Tugend und Laster
 * @see  	Object#hashCode()
 * @see		IHashTable
 */
public class HashTable<K, V> implements IHashTable<K, V> {

	/**
	 * The default initial capacity - MUST be a power of two.
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * The maximum capacity, used if a higher value is implicitly specified by
	 * either of the constructors with arguments. MUST be a power of two <=
	 * 1<<30.
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;

	/**
	 * The load factor used when none specified in constructor.
	 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * The maximum count of double hashes in the hash function.
	 */
	private static final int DEFAULT_COUNT_OF_HASHES = 5;

	/**
	 * The table, resized as necessary. Length MUST Always be a power of two.
	 */
	private Entry<K, V>[] table;

	/**
	 * The number of the actual entries in this table.
	 */
	private int size;

	/**
	 * The load factor for the hash table.
	 */
	private float loadFactor;

	/**
	 * The next size value at which to resize (capacity * load factor).
	 */
	private int threshold;

    /**
     * Constructs an empty <tt>HashTable</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is non positive
     */
	@SuppressWarnings("unchecked")
	public HashTable(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: "
					+ initialCapacity);
		}
		if (initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("Illegal load factor: "
					+ loadFactor);
		}
		this.size = 0;
		this.loadFactor = loadFactor;
		this.threshold = (int) (loadFactor * initialCapacity);
		this.table = new Entry[initialCapacity];
	}

    /**
     * Constructs an empty <tt>HashTable</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
	public HashTable(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

    /**
     * Constructs an empty <tt>HashTable</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
	@SuppressWarnings("unchecked")
	public HashTable() {
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		this.table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	@Override
	public V put(K key, V value) {
		if (size >= threshold) {
			resize(2 * table.length);
		}
		return internalPut(key, value);
	}

	/**
	 * This method fills a associated key and value in the table.
	 * In case of collisions 
	 * 
	 * @param key
	 * @param value
	 * @return the value which filled in the table
	 */
	private V internalPut(K key, V value) {
		int hash = hash(key, 0);
		if (table[hash] != null && key.equals(table[hash].key)) {
			addEntry(key, value, hash);
			return table[hash].value;
		}
		if (!(table[hash] == null)) {
			for (int i = 1; i < DEFAULT_COUNT_OF_HASHES; i++) {
				hash = hash(key, i);
				if (table[hash] != null && key.equals(table[hash].key)) {
					addEntry(key, value, hash);
					return table[hash].value;
				}
				if (table[hash] == null) {
					Entry<K, V> entry = new Entry<K, V>(key, value, null);
					table[hash] = entry;
					size++;
					return table[hash].value;
				} else if (i == DEFAULT_COUNT_OF_HASHES - 1) {
					resize(2 * table.length);
					size++;
					return internalPut(key, value);
				}
			}
		}
		Entry<K, V> entry = new Entry<K, V>(key, value, null);
		table[hash] = entry;
		size++;
		return table[hash].value;
	}

	/**
	 * @param key
	 * @param value
	 * @param bucketIndex
	 */
	private void addEntry(K key, V value, int bucketIndex) {
		Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>(key, value, e);
	}

	/**
	 * Diese Methode berechnet den Index fuer einen Schluessel in der
	 * Datenstruktur der HashTable.
	 * 
	 * (key % table.length) + 1 + (key % (table.length -2)) % table.length
	 * 
	 * @param key
	 * @param collCount
	 * @return
	 */
	private int hash(K key, int collCount) {
		System.out.println("Kollision: " + collCount);
		System.out.println(("Key: " + key.hashCode() + "Hash: " + key
				.hashCode() % table.length));
		return Math
				.abs(((key.hashCode() % table.length) + (1 + (key.hashCode() % (table.length - 2)))
						* (collCount * collCount))
						% table.length);
	}

	/**
	 * This Method increases or decreases the data structure depending on the
	 * load factor.
	 * 
	 * @param newCapacity
	 */
	@SuppressWarnings("unchecked")
	private void resize(int newCapacity) {
		Entry<K, V>[] oldTable = table;
		Entry<K, V>[] newTable = new Entry[newCapacity];
		table = newTable;
		size = 0;
		threshold = (int) (newCapacity * loadFactor);
		rehash(oldTable);
	}

	/**
	 * @param oldTable
	 */
	private void rehash(Entry<K, V>[] oldTable) {
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] != null) {
				internalPut(oldTable[i].key, oldTable[i].value);
			}
		}
	}

	@Override
	public V remove(K key) {
		int hash;
		for (int i = 0; i < DEFAULT_COUNT_OF_HASHES; i++) {
			hash = hash(key, i);
			if (table[hash] != null && key.equals(table[hash].key)) {
				table[hash].isDeleted = true;
				if (table[hash].next != null) {
					for (Entry<K, V> e = table[hash]; e != null; e = e.next) {
						e.isDeleted = true;
					}
				}
				size--;
				return table[hash].value;
			}
		}
		return null;
	}

	@Override
	public List<V> get(K key) {
		List<V> valueList = new ArrayList<V>();
		int hash;
		for (int i = 0; i < DEFAULT_COUNT_OF_HASHES; i++) {
			hash = hash(key, i);
			if (table[hash] != null && key.equals(table[hash].key)) {
				valueList.add(table[hash].value);
				if (table[hash].next != null) {
					for (Entry<K, V> e = table[hash]; e != null; e = e.next) {
						valueList.add(e.value);
					}
				}
				return valueList;
			}
		}
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		for (int i = 0; i < table.length; i++) {
			// One if-statement is enough (no separate chaining).
			if (table[i] != null) {
				if (key.equals(table[i].key)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < table.length; i++) {
			// Iteration for every element with the same key.
			for (Entry<K, V> entry = table[i]; entry != null; entry = entry.next) {
				if (value.equals(entry.value)) {
					return true;
				}
			}
			if (value.equals(table[i].value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			this.table[i] = null;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<K, V> e : table) {
			sb.append("[");
			if (e != null) {
				sb.append("key:" + e.key + "-> values: ");
				for (Entry<K, V> entry = e; entry != null; entry = entry.next) {
					sb.append("isDeleted: " + entry.isDeleted + " ");
					sb.append(" " + entry.value + " ");
				}
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
}
