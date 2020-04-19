package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * This class is an implementation of the SkipListMap data structure.
 * I used the sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich 
 * to implement the code.
 * @author John Widdifield and NCSU Staff
 *
 * @param <K>	Generic key parameter
 * @param <V>	Generic value parameter
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	
	private Random coinToss;
	private SkipListEntry<K, V> start;
	private int size;
	private int height;

	/**
	 * This constructs a SkipListMap with the default comparator
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * This constructs a SkipListMap with a custom comparator
	 * @param compare 	Comparator which you wish to use for the objects you are storing
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower		
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

    /**
     *  Helper method to determine if an entry is one of the sentinel
     *  -INFINITY or +INFINITY nodes (containing a null key)
     * @param entry	Entry to check for sentinality
     * @return True if entry is a sentinel false if not
     */
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}	

	/**
	 * This 
	 * @param key
	 * @return
	 */
    private SkipListEntry<K, V> lookUp(K key) {
        SkipListEntry<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
		if(temp != null && temp.getKey() != null && temp.getKey().equals(key)) {
			return temp.getValue();
		}
		return null;
	}

	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> below, K key, V value) {
		SkipListEntry<K, V> newEntry = new SkipListEntry<K, V>(key, value);
		newEntry.setBelow(below);
		newEntry.setPrevious(prev);
		if(prev != null) {
			newEntry.setNext(prev.next);
			newEntry.prev.setNext(newEntry);
		}
		if(newEntry.next != null) {
			newEntry.next.setPrevious(newEntry);
		}
		if(below != null) {
			below.setAbove(newEntry);
		}
		return newEntry;
	}

	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> p = lookUp(key);
		
		if(p.getKey() != null && p.getKey().equals(key)) {
			V originalValue = p.getValue();
			while(p != null) {
				p.setValue(value);
				p = p.above;
			}
			return originalValue;
		}
		SkipListEntry<K, V> q = null;
		int currentLevel = -1;
		do {
			currentLevel++;
			if(currentLevel >= height) {
				height++;
				SkipListEntry<K, V> tail = start.next;
				start = insertAfterAbove(null, start, null, null);
				insertAfterAbove(start, tail, null, null);
			}
			q = insertAfterAbove(p, q, key, value);
			while(p.getAbove() == null) {
				p = p.prev;
			}
			p = p.above;
			
		} while(!coinToss.nextBoolean());
		
		size++;
		return null;
		
	}
	
    @Override
    public V remove(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        if(temp.getKey() == null || !temp.getKey().equals(key)) {
        	return null;
        }
        V originalValue = temp.getValue();
        do {
        	temp.prev.setNext(temp.next);
            temp.next.setPrevious(temp.getPrevious());
            temp = temp.getAbove();
            if(temp != null) {
            	temp.below = null;
            }
        } while(temp != null);
        size--;
        return originalValue;
    }	

	@Override
	public int size() {
		return size;
	}
	
	@Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        SkipListEntry<K, V> current = start;
        while(current.below != null){
            current = current.getBelow();
        }
        current = current.next;
        while(!isSentinel(current)) {
            set.addLast(current);
            current = current.next;
        }
        return set;
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while( cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while(cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if(!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");
		
		return sb.toString();
	}

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.	
/*	public String toFullString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
		SkipListEntry<K,V> cursor = start;
		SkipListEntry<K,V> firstInList = start;
		while( cursor != null) {
			firstInList = cursor;
			sb.append("-INF -> ");
			cursor = cursor.next;
			while(cursor != null && !isSentinel(cursor)) {
				sb.append(cursor.getKey()+ " -> ");
				cursor = cursor.next;
			}
			sb.append("+INF\n");
			cursor = firstInList.below;
		}
		sb.append("]");
		return sb.toString();
	}*/
	
	/**
	 * This class if for creating Entries for your skip list.
	 * @author John Widdifield
	 *
	 * @param <K>	Generic key parameter
	 * @param <V>	Generic value parameter
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {

        private SkipListEntry<K, V> above;
        private SkipListEntry<K, V> below;
        private SkipListEntry<K, V> prev;
        private SkipListEntry<K, V> next;

        public SkipListEntry(K key, V value) {
            super(key, value);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        public SkipListEntry<K, V> getBelow() {
            return below;
        }
        
        public SkipListEntry<K, V> getNext() {
            return next;
        }
        
        public SkipListEntry<K, V> getPrevious() {
            return prev;
        }
        
        public SkipListEntry<K, V> getAbove() {
            return above;
        }
        
        public void setBelow(SkipListEntry<K, V> down) {
            this.below = down;
        }
        
        public void setNext(SkipListEntry<K, V> next) {
            this.next = next;
        }
        
        public void setPrevious(SkipListEntry<K, V> prev) {
            this.prev = prev;
        }
        
        public void setAbove(SkipListEntry<K, V> up) {
            this.above = up;
        }
    }
}