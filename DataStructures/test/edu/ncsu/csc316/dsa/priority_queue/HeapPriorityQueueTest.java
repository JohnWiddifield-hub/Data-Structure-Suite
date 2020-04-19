package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * This will test the heap priority queue implementation
 * @author John Widdifield and ncsu staff
 *
 */
public class HeapPriorityQueueTest {

	private PriorityQueue<Integer, String> heap;
	
	/**
	 * This will set the heap up for the tests below
	 */
	@Before
	public void setUp() {
		heap = new HeapPriorityQueue<Integer, String>();
	}
	
	/**
	 * This will test the insert method
	 */
	@Test
	public void testInsert() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		heap.insert(8, "eight");
		assertEquals(1, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(8, (int)heap.min().getKey());
		
		heap.insert(2, "two");
		assertEquals(2, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(2, (int)heap.min().getKey());
		
		heap.insert(1, "one");
		assertEquals(3, heap.size());
		assertFalse(heap.isEmpty());
		assertEquals(1, (int)heap.min().getKey());
	}
	
	/**
	 * This will test the min method
	 */
	@Test
	public void testMin() {
		assertTrue(heap.isEmpty());
		assertTrue(heap.size() == 0);
		
		assertNull(heap.min());
		
		heap.insert(5, "five");
		heap.insert(2, "two");
		heap.insert(6, "six");
		heap.insert(3, "three");
		
		assertEquals(2, (int)heap.min().getKey());
		heap.insert(1, "one");
		assertEquals(1, (int)heap.min().getKey());
		
		heap.deleteMin();
		assertEquals(2, (int)heap.min().getKey());
	}
	
	/**
	 * This will test the delete min method
	 */
	@Test 
	public void deleteMin() {
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
		
		assertNull(heap.deleteMin());
		
		heap.insert(5, "five");
		heap.insert(2, "two");
		heap.insert(6, "six");
		heap.insert(3, "three");
		
		assertEquals(2, (int)heap.deleteMin().getKey());
		assertEquals(3, (int)heap.deleteMin().getKey());
		
	}
	
	/**
	 * This will test the student heap implementation of the priority queue
	 */
	@Test
	public void testStudentHeap() {
		PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
		Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
		Student s2 = new Student("J", "S", 2, 1, 2, "js2");
		Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
		Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
		Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
		
		assertTrue(sHeap.isEmpty());
		assertEquals(0, sHeap.size());
		
		sHeap.insert(s1, "s1");
		sHeap.insert(s2, "s2");
		sHeap.insert(s3, "s3");
		sHeap.insert(s4, "s2");
		sHeap.insert(s5, "s3");
		
		assertEquals(5, sHeap.size());
		assertFalse(sHeap.isEmpty());
		
		assertEquals(s1, sHeap.min().getKey());
		
		assertEquals(s1, sHeap.deleteMin().getKey());
		assertEquals(s2, sHeap.min().getKey());
		assertEquals(s2, sHeap.deleteMin().getKey());
		
	}
}