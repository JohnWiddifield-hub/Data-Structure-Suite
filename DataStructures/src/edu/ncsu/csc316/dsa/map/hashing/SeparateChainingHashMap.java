package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SkipListMap;

/**
 * This is an implementation of a separate chaining hash map which utilizes a skip list
 * for a bucket's list.  It was implemented using the sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich 
 * to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <K> generic Key Param
 * @param <V> Generic value param
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

    private Map<K, V>[] table;
    private int size;
    
    /**
     * This creates a separate chaining hash map with a default capacity.
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * This creates a separate chaining has map with a boolean for testing
     * with specific values for hashing.
     * @param isTesting true if you are testing the data structure false otherwise
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }  	
	
    /**
     * This creates a separate chaining has map with a custom capacity
     * @param capacity number of entrys you wish to have
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }    
    
    /**
     * This creates a separate chaining has map with a boolean for testing and a 
     * custom capacity 
     * @param isTesting true if you are testing the structure false otherwise
     * @param capacity number of entry's you wish to have
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
        for(int i = 0; i < table.length; i++) {
        	if(table[i] != null) {
        		for(Entry<K, V> entry : table[i].entrySet()) {
        			list.addLast(entry);
        		}
        	}
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // Example -- change this to whatever map you'd like        
        table = (SkipListMap<K, V>[])new SkipListMap [capacity];
        size = 0;
    }

    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table      
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist      
        if(bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value     
        return bucket.get(key);
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        Map<K, V> bucket = table[hash]; 
        if(bucket == null) {
        	table[hash] = new SkipListMap<>();
        	bucket = table[hash];
        }
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        size += (bucket.size() - oldSize);
        return answer;
    }

    @Override
    public V bucketRemove(int hash, K key) {
        Map<K, V> bucket = table[hash];
        if (bucket == null) {
        	return null;
        }
        int oldSize = bucket.size();
        V answer = bucket.remove(key);
        size -= (oldSize - bucket.size());
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
}