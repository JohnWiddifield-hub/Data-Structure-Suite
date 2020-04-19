package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;


/**
 * This is an implementation of a linear probing hash map.
 *   It was implemented using the sixth edition of Data Structures and 
 *   Algorithms in Java by Michael T. Goodrich 
 * to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <K> generic Key Param
 * @param <V> Generic value param
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    // This time, our array is an array of TableEntry objects
    private TableEntry<K, V>[] table;
    private int size;

    /**
     * This creates a linear probing hash map with a default capacity
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * This creates a linear probing hash map with a boolean for testing.
     * @param isTesting true if you are testing the structure false otherwise
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }
    
    /**
     * This creates a linear probing hash map with a  
     * custom capacity 
     * @param capacity number of entry's you wish to have
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }
    
    /**
     * This creates a linear probing hash map with a boolean for testing and a 
     * custom capacity 
     * @param isTesting true if you are testing the structure false otherwise
     * @param capacity number of entry's you wish to have
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	List<Entry<K, V>> buffer = new SinglyLinkedList<Entry<K, V>>();
        for(int h = 0; h < capacity(); h++) {
        	if(!isAvailable(h) && !table[h].isDeleted()) {
        		buffer.addLast(table[h]);
        	}
        }
        return buffer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    // Helper method to determine whether a bucket has an entry or not  
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    // Helper method to find the bucket for an entry;
    // If the entry *is* in the map, returns the index of the bucket
    // If the entry is *not* in the map, returns -(a + 1) to indicate 
    //     that the entry should be added at index a
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
        	if(isAvailable(j)) {
        		if (avail == -1) {
        			avail = j;
        		}
        		if(table[j] == null) {
        			return -(avail + 1);
        		}
        		
        	} else if(size == 0 || table[j].isDeleted()) {
        		return -1;
        	} else if(table[j].getKey().equals(key)) {
        		return j;
        	}
        	j = (j + 1) % table.length;
        } while(j != index);
        return -(avail + 1);
    }
    
    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        if(index < 0) {
        	return null;
        }
        return table[index].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        if (index >= 0) {
        	return table[index].setValue(value);
        }
        table[-(index + 1)] = new TableEntry<>(key, value);
        size++;
        return null;
    }   

    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        if(index < 0) {
        	return null;	
        }
        V answer = table[index].getValue();
        table[index] = new TableEntry<>(null, null);
        table[index].setDeleted(true);
        size--;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
    
    /**
     * This class is for entrys in the table
     * @author John Widdifield
     *
     * @param <K> generic param for keys 
     * @param <V> generic param for values
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

        private boolean isDeleted;

        /**
         * Creates a table entry with a given key and value
         * @param key key of the entry you want
         * @param value value of the entry you want
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * This will tell you if the entry was deleted or not
         * @return true if it was deleted false if it still exists
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * This will set the deleted value to a specified bool
         * @param deleted true if you want to set the status to deleted, 
         * false if otherwise
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}