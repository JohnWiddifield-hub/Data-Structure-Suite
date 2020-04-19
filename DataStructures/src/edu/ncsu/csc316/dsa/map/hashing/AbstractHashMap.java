package edu.ncsu.csc316.dsa.map.hashing;

import java.util.Random;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractMap;

/**
 * This is the abstract hash map class to be used when implementing a hashmap. Also,
 * I used the sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich 
 * to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <K> Generic param for key
 * @param <V> Generic param for value
 */
public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {

    /** An initial capacity for the hash table */
    protected static final int DEFAULT_CAPACITY = 17;
    
    /** From our discussion in class, the expected number of probes
    // for separate chaining remains relatively the same no matter
    // what the load factor may be. However, for linear probing, to
    // reduce the chance of having large clusters, we will resize
    // when the load factor reaches 0.5 */
    private static final double MAX_LOAD_FACTOR = 0.5;
    
    /** prime to be used when hashing in default */
    protected static final int DEFAULT_PRIME = 109345121;
    
    // Alpha and Beta values for MAD compression
    // This implementation uses a variation of the MAD method
    // where h(k) = ( (alpha * f(k) + beta) % prime) % capacity
    private long alpha;
    private long beta;
    
    // The prime number to use for compression strategy
    private int prime;
    
    // You can use the isTesting flag (set to true) to control
    // the testing environment and avoid random numbers when testing
    /**
     * This will create an abstract hash map with a custom capacity 
     * and a value if testing is being done
     * @param capacity size of the hash map you desire
     * @param isTesting true if you are testing the structure false otherwise
     */
    public AbstractHashMap(int capacity, boolean isTesting) {
        if(isTesting) {
            alpha = 1;
            beta = 1;
            prime = 7;
        } else {
            Random rand = new Random();
            alpha = rand.nextInt(DEFAULT_PRIME - 1) + 1;
            beta = rand.nextInt(DEFAULT_PRIME);
            prime = DEFAULT_PRIME;
        }
        createTable(capacity);
    }
    
    private int compress(K key) {
        return (int)((Math.abs(key.hashCode() * alpha + beta) % prime) % capacity());
    }

    @Override
    public V put(K key, V value) {
        V ret = bucketPut(compress(key), key, value);
        if( (double)size() / capacity() > MAX_LOAD_FACTOR) {
            resize(2 * capacity() + 1);
        }
        return ret;
    }
    
    @Override
    public V get(K key) {
        return bucketGet(compress(key), key);
    }

    @Override
    public V remove(K key) {
        return bucketRemove(compress(key), key);
    }
    
    private void resize(int newCapacity) {
        List<Entry<K, V>> list = new ArrayBasedList<Entry<K, V>>();
        for(Entry<K, V> entry : entrySet()) {
            list.addLast(entry);
        }
        createTable(newCapacity);
        for(Entry<K, V> entry : list) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * This will get the capacity for the map
     * @return capacity for the map
     */
    protected abstract int capacity();
    
    /**
     * This will create a table for you with a custom capacity
     * @param capacity capacity of the table
     */
    protected abstract void createTable(int capacity);
    
    /**
     * This will get a bucket from teh table for you
     * @param hash hashCode value you want to retrieve
     * @param key key at teh hash value
     * @return the bucket at the hashcode
     */
    protected abstract V bucketGet(int hash, K key);
    
    /**
     * This will put a bucket at a specified hash
     * @param hash location you wish to store
     * @param key key of the entry
     * @param value value of the entry
     * @return null if successfull, or the value that was overwritten
     */
    protected abstract V bucketPut(int hash, K key, V value);
    
    /**
     * This will remove a bucket from the table
     * @param hash hash location to remove from
     * @param key key of the entry to remove
     * @return value of the removed entry
     */
    protected abstract V bucketRemove(int hash, K key);
}