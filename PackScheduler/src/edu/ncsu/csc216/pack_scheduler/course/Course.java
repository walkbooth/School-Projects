package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Contains and validates all the information of a college course and formatting functions for the start 
 * and end time. Has a name, title, section number, number of credit hours, instructor id and meeting 
 * days. Depending on how the class meets, a course has also have a start and end time.
 * time. 
 * @author Alex Johnson (abjohns6@ncsu.edu)
 */
public class Course extends Activity implements Comparable<Course> {
	/** The length of a section code */
	private static final int SECTION_LENGTH = 3;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Coure's roll of students */
	private CourseRoll roll;
	
	/** CourseNameValidator object */
	private CourseNameValidator validator;
	
	
	/**
	 * Creates a Course for the given name, title, section, credit hours, instructor ID, meeting days, start time 
	 * and end time.
	 * @param name the course name starts with a 3 letter department code and followed by a 3 digit class code
	 * @param title the title of the class, more descriptive than the name
	 * @param section the section number of the course
	 * @param credits the number of credit hours for the course
	 * @param instructorId the school's id of the instructor
	 * @param meetingDays the meeting days of the course. A combination of weekdays or arranged
	 * @param startTime the start time for the course in military time
	 * @param endTime the end time for the course in military time
	 * @param enrollmentCap The enrollment capacity
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime); 
		setName(name); 
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId); 
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course for the given name, title, section, credit hours, instructor ID and meeting days.
	 * @param name the course name starts with a 3 letter department code and followed by a 3 digit class code
	 * @param title the title of the class, more descriptive than the name
	 * @param section the section number of the course
	 * @param credits the number of credit hours for the course
	 * @param instructorId the school's id of the instructor
	 * @param meetingDays the meeting days of the course. A combination of weekdays or arranged
	 * @param enrollmentCap The enrollment capacity
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
	
	/**
	 * Gets the name of the course.
	 * @return the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course. 
	 * @param name the name to set the course name to
	 * @throws IllegalArgumentException if the course is name is not valid 
	 */
	private void setName(String name) {
		
		validator = new CourseNameValidator();
		try {
			if (!validator.isValid(name)){
				throw new IllegalArgumentException();
			} 
			this.name = name;
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Gets the section number (String) of the course.
	 * @return the section number (String) of the course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the section number of the course. Must be a String of three digits.
	 * @param section the section to set the course section to
	 * @throws IllegalArgumentException if section is not three digits
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException("Invalid section");
		}
		
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section");
		} else {
			// checks that every character is a digit
			char[] sect = section.toCharArray();
			for (int i = 0; i < sect.length; i++) {
				if (!Character.isDigit(sect[i])) {
					throw new IllegalArgumentException("Invalid section");
				}
			}
		}
		this.section = section;
	}

	/**
	 * Gets the number of credit hours for the course.
	 * @return the credit hours of the course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the number of credit hours of the course. Credits must be an integer 1 to 5.
	 * @param credits the number of credit hours to set the hour credit hours to
	 * @throws IllegalArgumentException if credits aren't a number 1 to 5
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.credits = credits;
	}

	/**
	 * Gets the instructor id of the course.
	 * @return the instructorId of the course
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructor id for the course. ID must be a String with one or more characters.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId is an empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId != null && instructorId.isEmpty()) {
			instructorId = null;
			return;
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the meeting days of the course. Must be a string of the characters M,T,W,H,F or just A.
	 * @param meetingDays the String to set meeting days to
	 * @throws IllegalArgumentException if the meeting days string consists is null or the empty string. 
	 * If meeting days isn't a string of the characters M,T,W,H,F or just A.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null) {  
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (meetingDays.contains("A")) {
			if (!meetingDays.equals("A")) {
				throw new IllegalArgumentException("Invalid meeting days");
			} else {
				super.setMeetingDays(meetingDays);
			}
		} else {
			char[] md = meetingDays.toCharArray();
			for (int i = 0; i < meetingDays.length(); i++) {
				if (md[i] != 'M' && md[i] != 'T'  && md[i] != 'W' && md[i] != 'H' && md[i] != 'F' ) {
					throw new IllegalArgumentException("Invalid meeting days");
				} 
			}
			super.setMeetingDays(meetingDays);
		}
	}
	
	/**
	 * Generates a Array of Strings used to display the Course.
	 * @return a short Array of Strings to display Course information.
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[]{name, section, getTitle(), getMeetingString(), Integer.toString(roll.getOpenSeats())};
	}

	/**
	 * Generates a Array of Strings used to display the Course.
	 * @return a long Array of Strings to display Course information.
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[]{name, section, getTitle(), getCredits() + "", instructorId, getMeetingString(), ""};
	}
	/**
	 * Gets the current CourseRoll
	 * @return roll The current CourseRoll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
	
    /**
     * Determines if an Activity in a duplicate of this Course
     * @param activity the activity to 
     * @return whether the Activity is a duplicate
     */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity == null) {
			throw new NullPointerException();
		}
		
		if (activity instanceof Course) {
			Course course = (Course) activity;
			if (course.getName().equals(getName())) {
				return true;	
			}
		}
		return false;
	}
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return string representation of Course
	 */
	@Override
	public String toString() { 
	    if (getMeetingDays().equals("A")) { 
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Generates a unique int for the object based on Course's state.
	 * @return a unique int based on the objects state.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + instructorId.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + section.hashCode();
		return result;
	}

	/**
	 * Compares two Courses to see if they are they same one. Compares type of object, section,
	 * name, the instructor and the credit hours.
	 * @return whether the Activities are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId != null) {
			if (!instructorId.equals(other.instructorId))
				return false;
		} else {
			if (instructorId != other.instructorId)
				return false;
		}
		if (!name.equals(other.name))
			return false;
		if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Compares two Course's to determine their order in a list
	 * @param o the course to compare to
	 * @return 0 if Courses are the same, and -1 or 1 depending on whether the Course is 
	 * greater than or less than the one it's being compared to
	 */
	@Override
	public int compareTo(Course o) {

		String first  = (name  + " " +  section);
		String second = (o.getName() + " " + o.getSection());
		
		for (int i = 0; i < first.length(); i++) {
			char firstChar  = first.charAt(i);
			char secondChar = second.charAt(i);
			if (firstChar != secondChar) {
				return firstChar > secondChar ? 1 : -1;
			}
		}
		return 0;
	}
	
}
