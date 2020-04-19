package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This will allow you to create and maintain an unordered linked map data structure.
 * I used the sixth edition of Data Structures and Algorithms in Java by Michael T. 
 * Goodrich to implement the code
 * @author John Widdifield and NCSU Staff
 *
 * @param <K> Generic param for keys
 * @param <V> Generic param for values
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	private PositionalList<Entry<K, V>> list;
	
	/**
	 * This will construct an unordered linked map object for you to maintain.
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}
	
	private Position<Entry<K, V>> lookUp(K key)
	{
		if(list.isEmpty()) {
			return null;
		} 
		Position<Entry<K, V>> current = list.first();
		
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		while(it.hasNext()) {
			if(it.next().getElement().getKey().equals(key)) {
				moveToFront(current);
				return list.first();
			}
			current = list.after(current);
		}
		return null;
		
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if(p == null || p.getElement() == null) {
			return null;
		}
		return p.getElement().getValue();
	}
	
	private void moveToFront(Position<Entry<K, V>> position) {
		list.addFirst(list.remove(position));
	}

	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
		if(p == null) {
			list.addFirst(new MapEntry<>(key, value));
			return null;
		} else {
			return p.getElement().setValue(value);
		}
	}
	
	@Override
	public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if(p != null) {
    	   return list.remove(p).getValue();
       }
       return null;
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
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