package edu.ncsu.csc316.dsa.stack;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test case for testing LinkedStack
 * 
 * @author John Widdifield and NCSU Staff
 *
 */
public class LinkedStackTest {

	/**
	 * Stack to be manipulated
	 */
	private Stack<String> stack;
	
	/**
	 * This sets up for the tests for linkedstack
	 */
	@Before
	public void setUp() {
		stack = new LinkedStack<String>();
	}
	
	/**
	 * This tests the push method for linkedstack
	 */
	@Test
	public void testPush() {
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		
		stack.push("one");
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		
		stack.push("two");
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
		
		stack.push("three");
		assertEquals(3, stack.size());
		assertFalse(stack.isEmpty());
		
	
	}

	/**
	 * This tests the pop method for linkedstack
	 */
	@Test
	public void testPop() {
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		stack.push("five");
		stack.push("six");
		assertEquals(6, stack.size());
		
		assertEquals("six",  stack.pop());
		assertEquals(5, stack.size());
		
		assertEquals("five",  stack.pop());
		assertEquals(4, stack.size());
		
		assertEquals("four",  stack.pop());
		assertEquals(3, stack.size());
		
		assertEquals("three",  stack.pop());
		assertEquals(2, stack.size());
		
		assertEquals("two",  stack.pop());
		assertEquals(1, stack.size());
		
		assertEquals("one",  stack.pop());
		assertEquals(0, stack.size());
		
		try {
			stack.pop();
			fail("EmptyStackException should have been thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	/**
	 * This tests the top method for linkedstack
	 */
	@Test
	public void testTop() {	
		assertEquals(0, stack.size());
		try {
			stack.pop();
			fail("Should have thrown an exception");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
		stack.push("one");
		assertEquals(stack.top(), "one");
	}

}