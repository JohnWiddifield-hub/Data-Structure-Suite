package edu.ncsu.csc316.dsa.priority_queue;

/**
 * This is the interface for creating a priority queue
 * @author John Widdifield and ncsu staff
 *
 * @param <K> generic key
 * @param <V> generic value
 */
public interface PriorityQueue<K, V> {

	/**
	 * This interface is for an entry into the priority queue
	 * @author John Widdifield and ncsu staff
	 *
	 * @param <K> generic key
	 * @param <V> generic value
	 */
	interface Entry<K, V> {
		/**
		 * This will get the key from the entry
		 * @return the key
		 */
		K getKey();
		
		/**
		 * This will get the value from the entry
		 * @return the value
		 */
		V getValue();
	}
	
	/**
	 * This will insert an entry into the priority queue
	 * @param key key to insert
	 * @param value key to insert
	 * @return entry which was created and inserted
	 */
	Entry<K, V> insert(K key, V value);
	
	/**
	 * This will get the minimum key in the queue
	 * @return entry with the minimum priority
	 */
	Entry<K, V> min();
	
	/**
	 * This will delete the minimum key in the queue
	 * @return entry with the minimum priority which was deleted
	 */
	Entry<K, V> deleteMin();
	
	/**
	 * This will tell you how many entrys are in the queue
	 * @return size of the queue
	 */
	int size();
	
	/**
	 * This will tell you if the priority queue is em[pty or not
	 * @return true if is empty false otherwise
	 */
	boolean isEmpty();
}