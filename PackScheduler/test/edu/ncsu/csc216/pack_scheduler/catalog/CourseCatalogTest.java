package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Tests class CourseCatalog. Some code was borrowed from WolfScheduler
 * @author Alex Johnson
 * @author Sarah Heckman
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt"; 
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
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

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception Thrown if the files can't be reset
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		RegistrationManager.getInstance().getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records_extended.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests the CourseCatalog constructor 
	 */
	@Test
	public void testNewCourseCatalog() {
		
		CourseCatalog c = new CourseCatalog();
		assertEquals("CourseCatalog should not have any Courses immediatly after construction", 
				0, c.getCourseCatalog().length);
		
	}
	
	/**
	 * Tests method loadCoursesFromFile of class CourseCatalog
	 */
	@Test
	public void testLoadCoursesFromFile() {
		
		CourseCatalog c1 = new CourseCatalog();
		assertEquals("CourseCatalog should not have any Courses immediatly after construction", 
				0, c1.getCourseCatalog().length);
	
		
		c1.newCourseCatalog();
		
		// test if invalid test file works
		try {
			c1.loadCoursesFromFile("abc_no_file_here.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Course from non-existance file was added", 0, c1.getCourseCatalog().length);
		}
		
		//Test with invalid file.  Should have an empty catalog and schedule. 
		CourseCatalog c2 = new CourseCatalog();
		c2.loadCoursesFromFile(invalidTestFile);
		assertEquals("Course(s) from an invalid file were added", 0, c2.getCourseCatalog().length);
		c2.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Test with valid file containing 8 courses. Since the file is already ordered
		//correctly this should pass either way, but checking for order anyway
		CourseCatalog c3 = new CourseCatalog();
        c3.loadCoursesFromFile(validTestFile);
        String[][] display = c3.getCourseCatalog();
		assertEquals("Not all Courses were loaded from a valid file", 8, display.length);	
		assertEquals("CSC116-001", display[0][0] + "-" + display[0][1]);
		assertEquals("CSC116-002", display[1][0] + "-" + display[1][1]);
		assertEquals("CSC116-003", display[2][0] + "-" + display[2][1]);
		assertEquals("CSC216-001", display[3][0] + "-" + display[3][1]);
		assertEquals("CSC216-002", display[4][0] + "-" + display[4][1]);
		assertEquals("CSC216-601", display[5][0] + "-" + display[5][1]);
		assertEquals("CSC226-001", display[6][0] + "-" + display[6][1]);
		assertEquals("CSC230-001", display[7][0] + "-" + display[7][1]);
		
	}
	
	
	/**
	 * Tests the method addCourseToCatalog CourseCatalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cat = new CourseCatalog();
		
		// Add a valid course and check its added correctly
		assertTrue(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
		String[][] course1 = cat.getCourseCatalog();
		assertEquals(1, course1.length);
		assertEquals(NAME, course1[0][0]);
		assertEquals(SECTION, course1[0][1]);
		assertEquals(TITLE, course1[0][2]);
		assertEquals("TH 1:30PM-2:45PM", course1[0][3]);
		
		// Add another valid course and check its added correctly
		assertTrue(cat.addCourseToCatalog("MA242", "Calculus 3", "005", CREDITS, INSTRUCTOR_ID, 10, "MT", 1130, 1345));
		String[][] course2 = cat.getCourseCatalog();
		assertEquals(2, course2.length);
		assertEquals("MA242", course2[1][0]);
		assertEquals("005", course2[1][1]);
		assertEquals("Calculus 3", course2[1][2]);
		assertEquals("MT 11:30AM-1:45PM", course2[1][3]);
		
		
		
		
		try {
			// add Course with invalid name
			assertFalse(cat.addCourseToCatalog("CSC4927", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}	
		try {
			// add Course with invalid title
			assertFalse(cat.addCourseToCatalog(NAME, "", SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}
		try {
			// add Course with invalid section
			assertFalse(cat.addCourseToCatalog(NAME, TITLE, "00A", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}	
		try {
			// add Course with invalid credits
			assertFalse(cat.addCourseToCatalog(NAME, TITLE, SECTION, 0, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}	
		assertFalse(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, "", 10, MEETING_DAYS, START_TIME, END_TIME));
		try {
			// add Course with invalid meeting days
			assertFalse(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, "S", START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}	
		try {
			// add Course with invalid start time
			assertFalse(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, 160, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}	
		try {
			// add Course with invalid end time
			assertFalse(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, 2515));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
		}	
		assertEquals("One of the above invalid courses was added", 2, cat.getCourseCatalog().length);
	}
	
	/**
	 * Tests method removeCourse of class CourseCatalog
	 */
	@Test 
	public void testRemoveCourse() {
		CourseCatalog cat = new CourseCatalog();
		 
		//Attempt to remove a Course from an empty catalog
		assertFalse(cat.removeCourseFromCatalog(NAME, SECTION));
		
		//Add some courses and remove them assertTrue(cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(cat.addCourseToCatalog("MA242", TITLE, "006", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals("Valid Courses were not added correctly", 1, cat.getCourseCatalog().length);
		assertTrue(cat.removeCourseFromCatalog("MA242", "006"));
		assertEquals("MA242-006 was not removed successfully", 0, cat.getCourseCatalog().length);
		

		cat.loadCoursesFromFile(validTestFile);		
		assertEquals(8, cat.getCourseCatalog().length);
		//Check that removing a course that doesn't exist when there are 
		//scheduled courses doesn't break anything
		assertFalse(cat.removeCourseFromCatalog("ABC123", "001"));  
		assertEquals(8, cat.getCourseCatalog().length);
		
		//Remove CSC116-001
		assertTrue(cat.removeCourseFromCatalog("CSC116", "001")); 
		assertEquals(7, cat.getCourseCatalog().length);
		
		//Remove all CSC216s
		assertTrue(cat.removeCourseFromCatalog("CSC216", "001"));
		assertTrue(cat.removeCourseFromCatalog("CSC216", "002"));
		assertTrue(cat.removeCourseFromCatalog("CSC216", "601"));
		assertEquals(4, cat.getCourseCatalog().length);
		
		//Remove CSC226
		assertTrue(cat.removeCourseFromCatalog("CSC226", "001"));
		assertEquals(3, cat.getCourseCatalog().length);
		
		//Remove CSC230
		assertTrue(cat.removeCourseFromCatalog("CSC230", "001")); 
		assertEquals(2, cat.getCourseCatalog().length);
		
		//Remove the rest of CSC116-002
		assertTrue(cat.removeCourseFromCatalog("CSC116", "002")); 
		assertTrue(cat.removeCourseFromCatalog("CSC116", "003")); 
		assertEquals(0, cat.getCourseCatalog().length);
		
		//Check that removing all doesn't break future adds
		assertTrue(cat.addCourseToCatalog("MA242", TITLE, "006", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
		assertEquals(1, cat.getCourseCatalog().length);
		
		//Remove MA242
		assertTrue(cat.removeCourseFromCatalog("MA242", "006"));
		assertEquals(0, cat.getCourseCatalog().length);
	}
	
	/**
	 * Test method getCourseFromCatalog of class CourseCatalog
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cat = new CourseCatalog();
		
		//Attempt to get a course that doesn't exist
		assertNull(cat.getCourseFromCatalog("CSC492", "001"));
		
		//Attempt to get a course that does exist
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		cat.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c1, cat.getCourseFromCatalog(NAME, SECTION));
		
		//Attempt to get another course that does exist
		Course c2 = new Course(NAME, TITLE, "005", CREDITS, INSTRUCTOR_ID, 10, "F", 100, 200);
		cat.addCourseToCatalog(NAME, TITLE, "005", CREDITS, INSTRUCTOR_ID, 10, "F", 100, 200);
		assertEquals(c2, cat.getCourseFromCatalog(NAME, "005"));
	}
	
	/**
	 * Tests method loadCourseCatalog of class CourseCatalog
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cat = new CourseCatalog(); 
		cat.loadCoursesFromFile(validTestFile);
		assertTrue(cat.addCourseToCatalog("MB242", TITLE, "006", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
		assertTrue(cat.addCourseToCatalog("MA242", TITLE, "006", CREDITS, INSTRUCTOR_ID, 10, MEETING_DAYS, START_TIME, END_TIME));
		
		
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cat.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
		//Row 8, testing sorting
		assertEquals("MA242", catalog[8][0]);
		assertEquals("006", catalog[8][1]);
		assertEquals(TITLE, catalog[8][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[8][3]);
		//Row 9, testing sorting
		assertEquals("MB242", catalog[9][0]);
		assertEquals("006", catalog[9][1]);
		assertEquals(TITLE, catalog[9][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[9][3]);
	}
	
	
	/**
	 * Tests method saveCourseCatalog of class CourseCatalog 
	 */
	@Test
	public void testSaveCourseCatalog() {
		
		// tests exporting with null file name
		CourseCatalog cat1 = new CourseCatalog();
		cat1.loadCoursesFromFile(validTestFile);
		try {
			cat1.saveCourseCatalog(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid file name", e.getMessage());
		}
		
		// tests exporting with empty string file name
		CourseCatalog cat2 = new CourseCatalog();
		cat2.loadCoursesFromFile(validTestFile);
		try {
			cat2.saveCourseCatalog("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid file name", e.getMessage());
		}
		
		// tests exporting with an invalid file path
		CourseCatalog cat3 = new CourseCatalog();
		cat3.loadCoursesFromFile(validTestFile);
		try {
			cat3.saveCourseCatalog("/a.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		
		//Test that normal catalog exports correctly
		CourseCatalog cat4 = new CourseCatalog();
		RegistrationManager.getInstance().getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records_extended.txt");
		cat4.loadCoursesFromFile(validTestFile);
		cat4.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_course_export.txt", "test-files/actual_empty_export.txt");
		
	}
	
	/** 
	 * Helper method to compare two files for the same contents. Written
	 * by Sarah Heckmann, code from WolfScheduler.
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
