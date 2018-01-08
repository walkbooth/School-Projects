package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Handles all registration config tasks. Allows a student or registrar to
 * login. Follows the singleton pattern.
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public class RegistrationManager {

	/** The single instance of RegistratoinManager */
	private static RegistrationManager instance;
	/** The catalog of available courses */
	private CourseCatalog courseCatalog;
	/** The directory of all students that can register for classes */
	private StudentDirectory studentDirectory;
	/** The directory of all faculty that can register for classes */
	private FacultyDirectory facultyDirectory;
	/** The registrar account */
	private User registrar;
	/** The current user logged in */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** The raw password for the registrar */
	private static final String PW = "Regi5tr@r";
	/** The hashed password for the registrar */
	private static String hashPW;

	// Static code block for hashing the registrar user's password
	{
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(PW.getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Prevents other classes creating instances of the REgistration Manager
	 */
	private RegistrationManager() {
		registrar = new Registrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Gets the single instance of the registration manager.
	 * @return the instance of registration manager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Gets the catalog of available courses
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Gets the directory of all students in the system
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Gets the directory of all faculty in the system
	 * @return the student directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Logs a User in for an username and id
	 * @param id the username of the user
	 * @param password the user's password
	 * @return if the login was successful
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
		
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		} else { 
			User u = studentDirectory.getStudentById(id);
		    if (u == null) {
		    	u = facultyDirectory.getFacultyById(id);
		    	if (u == null) {
		    		throw new IllegalArgumentException("User doesn't exist.");
		    	}
		    }
		    
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (u.getPassword().equals(localHashPW)) {
					currentUser = u;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		return false;
	}

	/**
	 * Logs the current user out of the registration system.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns the current user selected by the system.
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears all student and course data. The course catalog and student 
	 * directory are cleared.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		facultyDirectory.newFacultyDirectory();
		studentDirectory.newStudentDirectory();
		logout();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    Student s = (Student)currentUser;
	    CourseRoll roll = c.getCourseRoll();
	    if (roll.canEnroll(s) && s.canAdd(c)) {
	        roll.enroll(s);
	        return true;
	    } else {
	      	return false;
        }
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    
	    Student s = (Student)currentUser;
	    if (!s.canAdd(c)) {
	     	c.getCourseRoll().drop(s);
	       	return s.canAdd(c); // if they can add the course they successfully dropped it
	    } else { 
	       	return false;
        }
	}

	/**
	 * Adds a Faculty member to a course
	 * @param c the course to add the faculty to
	 * @param f the faculty member to add
	 * @return if the faculty was added to the schedule
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			c.setInstructorId(null);
			throw new IllegalArgumentException("User must be Registrar to edit faculty");
		}
		if (c.getInstructorId() == null) {
			f.getSchedule().addCourseToSchedule(c);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes a Faculty member from a course
	 * @param c the course to remove the faculty member from
	 * @param f the faculty member to remove
	 * @return if the faculty was removed from the schedule
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			c.setInstructorId(null);
			throw new IllegalArgumentException("User must be Registrar to edit faculty");
		}
		for (int i = 0; i < f.getSchedule().getNumScheduledCourses(); i++) {
			if (f.getSchedule().getScheduledCourses()[i][0].equals(c.getName()) && 
				f.getSchedule().getScheduledCourses()[i][1].equals(c.getSection())) {
				f.getSchedule().removeCourseFromSchedule(c);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the faculty's schedule
	 * @param f the faculty to reset schedule
	 */
	public void resetFacultySchedule (Faculty f) {
		if (currentUser == null || !(currentUser instanceof Registrar)) {
			throw new IllegalArgumentException("User must be Registrar to edit faculty");
		}
		f.getSchedule().resetSchedule();
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

	/**
	 * The Registrar account. Is private because the only time it is used in the 
	 * RegistrationManager class.
	 * @author Alex Johnson
	 * @author James Ritchey
	 * @author Connor McCarthy 
	 */
	private static class Registrar extends User {
		/** The 'first name' of the registrar */
		private static final String FIRST_NAME = "Wolf";
		/** The 'last name' of the registrar */
		private static final String LAST_NAME = "Scheduler";
		/** The id of the registrar */
		private static final String ID = "registrar";
		/** The 'email' of the registrar */
		private static final String EMAIL = "registrar@ncsu.edu";

		/**
		 * Create a registrar user with the user id of registrar and password of
		 * Regi5tr@r. Note that hard coding passwords in a project is HORRIBLY
		 * INSECURE, but it simplifies testing here. This should NEVER be done
		 * in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
}