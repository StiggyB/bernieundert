package a10;

public class HashTable<K extends Integer, V> implements IHashTable<K, V> {

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

	private static final int DEFAULT_COUNT_OF_HASHES = 5;
	
    
	private Entry[] table;
	private int size;
	private float loadFactor;
	
	/**
	 * @param table
	 * @param size
	 * @param loadFactor
	 */
	public HashTable( int initialCapacity, float loadFactor) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " +
					initialCapacity);
		} if (initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		} if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("Illegal load factor: " +
					loadFactor);
		}
		this.loadFactor = loadFactor;
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
	public HashTable() {
		this.size = 0;
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		this.table = new Entry[DEFAULT_INITIAL_CAPACITY]; 
	}
	
	
	



	@Override
	public V put(K key, V value) {
		int idx = hash(key);
		Entry<K, V> entry = new Entry<K, V>(key, value);
		
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
	private int hash(K key) {
		int hash = 0;
		hash = key % size;
		if(!(table[hash].equals(null))) {
			for (int i = 0; i < DEFAULT_COUNT_OF_HASHES; i++) {
				hash = ((key % size) + (1 + (key % (size -2)) * (i*i)));
				if(!(table[hash].equals(null))) {
					return hash;
				} else if (i == DEFAULT_COUNT_OF_HASHES){
					resize();
					hash = hash(key);
				}
			}
		}
		return hash;
	}
	
	/**
	 * Diese Methode laesst die Datenstruktur entsprechend
	 * des load factors wachsen bzw. schrumpfen.
	 */
	void resize() {
		
	}
	
}
