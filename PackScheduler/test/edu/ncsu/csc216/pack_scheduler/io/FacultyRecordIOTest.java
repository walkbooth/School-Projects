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

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Testers for FacultyRecordIO
 * @author Victor, Alex, Daniel
 *
 */
public class FacultyRecordIOTest { 

	/** String array of valid student lines */
	private String[] validFaculty;
	/** The hashed password for facultyMembers */
	private String hashPW;
	/** The hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Sets up the hashing algorithm, code from the Lab 2 guide
	 */
	@Before
	public void setUp() {
		// Valid facultyMembers 
		LinkedList<String> vs = new LinkedList<String>();
		// get 
		try {
			Scanner in = new Scanner(new FileInputStream(new File("test-files/expected_full_faculty_records.txt")));
			while (in.hasNextLine()) {
				vs.add(in.nextLine());
			}
			in.close();
		} catch (IOException e) {
			fail("expected_full_faculty_records.txt not found");
		}
		
		// convert LinkedList to String[]
		validFaculty = new String[vs.size()]; 
		for (int i = 0; i < validFaculty.length; i++) {
			validFaculty[i] = vs.get(i);
		}
			
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	        	validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Tests that a FacultyRecordIO cannot be constructed
	 */
	@Test
	public void testNewFacultyRecordIO() { 
		try {
			new FacultyRecordIO();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Class FacultyRecordIO is only static methods, cannot be initialized", e.getMessage());
		}
	}
	
	/**
	 * Tests the static method readFacultyRecords, of class FacultyRecordIO
	 */
	@Test
	public void testReadFacultyRecords() { 
		// typed up facultyMembers, hand sorted into alphabetical order
		User f0 = new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2);
		User f1 = new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3);
		User f2 = new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1);
		User f3 = new Faculty("Halla", "Aguirre", "haguirr", "Fusce.dolor.quam@amalesuadaid.net", hashPW, 3);
		User f4 = new Faculty("Kevyn", "Patel", "kpatel", "risus@pellentesque.ca", hashPW, 1);
		User f5 = new Faculty("Elton", "Briggs", "ebriggs", "arcu.ac@ipsumsodalespurus.edu", hashPW, 3);
		User f6 = new Faculty("Norman", "Brady", "nbrady", "pede.nonummy@elitfermentum.co.uk", hashPW, 1);
		User f7 = new Faculty("Lacey", "Walls", "lwalls", "nascetur.ridiculus.mus@fermentum.net", hashPW, 2);
		
		LinkedList<Faculty> facultyMembers = null;
		try { 
			facultyMembers = FacultyRecordIO.readFacultyRecords("test-files/expected_full_faculty_records.txt");
		} catch (IOException e) {
			fail(e.getMessage());
		}
		// readFacultyRecords shouldn't return null
		assertNotNull(facultyMembers);
		assertEquals(f0, facultyMembers.get(0));
		assertEquals(f1, facultyMembers.get(1));
		assertEquals(f2, facultyMembers.get(2));
		assertEquals(f3, facultyMembers.get(3));
		assertEquals(f4, facultyMembers.get(4));
		assertEquals(f5, facultyMembers.get(5)); 
		assertEquals(f6, facultyMembers.get(6));
		assertEquals(f7, facultyMembers.get(7));
	
		// test invalid faculty records doesn't throw errors
		try {   
			facultyMembers = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
		} catch (IOException e) {
			fail(); 
		}
		assertEquals(0, facultyMembers.size());
		
		// test duplicates in file
		try { 
			facultyMembers = FacultyRecordIO.readFacultyRecords("test-files/completely_invalid_file.txt");
		} catch (Exception e) {
			fail(); 
		}
		assertEquals(0, facultyMembers.size());
		
	}		

	/**
	 * Tests writing full student records 
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> facultyMembers = new LinkedList<Faculty>();
		facultyMembers.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		facultyMembers.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		facultyMembers.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
		facultyMembers.add(new Faculty("Halla", "Aguirre", "haguirr", "Fusce.dolor.quam@amalesuadaid.net", hashPW, 3));
		facultyMembers.add(new Faculty("Kevyn", "Patel", "kpatel", "risus@pellentesque.ca", hashPW, 1));
		facultyMembers.add(new Faculty("Elton", "Briggs", "ebriggs", "arcu.ac@ipsumsodalespurus.edu", hashPW, 3));
		facultyMembers.add(new Faculty("Norman", "Brady", "nbrady", "pede.nonummy@elitfermentum.co.uk", hashPW, 1));
		facultyMembers.add(new Faculty("Lacey", "Walls", "lwalls", "nascetur.ridiculus.mus@fermentum.net", hashPW, 2));
		
		//Assumption that you are using a hash of "pw" stored in hashPW
		 
		// null file name should throw error
		try{ 
			FacultyRecordIO.writeFacultyRecords(null, facultyMembers);
			fail();
		} catch (Exception e) {
			assertEquals("File name cannot be null", e.getMessage());
		}
		
		// empty string file name should throw error
		try {
			FacultyRecordIO.writeFacultyRecords("", facultyMembers);
			fail();
		} catch (Exception e) {
			assertEquals("File name must have a least 1 character", e.getMessage());
		}
		
		// null records should throw error
		try {
			FacultyRecordIO.writeFacultyRecords("irrelavent", null);
			fail();
		} catch (Exception e) {
			assertEquals("Faculty Directory cannot be null", e.getMessage());
		}
		
		try{
			FacultyRecordIO.writeFacultyRecords("test-files/actual_full_faculty_records.txt", facultyMembers);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		checkFiles("test-files/expected_full_faculty_records.txt", "test-files/actual_full_faculty_records.txt");
	}

	/**
	 * Tests writing a file to a non-existent folder
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> facultyMembers = new LinkedList<Faculty>();
		facultyMembers.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisqui.com", hashPW, 3));
		//Assumption that you are using a hash of "pw" stored in hashPW
		
		try{
			FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_student_records.txt", facultyMembers);
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
