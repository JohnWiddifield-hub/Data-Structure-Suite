package edu.ncsu.csc316.dsa.map;

/**
 * This is the interface to be used when creating a map of any type.
 * @author John Widdifield and NCSU Staff
 *
 * @param <K>	Generic param for keys
 * @param <V>	Generic param for values
 */
public interface Map<K, V> extends Iterable<K> {
	/**
	 * This will get you the set of entries inside the map.
	 * 
	 * @return An interable set of entries of keys and values.
	 */
	Iterable<Entry<K, V>> entrySet();
	
	/**
	 * This will retrieve a value from a map based on the searched key
	 * @param key	Key to search for the value for
	 * @return 	Value associated with the given key
	 */
	V get(K key);
	/**
	 * This will let you know if the map is empty or not
	 * 
	 * @return True if the map is empty meaning it contains no entries, false if not
	 */
	boolean isEmpty();
	/**
	 * This will add an entry to your map
	 * @param key	Key for the value you wish to add
	 * @param value	Value to be associated with the given key
	 * @return	The value which was replaced by the new one, or null if no value was replaced
	 */
	V put(K key, V value);
	/**
	 * This will remove an entry from the map
	 * @param key Key of the entry you wish to remove
	 * @return The value associated with the key in which you removed
	 */
	V remove(K key);
	/**
	 * This will tell you how many entries are stored within your map
	 * @return The number of entries inside the map.
	 */
	int size();
	/**
	 * This will get you an iterable set of values contained within the map
	 * @return	Iterable set of values contained within the map
	 */
	Iterable<V> values();
	
	/**
	 * This is an interface for an entry object (the component parts of a map)
	 * @author John Widdifield
	 *
	 * @param <K>	Generic param for keys
	 * @param <V>	Generic param for values
	 */
	interface Entry<K, V> {
		/**
		 * This will get the key of the entry
		 * @return The key of the entry
		 */
		K getKey();
		/**
		 * This will get the value of the entry
		 * @return The value of the entry
		 */
		V getValue();
		/**
		 * This will set the key of the entry
		 * @param key Key you wish to set
		 * @return The original key of the entry
		 */
		K setKey(K key);
		/**
		 * This will set the value of the entry
		 * @param value value you wish to set
		 * @return The original value of the entry
		 */
		V setValue(V value);
	}
}