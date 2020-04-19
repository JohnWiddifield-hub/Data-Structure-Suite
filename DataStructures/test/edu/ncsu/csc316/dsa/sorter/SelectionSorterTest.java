/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.sorter.SelectionSorter;

/**
 * This class is to test the selection sort implementation in DataStructures
 * 
 * @author John Widdifield
 *
 */
public class SelectionSorterTest {

	/**
	 * SelectionSorter object to be used in testing using integers
	 */
	public SelectionSorter<Integer> integerSorter;
	/**
	 * This method sets up the tests sorter
	 */
	@Before
	public void setUp() {
		
		integerSorter = new SelectionSorter<Integer>();
	}
	
	/**
	 * Tests the sort function for proper sorting of lists
	 */
	@Test
	public void sortTest() {
		Integer[] data = {1, 2, 3, 4};
		Integer[] data2 = {4, 3, 2, 1};
		Integer[] data3 = {3, 2, 4, 1};
		
		integerSorter.sort(data);
		assertEquals(1, data[0].intValue());
		assertEquals(2, data[1].intValue());
		assertEquals(3, data[2].intValue());
		assertEquals(4, data[3].intValue());
		
		integerSorter.sort(data2);
		assertEquals(1, data2[0].intValue());
		assertEquals(2, data2[1].intValue());
		assertEquals(3, data2[2].intValue());
		assertEquals(4, data2[3].intValue());
		
		integerSorter.sort(data3);
		assertEquals(1, data3[0].intValue());
		assertEquals(2, data3[1].intValue());
		assertEquals(3, data3[2].intValue());
		assertEquals(4, data3[3].intValue());
		
	}

}
