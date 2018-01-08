package edu.ncsu.csc216.pack_scheduler.user.schedule;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Contains information about a schedule of Courses. A course can be added or removed to the schedule, 
 * and the schedule can be returned as a 2D array containing course name, title, and section 
 * @author Conner McCarthy
 */
public class Schedule {
	/** Title of the schedule */
	private String title;
	/** ArrayList that contains the Courses in the schedule */
	private ArrayList<Course> schedule; 
	
	/**
	 * Constructs a Scheduler object with a default title "My Schedule",
	 * and creates an empty ArrayList of Courses
	 * 
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * Adds a course from the catalog to the schedule
	 * 
	 * @param addCourse Course being added
	 * @return true if a course is added. False if the course is not in the
	 *         catalog
	 * @throws IllegalArgumentException
	 *             if the course being added is already in the schedule
	 */
	public boolean addCourseToSchedule(Course addCourse) {
		for (Course course : schedule) {
			if (course.isDuplicate(addCourse)) {
				// course name is already in schedule
				throw new IllegalArgumentException("You are already enrolled in " + addCourse.getName());
			}
			//Checks for conflict
			try {
				course.checkConflict(addCourse);
			} catch (ConflictException c){
				// if there is a conflict 
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		//If there are no errors and the course is in the catalog, it is added to schedule
		schedule.add(addCourse);
		return true;
	}
	
	/**
	 * Removes a course from the schedule
	 * 
	 * @param removeCourse
	 *            course to be removed
	 * 
	 * @return true if the course was removed, false if it was not
	 */
	public boolean removeCourseFromSchedule(Course removeCourse) {
		for (Course course : schedule) {
			if (course.equals(removeCourse)) {
				schedule.remove(course);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the schedule
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * Returns a 2D string array of the catalog with course name, section and
	 * title, and meeting string
	 * 
	 * @return 2D string array
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleArray = new String[schedule.size()][5];
		for (int i = 0; i < scheduleArray.length; i++) {
			scheduleArray[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduleArray;
	}
	
	/**
	 * Sets the schedule's title
	 * @param title title to be set
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}
	
	/**
	 * Returns the schedule's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Gets the credits for the current schedule
	 * @return sum The number of credits in the schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (Course course : schedule) {
			sum += course.getCredits();
		}
		return sum;
	}
	/**
	 * Checks if you can add course to the schedule
	 * @param c course being checked
	 * @return true if you add, false if you cannot
	 */
	public boolean canAdd(Course c) {
		
		if(c == null || schedule.contains(c)) {
			return false;
		}
		try {
		addCourseToSchedule(c);
		removeCourseFromSchedule(c);
		return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
		
	}
}
