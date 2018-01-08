package edu.ncsu.csc216.pack_scheduler.user;


import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;


/**
 * Creates a faculty and sets all the info
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan Cui
 */
public class Faculty extends User implements Comparable<Faculty> {

	/** The default max courses */
	public final static int MAX_COURSES = 3;
	/** Faculty's max courses */
	private int maxCourses;
	/** Faculty's schedule*/
	private FacultySchedule schedule;

	/**
	 * Constructs a faculty member for first name, last name, faculty id, email and hashedPW,
	 * @param firstName the faculty's first name
	 * @param lastName the faculty's last name
	 * @param id the faculty's id
	 * @param email the faculty's email
	 * @param hashPW the faculty's password as a hash
	 * @param maxCredits the max number of credits the faculty member can teach
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCredits);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Gets the max courses for the faculty
	 * @return the faculty's max courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the facultys max courses. Must not be less than 1 or greater than 3.
	 * @param maxCourses the maxCourses to set 
	 * @throws IllegalArgumentException if maxCourses is null or empty string
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < 1 || maxCourses > 3) {
			throw new IllegalArgumentException("Invalid max courses");
		} else {
			this.maxCourses = maxCourses;
		}
	}
	
	/**
	 * Returns the Faculty's schedule
	 * @return The Faculty's schedule
	 */
	public FacultySchedule getSchedule(){
		return this.schedule;
	}

	/**
	 * Creates a string representation of a Faculty
	 * @return a string representation of a Faculty
	 */
	@Override
	public String toString() {
		return this.getFirstName() + ',' + this.getLastName() + ',' + this.getId() + ',' + this.getEmail() + ',' + this.getPassword() + ',' + this.getMaxCourses();

	}
	
	/**
	 * Return if courses number is over than max course
	 * @return true/false
	 */ 
	public boolean isOverloaded()
	{
		return schedule.getNumScheduledCourses() > getMaxCourses();
	}

	/**
	 * Compares Facultys to determine their order
	 * @param s the Faculty to compare this Faculty to
	 * @return an integer value of 0 if Facultys are equal, negative if it should be ordered
	 * behind in or positive if it should be ordered in front of
	 */
	@Override
	public int compareTo(Faculty s) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
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
		if (!(obj instanceof Faculty))
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false; 
		// assume schedules are equal
		return true;
	}
	
	
    
}
