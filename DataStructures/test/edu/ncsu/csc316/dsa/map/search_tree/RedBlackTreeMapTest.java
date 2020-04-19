package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * This class will test the redblacktreemap class for proper implementation
 * @author John Widdifield and NCSU staff
 *
 */
public class RedBlackTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    private BinarySearchTreeMap<Integer, Student> sTree;
    
	/**
	 * This will set the tree up for all of the test cases
	 */
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
        sTree = new RedBlackTreeMap<Integer, Student>(Comparator.naturalOrder());
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
        
        assertNull(tree.put(4,  "four"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
 
        assertNull(tree.put(7,  "seven"));
        Iterable<Position<Entry<Integer, String>>> it = tree.children(tree.root());
        Iterator<Position<Entry<Integer, String>>> it2 = it.iterator();
        assertTrue(it2.hasNext());
        assertEquals( tree.numChildren(tree.left(tree.root())), 0);
        assertEquals( tree.numChildren(tree.right(tree.root())), 2);
        assertEquals(2, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(12,  "twelve"));
        assertEquals(3, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(13, "thirteen"));
        assertEquals(4, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(14, "fourteen"));
        assertEquals(5, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(13, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(15, "fifteen"));
        assertEquals(6, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(13, (int)tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(16,  "sixteen"));
        assertEquals(7, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(13, (int)tree.right(tree.root()).getElement().getKey());
       
    }
    
	/**
	 * This will test the get method
	 */
    @Test
    public void testGet() {
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(6, "six");
        
        assertEquals(tree.get(6), "six");
        assertEquals(tree.get(5), "five");
        assertEquals(tree.get(4), "four");
        assertEquals(tree.get(3), "three");
        assertEquals(tree.get(2), "two");
        assertEquals(tree.get(1), "one");
        
        
        
    }
    
    /**
     * This will test the remove method
     */
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(6, "six");
        tree.put(12, "twelve");
        tree.put(11, "eleven");
        tree.put(10, "ten");
        tree.put(9, "nine");
        tree.put(8, "eight");
        tree.put(7, "seven");
        
        assertEquals(tree.remove(8), "eight");
        assertEquals(11, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(5), "five");
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(2), "two");
        assertEquals(9, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(6), "six");
        assertEquals(8, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(1), "one");
        assertEquals(7, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(11), "eleven");
        assertEquals(6, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(12), "twelve");
        assertEquals(5, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(3), "three");
        assertEquals(4, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(4), "four");
        assertEquals(3, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(10), "ten");
        assertEquals(2, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(7), "seven");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(tree.remove(9), "nine");
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertEquals(tree.toString(), "BalanceableBinaryTree[\n" + 
        		"null\n" + 
        		"]");
        
    }
}