
package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * This is the test class for the separate chaining hash map.
 * @author John Widdifield and NCSU staff
 */
public class SeparateChainingHashMapTest {

	private Map<Integer, String> map;
	
	   /**
     * This will set up each of the following tests.
     */
	@Before
	public void setUp() {
		// Use the "true" flag to indicate we are testing.
		// Remember that (when testing) alpha = 1, beta = 1, and prime = 7
		// based on our AbstractHashMap constructor.
		// That means you can draw the hash table by hand
		// if you use integer keys, since Integer.hashCode() = the integer value, itself
		// Finally, apply compression. For example:
		// for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
		// for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
		// for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
		// for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
		// for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
		// for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
		// etc.
		// Remember that our secondary map (an AVL tree) is a search
		// tree, which means the entries should be sorted in order within
		// that tree
		map = new SeparateChainingHashMap<Integer, String>();
		map = new SeparateChainingHashMap<Integer, String>(true);
		map = new SeparateChainingHashMap<Integer, String>(7);
		map = new SeparateChainingHashMap<Integer, String>(7, true);

	}
	
	/**
	 * This will test the put method for the map
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));

		// Since our entrySet method returns the entries in the table
		// from left to right, we can use the entrySet to check
		// that our values are in the correct order in the hash table.
		// Alternatively, you could implement a toString() method if you
		// want to check that the exact index/map of each bucket is correct
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
		
		
		assertNull(map.put(4, "string4"));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
		assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
		
		assertNull(map.put(10, "string10"));
		assertEquals(3, map.size());
		assertFalse(map.isEmpty());
		it = map.entrySet().iterator();
		assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
		assertEquals(10, (int)it.next().getKey()); // should be in a map in index 5
		assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
	}
	
	/**
	 * This will test the get method for the map
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		
		assertEquals(map.get(5), "string5");
		assertEquals(map.get(1), "string1");
		assertEquals(map.get(2), "string2");
		assertEquals(map.get(3), "string3");
		assertEquals(map.get(4), "string4");
		
	}
	
	/**
	 * This will test the remove method for the map
	 */
	@Test
	public void testRemove() {
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		assertFalse(map.isEmpty());
		assertEquals(map.size(), 5);
		
		assertEquals(map.remove(3), "string3");
		assertEquals(map.size(), 4);
		
		assertEquals(map.remove(1), "string1");
		assertEquals(map.size(), 3);
		
		assertEquals(map.remove(5), "string5");
		assertEquals(map.size(), 2);
	}
	
	/**
	 * This will test the iterator method for the map
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
		assertEquals(it.next().intValue(), 1);
		assertEquals(it.next().intValue(), 2);
		assertEquals(it.next().intValue(), 3);
		assertEquals(it.next().intValue(), 4);
		assertEquals(it.next().intValue(), 5);
		assertFalse(it.hasNext());
		
	}
	
	/**
	 * This will test the entry iterator method for the map
	 */
	@Test
	public void testEntrySet() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry = it.next();
		assertEquals(entry.getValue(), "string1");
		entry = it.next();
		assertEquals(entry.getValue(), "string2");
		entry = it.next();
		assertEquals(entry.getValue(), "string3");
		entry = it.next();
		assertEquals(entry.getValue(), "string4");
		entry = it.next();
		assertEquals(entry.getValue(), "string5");
		assertFalse(it.hasNext());
	}
	
	/**
	 * This will test the values iterator method for the map
	 */
	@Test
	public void testValues() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<String> it = map.values().iterator();
		assertTrue(it.hasNext());
		assertEquals(it.next(), "string1");
		assertEquals(it.next(), "string2");
		assertEquals(it.next(), "string3");
		assertEquals(it.next(), "string4");
		assertEquals(it.next(), "string5");
		assertFalse(it.hasNext());
	}
}