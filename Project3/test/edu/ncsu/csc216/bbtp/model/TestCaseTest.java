package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * This class tests the TestCase class
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestCaseTest {

	/** Test Case ID */
	private static final String TEST_CASE_ID = "TL1-TC1";
	/** Creation date and time of test case */
	@SuppressWarnings("deprecation")
	private static final Date CREATION_DATE_TIME = new Date(117, 4, 10, 4, 37, 0);
	/** Description of test case */
	private static final String DESCRIPTION = "Tests remove - invalid";
	/** Expected results of test case */
	private static final String EXPECTED_RESULTS = "Error message is displayed";
	/** Actual results of test case */
	private static final String ACTUAL_RESULTS = "Error message is displayed";
	/** Date and time the test was last tested */
	@SuppressWarnings("deprecation")
	private static final Date LAST_TESTED_DATE_TIME = new Date(117, 4, 10, 4, 41, 0);
	/** Has the test been run? */
	private static final boolean TESTED_STATUS = true;
	/** Has the test passed? */
	private static final boolean PASS = true;
	/** Type of test */
	private static final TestingType TESTING_TYPE = new TestingType("TT1", "Boundary Value", "Tests boundaries");
	
	/**
	 * Test constructing TestCase object
	 */
	@Test
	public void testTestCase() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
				                  TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		assertEquals(TEST_CASE_ID, t.getTestCaseID());
		assertEquals(DESCRIPTION, t.getDescription());
		assertEquals(TESTING_TYPE, t.getTestingType());
		assertEquals(CREATION_DATE_TIME, t.getCreationDateTime());
		assertEquals(EXPECTED_RESULTS, t.getExpectedResults());
		assertTrue(t.tested());
		assertEquals(LAST_TESTED_DATE_TIME, t.getLastTestedDateTime());
		assertEquals(ACTUAL_RESULTS, t.getActualResults());
		assertTrue(t.pass());
		
		//Test empty test case id
		t = null;
		try
		{
			t = new TestCase("", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
	                  TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertNull(t);
		}
		
		//Test null test case id
		t = null;
		try
		{
			t = new TestCase(null, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
	                  TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertNull(t);
		}
	}

	/**
	 * Test setting the creation date and time
	 */
	@Test
	public void testSetCreationDateTime() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		//Test null creation date and time
		try
		{
			t.setCreationDateTime(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(CREATION_DATE_TIME, t.getCreationDateTime());
		}
	}

	/**
	 * Test setting the description
	 */
	@Test
	public void testSetDescription() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		//Test null description
		try
		{
			t.setDescription(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(DESCRIPTION, t.getDescription());
		}
		
		//Test empty description
		try
		{
			t.setDescription("");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(DESCRIPTION, t.getDescription());
		}
		
		//Test whitespace description
		try
		{
			t.setDescription(" ");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(DESCRIPTION, t.getDescription());
		}
		
		//Test valid description
		t.setDescription("new");
		assertEquals("new", t.getDescription());
	}

	/**
	 * Test setting the expected results
	 */
	@Test
	public void testSetExpectedResults() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		//Test null expected results
		try
		{
			t.setExpectedResults(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(EXPECTED_RESULTS, t.getExpectedResults());
		}
		
		//Test empty expected results
		try
		{
			t.setExpectedResults("");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(EXPECTED_RESULTS, t.getExpectedResults());
		}
		
		//Test whitespace expected results
		try
		{
			t.setExpectedResults(" ");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(EXPECTED_RESULTS, t.getExpectedResults());
		}
		
		//Test valid expected results
		t.setExpectedResults("new");
		assertEquals("new", t.getExpectedResults());
	}

	/**
	 * Test setting the actual results
	 */
	@Test
	public void testSetActualResults() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		//Test null actual results
		try
		{
			t.setActualResults(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(ACTUAL_RESULTS, t.getActualResults());
		}
		
		//Test empty actual results
		try
		{
			t.setActualResults("");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(ACTUAL_RESULTS, t.getActualResults());
		}
		
		//Test whitespace actual results
		try
		{
			t.setActualResults(" ");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(ACTUAL_RESULTS, t.getActualResults());
		}
		
		//Test valid actual results
		t.setActualResults("new");
		assertEquals("new", t.getActualResults());
		
		//Test that actual results can be empty when t is not tested
		t.setTestedStatus(false);
		assertFalse(t.tested());
		t.setActualResults(" ");
		assertEquals(" ", t.getActualResults());
	}

	/**
	 * Test setting the last tested date and time
	 */
	@Test
	public void testSetLastTestedDateTime() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		//Test null last tested date and time
		try
		{
			t.setLastTestedDateTime(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(LAST_TESTED_DATE_TIME, t.getLastTestedDateTime());
		}
	}

	/**
	 * Test setting the testing type
	 */
	@Test
	public void testSetTestingType() {
		TestCase t = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		//Test null testing type
		try
		{
			t.setTestingType(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(TESTING_TYPE, t.getTestingType());
		}
	}

	/**
	 * Test hashcode method
	 */
	@Test
	public void testHashCode() {
		TestCase t1 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase t2 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase t3 = new TestCase("TL1-TC2", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		assertEquals(t1.hashCode(), t2.hashCode());
		assertNotEquals(t1.hashCode(), t3.hashCode());
	}
	
	/**
	 * Test equals method
	 */
	@Test
	public void testEqualsObject() {
		TestCase t1 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase t2 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase t3 = new TestCase("TL1-TC2", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t1));
		assertFalse(t1.equals(t3));
	}

	/**
	 * Test compareTo method
	 */
	@Test
	public void testCompareTo() {
		TestCase t1 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase t2 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		@SuppressWarnings("deprecation")
		TestCase t3 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, new Date(117, 4, 10, 4, 50, 0), ACTUAL_RESULTS, PASS);
		@SuppressWarnings("deprecation")
		TestCase t4 = new TestCase(TEST_CASE_ID, DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, 
                TESTED_STATUS, new Date(117, 4, 10, 4, 30, 0), ACTUAL_RESULTS, PASS);
		
		assertTrue(t1.compareTo(t2) == 0);
		assertTrue(t1.compareTo(t3) < 0);
		assertTrue(t1.compareTo(t4) > 0);
	}

}
