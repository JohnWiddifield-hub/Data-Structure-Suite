package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;


/**
 * This class simply tests the insertion sorter class for proper implementation of the insertion
 * sorter algorithm
 * 
 * @author John Widdifield
 *
 */
public class MergeSorterTest {

	/** InsertionSorter object for integers */
	public MergeSorter<Integer> integerSorter;
	/** InsertionSorter object for Students */
	public MergeSorter<Student> studentSorter;

	/**
	 * This method sets up the tests sorter
	 */
	@Before
	public void setUp() {
		integerSorter = new MergeSorter<Integer>();
		studentSorter = new MergeSorter<Student>();
	}

	/** 
	 * This method tests the sorting method for sorting integers
	 */
	@Test
	public void testSortIntegers() {
		
		Integer[] data = {1, 2, 3, 4};
		Integer[] data2 = {4, 3, 2, 1};
		Integer[] data3 = {1, 2, 3, 4};
		
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

	/**
	 * Tests the insertion sorter on student objects
	 */
	@Test
	public void testSortStudent() {
		Student student = new Student("John1", "Smith1", 100100100, 18, 3.9, "jsmith");
		Student student2 = new Student("John2", "Smith2", 100100101, 18, 3.9, "jsmith");
		Student student3 = new Student("John3", "Smith3", 100100102, 18, 3.9, "jsmith");
		Student student4 = new Student("John4", "Smith4", 100100103, 18, 3.9, "jsmith");
		
		Student[] students = new Student[4];
		students[0] = student;
		students[1] = student4;
		students[2] = student3;
		students[3] = student2;
		
		studentSorter.sort(students);
		assertEquals(students[0], student);
		assertEquals(students[1], student2);
		assertEquals(students[2], student3);
		assertEquals(students[3], student4);
	}
}