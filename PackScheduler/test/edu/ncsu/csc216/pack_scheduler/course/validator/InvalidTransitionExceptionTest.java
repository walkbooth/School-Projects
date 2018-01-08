package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the class InvalidTransitionException
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public class InvalidTransitionExceptionTest {
	
	/**
	 * Tests the constructor of InvalidTransitionException 
	 */
	@Test 
	public void newInvalidTransitionException () {
		
		// Test default constructor
		InvalidTransitionException e1 = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e1.getMessage());
		
		// Test constructor with specified message
		InvalidTransitionException e2 = new InvalidTransitionException("Wrong transition buddy.");
		assertEquals("Wrong transition buddy.", e2.getMessage());
		
	}
	
	/**
	 * Tests that InvalidTransitionException is a checked exception and is thrown correctly
	 */
	@Test
	public void isThrownCorrectly () {
		
		// tests if InvalidTransitionException is not a RuntimeException
		try {
			throw new InvalidTransitionException();
		} catch (InvalidTransitionException e) {
			assertEquals("Invalid FSM Transition.", e.getMessage());
			assertFalse((Exception)e instanceof RuntimeException);
		}
		
		// tests if InvalidTransitionException message constructor works
		try {
			throw new InvalidTransitionException("Wrong transition buddy.");
		} catch (InvalidTransitionException e) {
			assertEquals("Wrong transition buddy.", e.getMessage());
		}
		
	}

}
