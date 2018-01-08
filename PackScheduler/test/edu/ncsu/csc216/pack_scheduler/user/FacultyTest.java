package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests class Faculty
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan Cui
 */
public class FacultyTest {

	/** Faculty's first name. */
	private static final String FIRSTNAME = "John";
	/** Faculty's last name */
	private static final String LASTNAME = "Doe";
	/** Faculty's id */
	private static final String ID = "jdoe";
	/** Faculty's email */
	private static final String EMAIL = "jdoe@ncsu.edu";
	/** Faculty's password */
	private static final String HASHPW = "hashedpassword";
	/** Faculty's max credits */
	private static final int MAXCOURSES = 2;

	/**
	 * Tests constructor of a Faculty for String, String, String, String,
	 * String, int
	 */
	@Test
	public void testFacultyStringStringStringStringStringInt() {

		// Test constructor for null FirstName
		User s1 = null;
		try {
			s1 = new Faculty(null, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s1);
		}

		// Test constructor for empty string FirstName
		User s2 = null;
		try {
			s2 = new Faculty("", LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s2);
		}

		// Test constructor for null LastName
		User s3 = null;
		try {
			s3 = new Faculty(FIRSTNAME, null, ID, EMAIL, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s3);
		}

		// Test constructor for empty string LastName
		User s4 = null;
		try {
			s4 = new Faculty(FIRSTNAME, "", ID, EMAIL, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s4);
		}

		// Test constructor for null id
		User s5 = null;
		try {
			s5 = new Faculty(FIRSTNAME, LASTNAME, null, EMAIL, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s5);
		}

		// Test constructor for empty string id
		User s6 = null;
		try {
			s6 = new Faculty(FIRSTNAME, LASTNAME, "", EMAIL, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s6);
		}

		// Test constructor for null id
		User s7 = null;
		try {
			s7 = new Faculty(FIRSTNAME, LASTNAME, ID, "", HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s7);
		}

		// Test constructor for empty string id
		User s8 = null;
		try {
			s8 = new Faculty(FIRSTNAME, LASTNAME, ID, null, HASHPW, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s8);
		}

		// Test constructor for null id
		User s9 = null;
		try {
			s9 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, "", MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s9);
		}

		// Test constructor for empty string id
		User s10 = null;
		try {
			s10 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, null, MAXCOURSES);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s10);
		}

		// Test constructor for null id
		User s11 = null;
		try {
			s11 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s11);
		}

		// Test constructor for empty string id
		User s12 = null;
		try {
			s12 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 21);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s12);
		}

	} 
	
	/**
	 * Tests Faculty's setFirstName method
	 */
	@Test
	public void testSetFirstName() {
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s.setFirstName(null);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s.setFirstName("");
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			s.setFirstName("RandomFirstName");
			assertEquals("RandomFirstName", s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
	}
	/**
	 * Tests Faculty's setLastName method 
	 */
	@Test
	public void testSetLastName() {
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s.setLastName(null);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s.setLastName("");
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			s.setLastName("RandomLastName");
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals("RandomLastName", s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
	}
	/**
	 * Tests setId() method in Faculty
	 */
	@Test
	public void testSetID() {
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s = new Faculty(FIRSTNAME, LASTNAME, null, EMAIL, HASHPW, MAXCOURSES);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s = new Faculty(FIRSTNAME, LASTNAME, "", EMAIL, HASHPW, MAXCOURSES);
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			s = new Faculty(FIRSTNAME, LASTNAME, "vaschrod", EMAIL, HASHPW, MAXCOURSES);
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals("vaschrod", s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
	}
	/**
	 * Tests setEmail in Faculty according to guidelines at https://pages.github.ncsu.edu/
	 * engr-csc216-staff/CSC216-SE-Materials/labs/02-lab/02-lab-faculty.html
	 */
	@Test
	public void testSetEmail() {
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			s.setEmail("test@test.com");
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals("test@test.com", s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
	}
	/**
	 * tests Faculty's setPassword method
	 */
	@Test
	public void testSetPassword() {
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
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
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			s.setPassword("hashPW");
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals("hashPW", s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
	}
	
	/**
	 * Tests Faculty's max credits method
	 */
	@Test
	public void testSetMaxCredits() {
		Faculty s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s.setMaxCourses(4);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		try{
			s.setMaxCourses(19);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(MAXCOURSES, s.getMaxCourses());
		}
		s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
			s.setMaxCourses(3);
			assertEquals(FIRSTNAME, s.getFirstName());
			assertEquals(LASTNAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
		    assertEquals(HASHPW, s.getPassword());
		    assertEquals(3, s.getMaxCourses());
	}
	/**
	 * Tests Faculty's equal method
	 */
	@Test 
	public void testEqualsObject() {
		User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		User sSame = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES); 
		User sDifferent = new Faculty("Victor", "Schroder", "vaschrod", "vaschrod@ncsu.edu", "diffHPW", 3);
		User s0 = new Faculty("Victor", LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES); 
		User s1 = new Faculty(FIRSTNAME, "Schroder", ID, EMAIL, HASHPW, MAXCOURSES); 
		User s2 = new Faculty(FIRSTNAME, LASTNAME, "vaschrod", EMAIL, HASHPW, MAXCOURSES); 
		User s3 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, "vaschrod@ncsu.edu", MAXCOURSES); 
		User s4 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, "diffHPW", MAXCOURSES); 
		User s5 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 3);
		User s6  = null;
		

		assertTrue(s.equals(sSame));
		assertTrue(sSame.equals(s));
		assertFalse(sSame.equals(sDifferent));
		assertFalse(s.equals(sDifferent));
		assertFalse(sDifferent.equals(s));
		assertFalse(sDifferent.equals(sSame));

		assertFalse(s.equals(s0));
		assertFalse(s.equals(s1)); 
		assertFalse(s.equals(s2));
		assertFalse(s.equals(s3));
		assertFalse(s.equals(s4));
		assertFalse(s.equals(s5));
		assertFalse(s.equals(s6));
		
		
	}
	/**
	 * Tests Faculty's hashCode method
	 */
	@Test
	public void testHashCode() {
		//User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		//User sSame = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		//User sDifferent = new Faculty("Victor", "Schroder", "vaschrod", "vaschrod@ncsu.edu", "diffHPW", 3);
		
		User s0 = new Faculty("ooio", LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		User s1 = new Faculty(FIRSTNAME, "asdf", ID, EMAIL, HASHPW, MAXCOURSES);
		User s2 = new Faculty(FIRSTNAME, "asdf", ID, EMAIL, HASHPW, MAXCOURSES);
		User s3 = new Faculty("ooio", LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		User s4 = new Faculty(FIRSTNAME, LASTNAME, "qwer", EMAIL, HASHPW, MAXCOURSES);
		User s5 = new Faculty(FIRSTNAME, LASTNAME, "qwer", EMAIL, HASHPW, MAXCOURSES);
		User s6 = new Faculty(FIRSTNAME, LASTNAME, ID, "vaschrod@ncsu.edu", HASHPW, MAXCOURSES);
		User s7 = new Faculty(FIRSTNAME, LASTNAME, ID, "vaschrod@ncsu.edu", HASHPW, MAXCOURSES);
		User s8 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, "diffHPW", MAXCOURSES);
		User s9 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, "diffHPW", MAXCOURSES);
		User s10 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 3);
		User s11 = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, 3);
		
		assertEquals(s1.hashCode(), s2.hashCode());
		assertEquals(s0.hashCode(), s3.hashCode());
		assertEquals(s4.hashCode(), s5.hashCode());
		assertEquals(s6.hashCode(), s7.hashCode());
		assertEquals(s8.hashCode(), s9.hashCode());
		assertEquals(s10.hashCode(), s11.hashCode());
		assertNotEquals(s3.hashCode(), s4.hashCode());
		
		assertEquals(s1.hashCode(), s1.hashCode());
		assertEquals(s2.hashCode(), s2.hashCode());
		assertEquals(s3.hashCode(), s3.hashCode());
	}
	/**
	 * Tests Faculty's toString method
	 */
	@Test
	public void testToString() {
		User s = new Faculty(FIRSTNAME, LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		String s1 = "John,Doe,jdoe,jdoe@ncsu.edu,hashedpassword,2";
		assertEquals(s.toString(), s1);
	}
	
	/**
	 * Tests the method compareTo from class faculty
	 */
	@Test
	public void testCompareTo() {
		
		// First and last are equal; id a comes before b
		Faculty s1 = new Faculty(FIRSTNAME, LASTNAME, "a", EMAIL, HASHPW, MAXCOURSES);
		Faculty s2 = new Faculty(FIRSTNAME, LASTNAME, "b", EMAIL, HASHPW, MAXCOURSES);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// Last are equal; first a comes before b and id doesn't matter
		s1 = new Faculty("a", LASTNAME, "b", EMAIL, HASHPW, MAXCOURSES);
		s2 = new Faculty("b", LASTNAME, "a", EMAIL, HASHPW, MAXCOURSES);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// first and id are equal; last name ending in m comes before nn
		s1 = new Faculty(FIRSTNAME, LASTNAME + "m", ID, EMAIL, HASHPW, MAXCOURSES);
		s2 = new Faculty(FIRSTNAME, LASTNAME + "nn", ID, EMAIL, HASHPW, MAXCOURSES);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// last and id are equal; first name ending in oo comes before p
		s1 = new Faculty(FIRSTNAME + "oo", LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		s2 = new Faculty(FIRSTNAME + "p", LASTNAME, ID, EMAIL, HASHPW, MAXCOURSES);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
		// First and last are equal; id yy comes before z
		s1 = new Faculty(FIRSTNAME, LASTNAME, ID + "yy", EMAIL, HASHPW, MAXCOURSES);
		s2 = new Faculty(FIRSTNAME, LASTNAME, ID + "z", EMAIL, HASHPW, MAXCOURSES);
		assertEquals(-1, s1.compareTo(s2));
		assertEquals( 1, s2.compareTo(s1));
		
	}
	


}