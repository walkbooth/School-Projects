package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Testers for StudentRecordIO
 * @author Victor, Alex, Daniel
 *
 */
public class StudentRecordIOTest { 

	/** String array of valid student lines */
	private String[] validStudents;
	/** The hashed password for students */
	private String hashPW;
	/** The hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Sets up the hashing algorithm, code from the Lab 2 guide
	 */
	@Before
	public void setUp() {
		// Valid students 
		SortedList<String> vs = new SortedList<String>();
		// get 
		try {
			Scanner in = new Scanner(new FileInputStream(new File("test-files/expected_full_student_records.txt")));
			while (in.hasNextLine()) {
				vs.add(in.nextLine());
			}
			in.close();
		} catch (IOException e) {
			fail("expected_full_student_records.txt not found");
		}
		
		// convert SortedList to String[]
		validStudents = new String[vs.size()]; 
		for (int i = 0; i < validStudents.length; i++) {
			validStudents[i] = vs.get(i);
		}
			
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Tests that a StudentRecordIO cannot be constructed
	 */
	@Test
	public void testNewStudentRecordIO() { 
		try {
			new StudentRecordIO();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Class StudentRecordIO is only static methods, cannot be initialized", e.getMessage());
		}
	}
	
	/**
	 * Tests the static method readStudentRecords, of class StudentRecordIO
	 */
	@Test
	public void testReadStudentRecords() { 
		// typed up students, hand sorted into alphabetical order
		User s0 = new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk", hashPW, 18);
		User s1 = new Student("Lane", "Berg", "lberg", "sociis@non.org", hashPW, 14);
		User s2 = new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", hashPW, 12);
		User s3 = new Student("Emerald", "Frost", "efrost", "adipiscing@acipsumPhasellus.edu", hashPW, 3);
		User s4 = new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", hashPW, 14);
		User s5 = new Student("Althea", "Hicks", "ahicks", "Phasellus.dapibus@luctusfelis.com", hashPW, 11);
		User s6 = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15);
		User s7 = new Student("Dylan", "Nolan", "dnolan", "placerat.Cras.dictum@dictum.net", hashPW, 5);
		User s8 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", hashPW, 4);
		User s9 = new Student("Griffith", "Stone", "gstone", "porta@magnamalesuadavel.net", hashPW, 17);
		
		SortedList<Student> students = null;
		try { 
			students = StudentRecordIO.readStudentRecords("test-files/expected_full_student_records.txt");
		} catch (IOException e) {
			fail(e.getMessage());
		}
		// readStudentRecords shouldn't return null
		assertNotNull(students);
		assertTrue(s0.equals(students.get(0)));
		assertTrue(s1.equals(students.get(1)));
		assertTrue(s2.equals(students.get(2)));
		assertTrue(s3.equals(students.get(3)));
		assertTrue(s4.equals(students.get(4)));
		assertTrue(s5.equals(students.get(5))); 
		assertTrue(s6.equals(students.get(6)));
		assertTrue(s7.equals(students.get(7)));
		assertTrue(s8.equals(students.get(8)));
		assertTrue(s9.equals(students.get(9)));
		
		// test invalid student records doesn't throw errors TODO
		try {   
			students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
		} catch (IOException e) {
			fail(); 
		}
		assertEquals(0, students.size());
		
		// test duplicates in file
		try {   
			students = StudentRecordIO.readStudentRecords("test-files/student_dup.txt");
		} catch (IOException e) {
			fail();
		}
		assertEquals(14, students.size());
		
		// test duplicates in file
		try { 
			students = StudentRecordIO.readStudentRecords("test-files/completely_invalid_file.txt");
		} catch (Exception e) {
			fail(); 
		}
		assertEquals(0, students.size());
		
	}		

	/**
	 * Tests writing full student records 
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>(); 
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		students.add(new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", hashPW, 4));
		students.add(new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", hashPW, 14));
		students.add(new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk", hashPW, 18));
		students.add(new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", hashPW, 12));
		students.add(new Student("Emerald", "Frost", "efrost", "adipiscing@acipsumPhasellus.edu", hashPW, 3));
		students.add(new Student("Lane", "Berg", "lberg", "sociis@non.org", hashPW, 14));
		students.add(new Student("Griffith", "Stone", "gstone", "porta@magnamalesuadavel.net", hashPW, 17));
		students.add(new Student("Althea", "Hicks", "ahicks", "Phasellus.dapibus@luctusfelis.com", hashPW, 11));
		students.add(new Student("Dylan", "Nolan", "dnolan", "placerat.Cras.dictum@dictum.net", hashPW, 5));
		//Assumption that you are using a hash of "pw" stored in hashPW
		 
		// null file name should throw error
		try{ 
			StudentRecordIO.writeStudentRecords(null, students);
			fail();
		} catch (Exception e) {
			assertEquals("File name cannot be null", e.getMessage());
		}
		
		// empty string file name should throw error
		try {
			StudentRecordIO.writeStudentRecords("", students);
			fail();
		} catch (Exception e) {
			assertEquals("File name must have a least 1 character", e.getMessage());
		}
		
		// null records should throw error
		try {
			StudentRecordIO.writeStudentRecords("irrelavent", null);
			fail();
		} catch (Exception e) {
			assertEquals("Student Directory cannot be null", e.getMessage());
		}
		
		try{
			StudentRecordIO.writeStudentRecords("test-files/actual_full_student_records.txt", students);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		checkFiles("test-files/expected_full_student_records.txt", "test-files/actual_full_student_records.txt");
	}

	/**
	 * Tests writing a file to a non-existent folder
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisqui.com", hashPW, 15));
		//Assumption that you are using a hash of "pw" stored in hashPW
		
		try{
			StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
			fail("Attempted to write to a directory location that doesnt exist or without the appropriate permissions and the write happened.");
		} catch (IOException e) {
			assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
			//The actual error message on Jenkins!
		}
	}
	
	
	/**
	 * Helper method to compare two files for the same contents. Code from the Lab 2 guide
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}


}
