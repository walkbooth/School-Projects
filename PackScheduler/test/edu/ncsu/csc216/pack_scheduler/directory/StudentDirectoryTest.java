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

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 */
public class StudentDirectoryTest { 
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_students_records.txt";
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
	private static final int MAX_CREDITS = 15;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test 
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		//Test invalid file
		try{
			sd.loadStudentsFromFile(invalidTestFile);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Unable to read file test-files/invalid_students_records.txt" , e.getMessage());
		}
		
		
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		// test duplicate
		assertFalse(sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertTrue(sd.addStudent(FIRST_NAME, LAST_NAME, "another1", EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertFalse(sd.addStudent(FIRST_NAME, LAST_NAME, "another1", EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		
		//Test invalid Password
		try{
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Invalid password", e.getMessage());
		}
		
		//Test invalid Password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test invalid Password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}

		// Test invalid Password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		
		//Test different passwords
		try{
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "asdg", MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Passwords do not match", e.getMessage());
		} 

		
	} 
	
	/**
	 * Tests method getStudentById of class StudentDirectory
	 */
	@Test
	public void testGetStudentById(){
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validTestFile);
		
		// Test for null parameter
		Student s1 = null;
		try {
			s1 = sd.getStudentById(null);
			fail("Didn't throw exception for an illegal argument");
		} catch (IllegalArgumentException e) {
			assertNull(s1);
			assertEquals("Invalid id.", e.getMessage());
		}
		
		// Test for empty string parameter
		Student s2 = null;
		try {
			s2 = sd.getStudentById("");
			fail("Didn't throw exception for an illegal argument");
		} catch (IllegalArgumentException e) {
			assertNull(s2);
			assertEquals("Invalid id.", e.getMessage());
		}
		
		// Test for id that doesn't exist
		Student s3 = sd.getStudentById("abcdefg");
		assertNull(s3);
		
		// test for id that does exist
		Student s4 = sd.getStudentById("daustin");
		assertEquals("Demetrius", s4.getFirstName());
		assertEquals("Austin", s4.getLastName());
		assertEquals("daustin", s4.getId());
		assertEquals("Curabitur.egestas.nunc@placeratorcilacus.co.uk", s4.getEmail());
		
		// test for id that does exist
		Student s5 = sd.getStudentById("dnolan");
		assertEquals("Dylan", s5.getFirstName());
		
		// test for id that does exist
		Student s6 = sd.getStudentById("lberg");
		assertEquals("Lane", s6.getFirstName());

		// test for id that does exist
		Student s7 = sd.getStudentById("rbrennan");
		assertEquals("Raymond", s7.getFirstName());

		// test for id that does exist
		Student s8 = sd.getStudentById("efrost");
		assertEquals("Emerald", s8.getFirstName());
		
		// test for id that does exist
		Student s9 = sd.getStudentById("shansen");
		assertEquals("Shannon", s9.getFirstName());

		// test for id that does exist
		Student s10 = sd.getStudentById("ahicks");
		assertEquals("Althea", s10.getFirstName());

		// test for id that does exist
		Student s11 = sd.getStudentById("zking");
		assertEquals("Zahir", s11.getFirstName());
		
		// test for id that does exist
		Student s12 = sd.getStudentById("cschwartz");
		assertEquals("Cassandra", s12.getFirstName());
		
		// test for id that does exist
		Student s13 = sd.getStudentById("gstone");
		assertEquals("Griffith", s13.getFirstName());
		
		sd.removeStudent("lberg");
		try {
			sd.getStudentById("lberg");
		} catch (IllegalArgumentException e) {
			assertEquals("User doesn't exist.", e.getMessage());
		}
		
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 * //Add students and remove
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
			
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		// First student should be daustin, because his last name starts with an A
		assertEquals("Demetrius", studentDirectory[0][0]);
		assertEquals("Austin", studentDirectory[0][1]);
		assertEquals("daustin", studentDirectory[0][2]);
		// Last student should be gstone, because his last name starts with St
		assertEquals("Griffith", studentDirectory[8][0]);
		assertEquals("Stone", studentDirectory[8][1]);
		assertEquals("gstone", studentDirectory[8][2]);
		// efrost should be at index 3 before removal and after shansen should take her place
		assertEquals("Shannon", studentDirectory[3][0]);
		assertEquals("Hansen", studentDirectory[3][1]);
		assertEquals("shansen", studentDirectory[3][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
		//Test to write to wrong file
		try{
			sd.saveStudentDirectory("/home/sesmith5/actual_student_records.txt");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e.getMessage());
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