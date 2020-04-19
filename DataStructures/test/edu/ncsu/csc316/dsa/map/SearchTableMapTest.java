package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This class tests the SearchTableMap class for proper implementation of the data structure.
 * @author John Widdifield and NCSU Staff
 *
 */
public class SearchTableMapTest {

	private Map<Integer, String> map;
	private Map<Student, Integer> studentMap;
	
	/**
	 * This will set up new maps for each test method
	 */
	@Before
	public void setUp() {
		map = new SearchTableMap<Integer, String>();
		studentMap = new SearchTableMap<Student, Integer>();
	}
	
	/**
	 * This will test the put method for the data structure
	 */
	@Test
	public void testPut() {
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertNull(map.put(3, "string3"));
		assertEquals("SearchTableMap[3]", map.toString());
		assertEquals(1, map.size());
		
		assertFalse(map.isEmpty());
		assertNull(map.put(2, "string2"));
		assertEquals("SearchTableMap[2, 3]", map.toString());
		assertEquals(2, map.size());
		
		assertFalse(map.isEmpty());
		assertNull(map.put(5, "string5"));
		assertEquals("SearchTableMap[2, 3, 5]", map.toString());
		assertEquals(3, map.size());
		
		assertFalse(map.isEmpty());
		assertNull(map.put(4, "string4"));
		assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
		assertEquals(4, map.size());
	}
	
	/**
	 * This will test the get method for the data structure
	 */
	@Test
	public void testGet() {
		assertTrue(map.isEmpty());
		for(int i = -100; i < 101; i++) {
			assertNull(map.get(i));
		}
		
		assertNull(map.put(1, "string1"));
		
		for(int i = -100; i < 1; i++) {
			assertNull(map.get(i));
		}
		
		for(int i = 2; i < 101; i++) {
			assertNull(map.get(i));
		}
		assertNull(map.put(3, "string3"));
		for(int i = -100; i < 1; i++) {
			assertNull(map.get(i));
		}
		assertNull(map.get(2));
		for(int i = 4; i < 101; i++) {
			assertNull(map.get(i));
		}
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(6, "string6"));
		assertNull(map.put(7, "string7"));
		assertNull(map.put(8, "string8"));
		assertNull(map.put(9, "string9"));
		assertNull(map.put(10, "string10"));
		
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string1", map.get(1));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string2", map.get(2));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string5", map.get(5));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertNull(map.get(0));
		
		assertEquals("string6", map.get(6));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string7", map.get(7));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string8", map.get(8));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string9", map.get(9));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertEquals("string10", map.get(10));
		assertEquals("SearchTableMap[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", map.toString());
		
		assertNull(map.get(11));
		assertNull(map.get(-1));
		assertNull(map.get(-10));
		assertNull(map.get(12));
		assertNull(map.get(20));
		
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
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertNull(map.remove(0));
		assertEquals(5, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
		
		assertEquals(map.remove(1), "string1");
		assertEquals(4, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
		
		assertEquals(map.remove(5), "string5");
		assertEquals(3, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[2, 3, 4]", map.toString());
		
		assertEquals(map.remove(3), "string3");
		assertEquals(2, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[2, 4]", map.toString());
		
		assertEquals(map.remove(2), "string2");
		assertEquals(1, map.size());
		assertFalse(map.isEmpty());
		assertEquals("SearchTableMap[4]", map.toString());
		
		assertEquals(map.remove(4), "string4");
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
		assertEquals("SearchTableMap[]", map.toString());
	}
	
	/**
	 * This will test data structure for proper usage of the generic types and comparators
	 */
	@Test
	public void testStudentMap() {
		Student s1 = new Student("J", "K", 1, 0, 0, "jk");
		Student s2 = new Student("J", "S", 2, 0, 0, "js");
		Student s3 = new Student("S", "H", 3, 0, 0, "sh");
		Student s4 = new Student("J", "J", 4, 0, 0, "jj");
		Student s5 = new Student("L", "B", 5, 0, 0, "lb");
		
		studentMap.put(s1,  100);
		assertEquals(1, studentMap.size());
		
		studentMap.put(s3,  199);
		assertEquals(2, studentMap.size());
		
		studentMap.put(s5,  74);
		assertEquals(3, studentMap.size());
	
		studentMap.put(s4,  13);
		assertEquals(4, studentMap.size());
		
		studentMap.put(s2,  10000);
		assertEquals(5, studentMap.size());
		
		assertEquals(studentMap.toString(), "SearchTableMap[L B 5, S H 3, J J 4, J K 1, J S 2]");
		
	}
	
	/**
	 * This will test the iterator method for the data structure
	 */
	@Test
	public void testIterator() {
		assertNull(map.put(3, "string3"));
		assertNull(map.put(5, "string5"));
		assertNull(map.put(2, "string2"));
		assertNull(map.put(4, "string4"));
		assertNull(map.put(1, "string1"));
		
		Iterator<Integer> it = map.iterator();
		int count = 1;
		while(it.hasNext()) {
			int key = it.next();
			assertEquals(key, count);
			count++;
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
		Map.Entry<Integer, String> entry3 = it.next();
		assertEquals(2, (int)(entry3.getKey()));
		assertEquals("string2", (String)(entry3.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry5 = it.next();
		assertEquals(3, (int)(entry5.getKey()));
		assertEquals("string3", (String)(entry5.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry2 = it.next();
		assertEquals(4, (int)(entry2.getKey()));
		assertEquals("string4", (String)(entry2.getValue()));
		
		assertTrue(it.hasNext());
		Map.Entry<Integer, String> entry4 = it.next();
		assertEquals(5, (int)(entry4.getKey()));
		assertEquals("string5", (String)(entry4.getValue()));
		
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