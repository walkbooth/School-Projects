package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule class
 * @author Conner McCarthy
 *
 */
public class ScheduleTest {
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/** Test Courses */
	private Course testCourse1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
	/** Test Courses */
	private Course testCourse2 = new Course("CSC226", "Discrete Mathematics for Computer Scientists", SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 1500, 1600);
	/** Test Courses */
	private Course testCourse3 = new Course("CSC116", "Intro to Programming - Java", "002", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 1630, 1700);
	
	/**
	 * tests the Schedule constructor
	 */
	@Test 
	public void testSchedule() {
		Schedule sch = new Schedule();
		assertEquals(0, sch.getScheduledCourses().length);
		assertEquals("My Schedule", sch.getTitle());
	}
	
	/**
	 * Tests the addCourseToSchedule method
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule sch = new Schedule();
			
			
		//Attempt to add a course that does exist
		assertTrue(sch.addCourseToSchedule(testCourse1));
		assertEquals(1, sch.getScheduledCourses().length);
		String [] course = sch.getScheduledCourses()[0];
		assertEquals(NAME, course[0]);
		assertEquals(SECTION, course[1]);
		assertEquals(TITLE, course[2]);
		
		Course c = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 1000, 1100);
		
		//Attempt to add a course that already exists, even if different section
		try {
			sch.addCourseToSchedule(c);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
		
		//Attempt to add a course with a conflict
		try {
			sch.addCourseToSchedule(new Course("CSC116", TITLE, "002", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 1300, 1400));
			sch.addCourseToSchedule(new Course("CSC230", TITLE, "001", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}		
	}
	
	/**
	 * Tests the removeCourseFromSchedule method
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule sch = new Schedule();
		
		//Attempt to remove from empty schedule
		assertFalse(sch.removeCourseFromSchedule(testCourse1));
		
		
		//Add some courses and remove them
		assertTrue(sch.addCourseToSchedule(testCourse1));
		assertTrue(sch.addCourseToSchedule(testCourse2));
		assertTrue(sch.addCourseToSchedule(testCourse3));
		assertEquals(3, sch.getScheduledCourses().length);
		
		//Check that removing a course that doesn't exist when there are 
		//scheduled courses doesn't break anything
		assertFalse(sch.removeCourseFromSchedule(new Course("CSC434", TITLE, "003", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 700, 800)));
		assertEquals(3, sch.getScheduledCourses().length);
		
		//Remove CSC226
		assertTrue(sch.removeCourseFromSchedule(testCourse2));
		assertEquals(2, sch.getScheduledCourses().length);
		
		//Remove CSC116
		assertTrue(sch.removeCourseFromSchedule(testCourse3));
		assertEquals(1, sch.getScheduledCourses().length);

		
		//Remove CSC216
		assertTrue(sch.removeCourseFromSchedule(testCourse1));
		assertEquals(0, sch.getScheduledCourses().length);
		
		//Check that removing all doesn't break future adds
		assertTrue(sch.addCourseToSchedule(new Course("CSC332", TITLE, "004", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME)));
		assertEquals(1, sch.getScheduledCourses().length);

	}
	
	/**
	 * Tests the resetSchedule method
	 */
	@Test
	public void testResetSchedule() {
		Schedule sch = new Schedule();
		
		//Add some courses and reset schedule
		assertTrue(sch.addCourseToSchedule(testCourse1));
		assertTrue(sch.addCourseToSchedule(testCourse2));
		assertTrue(sch.addCourseToSchedule(testCourse3));
		assertEquals(3, sch.getScheduledCourses().length);
		
		sch.resetSchedule();
		assertEquals(0, sch.getScheduledCourses().length);

		
		//Check that resetting doesn't break future adds
		assertTrue(sch.addCourseToSchedule(testCourse3));
		assertEquals(1, sch.getScheduledCourses().length);

	}
	
	/**
	 * Tests the getScheduledCourses method
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule sch = new Schedule();
		
		//Add some courses and get schedule
		//Name, section, title
		assertTrue(sch.addCourseToSchedule(testCourse1));
		assertTrue(sch.addCourseToSchedule(testCourse2));
		assertTrue(sch.addCourseToSchedule(testCourse3));
		
		String [][] schedule = sch.getScheduledCourses();
		//Row 1
		assertEquals("CSC216", schedule[0][0]);
		assertEquals("001", schedule[0][1]);
		assertEquals("Programming Concepts - Java", schedule[0][2]);
		//Row 2
		assertEquals("CSC226", schedule[1][0]);
		assertEquals("001", schedule[1][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", schedule[1][2]);
		//Row 3
		assertEquals("CSC116", schedule[2][0]);
		assertEquals("002", schedule[2][1]);
		assertEquals("Intro to Programming - Java", schedule[2][2]);
	}
	
	/**
	 * Tests the setTitle method
	 */
	@Test
	public void testSetTitle() {
		Schedule sch = new Schedule();
		
		//Set Title and check that changed
		sch.setTitle("New Title");
		assertEquals("New Title", sch.getTitle());
		
		//Check that exception is thrown if null title and no
		//change to title already there.
		try {
			sch.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("New Title", sch.getTitle());
		}
	}


}
