package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This class will test the AVLTreeMap class for proper implementation
 * @author John Widdifield and NCSU staff
 *
 */
public class AVLTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    private BinarySearchTreeMap<Integer, Student> sTree;
    
	/**
	 * This will set the tree up for all of the test cases
	 */
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
        sTree = new AVLTreeMap<Integer, Student>(Comparator.naturalOrder());
    }
    
	/**
	 * This will test the put method for the tree structure
	 */
    @Test
    public void testPut() {
        assertEquals(0, sTree.size());
        assertNull(sTree.put(1, new Student("John1", "Smith1", 100100100, 18, 3.9, "jsmith")));
        
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(5, "string5"));
        assertEquals(1, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        
        assertNull(tree.put(10, "string10"));
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertNull(tree.left(tree.right(tree.root())).getElement());
        assertNull(tree.right(tree.right(tree.root())).getElement());
        assertEquals(2, tree.size());
        
        assertNull(tree.put(6,  "string6"));
        assertEquals((int) tree.root().getElement().getKey(), 6);
        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(11, "string11"));
        assertNull(tree.put(12, "string12"));

        assertEquals(5, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(11, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(12, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(10, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        
        setUp();
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(1, "one"));
        assertEquals((int) tree.root().getElement().getKey(), 2);
        assertEquals((int) tree.right(tree.root()).getElement().getKey(), 3);
        assertEquals((int) tree.left(tree.root()).getElement().getKey(), 1);
        
        setUp();
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        
        assertEquals((int) tree.root().getElement().getKey(), 2);
        assertEquals((int) tree.right(tree.root()).getElement().getKey(), 3);
        assertEquals((int) tree.left(tree.root()).getElement().getKey(), 1);

        
    }
    
	/**
	 * This will test the get method
	 */
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(3, "string3"));
        assertFalse(tree.isEmpty());
        
        assertEquals("string3", tree.get(3));
        assertEquals(null, tree.get(6));
        assertEquals(null, tree.get(0));
        
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        
        assertEquals(tree.get(1), "one");
        assertEquals(tree.get(2), "two");
        assertEquals(tree.get(3), "string3");
        
    }
    
    /**
     * This will test the remove method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(7, "seven"));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        
        assertNull(tree.remove(0));
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        setUp();
        assertTrue(tree.isEmpty());
        assertNull(tree.put(7, "seven"));
        assertNull(tree.put(6, "six"));
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(4, "four"));
        assertNull(tree.put(3, "three"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(1, "one"));
        
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(9, "nine");
        tree.put(8, "eight");
        
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(8, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(9, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        tree.remove(1);
        tree.put(-5, "negFive");
        tree.put(-3, "negThree");
        tree.put(-4, "negFour");
        
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(-4, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(-5, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(-3, (int)tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(3, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(8, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, (int)tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(9, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());

        
    }
}