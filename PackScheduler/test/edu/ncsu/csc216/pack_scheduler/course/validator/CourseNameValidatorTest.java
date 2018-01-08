package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the class CourseNameValidator. CourseNameValidator has an Object based FSM.
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public class CourseNameValidatorTest {
	/** The CourseNameValidator object used for testing */
	private CourseNameValidator fsm;
	
	/**
	 * Sets up the CourseNameValidator tests.
	 * @throws Exception if there's any error initializing CourseNameValidatorTest
	 */
	@Before
	public void setUp() throws Exception {
		fsm = new CourseNameValidator();
	}

	/**
	 * Tests the initial state of the CourseNameValidator
	 */
	@Test
	public void testInitial() {
		
		// Test if name is null
		try {
			fsm.isValid(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test if name is the empty string
		try {
			fsm.isValid("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test first character is other, invalid
		try {
			fsm.isValid(")SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		// Test first character is digit, invalid
		try {
			fsm.isValid("1SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		// Test first character is digit, invalid
		try {
			fsm.isValid("8SC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}

		// Test first character is letter, valid
		try {
			assertTrue(fsm.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
	}
	
	/**
	 * Tests the LetterState of the CourseNameValidator
	 */
	@Test
	public void testLetter() {
		// Test that a single letter is not an acceptance state, and doesn't throw exception
		try {
			assertFalse(fsm.isValid("C"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test that 2 letters is not an acceptance state, and doesn't throw exception
		try {
			assertFalse(fsm.isValid("CS"));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test that 3 letters is not an acceptance state, and doesn't throw exception
		try {
			assertFalse(fsm.isValid("CSC"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test that 4 letters is not an acceptance state, and doesn't throw exception
		try {
			assertFalse(fsm.isValid("CSCC"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test that 5 letters is an InvalidTransition
		try {
			assertFalse(fsm.isValid("CSCCC"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}

		// Test that other character is an InvalidTransition
		try {
			assertFalse(fsm.isValid("C%"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		// Test that other character is an InvalidTransition
		try {
			assertFalse(fsm.isValid("CS%"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		// Test that other character is an InvalidTransition
		try {
			assertFalse(fsm.isValid("CSC%"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		// Test that other character is an InvalidTransition
		try {
			assertFalse(fsm.isValid("CSCC%"));
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		// Test if digit is called after 1 letter
		try{
			assertFalse(fsm.isValid("C1"));
		} catch(InvalidTransitionException e){
			fail();
		}

		// Test if digit is called after 2 letters
		try{
			assertFalse(fsm.isValid("CS1"));
		} catch(InvalidTransitionException e){
			fail();
		}
		
		// Test if digit is called after 3 letters
		try{
			assertFalse(fsm.isValid("CSC1"));
		} catch(InvalidTransitionException e){
			fail();
		}
		
		// Test valid course name with 1 digit
		try {
			assertTrue(fsm.isValid("C116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test valid course name with 4 digits
		try {
			assertTrue(fsm.isValid("CSCC116"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		
	}
	
	/**
	 * Test the digit state of the CourseNameValidator
	 */
	@Test
	public void testDigit() {
		
		// Testing that valid transition with 1 digit doesn't throw error
		try{
			assertFalse(fsm.isValid("C1"));
		} catch(InvalidTransitionException e){
			fail();
		}

		// Testing that valid transition with 2 digits doesn't throw error
		try{
			assertFalse(fsm.isValid("C16"));
		} catch(InvalidTransitionException e){
			fail();
		}

		// Testing that valid transition with 3 digits doesn't throw error
		try{
			assertTrue(fsm.isValid("C116"));
		} catch(InvalidTransitionException e){
			fail();
		}
		
		// Testing that transition with other character throws error
		try{
			fsm.isValid("C1@");
			fail();
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

		// Testing that transition with other character throws error
		try{
			fsm.isValid("C16@");
			fail();
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

		// Testing that transition with other character throws error
		try{
			fsm.isValid("C116@");
			fail();
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
		
		// Testing an amount of digits not equal to three
		try{
			fsm.isValid("C1A");
			fail();
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}

		// Testing an amount of digits not equal to three
		try{
			fsm.isValid("C16A");
			fail();
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}

		// Testing 4 digits throws error
		try{
			fsm.isValid("C1166");
			fail();
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name can only have 3 digits.");
		}
	}
	
	/**
	 * Tests the suffix state of CourseNameValidator
	 */
	@Test
	public void testSuffix() {
		
		// Test valid suffix
		try{
			assertTrue(fsm.isValid("C116A"));
		} catch(InvalidTransitionException e){
			fail();
		}
		
		// Testing too many letters in the suffix
		try{
			fsm.isValid("C116AB");
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
		}
		
		// Testing a number in the suffix
		try{
			fsm.isValid("C116A1");
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
		}
		
		// Testing going to other from suffix
		try{
			fsm.isValid("C116%");
		} catch(InvalidTransitionException e){
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
	}
}
