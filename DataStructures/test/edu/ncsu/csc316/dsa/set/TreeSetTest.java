package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * This will test teh tree set class
 * @author John Widdifield
 *
 */
public class TreeSetTest {

    private Set<Integer> set;
    
    /**
     * This will set up for the testing with a new set
     */
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
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

    
    /**
     * This will test the Iterator method
     */
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
        Iterator<Integer> it = set.iterator();
        Integer five = 5;
        Integer ten = 10;
        Integer fifteen = 15;
        Integer twenty = 20;
        Integer twentyfive = 25;
        assertTrue(it.hasNext());
        assertEquals(it.next(), five);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), fifteen);
        assertEquals(it.next(), twenty);
        assertEquals(it.next(), twentyfive);
        assertFalse(it.hasNext());
        
    }
}