package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * This represents an abstract priority queue implementation
 * It was implemented using the sixth edition of Data Structures and 
 *   Algorithms in Java by Michael T. Goodrich 
 * @author John Widdiifeld and ncsu staff
 *
 * @param <K> Generic key 
 * @param <V> generic value
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	private Comparator<K> comparator;

	/**
	 * This will construct a priority queue with a custom comparator
	 * @param c comparator to use
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}
	
	/**
	 * This will set the comparator for a priority queue
	 * @param c comparator to set to
	 */
	private void setComparator(Comparator<K> c) {
		if(c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * This class represents the natural comparator for priority
	 * @author John Widdifield and ncsu staff
	 *
	 */
	public class NaturalOrder implements Comparator<K> {
		
		/**
		 * This will compare two keys
		 * @param first first key to compare
		 * @param second second key to compare
		 * @return 0 if they are the same 1 if first is lesser -1 if first is greater
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * This will compare two keys
	 * @param data1 first key to compare
	 * @param data2 second key to compare
	 * @return 0 if they are equal 1 if 1 is less than 2, -1 otherwise
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * This class is for entrys into the priority queue
	 * @author John Widdifield and ncsu staff
	 *
	 * @param <K> generic key	
	 * @param <V> generic value
	 */
    // Make sure you import PriorityQueue.Entry and NOT Map.Entry!
	public static class PQEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		/**
		 * This will construct an entry
		 * @param key key to create
		 * @param value value to create
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * This will set an entrys key
		 * @param key key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * This will set an entrys value
		 * @param value value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
	/**
	 * This will create an entry into the priority queue
	 * @param key key for the entry
	 * @param value value for the entry
	 * @return entry which was created
	 */
    // factory method for constructing a new priority queue entry object
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}