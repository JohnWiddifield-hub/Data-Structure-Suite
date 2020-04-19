package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class tests the positionalLinkedList class for proper implementation
 * of the data structure
 * 
 * @author John Widdifield
 *
 */
public class PositionalLinkedListTest {

	private PositionalList<String> list;
	
	/**
	 * This sets up the tests
	 */
	@Before
	public void setUp() {
		list = new PositionalLinkedList<String>();
	}
	
	/**
	 * This tests the first function for proper implementation
	 */
	@Test
	public void testFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try{
			list.first();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertEquals(first, list.first());
		
		Position<String> second = list.addFirst("two");
		assertEquals(2, list.size());
		assertEquals(second, list.first());
	}
	
	/**
	 * This tests the last function for proper implementation
	 */
	@Test
	public void testLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try{
			list.last();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
		
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());
		
		Position<String> second = list.addLast("two");
		assertEquals(2, list.size());
		assertEquals(second, list.last());
	}
	
	/**
	 * This tests the addFirst function for proper implementation
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addFirst("one");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(list.first(), first);
	}
	
	/**
	 * This tests the addLast function for proper implementation
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		assertEquals(1, list.size());
		assertEquals(first, list.last());
	}
	
	/**
	 * This tests the before and after functions for proper implementation
	 */
	@Test
	public void testBeforeAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(list.before(second), first);
		assertEquals(list.before(third), second);
		assertEquals(list.after(second), third);
		assertEquals(list.after(first), second);
	}

	/**
	 * This tests the addbefore and addAfter functions for proper implementation
	 */
	@Test
	public void testAddBeforeAfter() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> third = list.addLast("three");
		list.addBefore(first, "zero");
		list.addAfter(third, "four");
		assertEquals(list.before(first).getElement(), "zero");
		assertEquals(list.after(third).getElement(), "four");
		assertEquals(list.size(), 4);
	}
	
	/**
	 * This tests the set function for proper implementation
	 */
	@Test
	public void testSet() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		list.set(first, "two");
		list.set(second, "three");
		list.set(third, "four");
		assertEquals(list.first().getElement(), "two");
		assertEquals(list.after(first).getElement(), "three");
		assertEquals(list.last().getElement(), "four");
		assertEquals(list.size(), 3);
	}
	
	/**
	 * This tests the remove function for proper implementation
	 */
	@Test
	public void testRemove() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		Position<String> first = list.addLast("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		list.remove(first);
		list.remove(third);
		list.addBefore(second, "one");
		list.addAfter(second, "three");
		list.remove(second);
		assertEquals(list.first().getElement(), "one");
		assertEquals(list.last().getElement(), "three");
		assertEquals(list.after(list.first()).getElement(), "three");
	}
	
	/**
	 * This tests the Iterator function for proper implementation
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
				assertEquals("one", list.first().getElement());
				
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
				assertEquals(list.first().getElement(), "one");
				assertEquals(list.before(list.last()).getElement(), "two");
				assertEquals(list.last().getElement(), "three");
				it = list.iterator();
				assertEquals("one", it.next());
				assertEquals("two", it.next());
				assertEquals("three", it.next());
				it.remove();
				assertEquals("two", list.last().getElement());
				list.addLast("three");
				list.addLast("four");
				it = list.iterator();
				it.next();
				it.next();
				it.remove(); // should get rid of two
				it.next();
				it.remove(); //should get rid of three leaving one and four
				it.next();
				assertFalse(it.hasNext());
				assertEquals(list.first().getElement(), "one");
				assertEquals(list.last().getElement(), "four");
			

	}
	
	/**
	 * This tests the positions function for proper implementation
	 */
	@Test
	public void testPositions() {
		assertEquals(0, list.size());
		Position<String> first = list.addFirst("one");
		Position<String> second = list.addLast("two");
		Position<String> third = list.addLast("three");
		assertEquals(3, list.size());
		
		Iterator<Position<String>> it = list.positions().iterator();
		assertTrue(it.hasNext());
		assertEquals(first, it.next());
		assertEquals(second, it.next());
		assertEquals(third, it.next());
		
		it.remove();
		assertEquals(list.first(), first);
		assertEquals(list.last(), second);
		
	}

}