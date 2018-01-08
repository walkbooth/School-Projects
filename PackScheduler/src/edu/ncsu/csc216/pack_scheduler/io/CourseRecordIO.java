package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author Daniel Grist
 * @author Sarah Heckman
 */
public class CourseRecordIO {
	
	/**
	 * Constructor for CourseRecordIO()
	 * @throws IllegalArgumentException because this class should not be constructed
	 */
	public CourseRecordIO(){
		throw new IllegalArgumentException("This class should not be constructed.");
	}
    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return courses a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     * @throws IllegalArgumentException if the course cannot construct or if there is a problem processing the line
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new File(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}
	/**
	 * Initially set the Course instructorId to null when a Course is first created.
     * Then the method should check if there is a Faculty with the given instructorId. 
     * If so, the Course should be added to the Faculty's FacultySchedule.  
     * FacultySchedule.addCourseToSchedule() will update the Course object if the Faculty is added.
	 * 
	 * @param nextLine the next line of a file
	 * @return c the next course
	 */
	private static Course readCourse(String nextLine) {
		if (nextLine == null || nextLine.equals("")) {
			throw new IllegalArgumentException();
		}

		Scanner line = new Scanner(nextLine);
		line.useDelimiter(",");

		FacultyDirectory dir = RegistrationManager.getInstance().getFacultyDirectory();
		Course c = null;
		try {
			String name = line.next();
			String title = line.next();
			String section = line.next();
			int credits = line.nextInt();

			String instructorId = line.next();
			int enrollmentCap = line.nextInt();
			String meetingDays = line.next();

			if (meetingDays.equals("A")) {
				if (line.hasNextInt()) {
					line.close();
					throw new IllegalArgumentException();
				} else {
					c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
				}
			} else {
				int startTime = line.nextInt();
				int endTime = line.nextInt();
				c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime,
						endTime);
			}
			
			if (!instructorId.isEmpty()) {
				Faculty f = dir.getFacultyById(instructorId);
				if (f != null) {
					f.getSchedule().addCourseToSchedule(c);
					c.setInstructorId(instructorId);
				}
			}

		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		line.close();

		return c;
	}


	/**
     * Writes information about each close to the file given
     * @param fileName the file in which the course information will be written
     * @param courses the courses that will be written in fileName
     * @throws IOException if the file is empty or null
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < courses.size(); i++) {
    	    fileWriter.println(courses.get(i).toString());
    	}

    	fileWriter.close();
        
    }

}