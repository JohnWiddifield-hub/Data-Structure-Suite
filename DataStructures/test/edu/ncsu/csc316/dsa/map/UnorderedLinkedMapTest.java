package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the UnorderedLinkedMap class for proper implementation of the data structure.
 * @author John Widdifield and NCSU Staff
 *
 */
public class UnorderedLinkedMapTest {

	private Map<Integer, String> map;
	
	/**
	 * This will set up new maps for each test method
	 */
	@Before
	public void setUp() {
		map = new UnorderedLinkedMap<Integer, String>();
	}
	
	
	/**
	 * This will test the put method for the data structure
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("UnorderedLinkedMap[3]", map.toString());
		assertEquals(1, map.size());
		assertNull(map.put(5, "string5"));
		assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
		assertEquals(2, map.size());

		assertFalse(map.isEmpty());
		assertNull(map.put(100, "string100"));
		assertEquals("UnorderedLinkedMap[100, 5, 3]", map.toString());
		assertEquals(3, map.size());
		assertTrue(map.get(100).contentEquals("string100"));
		
		assertFalse(map.isEmpty());
		assertEquals(map.put(3, "newString3"), "string3");
		assertEquals("UnorderedLinkedMap[3, 100, 5]", map.toString());
		assertEquals(3, map.size());
		assertTrue(map.get(3).contentEquals("newString3"));
	}
	
	/**
	 * This will test the get method for the data structure
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals("string3", map.get(3));
		assertEquals("UnorderedLinkedMap[3, 1, 4, 2, 5]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("UnorderedLinkedMap[2, 3, 1, 4, 5]", map.toString());
		
		assertNull(map.get(0));
		assertNull(map.get(6));
		
	}
	
	/**
	 * This will test the remove method for the data structure
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
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
		
		assertEquals(map.get(4), map.remove(4));
		assertEquals(4, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[1, 2, 5, 3]", map.toString());
		
		assertEquals(map.get(1), map.remove(1));
		assertEquals(3, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[2, 5, 3]", map.toString());
		
		assertEquals(map.get(3), map.remove(3));
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[2, 5]", map.toString());
		
		assertEquals(map.get(2), map.remove(2));
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());
		assertEquals("UnorderedLinkedMap[5]", map.toString());
		
		assertEquals(map.get(5), map.remove(5));
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertEquals("UnorderedLinkedMap[]", map.toString());
	}
	
	/**
	 * This will test the iterator method for the data structure
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(1, "string1"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(3, "string3"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(5, "string5"));

		Iterator<Integer> it = map.iterator();
		int count = 5;
		while(it.hasNext()) {
			int key = it.next();
			assertEquals(key, count);
			count--;
		}
	}
	
	/**
	 * This will test the entry set iterator method for the data structure
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
		Map.Entry<Integer, String> entry1 = it.next();
		assertEquals(1, (int)(entry1.getKey()));
		assertEquals("string1", (String)(entry1.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry2 = it.next();
		assertEquals(4, (int)(entry2.getKey()));
		assertEquals("string4", (String)(entry2.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry3 = it.next();
		assertEquals(2, (int)(entry3.getKey()));
		assertEquals("string2", (String)(entry3.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry4 = it.next();
		assertEquals(5, (int)(entry4.getKey()));
		assertEquals("string5", (String)(entry4.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry5 = it.next();
		assertEquals(3, (int)(entry5.getKey()));
		assertEquals("string3", (String)(entry5.getValue()));
		
		assertFalse(it.hasNext());
	}
	
	/**
	 * This will test the value iterator method for the data structure
	 */
	@Test
	public void testValues() {
		assertNull(map.put(5, "string5"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(3, "string3"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(1, "string1"));
		
		Iterator<String> it = map.values().iterator();
		
		String tempVal = it.next();
		assertEquals(tempVal, "string1");
		
		tempVal = it.next();
		assertEquals(tempVal, "string2");
		
		tempVal = it.next();
		assertEquals(tempVal, "string3");
		
		tempVal = it.next();
		assertEquals(tempVal, "string4");
		
		tempVal = it.next();
		assertEquals(tempVal, "string5");
		
		assertFalse(it.hasNext());
	}
}