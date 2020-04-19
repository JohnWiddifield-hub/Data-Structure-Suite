package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
/**
 * This class tests ArrayBasedList for proper implementation
 * @author John Widdifield
 *
 */
public class ArrayBasedListTest {

	private List<String> list;

	/**
	 * Sets up for the following tests
	 */
	@Before
	public void setUp() {
		list = new ArrayBasedList<String>();
	}

	/**
	 * This tests the addIndex function
	 */
	@Test
	public void testAddIndex() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		assertEquals(1, list.size());
		assertEquals("one", list.get(0));
		assertFalse(list.isEmpty());
		
		list.add(1, "two");
		assertEquals(2, list.size());
		assertEquals("two", list.get(1));
		assertFalse(list.isEmpty());
		list.add(2, "three");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");
		list.add(6, "seven");
		list.add(7, "eight");
		list.add(8, "nine");
		list.add(9, "ten");
		list.add(10, "eleven");
		assertEquals(11, list.size());
		assertEquals("eleven", list.get(10));
		assertFalse(list.isEmpty());
		list.add(11, "twelve");
		assertEquals(12, list.size());
		assertEquals("twelve", list.get(11));
		assertFalse(list.isEmpty());
		list.add(0, "negOne");
		assertEquals("negOne", list.get(0));
		assertEquals("twelve", list.get(12));
		assertEquals("eleven", list.get(11));
		list.add(7, "six");
		assertEquals(list.get(7), "six");
		assertEquals("negOne", list.get(0));
		assertEquals("twelve", list.get(13));
		assertEquals("eleven", list.get(12));
		
		// Use the statements above to help guide your test cases
		// for data structures: Start with an empty data structure, then
		// add an element and check the accessor method return values.
		// Then add another element and check again. Continue to keep checking
		// for special cases. For example, for an array-based list, you might
		// continue adding until you trigger a resize operation to make sure
		// the resize operation worked as expected.
		
		try{
			list.add(15,  "fifteen");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		try{
			list.add(-9,  "ahhh");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}

	/**
	 * This tests the addLast function
	 */
	@Test
	public void testAddLast() {
		setUp();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.addLast("two");
		assertEquals(list.get(1), "two");
		setUp();
		list.addLast("one");
		assertEquals(list.get(0), "one");
		
	}

	/**
	 * This tests the last function
	 */
	@Test
	public void testLast() {
		setUp();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.addLast("two");
		assertEquals(list.last(), "two");
	}

	/**
	 * This tests the addFirst function
	 */
	@Test
	public void testAddFirst() {
		setUp();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.addLast("two");
		assertEquals(list.get(1), "two");
		list.addFirst("zero");
		assertEquals(list.get(0), "zero");
		assertEquals(list.get(1), "one");
		assertEquals(list.size(), 3);
	}

	/**
	 * This tests the first function
	 */
	@Test
	public void testFirst() {
		setUp();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.addLast("two");
		assertEquals(list.first(), "one");
	}

		/**
		 * This tests the iterator function
		 */
	@Test
	public void testIterator() {
		// Start with an empty list
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		// Create an iterator for the empty list
		Iterator<String> it = list.iterator();
		
		// Try different operations to make sure they work
		// as expected for an empty list (at this point)
		try{
			it.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
		assertFalse(it.hasNext());

		// Now add an element
		list.addLast("one");
		
		// Use accessor methods to check that the list is correct
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertEquals("one", list.get(0));
		
		// Create an iterator for the list that has 1 element
		it = list.iterator();
		
		// Try different iterator operations to make sure they work
		// as expected for a list that contains 1 element (at this point)
		assertTrue(it.hasNext());
		assertEquals("one", it.next());
		assertFalse(it.hasNext());
		try{
			it.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}

		it.remove();
		assertEquals(list.size(), 0);
		
		list.addLast("one");
		list.addLast("two");
		list.addLast("three");
		it = list.iterator();
		assertEquals("one", it.next());
		assertEquals("two", it.next());
		assertEquals("three", it.next());
	}

	/**
	 * This tests the removeIndex function
	 */
	@Test
	public void testRemoveIndex() {
		setUp();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.add(0, "one");
		list.addLast("two");
		list.addLast("three");
		
		assertEquals(list.remove(2), "three");
		assertEquals(list.size(), 2);
		assertEquals(list.isEmpty(), false);
		list.remove(0);
		list.remove(0);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		list.add(0, "one");
		list.addLast("two");
		list.addLast("three");
		assertEquals(list.remove(1), "two");
		assertEquals(list.get(0), "one");
		assertEquals(list.get(1), "three");
		
		list.add(2, "two");
		list.add(3, "four");
		list.add(4, "five");
		list.add(5, "six");
		list.add(6, "seven");
		list.add(7, "eight");
		list.add(8, "nine");
		list.add(9, "ten");
		
		list.remove(9);
		
	}

	/**
	 * This tests the removeFirst function
	 */
	@Test
	public void testRemoveFirst() {
		list.add(0, "one");
		list.addLast("two");
		list.addLast("three");
		assertEquals(list.removeFirst(), "one");
		assertEquals("two", list.get(0));
	}

	/**
	 * This tests the removeLast function
	 */
	@Test
	public void testRemoveLast() {
		list.add(0, "one");
		list.addLast("two");
		list.addLast("three");
		assertEquals(list.removeLast(), "three");
		assertEquals("two", list.get(1));
	}

	/**
	 * This tests the set function
	 */
	@Test
	public void testSet() {
		list.add(0, "one");
		list.addLast("two");
		list.addLast("three");
		assertEquals(list.set(1, "ten"), "two");
		assertEquals(list.set(2, "eleven"), "three");
		assertEquals(list.set(0, "nine"), "one");
		assertEquals(list.size(), 3);
	}
}