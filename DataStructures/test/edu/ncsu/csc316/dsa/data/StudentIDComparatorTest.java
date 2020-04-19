package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the StudentIDComparator class for proper comparison of students based on their
 * ID values.
 * @author John Widdifield
 *
 */
public class StudentIDComparatorTest {

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

	/** Comparator to use for testing */
	private StudentIDComparator comparator;

	/**
	 * This sets up necessary students to be used in unit tests
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * This method tests the compare method for proper reporting of comparisons based on ID
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) > 0);
		assertFalse(comparator.compare(sOne, sTwo) > 0);

		assertTrue(comparator.compare(sThree, sTwo) > 0);
		assertTrue(comparator.compare(sFour, sThree) > 0);
		assertTrue(comparator.compare(sFive, sFour) > 0);
		assertTrue(comparator.compare(sOne, sFive) < 0);
		assertEquals(comparator.compare(sThree, sThree), 0);
	}


}
