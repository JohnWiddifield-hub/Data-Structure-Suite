package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;

/**
 * This class is for use when creating a SortedMap object.  It allows for
 * custom comparators
 * @author John Widdifield and NCSU Staff
 *
 * @param <K>	Generic key parameter
 * @param <V>	Generic value paramter v
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	private Comparator<K> compare;

	/**
	 * This constructs an AbstractSortedMap with a custom comparator for use
	 *  with the object to be compared
	 * @param compare	Comparator you wish to be used in comparisons
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * This compares two keys returning a positive number if key1 is greater than key 2, a 0
	 * if they are equal and a negative number if key1 is less than key2
	 * @param key1	First key to compare	
	 * @param key2	Second key to compare
	 * @return Integer representation of their comparison, see method description.
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * This class determines the natural order of an object using Java's compareTo method.
	 * @author John Widdifield & NCSU Staff
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}