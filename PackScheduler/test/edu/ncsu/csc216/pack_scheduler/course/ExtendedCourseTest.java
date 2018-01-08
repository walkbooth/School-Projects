package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the extended functionality of Course from 
 * Guided Project 2.
 * @author Sarah Heckman
 */
public class ExtendedCourseTest { 
	
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
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests Course.getShortDisplayArray().
	 */
	@Test 
	public void testGetShortDisplayArray() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		String [] actualShortDisplay = c.getShortDisplayArray();
		assertEquals(NAME, actualShortDisplay[0]);
		assertEquals(SECTION, actualShortDisplay[1]);
		assertEquals(TITLE, actualShortDisplay[2]);
		assertEquals("MW 1:30PM-2:45PM", actualShortDisplay[3]);
		
		Course c1 = new Course("MA305", "Introduction to Linear Algebra", "005", CREDITS, INSTRUCTOR_ID, 10, "MWF", 0, 2359);
		assertEquals("MA305", c1.getName());
		assertEquals("Introduction to Linear Algebra", c1.getTitle());
		assertEquals("005", c1.getSection());
		assertEquals(CREDITS, c1.getCredits());
		assertEquals(INSTRUCTOR_ID, c1.getInstructorId());
		assertEquals("MWF", c1.getMeetingDays());
		assertEquals(0, c1.getStartTime());
		assertEquals(2359, c1.getEndTime());
		
		String [] actualShortDisplay1 = c1.getShortDisplayArray();
		assertEquals("MA305", actualShortDisplay1[0]);
		assertEquals("005", actualShortDisplay1[1]);
		assertEquals("Introduction to Linear Algebra", actualShortDisplay1[2]);
		assertEquals("MWF 12:00AM-11:59PM", actualShortDisplay1[3]);
	}

	/**
	 * Tests Course.getLongDisplayArray().
	 */
	@Test
	public void testGetLongDisplayArray() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		String [] actualLongDisplay = c.getLongDisplayArray();
		assertEquals(NAME, actualLongDisplay[0]);
		assertEquals(SECTION, actualLongDisplay[1]);
		assertEquals(TITLE, actualLongDisplay[2]);
		assertEquals("" + CREDITS, actualLongDisplay[3]);
		assertEquals(INSTRUCTOR_ID, actualLongDisplay[4]);
		assertEquals("MW 1:30PM-2:45PM", actualLongDisplay[5]);
		assertEquals("", actualLongDisplay[6]);
		
		Course c1 = new Course("MA305", "Introduction to Linear Algebra", "005", CREDITS, INSTRUCTOR_ID, 10, "MWF", 0, 2359);
		assertEquals("MA305", c1.getName());
		assertEquals("Introduction to Linear Algebra", c1.getTitle());
		assertEquals("005", c1.getSection());
		assertEquals(CREDITS, c1.getCredits());
		assertEquals(INSTRUCTOR_ID, c1.getInstructorId());
		assertEquals("MWF", c1.getMeetingDays());
		assertEquals(0, c1.getStartTime());
		assertEquals(2359, c1.getEndTime());
		String [] actualLongDisplay1 = c1.getLongDisplayArray();
		assertEquals("MA305", actualLongDisplay1[0]);
		assertEquals("005", actualLongDisplay1[1]);
		assertEquals("Introduction to Linear Algebra", actualLongDisplay1[2]);
		assertEquals("" + CREDITS, actualLongDisplay1[3]);
		assertEquals(INSTRUCTOR_ID, actualLongDisplay1[4]);
		assertEquals("MWF 12:00AM-11:59PM", actualLongDisplay1[5]);
		assertEquals("", actualLongDisplay1[6]);
	}

}