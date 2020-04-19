package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * This allows you to create and maintain an unordered array map data structure.
 * I used the sixth edition of Data Structures and Algorithms in Java by Michael 
 * T. Goodrich to implement the code
 * @author John Widdifield and NCSU Staff
 *
 * @param <K>	Generic param for keys
 * @param <V>	Generic param for values
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	// Use the adapter pattern to delegate to our existing
	// array-based list implementation
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * This constructs an unordered array map for you to maintain
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}
	
	// LookUp is a core behavior of all maps
    // This lookup should perform a sequential search
    // and return the index where the entry
    // is located. If the entry is not in the map, return -1
	private int lookUp(K key)
	{
		int indexCount = -1;
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			indexCount++;
			if(it.next().getKey().equals(key)) {
				return indexCount;
			}
		}
		return -1;
	}
	
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if(index == -1) {
			return null;
		}
		V tempVal = list.get(index).getValue();
		transpose(index);
		return tempVal;
	}
	
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if(index != -1) {
			V tempVal = list.get(index).getValue();
			list.get(index).setValue(value);
			transpose(index);
			return tempVal;
		} else {
			list.add(0, new MapEntry<>(key, value));
			return null;
		}
		
	}
	
	@Override
	public V remove(K key) {
       int index = lookUp(key);
       if(index != -1) {
    	  return list.remove(index).getValue();
       } else {
    	   return null;
       }
	}

	@Override
	public int size() {
		return list.size();
	}

	
	private V transpose(int index)
	{
		if(index > 0) {
			Map.Entry<K, V> temp = list.get(index);
			list.set(index, list.get(index - 1));
			list.set(index - 1, temp);
		}
		return list.get(index).getValue();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		
		ArrayBasedList<Entry<K, V>> tempList = new ArrayBasedList<Entry<K, V>>();
		for(int i = 0; i < list.size(); i++) {
			tempList.add(i, list.get(i));
		}
		return tempList;
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