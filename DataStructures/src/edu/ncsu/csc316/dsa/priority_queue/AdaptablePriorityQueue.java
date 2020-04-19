package edu.ncsu.csc316.dsa.priority_queue;

/**
 * This is the interface for an adaptable priority queue
 * @author John WIddifeld
 *
 * @param <K> generic key 
 * @param <V> Generic value
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * This method will remove an entry from the priority queue
	 * @param entry entry to remove
	 */
	void remove(Entry<K, V> entry);
	
	/**
	 * This with replace a entry old key with a new key
	 * @param entry entry with the new key
	 * @param key new key to set the entry to
	 */
	void replaceKey(Entry<K, V> entry, K key);
	
	/**
	 * This with replace a entry old value with a new value
	 * @param entry entry with the new value
	 * @param value new value to set the entry to
	 */
	void replaceValue(Entry<K, V> entry, V value);	
}
