/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Represents the catalog of Courses. Sorts a list of Courses, loads Courses from
 * a file, removes and adds courses, and writes the catalog to a file.
 * @author Daniel Grist
 * @author Victor Schroder
 * @author Alex Johnson
 */
public class CourseCatalog {
	/** The entire course catalog */
	private SortedList<Course> catalog;
	/** The length of the short display array */
	private static final int SHORT_INFO_LENGTH = 4;

	/**
	 * Constructs a new CourseCatalog
	 * 
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Initializes the list of Courses 
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Loads Courses from a specified file
	 * @param fileName the path to the course file to load
	 * @throws IllegalArgumentException if the fileName is invalid or any IO exception is thrown
	 */
	public void loadCoursesFromFile(String fileName) {
		if (fileName == null || fileName.length() < 1) {
			throw new IllegalArgumentException("Cannot find file."); 
		}
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Creates a Course for a give name, title, section, credits, instructorId, meetinDays,
	 * startTime and endTime, then adds to the Course Catalog.
	 * @param name the name of the course to add
	 * @param title the title of the course 
	 * @param section the section of the course
	 * @param credits the number of credit hours of the course
	 * @param instructorId the instructor id for the professor of the course
	 * @param enrollmentCap the maximum amount of students that can be in a class
	 * @param meetingDays the meeting days of the course
	 * @param startTime the start time (in military time) of the course
	 * @param endTime the end time (in military time) of the course
	 * @return true if the Course was created and added successfully, otherwise false.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {

		Course c1 = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for(int i = 0; i < this.catalog.size(); i++) {
			if(this.catalog.get(i).getName().equals(name) && this.catalog.get(i).getSection().equals(section))
				return false;
		}
		catalog.add(c1);
		return true;
	}

	/**
	 * Removes a Course for a given name and section, which is unique.
	 * @param name the name of the Course to remove
	 * @param section the section of the Course to remove
	 * @return whether the Course was successfully removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++)
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		return false;
	}

	/**
	 * Gets a Course for a given name and section.
	 * @param name the name of the Course to return
	 * @param section the section of the Course to return
	 * @return the Course for the given name and section, or null if Course does not exist
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Gets the Course catalog as a 2d array of strings for display
	 * @return the Course catalog as a 2d array of strings
	 */
	public String[][] getCourseCatalog() {
		String[][] output = new String[this.catalog.size()][SHORT_INFO_LENGTH];
		for (int i = 0; i < this.catalog.size(); i++) {
			output[i] = this.catalog.get(i).getShortDisplayArray();
		}
		return output;
	}

	/**
	 * Writes the Course Catalog to a file, for a given file name
	 * @param fileName the file name of the file to write
	 * @throws IllegalArgumentException if there is any error writing the file
	 */
	public void saveCourseCatalog(String fileName) {
		if (fileName == null || fileName.length() < 1) {
			throw new IllegalArgumentException("Invalid file name"); 
		}
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}
}
