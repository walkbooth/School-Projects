package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the class ConflictException
 * @author Alex Johnson
 */
public class ConflictExceptionTest {

	/**
	 * Tests the default constructor for class ConflictException
	 */
	@Test
	public void testConflictException() {
		
		// Test there's no errors constructing ConflictException
		ConflictException c = new ConflictException();
		assertEquals("Schedule conflict.", c.getMessage());
		
		// Test that Conflict Exception is Thrown correctly
		try {
			throw c;
		} catch (Exception e) {
			assertTrue(e instanceof ConflictException);
			assertEquals("Schedule conflict.", e.getMessage());
		}
	}
	
	/**
	 * Tests the constructor String for class ConflictException
	 */
	@Test
	public void testConflictExceptionString() { 
		
		// Test there's no errors constructing ConflictException with a String
		ConflictException c = new ConflictException("Event time conflict");
		assertEquals("Event time conflict", c.getMessage());
		 
		// Test that Conflict Exception is Thrown correctly with a custom message
		try {
			throw c; 
		} catch (Exception e) {
			assertTrue(e instanceof ConflictException);
			assertEquals("Event time conflict", e.getMessage());
		}
	}

}
