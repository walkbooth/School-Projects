package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a student and sets all the info
 * 
 * @author Daniel Grist
 * @author Alex Johnson
 * @author Victor Schroder
 */

public class Student extends User implements Comparable<Student> {

	/** The default max credits */
	public final static int MAX_CREDITS = 18;
	/** Student's max credits */
	private int maxCredits;
	/** Student's schedule*/
	private Schedule schedule;
	
	/**
	 * Constructs a student for first name, last name, student id, email, hashedPW and max credits,
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student id
	 * @param email the student's email
	 * @param hashPW the student's password as a hash
	 * @param maxCredits the max credits that the student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}

	/**
	 * Constructs a student for first name, last name, student id, email and hashedPW,
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's id
	 * @param email the student's email
	 * @param hashPW the student's password as a hash
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, 18);
	}

	/**
	 * Gets the max credits for the student
	 * @return the student's max credit
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the students max credits. Must not be less than 3 or greater than 18.
	 * @param maxCredits the maxCredits to set 
	 * @throws IllegalArgumentException if maxCredits is null or empty string
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		} else {
			this.maxCredits = maxCredits;
		}
	}
	/**
	 * Returns the Student's schedule
	 * @return The Student's schedule
	 */
	public Schedule getSchedule(){
		return this.schedule;
	}

	/**
	 * Creates a string representation of a Student
	 * @return a string representation of a Student
	 */
	@Override
	public String toString() {
		return this.getFirstName() + ',' + this.getLastName() + ',' + this.getId() + ',' + this.getEmail() + ',' + this.getPassword() + ',' + this.getMaxCredits();

	}

	/**
	 * Compares Students to determine their order
	 * @param s the Student to compare this Student to
	 * @return an integer value of 0 if Students are equal, negative if it should be ordered
	 * behind in or positive if it should be ordered in front of
	 */
	@Override
	public int compareTo(Student s) {
		String first  = (this.getLastName()   + " " +   this.getFirstName() + " " +   this.getId()).toLowerCase();
		String second = (s.getLastName() + " " + s.getFirstName() + " " + s.getId()).toLowerCase();
		
		for (int i = 0; i < first.length(); i++) {
			char firstChar  = first.charAt(i);
			char secondChar = second.charAt(i);
			if (firstChar != secondChar) {
				return firstChar > secondChar ? 1 : -1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Tests if the Student can add a Course
	 * @param c the course to test
	 * @return if the student can add a Course to their schedule
	 */
	public boolean canAdd (Course c) {
		if (c == null) {
			throw new IllegalArgumentException("Course can't be null");
		}
		
		if (!schedule.canAdd(c)) {
			return false;
		} else if (schedule.getScheduleCredits() + c.getCredits() > maxCredits) {
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
}
