package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** The students first name */
	private String first;
	/** The students last name */
	private String last;
	/** The Students ID */
	private int id;
	/** The credit hours that the student is taking */
	private int creditHours;
	/** the Students GPA */
	private double gpa;
	/** The Students Unity ID */
	private String unityID;
	
	/**
	 * Constructs a student using their first and last name, id number, credit hours, gpa, and Unity Id
	 * @param firstn	Student's First Name
	 * @param lastn		Student's Last Name
	 * @param idnum		Students Id Number
	 * @param credits	Students Credit Hours
	 * @param gpaVal	Students GPA as a double
	 * @param unity		Students Unity ID
	 */
	public Student(String firstn, String lastn, int idnum, int credits, double gpaVal, String unity) {
		setFirst(firstn);
		setLast(lastn);
		setId(idnum);
		setCreditHours(credits);
		setGpa(gpaVal);
		setUnityID(unity);
	}

	/**
	 * Gets the Students first name
	 * @return The Students First Name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the students first name.
	 * @param first The first name to set.
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Gets the Students Last name.
	 * @return The Students Last Name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the students last name
	 * @param last the last name to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Gets the students ID number
	 * @return the id number of the student
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the students ID number
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the credit hours of the student
	 * @return the credit hours of the student
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the students credit hours
	 * @param creditHours the credit hours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Gets the students GPA
	 * @return the gpa of the student
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the students GPA
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Gets the students UnityID
	 * @return the unityID of the student
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the students Unity ID
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}

	@Override
	public int compareTo(Student student) {
		if(last.compareTo(student.getLast()) != 0){
			return last.compareTo(student.getLast());
		} else if(first.compareTo(student.getFirst()) != 0) {
			return last.compareTo(student.getFirst());
		} else if(id - student.getId() != 0) {
			return id - student.getId();
		} else return 0;
	}

	@Override
	public String toString() {
		return first + " " + last + " " + id;
	}
	
	
}
