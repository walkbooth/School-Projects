package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Imports and exports Faculty from a record file.
 * @author Daniel Grist 
 * @author Alex Johnson
 * @author Victor Schroder
 */
public class FacultyRecordIO {  

	/** The character that separates fields in the file */
	private static final String DELIMITER = ",";
	
	/**
	 * Faculty Records constructor
	 */
	public FacultyRecordIO () {
		throw new IllegalArgumentException("Class FacultyRecordIO is only static methods, cannot be initialized");
	}
	
	/**
	 * Reads course records from a file to generate a SortedList of Course. File 
	 * must be readable and exist. Code reused from project WolfScheduler.
	 * @param fileName the path of a valid file
	 * @return a SortedList of {@link Faculty}
	 * @throws FileNotFoundException if there is any error reading the file
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
	    FileInputStream inputStream = new FileInputStream(new File(fileName));
	    Scanner fileReader = new Scanner(inputStream);
	    LinkedList<Faculty> facultyMembers = new LinkedList<Faculty>();
	    while (fileReader.hasNextLine()) {
	        try {
	        	Faculty faculty = parseFaculty(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < facultyMembers.size(); i++) {
	            	User s = facultyMembers.get(i);
	                if (faculty.getFirstName().equals(s.getFirstName()) &&
	                		faculty.getLastName().equals(s.getLastName())) {
	                    //it's a duplicate
	                    duplicate = true; 
	                }
	            }
	            if (!duplicate) {
	            	facultyMembers.add(faculty);
	            }
	        } catch (IllegalArgumentException e) { 
	            // Student is invalid so skip the line
	        }
	    }
	    fileReader.close();
	    return facultyMembers;
	}

	
	/**
	 * Parses a line from a faculty record String into a {@link Faculty} object. Code
	 * reused from project WolfScheduler.
	 * @param courseRecord the string to parse into a  file
	 * @return a {@link Faculty} for a line
	 * @throws IllegalArgumentException if line is not formatted correctly 
	 */ 
	private static Faculty parseFaculty(String facultyRecord) {
		
		Scanner line = new Scanner(facultyRecord);
		line.useDelimiter(DELIMITER);
		
		Faculty f = null;
		try {
			// Get each one of the required fields
			String firstName = line.next();
			String lastName = line.next();
			String id = line.next();
			String email = line.next(); 
			String hashPW = line.next();
			int maxCredits = line.nextInt();
				
			f = new Faculty (firstName, lastName, id, email, hashPW, maxCredits);
				
			line.close(); 
			
		} catch (NoSuchElementException e) {
			// If there's any scanner errors then throw IllegalArgumentException
			throw new IllegalArgumentException("Invalid file");
		}
		
		return f;
	}

	/**
	 * Writes the student records to a file.
	 * @param fileName the name of the file to save the student records as
	 * @param facultyDirectory the array of students to write
	 * @throws IOException if there is any problem writing the file 
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		
		if (fileName == null) { 
			throw new IllegalArgumentException("File name cannot be null");
		}
		
		if (fileName.equals("")) {
			throw new IllegalArgumentException("File name must have a least 1 character");
		}
		
		if (facultyDirectory == null) {
			throw new IllegalArgumentException("Faculty Directory cannot be null");
		}
		
		PrintStream fileWriter = null;
		
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (IOException e) {
			throw new FileNotFoundException(fileName + " (Permission denied)");
		}
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
		    fileWriter.println(facultyDirectory.get(i).toString());
		}

		fileWriter.close();
	}

}
