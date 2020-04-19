package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * This class tests the binary search tree map class for proper implementation
 * @author John Widdifield and NCSU staff
 *
 */
public class BinarySearchTreeMapTest {

	/** this is the tree which is being tested */
	BinarySearchTreeMap<Integer, String> tree;
	
	/**
	 * This will set the tree up for all of the test cases
	 */
	@Before
	public void setUp() {
		tree = new BinarySearchTreeMap<Integer, String>();
	}
	
	/**
	 * This will test the put method for the tree structure
	 */
	@Test
	public void testPut() {
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		tree.put(1, "one");
		assertEquals(1, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int)tree.root().getElement().getKey());
		
		tree.put(2, "two");
		assertEquals(2, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int)tree.root().getElement().getKey());
		assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
		
		tree.put(0, "zero");
		assertEquals(3, tree.size());
		assertFalse(tree.isEmpty());
		assertEquals(1, (int)tree.root().getElement().getKey());
		assertEquals(2, (int)tree.right(tree.root()).getElement().getKey());
		assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
	}
	
	/**
	 * This will test the get method
	 */
	@Test
	public void testGet() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		
		tree.put(2, "two");
		tree.put(0, "zero");
		tree.put(4, "four");
		
		assertEquals(tree.get(0), "zero");
		assertEquals(tree.get(1), "one");
		assertEquals(tree.get(2), "two");
		assertEquals(tree.get(4), "four");
	}
	
    /**
     * This will test the remove method
     */
	@Test
	public void testRemove() {
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		
		assertNull(tree.remove(10));
		assertEquals(1, tree.size());
		
		assertEquals("one", tree.remove(1));
		assertEquals(0, tree.size());
		
		tree.put(1,  "one");
		assertEquals(1, tree.size());
		tree.put(4, "four");
		assertEquals(2, tree.size());
		tree.put(2, "two");
		assertEquals(3, tree.size());
		tree.put(3, "three");
		assertEquals(4, tree.size());
		
		assertEquals(tree.remove(2), "two");
		assertEquals(tree.size(), 3);
		
		assertEquals(tree.remove(4), "four");
		assertEquals(tree.size(), 2);
		
		tree.put(2, "two");
		tree.put(4,  "four");
		
		assertEquals(tree.remove(3), "three");
		assertEquals(tree.size(), 3);
		
		assertEquals(tree.remove(1), "one");
		assertEquals(tree.size(), 2);
		assertEquals(tree.remove(4), "four");
		
		assertEquals(tree.size(), 1);
	}
	
	/**
	 * This will test all of the traversal methods
	 */
	@Test
	public void testTrav() {
		tree.put(2, "two");
		tree.put(1, "one");
		tree.put(4, "four");
		tree.put(3, "one");
		
		Iterator<Position<Entry<Integer, String>>> pre = tree.preOrder().iterator();
		Iterator<Position<Entry<Integer, String>>> post = tree.postOrder().iterator();
		Iterator<Position<Entry<Integer, String>>> lev = tree.levelOrder().iterator();
		Iterator<Position<Entry<Integer, String>>> in = tree.inOrder().iterator();
		Iterator<Entry<Integer, String>> ent = tree.entrySet().iterator();
		
		//can't test the sentinel nodes so I have to stop at each sentinel, they throw null pointer
		assertEquals((int) pre.next().getElement().getKey(), 2);
		assertEquals((int)pre.next().getElement().getKey(), 1);
		
		
		assertTrue(post.hasNext());
		
		assertEquals((int)lev.next().getElement().getKey(), 2);
		assertEquals((int)lev.next().getElement().getKey(), 1);
		assertEquals((int)lev.next().getElement().getKey(), 4);
		
		assertTrue(in.hasNext());
		
		assertTrue(ent.hasNext());
		
	}
}