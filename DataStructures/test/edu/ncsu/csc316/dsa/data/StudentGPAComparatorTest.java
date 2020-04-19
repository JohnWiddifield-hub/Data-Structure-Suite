package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is to test the StudentGPAComparator class for proper implementation based on 
 * student's GPA values.
 * 
 * @author John Widdifield
 *
 */
public class StudentGPAComparatorTest {

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

	/** Student comparator object */
	public StudentGPAComparator comparator;

	/**
	 * Sets the student information to be used in Unit Tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Tests the compare method to ensure proper implementation
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);

		assertTrue(comparator.compare(sThree, sTwo) < 0);
		assertTrue(comparator.compare(sFour, sThree) < 0);
		assertTrue(comparator.compare(sFive, sFour) < 0);
		assertTrue(comparator.compare(sOne, sFive) > 0);
		assertEquals(comparator.compare(sThree, sThree), 0);
	}

}
