package edu.ncsu.csc316.dsa.queue;


import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a JUnit test case meant to test the ArrayBasedQueue class for proper functionality
 * @author John Widdifield and NCSU Staff
 *
 */
public class ArrayBasedQueueTest {

	/**
	 * This is the queue which will be manipulated to test the class
	 */
    private Queue<String> queue;
    
    /**
     * This sets up the queue for the tests
     */
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }
    
    /**
     * This tests the enqueue function for arraybasedqueue
     */
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        queue.enqueue("seven");
        queue.enqueue("eight");
        queue.enqueue("nine");
        assertEquals(9, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("ten");
        assertEquals(10, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("eleven");
        assertEquals(11, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(queue.dequeue(), "one");
        assertEquals(queue.dequeue(), "two");
        assertEquals(queue.dequeue(), "three");
        assertEquals(queue.dequeue(), "four");
        assertEquals(queue.dequeue(), "five");
        assertEquals(queue.dequeue(), "six");
        assertEquals(queue.dequeue(), "seven");
        assertEquals(queue.dequeue(), "eight");
        assertEquals(queue.dequeue(), "nine");
        assertEquals(queue.dequeue(), "ten");
        assertEquals(queue.dequeue(), "eleven");
        
    }
    
    /**
     * This tests the dequeue function for arraybasedqueue
     */
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        assertEquals(6, queue.size());
        
        assertEquals("one",  queue.dequeue());
        assertEquals(5, queue.size());
        
        assertEquals("two",  queue.dequeue());
        assertEquals(4, queue.size());
        
        assertEquals("three",  queue.dequeue());
        assertEquals(3, queue.size());
        
        assertEquals("four",  queue.dequeue());
        assertEquals(2, queue.size());
        
        assertEquals("five",  queue.dequeue());
        assertEquals(1, queue.size());
        
        assertEquals("six",  queue.dequeue());
        assertEquals(0, queue.size());
        
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");     
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    
    /**
     * This tests the front function for arraybasedqueue
     */
    @Test
    public void testFront() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        queue.enqueue("seven");
        queue.enqueue("eight");
        queue.enqueue("nine");
        assertEquals(9, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("ten");
        assertEquals(10, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("eleven");
        assertEquals(11, queue.size());
        assertFalse(queue.isEmpty());
        
        assertEquals(queue.front(), "one");
        assertEquals(queue.dequeue(), "one");
        assertEquals(queue.dequeue(), "two");
        assertEquals(queue.dequeue(), "three");
        assertEquals(queue.dequeue(), "four");
        assertEquals(queue.dequeue(), "five");
        assertEquals(queue.dequeue(), "six");
        assertEquals(queue.front(), "seven");
        assertEquals(queue.dequeue(), "seven");
        assertEquals(queue.dequeue(), "eight");
        assertEquals(queue.dequeue(), "nine");
        assertEquals(queue.dequeue(), "ten");
        assertEquals(queue.front(), "eleven");
        assertEquals(queue.dequeue(), "eleven");
        assertEquals(queue.size(), 0);
    }

}
