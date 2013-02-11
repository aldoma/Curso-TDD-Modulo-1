/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio5;

import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implementación del interfaze {@link LazyMap}.
 * 
 * @param <K>
 *        the type of keys maintained by this map
 * @param <V>
 *        the type of mapped values
 * @author Alberto Dominguez Matamoros
 */
public class HashLazyMap<K, V>
		implements
			LazyMap<K, V>,
			Cloneable,
			Serializable {

	/**
	 * The default initial capacity - MUST be a power of two.
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * The maximum capacity, used if a higher value is implicitly specified by either of the
	 * constructors with arguments. MUST be a power of two <= 1<<30.
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;

	/**
	 * The load factor used when none specified in constructor.
	 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * The table, resized as necessary. Length MUST Always be a power of two.
	 */
	@SuppressWarnings( "rawtypes" )
	transient Entry[] table;

	/**
	 * The number of key-value mappings contained in this map.
	 */
	transient int size;

	/**
	 * The next size value at which to resize (capacity * load factor).
	 * 
	 * @serial
	 */
	int threshold;

	/**
	 * The load factor for the hash table.
	 * 
	 * @serial
	 */
	final float theLoadFactor;

	/**
	 * The number of times this HashLazyMap has been structurally modified Structural modifications
	 * are those that change the number of mappings in the HashMap or otherwise modify its internal
	 * structure (e.g., rehash). This field is used to make iterators on Collection-views of the
	 * HashLazyMap fail-fast.
	 * 
	 * @see ConcurrentModificationException
	 */
	transient volatile int modCount;

	/**
	 * Constructs an empty <tt>HashLazyMap</tt> with the specified initial capacity and load factor.
	 * 
	 * @param initialCapacity
	 *        the initial capacity
	 * @param loadFactor
	 *        the load factor
	 * @throws IllegalArgumentException
	 *         if the initial capacity is negative or the load factor is nonpositive
	 */
	public HashLazyMap( final int initialCapacity,
						final float loadFactor ) {
		int theInitialCapacity = initialCapacity;
		if (theInitialCapacity < 0) {
			throw new IllegalArgumentException( "Illegal initial capacity: " + theInitialCapacity ); //$NON-NLS-1$
		}
		if (theInitialCapacity > HashLazyMap.MAXIMUM_CAPACITY) {
			theInitialCapacity = HashLazyMap.MAXIMUM_CAPACITY;
		}
		if (loadFactor <= 0 || Float.isNaN( loadFactor )) {
			throw new IllegalArgumentException( "Illegal load factor: " + loadFactor ); //$NON-NLS-1$
		}

		// Find a power of 2 >= initialCapacity
		int capacity = 1;
		while (capacity < theInitialCapacity) {
			capacity <<= 1;
		}

		this.theLoadFactor = loadFactor;
		threshold = (int) (capacity * loadFactor);
		table = new Entry[capacity];
		init();
	}

	/**
	 * Constructs an empty <tt>HashLazyMap</tt> with the specified initial capacity and the default
	 * load factor (0.75).
	 * 
	 * @param initialCapacity
	 *        the initial capacity.
	 * @throws IllegalArgumentException
	 *         if the initial capacity is negative.
	 */
	public HashLazyMap( final int initialCapacity ) {
		this( initialCapacity, HashLazyMap.DEFAULT_LOAD_FACTOR );
	}

	/**
	 * Constructs an empty <tt>HashLazyMap</tt> with the default initial capacity (16) and the
	 * default load factor (0.75).
	 */
	public HashLazyMap() {
		this.theLoadFactor = HashLazyMap.DEFAULT_LOAD_FACTOR;
		threshold = (int) (HashLazyMap.DEFAULT_INITIAL_CAPACITY * HashLazyMap.DEFAULT_LOAD_FACTOR);
		table = new Entry[HashLazyMap.DEFAULT_INITIAL_CAPACITY];
		init();
	}

	/**
	 * Constructs a new <tt>HashLazyMap</tt> with the same mappings as the specified <tt>Map</tt>.
	 * The <tt>HashLazyMap</tt> is created with default load factor (0.75) and an initial capacity
	 * sufficient to hold the mappings in the specified <tt>Map</tt>.
	 * 
	 * @param m
	 *        the map whose mappings are to be placed in this map
	 * @throws NullPointerException
	 *         if the specified map is null
	 */
	public HashLazyMap( final Map<? extends K, ? extends V> m ) {
		this( Math.max( (int) (m.size() / HashLazyMap.DEFAULT_LOAD_FACTOR) + 1, HashLazyMap.DEFAULT_INITIAL_CAPACITY ),
				HashLazyMap.DEFAULT_LOAD_FACTOR );
		putAllForCreate( m );
	}

	/**
	 * Constructs a new <tt>HashLazyMap</tt> with the same mappings as the specified
	 * <tt>LazyMap</tt>. The <tt>HashLazyMap</tt> is created with default load factor (0.75) and an
	 * initial capacity sufficient to hold the mappings in the specified <tt>LazyMap</tt>.
	 * 
	 * @param m
	 *        the map whose mappings are to be placed in this map
	 * @throws NullPointerException
	 *         if the specified map is null
	 */
	public HashLazyMap( final LazyMap<? extends K, ? extends V> m ) {
		this( Math.max( (int) (m.size() / HashLazyMap.DEFAULT_LOAD_FACTOR) + 1, HashLazyMap.DEFAULT_INITIAL_CAPACITY ),
				HashLazyMap.DEFAULT_LOAD_FACTOR );
		putAllForCreate( m );
	}

	// internal utilities

	/**
	 * Initialization hook for subclasses. This method is called in all constructors and
	 * pseudo-constructors (clone, readObject) after HashLazyMap has been initialized but before any
	 * entries have been inserted. (In the absence of this method, readObject would require explicit
	 * knowledge of subclasses.)
	 */
	void init() {
		//
	}

	/**
	 * Applies a supplemental hash function to a given hashCode, which defends against poor quality
	 * hash functions. This is critical because HashLazyMap uses power-of-two length hash tables,
	 * that otherwise encounter collisions for hashCodes that do not differ in lower bits. Note:
	 * Null keys always map to hash 0, thus index 0.
	 */
	static int hash( final int hash ) {
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		int h = hash;
		h ^= h >>> 20 ^ h >>> 12;
		return h ^ h >>> 7 ^ h >>> 4;
	}

	/**
	 * Returns index for hash code h.
	 */
	static int indexFor(	final int h,
							final int length ) {
		return h & length - 1;
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 * 
	 * @return the number of key-value mappings in this map
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 * 
	 * @return <tt>true</tt> if this map contains no key-value mappings
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the value to which the specified key is mapped, or {@code null} if this map contains
	 * no mapping for the key.
	 * <p>
	 * More formally, if this map contains a mapping from a key {@code k} to a value {@code v} such
	 * that {@code (key==null ? k==null :
	 * key.equals(k))}, then this method returns {@code v}; otherwise it returns {@code null}.
	 * (There can be at most one such mapping.)
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that the map contains no
	 * mapping for the key; it's also possible that the map explicitly maps the key to {@code null}.
	 * The {@link #containsKey containsKey} operation may be used to distinguish these two cases.
	 * 
	 * @see #put(Object, Object)
	 */
	@Override
	public V get( final Object key ) {
		if (key == null) {
			return getForNullKey();
		}
		final int hash = HashLazyMap.hash( key.hashCode() );
		for (@SuppressWarnings( "unchecked" )
		Entry<K, V> e = table[HashLazyMap.indexFor( hash, table.length )]; e != null; e = e.next) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || key.equals( k ))) {
				return e.value;
			}
		}
		return null;
	}

	/**
	 * Offloaded version of get() to look up null keys. Null keys map to index 0. This null case is
	 * split out into separate methods for the sake of performance in the two most commonly used
	 * operations (get and put), but incorporated with conditionals in others.
	 */
	private V getForNullKey() {
		for (@SuppressWarnings( "unchecked" )
		Entry<K, V> e = table[0]; e != null; e = e.next) {
			if (e.key == null) {
				return e.value;
			}
		}
		return null;
	}

	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the specified key.
	 * 
	 * @param key
	 *        The key whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map contains a mapping for the specified key.
	 */
	@Override
	public boolean containsKey( final Object key ) {
		return getEntry( key ) != null;
	}

	/**
	 * Returns the entry associated with the specified key in the HashMap. Returns null if the
	 * HashLazyMap contains no mapping for the key.
	 */
	final Entry<K, V> getEntry( final Object key ) {
		final int hash = key == null ? 0 : HashLazyMap.hash( key.hashCode() );
		for (@SuppressWarnings( "unchecked" )
		Entry<K, V> e = table[HashLazyMap.indexFor( hash, table.length )]; e != null; e = e.next) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || key != null && key.equals( k ))) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Almacena en el mapa el valor especificado asociado a la clave especificada. Si el mapa
	 * contenía previamente un valor para la clave especificada el mapa no es modificado y la
	 * función retorna {@code false}.
	 * 
	 * @param key
	 *        key with which the specified value is to be associated
	 * @param value
	 *        value to be associated with the specified key
	 * @return {@code true} si la clave y el valor especificados son insertados en el mapa y
	 *         asociados correctamente. {@code false} en caso contrario.
	 */
	@Override
	public boolean put( final K key,
						final V value ) {
		if (key == null) {
			return putForNullKey( value );
		}
		final int hash = HashLazyMap.hash( key.hashCode() );
		final int i = HashLazyMap.indexFor( hash, table.length );
		for (@SuppressWarnings( "unchecked" )
		Entry<K, V> e = table[i]; e != null; e = e.next) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || key.equals( k ))) {
				return false;
			}
		}

		modCount++;
		addEntry( hash, key, value, i );
		return true;
	}

	/**
	 * Offloaded version of put for null keys
	 */
	private boolean putForNullKey( final V value ) {
		for (@SuppressWarnings( "unchecked" )
		Entry<K, V> e = table[0]; e != null; e = e.next) {
			if (e.key == null) {
				return false;
			}
		}
		modCount++;
		addEntry( 0, null, value, 0 );
		return true;
	}

	/**
	 * This method is used instead of put by constructors and pseudoconstructors (clone,
	 * readObject). It does not resize the table, check for comodification, etc. It calls
	 * createEntry rather than addEntry.
	 */
	private void putForCreate(	final K key,
								final V value ) {
		final int hash = key == null ? 0 : HashLazyMap.hash( key.hashCode() );
		final int i = HashLazyMap.indexFor( hash, table.length );

		/**
		 * Look for preexisting entry for key. This will never happen for clone or deserialize. It
		 * will only happen for construction if the input Map is a sorted map whose ordering is
		 * inconsistent w/ equals.
		 */
		for (@SuppressWarnings( "unchecked" )
		Entry<K, V> e = table[i]; e != null; e = e.next) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || key != null && key.equals( k ))) {
				e.value = value;
				return;
			}
		}

		createEntry( hash, key, value, i );
	}

	private void putAllForCreate( final LazyMap<? extends K, ? extends V> m ) {
		for (final LazyMap.Entry<? extends K, ? extends V> e : m.entrySet()) {
			putForCreate( e.getKey(), e.getValue() );
		}
	}

	private void putAllForCreate( final Map<? extends K, ? extends V> m ) {
		for (final Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			putForCreate( e.getKey(), e.getValue() );
		}
	}

	/**
	 * Rehashes the contents of this map into a new array with a larger capacity. This method is
	 * called automatically when the number of keys in this map reaches its threshold. If current
	 * capacity is MAXIMUM_CAPACITY, this method does not resize the map, but sets threshold to
	 * Integer.MAX_VALUE. This has the effect of preventing future calls.
	 * 
	 * @param newCapacity
	 *        the new capacity, MUST be a power of two; must be greater than current capacity unless
	 *        current capacity is MAXIMUM_CAPACITY (in which case value is irrelevant).
	 */
	void resize( final int newCapacity ) {
		@SuppressWarnings( "unchecked" )
		final Entry<K, V>[] oldTable = table;
		final int oldCapacity = oldTable.length;
		if (oldCapacity == HashLazyMap.MAXIMUM_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return;
		}

		@SuppressWarnings( "unchecked" )
		final Entry<K, V>[] newTable = new Entry[newCapacity];
		transfer( newTable );
		table = newTable;
		threshold = (int) (newCapacity * theLoadFactor);
	}

	/**
	 * Transfers all entries from current table to newTable.
	 */
	void transfer( final Entry<K, V>[] newTable ) {
		@SuppressWarnings( "unchecked" )
		final Entry<K, V>[] src = table;
		final int newCapacity = newTable.length;
		for (int j = 0; j < src.length; j++) {
			Entry<K, V> e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					final Entry<K, V> next = e.next;
					final int i = HashLazyMap.indexFor( e.hash, newCapacity );
					e.next = newTable[i];
					newTable[i] = e;
					e = next;
				}
				while (e != null);
			}
		}
	}

	/**
	 * Copies all of the mappings from the specified map to this map. These mappings will replace
	 * any mappings that this map had for any of the keys currently in the specified map.
	 * 
	 * @param m
	 *        mappings to be stored in this map
	 * @throws NullPointerException
	 *         if the specified map is null
	 */
	@Override
	public void putAll( final Map<? extends K, ? extends V> m ) {
		final int numKeysToBeAdded = m.size();
		if (numKeysToBeAdded == 0) {
			return;
		}

		/*
		 * Expand the map if the map if the number of mappings to be added is greater than or equal
		 * to threshold. This is conservative; the obvious condition is (m.size() + size) >=
		 * threshold, but this condition could result in a map with twice the appropriate capacity,
		 * if the keys to be added overlap with the keys already in this map. By using the
		 * conservative calculation, we subject ourself to at most one extra resize.
		 */
		if (numKeysToBeAdded > threshold) {
			int targetCapacity = (int) (numKeysToBeAdded / theLoadFactor + 1);
			if (targetCapacity > HashLazyMap.MAXIMUM_CAPACITY) {
				targetCapacity = HashLazyMap.MAXIMUM_CAPACITY;
			}
			int newCapacity = table.length;
			while (newCapacity < targetCapacity) {
				newCapacity <<= 1;
			}
			if (newCapacity > table.length) {
				resize( newCapacity );
			}
		}

		for (final java.util.Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			put( e.getKey(), e.getValue() );
		}
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * 
	 * @param key
	 *        key whose mapping is to be removed from the map
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no
	 *         mapping for <tt>key</tt>. (A <tt>null</tt> return can also indicate that the map
	 *         previously associated <tt>null</tt> with <tt>key</tt>.)
	 */
	@Override
	public V remove( final Object key ) {
		final Entry<K, V> e = removeEntryForKey( key );
		return e == null ? null : e.value;
	}

	/**
	 * Removes and returns the entry associated with the specified key in the HashMap. Returns null
	 * if the HashMap contains no mapping for this key.
	 */
	final Entry<K, V> removeEntryForKey( final Object key ) {
		final int hash = key == null ? 0 : HashLazyMap.hash( key.hashCode() );
		final int i = HashLazyMap.indexFor( hash, table.length );
		@SuppressWarnings( "unchecked" )
		Entry<K, V> prev = table[i];
		Entry<K, V> e = prev;

		while (e != null) {
			final Entry<K, V> next = e.next;
			Object k;
			if (e.hash == hash && ((k = e.key) == key || key != null && key.equals( k ))) {
				modCount++;
				size--;
				if (prev == e) {
					table[i] = next;
				}
				else {
					prev.next = next;
				}
				e.recordRemoval( this );
				return e;
			}
			prev = e;
			e = next;
		}

		return e;
	}

	/**
	 * Special version of remove for EntrySet.
	 */
	final Entry<K, V> removeMapping( final Object o ) {
		if (!(o instanceof LazyMap.Entry)) {
			return null;
		}

		@SuppressWarnings( "unchecked" )
		final LazyMap.Entry<K, V> entry = (LazyMap.Entry<K, V>) o;
		final Object key = entry.getKey();
		final int hash = key == null ? 0 : HashLazyMap.hash( key.hashCode() );
		final int i = HashLazyMap.indexFor( hash, table.length );
		@SuppressWarnings( "unchecked" )
		Entry<K, V> prev = table[i];
		Entry<K, V> e = prev;

		while (e != null) {
			final Entry<K, V> next = e.next;
			if (e.hash == hash && e.equals( entry )) {
				modCount++;
				size--;
				if (prev == e) {
					table[i] = next;
				}
				else {
					prev.next = next;
				}
				e.recordRemoval( this );
				return e;
			}
			prev = e;
			e = next;
		}

		return e;
	}

	/**
	 * Removes all of the mappings from this map. The map will be empty after this call returns.
	 */
	@Override
	public void clear() {
		modCount++;
		@SuppressWarnings( "unchecked" )
		final Entry<K, V>[] tab = table;
		for (int i = 0; i < tab.length; i++) {
			tab[i] = null;
		}
		size = 0;
	}

	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the specified value.
	 * 
	 * @param value
	 *        value whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map maps one or more keys to the specified value
	 */
	@Override
	public boolean containsValue( final Object value ) {
		if (value == null) {
			return containsNullValue();
		}

		@SuppressWarnings( "unchecked" )
		final Entry<K, V>[] tab = table;
		for (final Entry<K, V> element : tab) {
			for (Entry<K, V> e = element; e != null; e = e.next) {
				if (value.equals( e.value )) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Special-case code for containsValue with null argument
	 */
	private boolean containsNullValue() {
		@SuppressWarnings( "unchecked" )
		final Entry<K, V>[] tab = table;
		for (final Entry<K, V> element : tab) {
			for (Entry<K, V> e = element; e != null; e = e.next) {
				if (e.value == null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a shallow copy of this <tt>HashLazyMap</tt> instance: the keys and values themselves
	 * are not cloned.
	 * 
	 * @return a shallow copy of this map
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public Object clone() {
		HashLazyMap<K, V> result = null;
		try {
			result = (HashLazyMap<K, V>) super.clone();
			result.table = new Entry[table.length];
			result.entrySet = null;
			result.modCount = 0;
			result.size = 0;
			result.init();
			result.putAllForCreate( this );
		}
		catch (final CloneNotSupportedException e) {
			assert false;
		}

		return result;
	}

	static class Entry<K, V>
			implements
				LazyMap.Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;
		final int hash;

		/**
		 * Creates new entry.
		 */
		Entry(	final int h,
				final K k,
				final V v,
				final Entry<K, V> n ) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		@Override
		public final K getKey() {
			return key;
		}

		@Override
		public final V getValue() {
			return value;
		}

		@Override
		public final V setValue( final V newValue ) {
			final V oldValue = value;
			value = newValue;
			return oldValue;
		}

		@Override
		public final boolean equals( final Object o ) {
			if (!(o instanceof LazyMap.Entry)) {
				return false;
			}
			@SuppressWarnings( "unchecked" )
			final LazyMap.Entry<K, V> e = (LazyMap.Entry<K, V>) o;
			final Object k1 = getKey();
			final Object k2 = e.getKey();
			if (k1 == k2 || k1 != null && k1.equals( k2 )) {
				final Object v1 = getValue();
				final Object v2 = e.getValue();
				if (v1 == v2 || v1 != null && v1.equals( v2 )) {
					return true;
				}
			}
			return false;
		}

		@Override
		public final int hashCode() {
			return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
		}

		@Override
		public final String toString() {
			return getKey() + "=" + getValue(); //$NON-NLS-1$
		}

		/**
		 * This method is invoked whenever the value in an entry is overwritten by an invocation of
		 * put(k,v) for a key k that's already in the HashMap.
		 */
		void recordAccess( @SuppressWarnings( "unused" ) final HashLazyMap<K, V> hashLazyMap ) {
			//
		}

		/**
		 * This method is invoked whenever the entry is removed from the table.
		 */
		void recordRemoval( @SuppressWarnings( "unused" ) final HashLazyMap<K, V> hashLazyMap ) {
			//
		}
	}

	/**
	 * Adds a new entry with the specified key, value and hash code to the specified bucket. It is
	 * the responsibility of this method to resize the table if appropriate. Subclass overrides this
	 * to alter the behavior of put method.
	 */
	void addEntry(	final int hash,
					final K key,
					final V value,
					final int bucketIndex ) {
		@SuppressWarnings( "unchecked" )
		final Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>( hash, key, value, e );
		if (size++ >= threshold) {
			resize( 2 * table.length );
		}
	}

	/**
	 * Like addEntry except that this version is used when creating entries as part of Map
	 * construction or "pseudo-construction" (cloning, deserialization). This version needn't worry
	 * about resizing the table. Subclass overrides this to alter the behavior of HashMap(Map),
	 * clone, and readObject.
	 */
	void createEntry(	final int hash,
						final K key,
						final V value,
						final int bucketIndex ) {
		@SuppressWarnings( "unchecked" )
		final Entry<K, V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K, V>( hash, key, value, e );
		size++;
	}

	private abstract class HashIterator<E>
			implements
				Iterator<E> {
		Entry<K, V> next; // next entry to return
		int expectedModCount; // For fast-fail
		int index; // current slot
		Entry<K, V> current; // current entry

		HashIterator() {
			expectedModCount = modCount;
			if (size > 0) { // advance to first entry
				@SuppressWarnings( "unchecked" )
				final Entry<K, V>[] t = table;
				while (index < t.length && (next = t[index++]) == null) {
					//
				}
			}
		}

		@Override
		public final boolean hasNext() {
			return next != null;
		}

		final Entry<K, V> nextEntry() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			final Entry<K, V> e = next;
			if (e == null) {
				throw new NoSuchElementException();
			}

			if ((next = e.next) == null) {
				@SuppressWarnings( "unchecked" )
				final Entry<K, V>[] t = table;
				while (index < t.length && (next = t[index++]) == null) {
					//
				}
			}
			current = e;
			return e;
		}

		@Override
		public void remove() {
			if (current == null) {
				throw new IllegalStateException();
			}
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			final Object k = current.key;
			current = null;
			HashLazyMap.this.removeEntryForKey( k );
			expectedModCount = modCount;
		}

	}

	private final class ValueIterator
			extends
				HashIterator<V> {
		/**
		 *
		 */
		public ValueIterator() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public V next() {
			return nextEntry().value;
		}
	}

	private final class KeyIterator
			extends
				HashIterator<K> {
		/**
		 *
		 */
		public KeyIterator() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public K next() {
			return nextEntry().getKey();
		}
	}

	private final class EntryIterator
			extends
				HashIterator<LazyMap.Entry<K, V>> {
		/**
		 *
		 */
		public EntryIterator() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public LazyMap.Entry<K, V> next() {
			return nextEntry();
		}
	}

	// Subclass overrides these to alter behavior of views' iterator() method
	Iterator<K> newKeyIterator() {
		return new KeyIterator();
	}

	Iterator<V> newValueIterator() {
		return new ValueIterator();
	}

	Iterator<LazyMap.Entry<K, V>> newEntryIterator() {
		return new EntryIterator();
	}

	// Views

	private transient Set<LazyMap.Entry<K, V>> entrySet = null;

	/**
	 * Each of these fields are initialized to contain an instance of the appropriate view the first
	 * time this view is requested. The views are stateless, so there's no reason to create more
	 * than one of each.
	 */
	transient volatile Set<K> keySet = null;
	transient volatile Collection<V> values = null;

	/**
	 * Returns a {@link Set} view of the keys contained in this map. The set is backed by the map,
	 * so changes to the map are reflected in the set, and vice-versa. If the map is modified while
	 * an iteration over the set is in progress (except through the iterator's own <tt>remove</tt>
	 * operation), the results of the iteration are undefined. The set supports element removal,
	 * which removes the corresponding mapping from the map, via the <tt>Iterator.remove</tt>,
	 * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt> operations.
	 * It does not support the <tt>add</tt> or <tt>addAll</tt> operations.
	 */
	@Override
	public Set<K> keySet() {
		final Set<K> ks = keySet;
		return ks != null ? ks : (keySet = new KeySet());
	}

	private final class KeySet
			extends
				AbstractSet<K> {
		/**
		 *
		 */
		public KeySet() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Iterator<K> iterator() {
			return newKeyIterator();
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean contains( final Object o ) {
			return containsKey( o );
		}

		@Override
		public boolean remove( final Object o ) {
			return HashLazyMap.this.removeEntryForKey( o ) != null;
		}

		@Override
		public void clear() {
			HashLazyMap.this.clear();
		}
	}

	/**
	 * Returns a {@link Collection} view of the values contained in this map. The collection is
	 * backed by the map, so changes to the map are reflected in the collection, and vice-versa. If
	 * the map is modified while an iteration over the collection is in progress (except through the
	 * iterator's own <tt>remove</tt> operation), the results of the iteration are undefined. The
	 * collection supports element removal, which removes the corresponding mapping from the map,
	 * via the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt> and <tt>clear</tt> operations. It does not support the <tt>add</tt> or
	 * <tt>addAll</tt> operations.
	 */
	@Override
	public Collection<V> values() {
		final Collection<V> vs = values;
		return vs != null ? vs : (values = new Values());
	}

	private final class Values
			extends
				AbstractCollection<V> {
		/**
		 *
		 */
		public Values() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Iterator<V> iterator() {
			return newValueIterator();
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean contains( final Object o ) {
			return containsValue( o );
		}

		@Override
		public void clear() {
			HashLazyMap.this.clear();
		}
	}

	/**
	 * Returns a {@link Set} view of the mappings contained in this map. The set is backed by the
	 * map, so changes to the map are reflected in the set, and vice-versa. If the map is modified
	 * while an iteration over the set is in progress (except through the iterator's own
	 * <tt>remove</tt> operation, or through the <tt>setValue</tt> operation on a map entry returned
	 * by the iterator) the results of the iteration are undefined. The set supports element
	 * removal, which removes the corresponding mapping from the map, via the
	 * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
	 * <tt>clear</tt> operations. It does not support the <tt>add</tt> or <tt>addAll</tt>
	 * operations.
	 * 
	 * @return a set view of the mappings contained in this map
	 */
	@Override
	public Set<LazyMap.Entry<K, V>> entrySet() {
		return entrySet0();
	}

	private Set<LazyMap.Entry<K, V>> entrySet0() {
		final Set<LazyMap.Entry<K, V>> es = entrySet;
		return es != null ? es : (entrySet = new EntrySet());
	}

	private final class EntrySet
			extends
				AbstractSet<LazyMap.Entry<K, V>> {
		/**
		 *
		 */
		public EntrySet() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Iterator<LazyMap.Entry<K, V>> iterator() {
			return newEntryIterator();
		}

		@Override
		public boolean contains( final Object o ) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			@SuppressWarnings( "unchecked" )
			final LazyMap.Entry<K, V> e = (LazyMap.Entry<K, V>) o;
			final Entry<K, V> candidate = getEntry( e.getKey() );
			return candidate != null && candidate.equals( e );
		}

		@Override
		public boolean remove( final Object o ) {
			return removeMapping( o ) != null;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public void clear() {
			HashLazyMap.this.clear();
		}
	}

	/**
	 * Save the state of the <tt>HashLazyMap</tt> instance to a stream (i.e., serialize it).
	 * 
	 * @serialData The <i>capacity</i> of the HashLazyMap (the length of the bucket array) is
	 *             emitted (int), followed by the <i>size</i> (an int, the number of key-value
	 *             mappings), followed by the key (Object) and value (Object) for each key-value
	 *             mapping. The key-value mappings are emitted in no particular order.
	 */
	private void writeObject( final java.io.ObjectOutputStream s )
			throws IOException {
		final Iterator<LazyMap.Entry<K, V>> i = size > 0 ? entrySet0().iterator() : null;

		// Write out the threshold, loadfactor, and any hidden stuff
		s.defaultWriteObject();

		// Write out number of buckets
		s.writeInt( table.length );

		// Write out size (number of Mappings)
		s.writeInt( size );

		// Write out keys and values (alternating)
		if (i != null) {
			while (i.hasNext()) {
				final LazyMap.Entry<K, V> e = i.next();
				s.writeObject( e.getKey() );
				s.writeObject( e.getValue() );
			}
		}
	}

	private static final long serialVersionUID = 362498820763181265L;

	/**
	 * Reconstitute the <tt>HashLazyMap</tt> instance from a stream (i.e., deserialize it).
	 */
	private void readObject( final java.io.ObjectInputStream s )
			throws IOException,
				ClassNotFoundException {
		// Read in the threshold, loadfactor, and any hidden stuff
		s.defaultReadObject();

		// Read in number of buckets and allocate the bucket array;
		final int numBuckets = s.readInt();
		table = new Entry[numBuckets];

		init(); // Give subclass a chance to do its thing.

		// Read in size (number of Mappings)
		@SuppressWarnings( "hiding" )
		final int size = s.readInt();

		// Read the keys and values, and put the mappings in the HashLazyMap
		for (int i = 0; i < size; i++) {
			@SuppressWarnings( "unchecked" )
			final K key = (K) s.readObject();
			@SuppressWarnings( "unchecked" )
			final V value = (V) s.readObject();
			putForCreate( key, value );
		}
	}

	// These methods are used when serializing HashSets
	int capacity() {
		return table.length;
	}

	float loadFactor() {
		return theLoadFactor;
	}
}
