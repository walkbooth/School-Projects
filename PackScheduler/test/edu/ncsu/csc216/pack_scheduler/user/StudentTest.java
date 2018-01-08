package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;

/**
 * Tests class Student
 * 
 * @author Daniel Grist
 * @author Alex Johnson
 * @author Victor Schroder
 */
public class StudentTest {

	/** Student's first name. */
	private static final String FIRSTNAME = "John";
	/** Student's last name */
	private static final String LASTNAME = "Doe";
	/** Student's id */
	private static final String ID = "jdoe";
	/** Student's email */
	private static final String EMAIL = "jdoe@ncsu.edu";
	/** Student's password */
	private static final String HASHPW = "hashedpassword";
	/** Student's max credits */
	private static final int MAXCREDITS = 18;

	/**
	 * Tests constructor of a Student for String, String, String, String,
	 * String, int
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {

		// Test constructor for null FirstName
		User s1 = null;
		try {
			s1 = new Student(null, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s1);
		}

		// Test constructor for empty string FirstName
		User s2 = null;
		try {
			s2 = new Student("", LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}

		// Test constructor for null LastName
		User s3 = null;
		try {
			s3 = new Student(FIRSTNAME, null, ID, EMAIL, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s3);
		}

		// Test constructor for empty string LastName
		User s4 = null;
		try {
			s4 = new Student(FIRSTNAME, "", ID, EMAIL, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s4);
		}

		// Test constructor for null id
		User s5 = null;
		try {
			s5 = new Student(FIRSTNAME, LASTNAME, null, EMAIL, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s5);
		}

		// Test constructor for empty string id
		User s6 = null;
		try {
			s6 = new Student(FIRSTNAME, LASTNAME, "", EMAIL, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s6);
		}

		// Test constructor for null id
		User s7 = null;
		try {
			s7 = new Student(FIRSTNAME, LASTNAME, ID, "", HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s7);
		}

		// Test constructor for empty string id
		User s8 = null;
		try {
			s8 = new Student(FIRSTNAME, LASTNAME, ID, null, HASHPW, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s8);
		}

		// Test constructor for null id
		User s9 = null;
		try {
			s9 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, "", MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s9);
		}

		// Test constructor for empty string id
		User s10 = null;
		try {
			s10 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, null, MAXCREDITS);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s10);
		}

		// Test constructor for null id
		User s11 = null;
		try {
			s11 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s11);
		}

		// Test constructor for empty string id
		User s12 = null;
		try {
			s12 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 21);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s12);
		}

	} 
	/**
	 * tests Student's constructor
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		// Test constructor for null FirstName
		User s13 = null;
		try {
			s13 = new Student(null, LASTNAME, ID, EMAIL, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s13);
		}

		// Test constructor for empty string FirstName
		User s14 = null;
		try {
			s14 = new Student("", LASTNAME, ID, EMAIL, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s14);
		}

		// Test constructor for null LastName
		User s15 = null;
		try {
			s15 = new Student(FIRSTNAME, null, ID, EMAIL, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s15);
		}

		// Test constructor for empty string LastName
		User s16 = null;
		try {
			s16 = new Student(FIRSTNAME, "", ID, EMAIL, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s16);
		}

		// Test constructor for null id
		User s17 = null;
		try {
			s17 = new Student(FIRSTNAME, LASTNAME, null, EMAIL, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s17);
		}

		// Test constructor for empty string id
		User s18 = null;
		try {
			s18 = new Student(FIRSTNAME, LASTNAME, "", EMAIL, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s18);
		}

		// Test constructor for null id
		User s19 = null;
		try {
			s19 = new Student(FIRSTNAME, LASTNAME, ID, "", HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s19);
		}

		// Test constructor for empty string id
		User s20 = null;
		try {
			s20 = new Student(FIRSTNAME, LASTNAME, ID, null, HASHPW);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s20);
		}

		// Test constructor for null id
		User s21 = null;
		try {
			s21 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s21);
		}

		// Test constructor for empty string id
		User s22 = null;
		try {
			s22 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s22);
		}

	}
	/**
	 * Tests Student's setFirstName method
	 */
	@Test
	public void testSetFirstName() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setFirstName(null);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setFirstName("");
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			s.setFirstName("RandomFirstName");
			assertEquals("RandomFirstName", s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
	}
	/**
	 * Tests Student's setLastName method 
	 */
	@Test
	public void testSetLastName() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setLastName(null);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setLastName("");
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			s.setLastName("RandomLastName");
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals("RandomLastName", s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
	}
	/**
	 * Tests setId() method in Student
	 */
	@Test
	public void testSetID() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s = new Student(FIRSTNAME, LASTNAME, null, EMAIL, HASHPW, MAXCREDITS);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s = new Student(FIRSTNAME, LASTNAME, "", EMAIL, HASHPW, MAXCREDITS);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			s = new Student(FIRSTNAME, LASTNAME, "vaschrod", EMAIL, HASHPW, MAXCREDITS);
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals("vaschrod", s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
	}
	/**
	 * Tests setEmail in Student according to guidelines at https://pages.github.ncsu.edu/
	 * engr-csc216-staff/CSC216-SE-Materials/labs/02-lab/02-lab-student.html
	 */
	@Test
	public void testSetEmail() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setEmail(null);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setEmail("");
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setEmail("noAT.character");
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setEmail("noPeriod@character");
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setEmail("period.before@character");
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			s.setEmail("test@test.com");
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals("test@test.com", s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
	}
	/**
	 * tests Student's setPassword method
	 */
	@Test
	public void testSetPassword() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setPassword(null);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setPassword("");
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			s.setPassword("hashPW");
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals("hashPW", s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests Student's max credits method
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setMaxCredits(2);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		try{
			s.setMaxCredits(19);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
			s.setMaxCredits(16);
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(16, s.getMaxCredits());
	}
	/**
	 * Tests Student's equal method
	 */
	@Test 
	public void testEqualsObject() {
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		User sSame = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS); 
		User sDifferent = new Student("Victor", "Schroder", "vaschrod", "vaschrod@ncsu.edu", "diffHPW", 17);
		User s0 = new Student("Victor", LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS); 
		User s1 = new Student(FIRSTNAME, "Schroder", ID, EMAIL, HASHPW, MAXCREDITS); 
		User s2 = new Student(FIRSTNAME, LASTNAME, "vaschrod", EMAIL, HASHPW, MAXCREDITS); 
		User s3 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, "vaschrod@ncsu.edu", MAXCREDITS); 
		User s4 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, "diffHPW", MAXCREDITS); 
		User s5 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 17);
		User s6  = null;
		StudentDirectory studentDirectory = new StudentDirectory();
		

		assertTrue(s.equals(s));
		assertTrue(s.equals(sSame));
		assertTrue(sSame.equals(s)); 
		assertTrue(sSame.equals(sSame));
		assertFalse(sSame.equals(sDifferent));
		assertFalse(s.equals(sDifferent));
		assertFalse(sDifferent.equals(s));
		assertFalse(sDifferent.equals(sSame));
		assertFalse(sDifferent.equals(studentDirectory));	

		assertFalse(s.equals(s0));
		assertFalse(s.equals(s1)); 
		assertFalse(s.equals(s2));
		assertFalse(s.equals(s3));
		assertFalse(s.equals(s4));
		assertFalse(s.equals(s5));
		assertFalse(s.equals(s6));
		
		
	}
	/**
	 * Tests Student's hashCode method
	 */
	@Test
	public void testHashCode() {
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		User sSame = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		User sDifferent = new Student("Victor", "Schroder", "vaschrod", "vaschrod@ncsu.edu", "diffHPW", 17);
		
		User s0 = new Student("ooio", LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		User s1 = new Student(FIRSTNAME, "asdf", ID, EMAIL, HASHPW, MAXCREDITS);
		User s2 = new Student(FIRSTNAME, "asdf", ID, EMAIL, HASHPW, MAXCREDITS);
		User s3 = new Student("ooio", LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		User s4 = new Student(FIRSTNAME, LASTNAME, "qwer", EMAIL, HASHPW, MAXCREDITS);
		User s5 = new Student(FIRSTNAME, LASTNAME, "qwer", EMAIL, HASHPW, MAXCREDITS);
		User s6 = new Student(FIRSTNAME, LASTNAME, ID, "vaschrod@ncsu.edu", HASHPW, MAXCREDITS);
		User s7 = new Student(FIRSTNAME, LASTNAME, ID, "vaschrod@ncsu.edu", HASHPW, MAXCREDITS);
		User s8 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, "diffHPW", MAXCREDITS);
		User s9 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, "diffHPW", MAXCREDITS);
		User s10 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 18);
		User s11 = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 18);
		
		assertEquals(s1.hashCode(), s2.hashCode());
		assertEquals(s0.hashCode(), s3.hashCode());
		assertEquals(s4.hashCode(), s5.hashCode());
		assertEquals(s6.hashCode(), s7.hashCode());
		assertEquals(s8.hashCode(), s9.hashCode());
		assertEquals(s10.hashCode(), s11.hashCode());
		assertNotEquals(s3.hashCode(), s4.hashCode());
		
		assertEquals(s.hashCode(), sSame.hashCode());
		assertNotEquals(s.hashCode(), sDifferent.hashCode());
		assertNotEquals(sSame.hashCode(), sDifferent.hashCode());
	}
	/**
	 * Tests Student's toString method
	 */
	@Test
	public void testToString() {
		User s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		String s1 = "John,Doe,jdoe,jdoe@ncsu.edu,hashedpassword,18";
		assertEquals(s.toString(), s1);
		
	}
	
	/**
	 * Tests the method compareTo from class student
	 */
	@Test
	public void testCompareTo() {
		
		// First and last are equal; id a comes before b
		Student s1 = new Student(FIRSTNAME, LASTNAME, "a", EMAIL, HASHPW, MAXCREDITS);
		Student s2 = new Student(FIRSTNAME, LASTNAME, "b", EMAIL, HASHPW, MAXCREDITS);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// Last are equal; first a comes before b and id doesn't matter
		s1 = new Student("a", LASTNAME, "b", EMAIL, HASHPW, MAXCREDITS);
		s2 = new Student("b", LASTNAME, "a", EMAIL, HASHPW, MAXCREDITS);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// first and id are equal; last name ending in m comes before nn
		s1 = new Student(FIRSTNAME, LASTNAME + "m", ID, EMAIL, HASHPW, MAXCREDITS);
		s2 = new Student(FIRSTNAME, LASTNAME + "nn", ID, EMAIL, HASHPW, MAXCREDITS);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// last and id are equal; first name ending in oo comes before p
		s1 = new Student(FIRSTNAME + "oo", LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		s2 = new Student(FIRSTNAME + "p", LASTNAME, ID, EMAIL, HASHPW, MAXCREDITS);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// First and last are equal; id yy comes before z
		s1 = new Student(FIRSTNAME, LASTNAME, ID + "yy", EMAIL, HASHPW, MAXCREDITS);
		s2 = new Student(FIRSTNAME, LASTNAME, ID + "z", EMAIL, HASHPW, MAXCREDITS);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
	}
	
	/**
	 * Tests the method canAdd from class student
	 */
	@Test
	public void testCanAdd() {

		Course c1 = new Course("CSC216", "ya", "003", 4, "her", 10, "MWF", 1430, 1530);
		Course c2 = new Course("CSC216", "ya", "004", 4, "her", 10, "MWF", 830, 925); // same class different time 
		Course c3 = new Course("CSC216", "ya", "003", 4, "her", 10, "TH", 1430, 1530); // same time different day
		
		Course c4 = new Course("CSC333", "ya", "004", 3, "her", 10, "MWF", 1400, 1500); // different class averlapping time
		Course c5 = new Course("CSC333", "ya", "004", 3, "her", 10, "MWF", 1500, 1600); // different class aver lapping time
		Course c6 = new Course("CSC333", "ya", "004", 3, "her", 10, "MWF", 1445, 1515); // different class containED
		Course c7 = new Course("CSC333", "ya", "004", 3, "her", 10, "MWF", 1415, 1545); // different class containING
		
		Student s = new Student(FIRSTNAME, LASTNAME, "a", EMAIL, HASHPW, 18);
		Student m = new Student(FIRSTNAME, LASTNAME, "b", EMAIL, HASHPW, 3);
		
		assertTrue(s.canAdd(c1));
		assertTrue(s.canAdd(c2));
		assertTrue(s.canAdd(c3));
		assertTrue(s.canAdd(c4));
		assertTrue(s.canAdd(c5));
		assertTrue(s.canAdd(c6));
		assertTrue(s.canAdd(c7));
		
		assertFalse(m.canAdd(c1)); // too many credits
		assertFalse(m.canAdd(c2));
		assertFalse(m.canAdd(c3));
		assertTrue(m.canAdd(c4));
		assertTrue(m.canAdd(c5));
		assertTrue(m.canAdd(c6));
		assertTrue(m.canAdd(c7));
		
		try {
			m.canAdd(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Course can't be null", e.getMessage());
		}
		
		assertTrue(s.canAdd(c1));
		assertTrue(s.canAdd(c2));
		assertTrue(s.canAdd(c3));
		
		assertTrue(s.canAdd(c4));
		assertTrue(s.canAdd(c5));
		assertTrue(s.canAdd(c6));
		assertTrue(s.canAdd(c7));
		
	}

}