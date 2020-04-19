package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * This class tests the heap adaptable priority queue implementation
 * @author John Widdifield and ncsu staff
 *
 */
public class HeapAdaptablePriorityQueueTest {

	private HeapAdaptablePriorityQueue<Integer, String> heap;
	
	/**
	 * This will set up all of my tests
	 */
	@Before
	public void setUp() {
		heap = new HeapAdaptablePriorityQueue<Integer, String>();
	}
	
	/**
	 * This will test my replace key method
	 */
	@Test
	public void testReplaceKey() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		heap.insert(7, "seven");
		heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		heap.insert(4, "four");
		heap.insert(3, "three");
		
		heap.insert(2, "two");
		heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.replaceKey(e8,  -8);
		assertEquals(-8, (int)heap.min().getKey());
		assertEquals("eight", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e0,  -10);
		assertEquals(-10, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		
		heap.replaceKey(e5,  -11);
		assertEquals(-11, (int)heap.min().getKey());
		assertEquals("five", heap.min().getValue());
		assertEquals(9, heap.size());
	}
	
	/**
	 * This will test my replace value method
	 */
	@Test
	public void testReplaceValue() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		heap.insert(7, "seven");
		heap.insert(6, "six");
		heap.insert(5, "five");
		heap.insert(4, "four");
		heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.replaceValue(e8,  "EIGHT");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("zero", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("EIGHT",  e8.getValue());
		
		heap.replaceValue(e0,  "ZERO");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("ZERO",  e0.getValue());
		
		heap.replaceValue(e2,  "TWO");
		assertEquals(0, (int)heap.min().getKey());
		assertEquals("ZERO", heap.min().getValue());
		assertEquals(9, heap.size());
		assertEquals("TWO",  e2.getValue());
	}
	
	/**
	 * This will test my remove method
	 */
	@Test
	public void testRemove() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		Entry<Integer, String> e8 = heap.insert(8, "eight");
		Entry<Integer, String> e7 = heap.insert(7, "seven");
		Entry<Integer, String> e6 = heap.insert(6, "six");
		Entry<Integer, String> e5 = heap.insert(5, "five");
		Entry<Integer, String> e4 = heap.insert(4, "four");
		Entry<Integer, String> e3 = heap.insert(3, "three");
		Entry<Integer, String> e2 = heap.insert(2, "two");
		Entry<Integer, String> e1 = heap.insert(1, "one");
		Entry<Integer, String> e0 = heap.insert(0, "zero");
		assertEquals(9, heap.size());
		
		heap.remove(e0);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(8, heap.size());
		
		heap.remove(e8);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(7, heap.size());
		
		heap.remove(e4);
		assertEquals(1, (int)heap.min().getKey());
		assertEquals("one", heap.min().getValue());
		assertEquals(6, heap.size());
		
		heap.remove(e1);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(5, heap.size());
		
		heap.remove(e3);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(4, heap.size());
		
		heap.remove(e5);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(3, heap.size());
		
		heap.remove(e6);
		assertEquals(2, (int)heap.min().getKey());
		assertEquals("two", heap.min().getValue());
		assertEquals(2, heap.size());
		
		heap.remove(e2);
		assertEquals(7, (int)heap.min().getKey());
		assertEquals("seven", heap.min().getValue());
		assertEquals(1, heap.size());
		
		heap.remove(e7);
		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());
	}
	
	/**
	 * This will test my student implementation of a HAPQ
	 */
	@Test
	public void testStudentHeap() {
		AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		Student s6 = new Student("A", "B", 1, 1, 1, "ab5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		sHeap.insert(s1, "s1");
		assertFalse(sHeap.isEmpty());
		sHeap.insert(s2, "s2");
		sHeap.insert(s4, "s4");
		sHeap.insert(s5, "s5");
		sHeap.insert(s3, "s3");
		
		assertEquals(s1, sHeap.min().getKey());
		assertEquals(5, sHeap.size());
		assertFalse(sHeap.isEmpty());
		
		sHeap.remove(sHeap.min());
		assertEquals(sHeap.min().getKey(), s2);
		
		sHeap.replaceValue(sHeap.min(), "s-1");
		sHeap.replaceKey(sHeap.min(), s6);
		
		assertEquals(sHeap.min().getKey(), s6);
		assertEquals(sHeap.min().getValue(), "s-1");

		
	}
}