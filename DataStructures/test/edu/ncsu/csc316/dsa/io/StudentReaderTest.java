package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This class tests the StudentReader class for proper reading of students from a file.
 * @author John Widdifield
 *
 */
public class StudentReaderTest {
	
	/**
	 * Method which tests the readInputAsArray method.
	 */
	@Test
	public void testReadFile() {
		StudentReader reader = new StudentReader();
		@SuppressWarnings("static-access")
		Student[] contents = reader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * This method tests the the reader gets the students information right
	 */
	@Test
	public void testInfo() {
		StudentReader reader = new StudentReader();
		@SuppressWarnings("static-access")
		Student[] contents = reader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals(contents[0].toString(), "Amber Michael 1");
		assertEquals(contents[0].getGpa(), 1.1, 0.1);
		assertEquals(contents[0].getCreditHours(), 10);
		
	}
}
