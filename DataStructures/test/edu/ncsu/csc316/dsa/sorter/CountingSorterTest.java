package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Tests the CountingSorter class for proper sorting using the countingsort algorithm
 * @author John Widdifield
 *
 */
public class CountingSorterTest {
	
	/** Student number one */
	public Student sOne;
	/** Student number two */
	public Student sTwo;
	/** Student number three */
	public Student sThree;
	/** Student number four */
	public Student sFour;
	/** Student number five */
	public Student sFive;
	
	/** Counting sorter object to be used for unit testing */
	public CountingSorter<Student> sorter;

	/**
	 * Sets up a few students for use in unit testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Tests sorting students using counting sort
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
		
		Student[] original2 = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(original2);
		assertEquals(sOne, original2[0]);
		assertEquals(sTwo, original2[1]);
		assertEquals(sThree, original2[2]);
		assertEquals(sFour, original2[3]);
		assertEquals(sFive, original2[4]);
		
		Student[] original3 = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(original3);
		assertEquals(sOne, original3[0]);
		assertEquals(sTwo, original3[1]);
		assertEquals(sThree, original3[2]);
		assertEquals(sFour, original3[3]);
		assertEquals(sFive, original3[4]);
		
	}
	

}
