package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests FacultyDirectory.
 * @author Sarah Heckman
 */
public class FacultyDirectoryTest { 
	
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test 
	public void testNewFacultyDirectory() {
		//Test that if there are faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		
		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		
		//Test invalid faculty
		fd.loadFacultyFromFile(invalidTestFile);
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
		
		//Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		// test duplicate
		assertFalse(fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertTrue(fd.addFaculty(FIRST_NAME, LAST_NAME, "another1", EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertFalse(fd.addFaculty(FIRST_NAME, LAST_NAME, "another1", EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		
		//Test invalid Password
		try{
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Invalid password", e.getMessage());
		}
		
		//Test invalid Password
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test invalid Password
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test invalid Password
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		
		//Test different passwords
		try{
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "asdg", MAX_COURSES);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Passwords do not match", e.getMessage());
		} 

		
	} 
	
	/**
	 * Tests method getFacultyById of class FacultyDirectory
	 */
	@Test
	public void testGetFacultyById(){
		FacultyDirectory fd = new FacultyDirectory();
		fd.loadFacultyFromFile(validTestFile);
		
		// Test for null parameter
		Faculty s1 = null;
		try {
			s1 = fd.getFacultyById(null);
			fail("Didn't throw exception for an illegal argument");
		} catch (IllegalArgumentException e) {
			assertNull(s1);
			assertEquals("Invalid id.", e.getMessage());
		}
		
		// Test for empty string parameter
		Faculty s2 = null;
		try {
			s2 = fd.getFacultyById("");
			fail("Didn't throw exception for an illegal argument");
		} catch (IllegalArgumentException e) {
			assertNull(s2);
			assertEquals("Invalid id.", e.getMessage());
		}
		
		// Test for id that doesn't exist
		Faculty s3 = fd.getFacultyById("abcdefg");
		assertNull(s3);
		
		// test for id that does exist
		Faculty s4 = fd.getFacultyById("fmeadow");
		assertEquals("Fiona", s4.getFirstName());
		assertEquals("Meadows", s4.getLastName());
		assertEquals("fmeadow", s4.getId());
		assertEquals("pharetra.sed@et.org", s4.getEmail());
		
		fd.removeFaculty("lberg");
		try {
			fd.getFacultyById("lberg");
		} catch (IllegalArgumentException e) {
			assertEquals("User doesn't exist.", e.getMessage());
		}
		
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 * //Add faculty and remove
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
			
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("fmeadow"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		// First faculty should be awitt
		assertEquals("Ashely", facultyDirectory[0][0]);
		assertEquals("Witt", facultyDirectory[0][1]);
		assertEquals("awitt", facultyDirectory[0][2]);
		// Last faculty should be lwalls
		assertEquals("Lacey", facultyDirectory[6][0]);
		assertEquals("Walls", facultyDirectory[6][1]);
		assertEquals("lwalls", facultyDirectory[6][2]);
		// bbrewer is at index 2 before removal
		assertEquals("Brent", facultyDirectory[1][0]);
		assertEquals("Brewer", facultyDirectory[1][1]);
		assertEquals("bbrewer", facultyDirectory[1][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Add a faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		sd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		sd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
		
		//Test to write to wrong file
		try{
			sd.saveFacultyDirectory("/home/sesmith5/actual_faculty_records.txt");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Unable to write to file /home/sesmith5/actual_faculty_records.txt", e.getMessage());
		}
	    
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}