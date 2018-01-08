package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * FacultyDirectory creates, stores and manipulates Faculty members in the PackScheduler system.
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan Cui
 */
public class FacultyDirectory {

	/** List of students in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty student directory.
	 */
	public FacultyDirectory() { 
		newFacultyDirectory();
	}
	
	/**
	 * Creates a new faculty directory list
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Loads Faculty from a file
	 * @param fileName the name of the file to load
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a new Faculty member for a given first name, last name, id, email, password and max credits
	 * @param firstName the first name of the faculty member
	 * @param lastName the last name of the faculty member
	 * @param id the id of the faculty member
	 * @param email the email of the faculty member
	 * @param password the faculty member's password 
	 * @param repeatPassword a repeat of the password
	 * @param maxCredits the maximum number of credits the faculty member can teach
	 * @return if the new faculty member
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCredits) {
		String hashPW = "";
		String repeatHashPW = "";
		try {
			
			if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
				throw new IllegalArgumentException("Invalid password");
			} 
			
			hashPW = hashString(password, HASH_ALGORITHM);
			repeatHashPW = hashString(repeatPassword, HASH_ALGORITHM);
			
		} catch (Exception e) {
			IllegalArgumentException err = new IllegalArgumentException(
					e instanceof NoSuchAlgorithmException ? "Cannot Hash Passord" : e.getMessage()
			);
			throw err;
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is throw, it's passed up from Student
		//to the GUI
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCredits);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}
	/**
	 * Hashs a String using an Algorithm.
	 * @return the hashed password
	 * @throws NoSuchAlgorithmException if the algorithm is invalid
	 */
	private String hashString (String x, String alg) throws NoSuchAlgorithmException {
		MessageDigest digest = null;
		digest = MessageDigest.getInstance(alg);
		digest.update(x.getBytes());
		return new String(digest.digest());
	}
	
	/**
	 * Removes a faculty member from the directory for a given id
	 * @param facultyId the id of the faculty member
	 * @return if the faculty was successfully removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Generates a display array of all the Faculty members
	 * @return a display array of the faculty members
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * Saves the faculty directory to a file
	 * @param fileName the fileName to save the faculty directory to
	 * @throws IllegalArgumentException if the id equals null or the empty string
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/**
	 * Gets a faculty member for a given id
	 * @param id the id of the faculty member to get
	 * @return the faculty member if found, if not found, null
	 * @throws IllegalArgumentException if the id equals null or the empty string
	 */
	public Faculty getFacultyById(String id) {
		if(id == null || id.equals("")){
			throw new IllegalArgumentException("Invalid id.");
		}
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty s = facultyDirectory.get(i);
			if (s.getId().equals(id.toLowerCase())) { 
				return s;
			}
		}
		return null;
	}	
}
