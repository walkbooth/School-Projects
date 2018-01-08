package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Imports and exports Students from a record file.
 * @author Daniel Grist 
 * @author Alex Johnson
 * @author Victor Schroder
 */
public class StudentRecordIO {  

	/** The character that separates fields in the file */
	private static final String DELIMITER = ",";
	
	/**
	 * Student Records constructor
	 */
	public StudentRecordIO () {
		throw new IllegalArgumentException("Class StudentRecordIO is only static methods, cannot be initialized");
	}
	
	/**
	 * Reads course records from a file to generate a SortedList of Course. File 
	 * must be readable and exist. Code reused from project WolfScheduler.
	 * @param fileName the path of a valid file
	 * @return a SortedList of {@link Student}
	 * @throws FileNotFoundException if there is any error reading the file
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
	    FileInputStream inputStream = new FileInputStream(new File(fileName));
	    Scanner fileReader = new Scanner(inputStream);
	    SortedList<Student> students = new SortedList<Student>();
	    while (fileReader.hasNextLine()) {
	        try {
	        	Student student = parseStudent(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < students.size(); i++) {
	            	User s = students.get(i);
	                if (student.getFirstName().equals(s.getFirstName()) &&
	                		student.getLastName().equals(s.getLastName())) {
	                    //it's a duplicate
	                    duplicate = true; 
	                }
	            }
	            if (!duplicate) {
	            	students.add(student);
	            }
	        } catch (IllegalArgumentException e) { 
	            // Student is invalid so skip the line
	        }
	    }
	    fileReader.close();
	    return students;
	}

	
	/**
	 * Parses a line from a student record String into a {@link Student} object. Code
	 * reused from project WolfScheduler.
	 * @param courseRecord the string to parse into a  file
	 * @return a {@link Student} for a line
	 * @throws IllegalArgumentException if line is not formatted correctly 
	 */ 
	private static Student parseStudent(String studentRecord) {
		
		Scanner line = new Scanner(studentRecord);
		line.useDelimiter(DELIMITER);
		
		Student s = null;
		try {
			// Get each one of the required fields
			String firstName = line.next();
			String lastName = line.next();
			String id = line.next();
			String email = line.next(); 
			String hashPW = line.next();
			int maxCredits = line.nextInt();
				
			s = new Student (firstName, lastName, id, email, hashPW, maxCredits);
				
			line.close(); 
			
		} catch (NoSuchElementException e) {
			// If there's any scanner errors then throw IllegalArgumentException
			throw new IllegalArgumentException("Invalid file");
		}
		
		return s;
	}

	/**
	 * Writes the student records to a file.
	 * @param fileName the name of the file to save the student records as
	 * @param studentDirectory the array of students to write
	 * @throws IOException if there is any problem writing the file 
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		
		if (fileName == null) { 
			throw new IllegalArgumentException("File name cannot be null");
		}
		
		if (fileName.equals("")) {
			throw new IllegalArgumentException("File name must have a least 1 character");
		}
		
		if (studentDirectory == null) {
			throw new IllegalArgumentException("Student Directory cannot be null");
		}
		
		PrintStream fileWriter = null;
		
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (IOException e) {
			throw new FileNotFoundException(fileName + " (Permission denied)");
		}
		
		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
	}

}
