package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests class Activity
 * @author Alex Johnson
 */
public class ActivityTest {

	/**
	 * Tests the method checkConflict in class Activity for when there 
	 * is a Conflict
	 */
	@Test
	public void testCheckConflict() {
		
		
		// Test checkConflict doesn't accept null
		try {
			(new Course("EN101", "English I", "001", 3, "prof", 10, "MW", 1330, 1410)).checkConflict(null); 
			fail("checkConflict accepts null.");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException); 
			assertEquals("possibleConflictingActivity is null.", e.getMessage());
		}
		
		// Note: these Activities are different from testCheckConflictClose objects
		Activity food = new Course("ST370", "Stats", "001", 3, "prof", 10, "MTWHF", 1230, 1330);
		Activity walk = new Course("EN201", "English 2", "001", 3, "prof", 10, "MTWHF", 1325, 1335);
		Activity engl = new Course("EN101", "English I", "001", 3, "prof", 10, "MW", 1330, 1410);
		Activity math = new Course("MA242", "Calculus III", "001", 4, "teach", 10, "MWF", 1400, 1450);
		
		// Test Course to Course conflict
		try {
			engl.checkConflict(math); 
			fail("Courses during the same time and day didn't throw ConflictException.");
		} catch (ConflictException e) {
			assertEquals("Schedule conflict.", e.getMessage());
		}

		// Test Event to Course conflict
		try {
			walk.checkConflict(engl);
			fail("An Event during the same time and day as a Course didn't throw ConflictException.");
		} catch (ConflictException e) {
			assertEquals("Schedule conflict.", e.getMessage());
		}

		// Test Event to Event conflict
		try {
			food.checkConflict(walk);
			fail("Events during the same time and day didn't throw ConflictException.");
		} catch (ConflictException e) {
			assertEquals("Schedule conflict.", e.getMessage());
		}

		// Test conflict parallelism
		try {
			engl.checkConflict(math);
			fail("Courses during same time/day threw exception one way but not the other.");
		} catch (ConflictException e) {
			assertEquals("Schedule conflict.", e.getMessage());
		}
		
		// Test adding an Arranged course
		Activity ea = new Course("EN101", "English I", "001", 3, "prof", 10, "A", 1330, 1410);
		try {
			engl.checkConflict(ea);
			ea.checkConflict(engl);
			assertEquals("Arranged", ea.getMeetingString());
			assertEquals("MW 1:30PM-2:10PM", engl.getMeetingString());
		} catch (ConflictException e) {
			fail("Threw ConflictException for an Arranged course.");
		}

	}
	
	/**
	 * Tests the method checkConflict in class Activity for more boundary 
	 * checks
	 */
	@Test
	public void testCheckConflictClose() {
		// Note: these Activities are different from testCheckConflict objects
		Activity math = new Course("MA242", "Calculus III", "001", 4, "teach", 10, "MW", 1300, 1350);
		Activity stat = new Course("ST370", "Statistics and Probability", "001", 3, "teach", 10, "MW", 1351, 1515);
		Activity engl = new Course("EN101", "English I", "001", 3, "prof", 10, "TH", 1300, 1350);
		
		// Tests Courses at same time on different days doesn't throw an exception
		try {
			math.checkConflict(engl);
			engl.checkConflict(math);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:00PM-1:50PM", math.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:00PM-1:50PM", engl.getMeetingString());
		} catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		
		// Tests Courses that have bordering times do not conflict again, repetition of previous
		try {
			math.checkConflict(stat);
			stat.checkConflict(math);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:00PM-1:50PM", math.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:51PM-3:15PM", stat.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when an Activities end time was anothers start time.");
		}

	}

}
