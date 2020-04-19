package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * This will allow you to create and maintain a search table map data structure.
 * I used the sixth edition of Data Structures and Algorithms in Java by Michael 
 * T. Goodrich to implement the code
 * @author John Widdifield and NCSU Staff
 *
 * @param <K>	Generic param for keys
 * @param <V>	Generic param for values
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * This constructs a search table map for you using the default comparator for the object
	 */
	public SearchTableMap() {
		this(null);
	}
	
	/**
	 * This constructs a search table map for you using a custom comparator
	 * @param compare Comparator in which you wish to use
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	private int lookUp(K key) {
		return binarySearchHelper(0, list.size() - 1, key);
	}

	private int binarySearchHelper(int min, int max, K key) {
		if(min > max) {
			return max + 1;
		}
		int mid = (max + min) / 2;
		if(compare(list.get(mid).getKey(), key) == 0){
			return mid;
		} else if(compare(list.get(mid).getKey(), key) > 0) {
			return binarySearchHelper(min, mid - 1, key);
		} else {
			return binarySearchHelper(mid + 1, max, key);
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if(list.isEmpty() || (index == 0 && list.first().getKey() != key)) {
			return null;
		}

		try {
			list.get(index);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
		
		if(!list.get(index).getKey().equals(key)) {
			return null;
		}
		if(index > -1 && index < list.size()) {
			return list.get(index).getValue();
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if(index < list.size() && index >= 0 && compare(key, list.get(index).getKey()) == 0) {
			return list.get(index).setValue(value);
		}
		list.add(index, new MapEntry<K, V>(key, value));
		return null;
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if(index < 0 || index == list.size() || compare(key, list.get(index).getKey()) != 0) {
			return null;
		}
		return list.remove(index).getValue();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}