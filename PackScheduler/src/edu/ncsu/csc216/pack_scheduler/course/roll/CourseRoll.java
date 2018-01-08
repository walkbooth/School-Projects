package edu.ncsu.csc216.pack_scheduler.course.roll;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;
import edu.ncsu.csc216.pack_scheduler.util.Queue;

/**
 * Keeps track of a roll of students.
 * Students can enroll or drop.
 * @author Conner McCarthy
 *
 */
public class CourseRoll {
	
	/** LinkedAbstractList of students*/
	private LinkedAbstractList<Student> roll;
	/** Enrollment cap for a class*/
	private int enrollmentCap; 
	
	/** min enrollment cap in a class*/
	private static final int MIN_ENROLLMENT = 10;
	/** max enrollment cap in a class*/
	private static final int MAX_ENROLLMENT = 250;
	/** The list of people waiting to get into the class */
	private Queue<Student> waitlist;
	/** The capacity of the waitlist */
	private static final int WAITLIST_CAP = 10;
	/** The Course of the course roll */
	private Course course;
	
	/**
	 * Constructs a CourseRoll object with an enrollment cap
	 * @param course the course that the course roll is holding students for
	 * @param cap Enrollment cap of the roll
	 */
	public CourseRoll(Course course, int cap) {
		roll = new LinkedAbstractList<Student>(cap);
		waitlist = new LinkedQueue<Student>(WAITLIST_CAP);
		setEnrollmentCap(cap);
		setCourse(course);
	}
	
	/**
	 * Sets the 
	 * @param course the course to set
	 */
	public void setCourse (Course course) {
		if (course == null) {
			throw new IllegalArgumentException("Cannot set null course");
		}
		this.course = course;
	}
	
	/**
	 * Returns the enrollment cap
	 * @return the enrollment cap
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	/**
	 * Gets the number of students are are on the waitlist
	 * @return the number of students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
	/**
	 * Sets the enrollment cap if "cap" is between 10 and 250 and enrollment cap is less that the roll size
	 * @param cap enrollment cap
	 * @throws IllegalArgumentException if the cap is less than 10 or more than 250
	 */
	public void setEnrollmentCap(int cap) {
		if (cap < MIN_ENROLLMENT || cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Cap must be between 10 and 250");
		} 
		
		if (cap < this.roll.size()) {
			throw new IllegalArgumentException("Cap is less that current roll size");
		}
		
		this.roll.setCapacity(cap);
		this.enrollmentCap = cap;
	}
	
	/**
	 * Enrolls a student 
	 * @param s student to enroll
	 * @throws IllegalArgumentException if the student is eligible to enroll
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Course cannot be added to schedule.");
		}
		// if there's open seats, enroll in the class
		if (getOpenSeats() > 0) {
			s.getSchedule().addCourseToSchedule(course);
			roll.add(s);
		} else if (waitlist.size() < WAITLIST_CAP) {
			s.getSchedule().addCourseToSchedule(course);
		    waitlist.enqueue(s);
		} else {
			throw new IllegalArgumentException("Course cannot be added to schedule.");
		}
		
	}
	
	/**
	 * Drops a student from the roll
	 * @param s student to drop
	 * @throws IllegalArgumentException if the student is null, or if the student is not enrolled
	 */
	public void drop(Student s) {
		if (s == null) { 
			throw new IllegalArgumentException("Null student cannot be removed from CourseRoll.");
		}
		
		int idx = roll.indexOf(s);
		if (idx == -1) { // if not on course roll possibly on waitlist
			for (int i = 0; i < waitlist.size(); i++) {
				Student test = waitlist.dequeue();
				if (!s.getId().equals(test.getId())) { // only enqunue if not dropped
					waitlist.enqueue(test);
				} else {
		            s.getSchedule().removeCourseFromSchedule(course);
				}
			}
		} else { // else drop them from the course
			roll.remove(idx);
			s.getSchedule().removeCourseFromSchedule(course);
			if (!waitlist.isEmpty())
				roll.add(waitlist.dequeue());
		}
	}
	
	/**
	 * Returns the number of open seats in the class
	 * @return the number of open seats in the class
	 */
	public int getOpenSeats() {
		return this.getEnrollmentCap() - this.roll.size(); 
	}
	
	/**
	 * Returns true if the student can enroll in a class, false if they cannot
	 * @param s Student being checked
	 * @return true if the student can enroll in a class, false if they cannot
	 */
	public boolean canEnroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Cannot check null student");
		}
		// if no open seats and waitlist is full
		if(getOpenSeats() == 0 && waitlist.size() == WAITLIST_CAP) {
			return false; 
		} else {
			// check for duplicates
			for (int i = 0; i < roll.size(); i++) {
				if (s.equals(roll.get(i))) {
					return false;
				}
			}
			return true; 
		}
	}
}
