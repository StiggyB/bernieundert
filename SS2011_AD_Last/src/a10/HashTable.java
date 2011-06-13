package a10;

import java.util.ArrayList;
import java.util.List;


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
	int threshold;

	/**
	 * @param table
	 * @param size
	 * @param loadFactor
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
	 * @param initialCapacity
	 */
	public HashTable(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	/**
	 * 
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
		if(size >= threshold) {
			System.out.println("RESIZE!");
			resize(2 * table.length);
		}
		return internalPut(key, value);
	}
	
	private V internalPut(K key, V value) {
		int hash = hash(key, 0);
		System.out.println(hash);
		if (table[hash] != null && key.equals(table[hash].key)) {
			addEntry(key, value, hash);
			return table[hash].value;
		}
		if (!(table[hash] == null)) {
			for (int i = 1; i < DEFAULT_COUNT_OF_HASHES; i++) {
				hash = hash(key, i);
				System.out.println(hash);
				if (table[hash] != null && key.equals(table[hash].key)) {
					addEntry(key, value, hash);
					return table[hash].value;
				}
				if (table[hash] == null) {
					Entry<K, V> entry = new Entry<K, V>(key, value, null);
					table[hash] = entry;
					size++;
					return table[hash].value;
				} else if (i == DEFAULT_COUNT_OF_HASHES) {
					resize(2 * table.length);
				}
			}
		}
		Entry<K, V> entry = new Entry<K, V>(key, value, null);
		table[hash] = entry;
		size++;
		return table[hash].value;
	}

	void addEntry(K key, V value, int bucketIndex) {
		Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>(key, value, e);
//		if (size++ >= threshold) {
//			System.out.println("ENTRY_RESIZE!");
//			resize(2 * table.length);
//		}
	}

	/**
	 * (key % table.length) + 1 + (key % (table.length -2)) % table.length
	 * 
	 * @param key
	 * @param collCount
	 * @return
	 */
	int hash(K key, int collCount) {
		System.out.println("Kollision: " + collCount);
		System.out.println(("Key: " + key.hashCode() + "Hash: " + key.hashCode() % table.length));
		return Math.abs(((key.hashCode() % table.length) + (1 + (key.hashCode() % (table.length - 3)))
				* (collCount * collCount))
				% table.length);
	}

	/**
	 * Diese Methode laesst die Datenstruktur entsprechend des load factors
	 * wachsen bzw. schrumpfen.
	 */
	void resize(int newCapacity) {
		
		Entry<K, V>[] oldTable = table;
		@SuppressWarnings("unchecked")
		Entry[] newTable = new Entry[newCapacity];
		table = newTable;
		size = 0;
        threshold = (int)(newCapacity * loadFactor);
        rehash(oldTable);
	}
	
	void rehash(Entry<K, V>[] oldTable) {
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
			System.out.println("hash : " + hash + " -- i : " + i);
			if (table[hash] != null && key.equals(table[hash].key)) {
				table[hash].isDeleted = true;
				System.out.println("EQUALS!");
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

	@SuppressWarnings("unchecked")
//	@Override
	public V[] get2(K key) {
		V[] valueArr;
		int hash;
		for (int i = 0; i < DEFAULT_COUNT_OF_HASHES; i++) {
			hash = hash(key, i);
			System.out.println("hash : " + hash + " -- i : " + i);
			if(table[hash] != null && key.equals(table[hash].key)) {
				System.out.println("EQUALS!");
				int countOfEntries = 1;
				valueArr = (V[])new Object[countOfEntries];
				valueArr[countOfEntries-1] = table[hash].value;
				if(table[hash].next != null) {
					System.out.println("BUCKETS!");
					for (Entry<K, V> e = table[hash]; e != null ; e = e.next, countOfEntries++);
					valueArr = (V[])new Object[countOfEntries];
					for (Entry<K, V> e = table[hash]; e != null ; e = e.next, countOfEntries--)  {
						valueArr[countOfEntries-1] = e.value;
					}
				}
				return (V[])valueArr;
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
			System.out.println("hash : " + hash + " -- i : " + i);
			if(table[hash] != null && key.equals(table[hash].key)) {
				System.out.println("EQUALS!");
				valueList.add(table[hash].value);
				if(table[hash].next != null) {
					System.out.println("BUCKETS!");
					for (Entry<K, V> e = table[hash]; e != null ; e = e.next)  {
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
			//One if-statement is enough (no separate chaining).
			if(table[i] != null) {
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
			//Iteration for every element with the same key.
			for (Entry<K, V> entry = table[i]; entry != null; entry = entry.next) {
				if (value.equals(entry.value)) {
					return true;
				}
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

	// interne Methoden -- nicht fuer den User bestimmt!

	/**
	 * Diese Methode berechnet den Index fuer einen Schluessel in der
	 * Datenstruktur der HashTable.
	 */
	int hash(K key) {
		int hash = 0;
		hash = key.hashCode() % table.length;
		if (key.equals(table[hash].key)) {
			return hash;
		}
		if (!(table[hash] == null)) {
			for (int i = 0; i < DEFAULT_COUNT_OF_HASHES; i++) {
				hash = ((key.hashCode() % table.length) + (1 + (key.hashCode() % (table.length - 2))
						* (i * i)));
				if (key.equals(table[hash].key)) {
					return hash;
				}
				if (table[hash] == null) {
					return hash;
				} else if (i == DEFAULT_COUNT_OF_HASHES) {
					resize(2 * table.length);
					hash = hash(key);
				}
			}
		}
		return hash;
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
	
	public Entry<K, V>[] getTable() {
		return table;
	}

}
