package edu.ncsu.csc316.dsa.manager;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.io.StudentReader;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * StudentManager manages Student information. StudentManager
 * can sort a Student roster.
 * @author Dr. King
 *
 */
public class StudentManager {

	/**
	 * A roster of students in the system
	 */
	private Student[] roster;
	
	/**
	 * The sorting algorithm to use when sorting
	 */
	private Sorter<Student> sorter;
	
	/**
	 * Initializes a StudentManager
	 * @param pathToFile - the path to the input student CSV file
	 * @param sorter - a reference to the sorting algorithm to use when sorting
	 */
	public StudentManager(String pathToFile, Sorter<Student> sorter)
	{
		roster = StudentReader.readInputAsArray(pathToFile);
		this.sorter = sorter;
	}
	
	/**
	 * Constructs a StudentManager
	 * @param pathToFile - the path to the input student CSV file
	 */
	public StudentManager(String pathToFile)
	{
		this(pathToFile, new InsertionSorter<Student>());
	}
		
    /**
     * Returns a sorted array of Students
     *
     * @return the sorted array of Students
     */
	public Student[] sort()
	{
		sorter.sort(roster);
		return roster;
	}

}
