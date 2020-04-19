package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * This class will test the hash set class
 * @author John Widdifield and ncsu staff
 *
 */
public class HashSetTest {

    private Set<Integer> set;
    private Set<Integer> testSet;
    
    /**
     * This will set up for the testing with a new set
     */
    @Before
    public void setUp() {
        // Will use a hash map with random alpha, beta values
        set = new HashSet<Integer>();
        
        // Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
    }
    
    
    /**
     * This will test the add method
     */
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(8);
        assertEquals(2, set.size());
        set.contains(8);
        assertTrue(set.contains(8));
        assertFalse(set.isEmpty());
    }
    
    /**
     * This will test the contains method
     */
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertFalse(set.contains(22));
    }

    
    /**
     * This will test the remove method
     */
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        set.remove(5);
        assertEquals(4, set.size());
       
        set.remove(10);
        set.remove(15);
        set.remove(20);
        set.remove(25);
        assertTrue(set.isEmpty());
    }
    
    
    /**
     * This will test the retainAll method
     */
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.retainAll(other);
        assertTrue(set.contains(10));
        assertTrue(set.contains(20));
        assertFalse(set.contains(30));
        assertFalse(set.contains(5));
        assertFalse(set.contains(15));
        assertFalse(set.contains(25));
    }
    
    
    /**
     * This will test the removeAll method
     */
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));
        assertFalse(set.contains(30));
        assertFalse(set.contains(20));
        assertFalse(set.contains(10));
    }
    
    
    /**
     * This will test the addAll method
     */
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
    }

    // Since our hash map uses random numbers, it can
    // be difficult to test that our hash set iterator returns
    // values in a certain order.
    // We could approach this problem with a few different options:
    // (1) instead of checking the specific order of entries
    //      visited by the iterator, we could save each
    //      iterator.next() into an array, then check that the 
    //      array *contains* the entry, regardless of its exact location
    //
    // (2) implement an isTesting flag for HashSet, similar to HashtestSet. 
    //     This is the more appropriate option since we can control the
    //     entire execution and know exactly which values should appear
    //     in the set and in the correct expected order from using the iterator 
    //
    // Revisit your test cases for HashMap iterator -- those tests can be adapted
    //     to work here, too, since you have already worked out the details
    //     of where entries with certain keys will be stored and in what order
    //     they will be stored
    
    /**
     * This will test the Iterator method
     */
    @Test
    public void testIterator() {
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next()); // should be index 0
        assertEquals(1, (int)it.next()); // should be index 2
        assertEquals(2, (int)it.next()); // should be index 3
        assertEquals(3, (int)it.next()); // should be index 4
        assertEquals(4, (int)it.next()); // should be index 5
        assertEquals(5, (int)it.next()); // should be index 6   
        assertFalse(it.hasNext());
    }
}