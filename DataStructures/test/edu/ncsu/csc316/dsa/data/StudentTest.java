package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the student class for proper implementation and representation of a student
 * @author John Widdifield
 *
 */
public class StudentTest {

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

	/**
	 * This method sets up a few students to be used in unit tests.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Tests the setFirst method for proper setting of the first name.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests the setLast function for proper setting of the last name.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests the setId function for proper setting of the ID.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests the setGpa function for proper setting of the GPA.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests the setUnityID function for proper setting of the Unity ID.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the compareTo function for proper comparisons of students.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sThree.compareTo(sFive) > 0);
		assertTrue(sFour.compareTo(sOne) < 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	
	/**
	 * This tests the equals and hashcode functions for proper reporting of results.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsAndHash() {
		Student sNull = null;
		assertTrue(sOne.equals(sOne));
		assertFalse(sOne.equals(sFive));
		assertFalse(sOne.equals(sNull));
		assertFalse(sOne.equals("heyo"));
		assertFalse(sOne.equals(sFour));
		Student sOneTwo = new Student("OneFirst", "OneLast2", 1, 1, 1.0, "oneUnityID");
		assertFalse(sOne.equals(sOneTwo));
		Student sOneThree = new Student("OneFirst", "OneLast", 2, 1, 1.0, "oneUnityID");
		assertFalse(sOne.equals(sOneThree));
		
		assertEquals(sOne.hashCode(), sOne.hashCode());
		assertNotEquals(sTwo.hashCode(), sOne.hashCode());
		
	}
	
	/**
	 * This tests the toString method for proper reporting of sting representation
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), "OneFirst OneLast 1");
	}
	
	/**
	 * This tests the getCreditHours method for proper reporting of credit hours.
	 */
	@Test
	public void testGetCreditHours() {
		assertEquals(sOne.getCreditHours(), 1);
	}
}
