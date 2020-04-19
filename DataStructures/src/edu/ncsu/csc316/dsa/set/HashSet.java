package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

// Since our hash map uses linear probing, the entries are not ordered.
// As a result, we do not restrict our hash set to use Comparable elements.
// This also gives you an option if you need a set to manage elements
//     that are *NOT* Comparable (versus a TreeSet)
/**
 * This implements a set using a hashmap data structure
 * @author John Widdifield
 *
 * @param <E>	generic param 
 */
public class HashSet<E> extends AbstractSet<E> {

    private Map<E, E> map;
    
    // This constructor will use our "production version" of our hash map
    // meaning random values for alpha and beta will be used
    /**
     * This will construct a new hash set
     */
    public HashSet() {
        this(false);
    }
    
    // If isTesting is true, this constructor will use our "development version" of our hash map
    // meaning alpha=1, beta=1, and prime=7 
    /**
     * This will construct a hash set with a boolean for testing purposes using
     * alpha=1 beta=1 and prime=7 for the hash map
     * @param isTesting	true if you are using it for testing false otherwise
     */
    public HashSet(boolean isTesting) {
        map = new LinearProbingHashMap<E, E>(isTesting);
    }   
    
    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }

    @Override
    public void add(E value) {
        map.put(value, value);
    }

    @Override
    public boolean contains(E value) {
        return map.get(value) != null;
    }

    @Override
    public E remove(E value) {
        return map.remove(value);
    }
    
    /**
     * Gets the size of the hash set
     * @return number of elements in the set
     */
    public int size() {
        return map.size();
    }
}