/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio5;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Interfaz inspirado en {@link Map} en el que, a diferencia de este, al añadir un nuevo elemento,
 * si este ya existe no se reemplaza el antiguo elemento. Referirse, por tanto, a la docuementación
 * de esta para una visión general.
 * 
 * @param <K>
 *        the type of keys maintained by this map
 * @param <V>
 *        the type of mapped values
 * @author Alberto Dominguez Matamoros
 * @see Map
 */
@SuppressWarnings( "PMD.TooManyMethods" )
public interface LazyMap<K, V> {
	// Query Operations

	/**
	 * Returns the number of key-value mappings in this map. If the map contains more than
	 * <tt>Integer.MAX_VALUE</tt> elements, returns <tt>Integer.MAX_VALUE</tt>.
	 * 
	 * @return the number of key-value mappings in this map
	 */
	int size();

	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 * 
	 * @return <tt>true</tt> if this map contains no key-value mappings
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the specified key. More formally,
	 * returns <tt>true</tt> if and only if this map contains a mapping for a key <tt>k</tt> such
	 * that <tt>(key==null ? k==null : key.equals(k))</tt>. (There can be at most one such mapping.)
	 * 
	 * @param key
	 *        key whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map contains a mapping for the specified key
	 * @throws ClassCastException
	 *         if the key is of an inappropriate type for this map (optional)
	 * @throws NullPointerException
	 *         if the specified key is null and this map does not permit null keys (optional)
	 */
	boolean containsKey( Object key );

	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the specified value. More
	 * formally, returns <tt>true</tt> if and only if this map contains at least one mapping to a
	 * value <tt>v</tt> such that <tt>(value==null ? v==null : value.equals(v))</tt>. This operation
	 * will probably require time linear in the map size for most implementations of the
	 * <tt>LazyMap</tt> interface.
	 * 
	 * @param value
	 *        value whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map maps one or more keys to the specified value
	 * @throws ClassCastException
	 *         if the value is of an inappropriate type for this map (optional)
	 * @throws NullPointerException
	 *         if the specified value is null and this map does not permit null values (optional)
	 */
	boolean containsValue( Object value );

	/**
	 * Returns the value to which the specified key is mapped, or {@code null} if this map contains
	 * no mapping for the key.
	 * <p>
	 * More formally, if this map contains a mapping from a key {@code k} to a value {@code v} such
	 * that {@code (key==null ? k==null :
	 * key.equals(k))}, then this method returns {@code v}; otherwise it returns {@code null}.
	 * (There can be at most one such mapping.)
	 * <p>
	 * If this map permits null values, then a return value of {@code null} does not
	 * <i>necessarily</i> indicate that the map contains no mapping for the key; it's also possible
	 * that the map explicitly maps the key to {@code null}. The {@link #containsKey containsKey}
	 * operation may be used to distinguish these two cases.
	 * 
	 * @param key
	 *        the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or {@code null} if this map contains
	 *         no mapping for the key
	 * @throws ClassCastException
	 *         if the key is of an inappropriate type for this map (optional)
	 * @throws NullPointerException
	 *         if the specified key is null and this map does not permit null keys (optional)
	 */
	V get( Object key );

	// Modification Operations

	/**
	 * Asocia el valor especificado con la clave especificada para este mapa (operación opcional). A
	 * diferencia del {@link Map} original, si el mapa ya contiene un elemento asociado a la clave
	 * especificada <strong>este no es sustituido</strong> por el nuevo. (A map <tt>m</tt> is said
	 * to contain a mapping for a key <tt>k</tt> if and only if {@link #containsKey(Object)
	 * m.containsKey(k)} would return <tt>true</tt>.)
	 * 
	 * @param key
	 *        key with which the specified value is to be associated
	 * @param value
	 *        value to be associated with the specified key
	 * @return {@code true} si el valor especificado es almacenado en el mapa asociado a la clave
	 *         especificada. {@code false} en caso contrario.
	 * @throws UnsupportedOperationException
	 *         if the <tt>put</tt> operation is not supported by this map
	 * @throws ClassCastException
	 *         if the class of the specified key or value prevents it from being stored in this map
	 * @throws NullPointerException
	 *         if the specified key or value is null and this map does not permit null keys or
	 *         values
	 * @throws IllegalArgumentException
	 *         if some property of the specified key or value prevents it from being stored in this
	 *         map
	 */
	boolean put(	K key,
					V value );

	/**
	 * Removes the mapping for a key from this map if it is present (optional operation). More
	 * formally, if this map contains a mapping from key <tt>k</tt> to value <tt>v</tt> such that
	 * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping is removed. (The map can
	 * contain at most one such mapping.)
	 * <p>
	 * Returns the value to which this map previously associated the key, or <tt>null</tt> if the
	 * map contained no mapping for the key.
	 * <p>
	 * If this map permits null values, then a return value of <tt>null</tt> does not
	 * <i>necessarily</i> indicate that the map contained no mapping for the key; it's also possible
	 * that the map explicitly mapped the key to <tt>null</tt>.
	 * <p>
	 * The map will not contain a mapping for the specified key once the call returns.
	 * 
	 * @param key
	 *        key whose mapping is to be removed from the map
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no
	 *         mapping for <tt>key</tt>.
	 * @throws UnsupportedOperationException
	 *         if the <tt>remove</tt> operation is not supported by this map
	 * @throws ClassCastException
	 *         if the key is of an inappropriate type for this map (optional)
	 * @throws NullPointerException
	 *         if the specified key is null and this map does not permit null keys (optional)
	 */
	V remove( Object key );

	// Bulk Operations

	/**
	 * Copies all of the mappings from the specified map to this map (optional operation). The
	 * effect of this call is equivalent to that of calling {@link #put(Object,Object) put(k, v)} on
	 * this map once for each mapping from key <tt>k</tt> to value <tt>v</tt> in the specified map.
	 * The behavior of this operation is undefined if the specified map is modified while the
	 * operation is in progress.
	 * <p>
	 * A diferencia del {@link Map} original, si alguna de las claves existentes en el mapa
	 * especificado ya existe en este mapa <strong>el elemento no es sustituido</strong>.
	 * 
	 * @param aMap
	 *        mappings to be stored in this map
	 * @throws UnsupportedOperationException
	 *         if the <tt>putAll</tt> operation is not supported by this map
	 * @throws ClassCastException
	 *         if the class of a key or value in the specified map prevents it from being stored in
	 *         this map
	 * @throws NullPointerException
	 *         if the specified map is null, or if this map does not permit null keys or values, and
	 *         the specified map contains null keys or values
	 * @throws IllegalArgumentException
	 *         if some property of a key or value in the specified map prevents it from being stored
	 *         in this map
	 */
	void putAll( Map<? extends K, ? extends V> aMap );

	/**
	 * Removes all of the mappings from this map (optional operation). The map will be empty after
	 * this call returns.
	 * 
	 * @throws UnsupportedOperationException
	 *         if the <tt>clear</tt> operation is not supported by this map
	 */
	void clear();

	// Views

	/**
	 * Returns a {@link Set} view of the keys contained in this map. The set is backed by the map,
	 * so changes to the map are reflected in the set, and vice-versa. If the map is modified while
	 * an iteration over the set is in progress (except through the iterator's own <tt>remove</tt>
	 * operation), the results of the iteration are undefined. The set supports element removal,
	 * which removes the corresponding mapping from the map, via the <tt>Iterator.remove</tt>,
	 * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt> operations.
	 * It does not support the <tt>add</tt> or <tt>addAll</tt> operations.
	 * 
	 * @return a set view of the keys contained in this map
	 */
	Set<K> keySet();

	/**
	 * Returns a {@link Collection} view of the values contained in this map. The collection is
	 * backed by the map, so changes to the map are reflected in the collection, and vice-versa. If
	 * the map is modified while an iteration over the collection is in progress (except through the
	 * iterator's own <tt>remove</tt> operation), the results of the iteration are undefined. The
	 * collection supports element removal, which removes the corresponding mapping from the map,
	 * via the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt> and <tt>clear</tt> operations. It does not support the <tt>add</tt> or
	 * <tt>addAll</tt> operations.
	 * 
	 * @return a collection view of the values contained in this map
	 */
	Collection<V> values();

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
	Set<LazyMap.Entry<K, V>> entrySet();

	/**
	 * A map entry (key-value pair). The <tt>Map.entrySet</tt> method returns a collection-view of
	 * the map, whose elements are of this class. The <i>only</i> way to obtain a reference to a map
	 * entry is from the iterator of this collection-view. These <tt>LazyMap.Entry</tt> objects are
	 * valid <i>only</i> for the duration of the iteration; more formally, the behavior of a map
	 * entry is undefined if the backing map has been modified after the entry was returned by the
	 * iterator, except through the <tt>setValue</tt> operation on the map entry.
	 * 
	 * @param <K>
	 *        the type of keys
	 * @param <V>
	 *        the type of values
	 * @see LazyMap#entrySet()
	 */
	interface Entry<K, V> {
		/**
		 * Returns the key corresponding to this entry.
		 * 
		 * @return the key corresponding to this entry
		 * @throws IllegalStateException
		 *         implementations may, but are not required to, throw this exception if the entry
		 *         has been removed from the backing map.
		 */
		K getKey();

		/**
		 * Returns the value corresponding to this entry. If the mapping has been removed from the
		 * backing map (by the iterator's <tt>remove</tt> operation), the results of this call are
		 * undefined.
		 * 
		 * @return the value corresponding to this entry
		 * @throws IllegalStateException
		 *         implementations may, but are not required to, throw this exception if the entry
		 *         has been removed from the backing map.
		 */
		V getValue();

		/**
		 * Replaces the value corresponding to this entry with the specified value (optional
		 * operation). (Writes through to the map.) The behavior of this call is undefined if the
		 * mapping has already been removed from the map (by the iterator's <tt>remove</tt>
		 * operation).
		 * 
		 * @param value
		 *        new value to be stored in this entry
		 * @return old value corresponding to the entry
		 * @throws UnsupportedOperationException
		 *         if the <tt>put</tt> operation is not supported by the backing map
		 * @throws ClassCastException
		 *         if the class of the specified value prevents it from being stored in the backing
		 *         map
		 * @throws NullPointerException
		 *         if the backing map does not permit null values, and the specified value is null
		 * @throws IllegalArgumentException
		 *         if some property of this value prevents it from being stored in the backing map
		 * @throws IllegalStateException
		 *         implementations may, but are not required to, throw this exception if the entry
		 *         has been removed from the backing map.
		 */
		V setValue( V value );

		/**
		 * Compares the specified object with this entry for equality. Returns <tt>true</tt> if the
		 * given object is also a map entry and the two entries represent the same mapping. More
		 * formally, two entries <tt>e1</tt> and <tt>e2</tt> represent the same mapping if
		 * 
		 * <pre>
		 * (e1.getKey() == null ? e2.getKey() == null : e1.getKey().equals( e2.getKey() ))
		 * 		&amp;&amp; (e1.getValue() == null ? e2.getValue() == null : e1.getValue().equals( e2.getValue() ))
		 * </pre>
		 * 
		 * This ensures that the <tt>equals</tt> method works properly across different
		 * implementations of the <tt>Map.Entry</tt> interface.
		 * 
		 * @param anObject
		 *        object to be compared for equality with this map entry
		 * @return <tt>true</tt> if the specified object is equal to this map entry
		 */
		@Override
		boolean equals( Object anObject );

		/**
		 * Returns the hash code value for this map entry. The hash code of a map entry <tt>e</tt>
		 * is defined to be:
		 * 
		 * <pre>
		 * (e.getKey() == null ? 0 : e.getKey().hashCode()) &circ; (e.getValue() == null ? 0 : e.getValue().hashCode())
		 * </pre>
		 * 
		 * This ensures that <tt>e1.equals(e2)</tt> implies that
		 * <tt>e1.hashCode()==e2.hashCode()</tt> for any two Entries <tt>e1</tt> and <tt>e2</tt>, as
		 * required by the general contract of <tt>Object.hashCode</tt>.
		 * 
		 * @return the hash code value for this map entry
		 * @see Object#hashCode()
		 * @see Object#equals(Object)
		 * @see #equals(Object)
		 */
		@Override
		int hashCode();
	}

	// Comparison and hashing

	/**
	 * Compares the specified object with this map for equality. Returns <tt>true</tt> if the given
	 * object is also a map and the two maps represent the same mappings. More formally, two maps
	 * <tt>m1</tt> and <tt>m2</tt> represent the same mappings if
	 * <tt>m1.entrySet().equals(m2.entrySet())</tt>. This ensures that the <tt>equals</tt> method
	 * works properly across different implementations of the <tt>Map</tt> interface.
	 * 
	 * @param anObject
	 *        object to be compared for equality with this map
	 * @return <tt>true</tt> if the specified object is equal to this map
	 */
	@Override
	boolean equals( Object anObject );

	/**
	 * Returns the hash code value for this map. The hash code of a map is defined to be the sum of
	 * the hash codes of each entry in the map's <tt>entrySet()</tt> view. This ensures that
	 * <tt>m1.equals(m2)</tt> implies that <tt>m1.hashCode()==m2.hashCode()</tt> for any two maps
	 * <tt>m1</tt> and <tt>m2</tt>, as required by the general contract of {@link Object#hashCode}.
	 * 
	 * @return the hash code value for this map
	 * @see LazyMap.Entry#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	@Override
	int hashCode();
}
