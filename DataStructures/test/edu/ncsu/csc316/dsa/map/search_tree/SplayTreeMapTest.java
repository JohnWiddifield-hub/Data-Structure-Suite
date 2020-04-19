package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This class will test the splaytreemap class for proper implementation
 * @author John Widdifield and NCSU staff
 *
 */
public class SplayTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    private BinarySearchTreeMap<Integer, Student> sTree;
    
	/**
	 * This will set the tree up for all of the test cases
	 */
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
        sTree = new SplayTreeMap<Integer, Student>(Comparator.naturalOrder());
    }
    
	/**
	 * This will test the put method for the tree structure
	 */
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(3, "string3"));
        assertEquals(1, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        
        setUp();
        assertEquals(0, sTree.size());
        assertNull(sTree.put(1, new Student("John1", "Smith1", 100100100, 18, 3.9, "jsmith")));
        
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(1, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        
        assertNull(tree.put(10, "string10"));
        assertEquals(10, (int)tree.root().getElement().getKey());

        assertEquals(2, tree.size());
        
        assertNull(tree.put(6,  "string6"));
        assertEquals((int) tree.root().getElement().getKey(), 6);
        
        assertNull(tree.put(11, "string11"));
        assertEquals((int) tree.root().getElement().getKey(), 11);
        assertNull(tree.put(12, "string12"));


        
        setUp();
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(1, "one"));
        assertEquals((int) tree.root().getElement().getKey(), 1);

        
        setUp();
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        
        assertEquals((int) tree.root().getElement().getKey(), 2);

    }
    
	/**
	 * This will test the get method
	 */
    @Test
    public void testGet() {
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        
        assertEquals(tree.get(1), "one");
        assertEquals(tree.get(2), "two");
        assertEquals(tree.get(3), "three");
        
    }
    
    /**
     * This will test the remove method
     */
    @Test
    public void testRemove() {
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.remove(4);
        assertEquals((int) tree.root().getElement().getKey(), 3);
        tree.remove(1);
        assertEquals((int) tree.root().getElement().getKey(), 3);
        tree.remove(3);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(2);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(5);
        assertEquals(tree.size(), 0);
        
        setUp();
        tree.put(2, "two");
        tree.put(4, "four");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(5, "five");
        tree.remove(4);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(1);
        assertEquals((int) tree.root().getElement().getKey(), 3);
        tree.remove(3);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(2);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(5);
        assertEquals(tree.size(), 0);
        
        setUp();
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(2, "two");


        tree.remove(4);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(1);
        assertEquals((int) tree.root().getElement().getKey(), 2);
        tree.remove(3);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(2);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(5);
        assertEquals(tree.size(), 0);
        
        setUp();
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(10, "ten");
        tree.put(8, "eight");


        tree.remove(8);
        assertEquals((int) tree.root().getElement().getKey(), 10);
        tree.remove(4);
        assertEquals((int) tree.root().getElement().getKey(), 2);
        tree.remove(1);
        assertEquals((int) tree.root().getElement().getKey(), 2);
        tree.remove(3);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(2);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(5);
        assertEquals((int) tree.root().getElement().getKey(), 10);
        tree.remove(10);
        assertEquals(tree.size(), 0);
        
        setUp();
        tree.put(10, "ten");
        tree.put(8, "eight");
        tree.put(1, "one");
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(3, "three");

        tree.remove(3);
        assertEquals((int) tree.root().getElement().getKey(), 4);
        tree.remove(2);
        assertEquals((int) tree.root().getElement().getKey(), 4);
        tree.remove(8);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(4);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(1);
        assertEquals((int) tree.root().getElement().getKey(), 5);
        tree.remove(5);
        assertEquals((int) tree.root().getElement().getKey(), 10);
        tree.remove(10);
        assertEquals(tree.size(), 0);

        assertNull(tree.remove(21));

    }
}