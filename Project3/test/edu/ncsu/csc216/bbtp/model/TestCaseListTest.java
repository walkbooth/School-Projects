/**
 * 
 */
package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Tests the TestCaseList class
 * @author Walker Booth
 * @author Noah Trimble 
 */
public class TestCaseListTest {
	
	/** Valid name for a test case list */
	private static final String VALID_NAME = "List 1";
	/** Valid id for a test case list */
	private static final String VALID_ID = "ID";
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
	@SuppressWarnings("deprecation")
	private static final Date LAST_TESTED_DATE_TIME_LATER = new Date(117, 4, 14, 4, 41, 0);
	@SuppressWarnings("deprecation")
	private static final Date LAST_TESTED_DATE_TIME_EARLIER = new Date(117, 4, 8, 4, 41, 0);
	/** Has the test been run? */
	private static final boolean TESTED_STATUS = true;
	/** Has the test passed? */
	private static final boolean PASS = true;
	/** Type of test */
	private static final TestingType TESTING_TYPE = new TestingType("TT1", "Boundary Value", "Tests boundaries");
	

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#TestCaseList(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testTestCaseList() {
		TestCaseList t = null;
		try {
			t = new TestCaseList(null, VALID_ID);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t);
		}
		
		try {
			t = new TestCaseList("", VALID_ID);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t);
		}
		
		try {
			t = new TestCaseList(VALID_NAME, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t);
		}
		
		try {
			t = new TestCaseList(VALID_NAME, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t);
		}
		
		t = new TestCaseList(VALID_NAME, VALID_ID);
		assertEquals(VALID_NAME, t.getName());
		assertEquals(VALID_ID, t.getTestCaseListID());
		assertEquals(0, t.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#addTestCase(java.lang.String, edu.ncsu.csc216.bbtp.model.TestingType, java.util.Date, java.lang.String, boolean, java.util.Date, java.lang.String, boolean)}.
	 */
	@Test
	public void testAddTestCase() {
		TestCaseList t = new TestCaseList(VALID_NAME, VALID_ID);
		TestCase test1 = new TestCase("ID-TC1", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase test2 = new TestCase("ID-TC2", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS);
		TestCase test3 = new TestCase("ID-TC3", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS);

		assertTrue(t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS));
		assertEquals(1, t.size());
		assertEquals(test1, t.getTestCaseAt(0));
		assertTrue(t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS));
		assertEquals(2, t.size());
		assertEquals(test1, t.getTestCaseAt(0));
		assertEquals(test2, t.getTestCaseAt(1));
		assertTrue(t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS));
		assertEquals(3, t.size());
		assertEquals(test3, t.getTestCaseAt(0));
		assertEquals(test1, t.getTestCaseAt(1));
		assertEquals(test2, t.getTestCaseAt(2));	
		
		assertFalse(t.addTestCase("", TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS));

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#indexOf(java.lang.String)}.
	 */
	@Test
	public void testIndexOf() {
		TestCaseList t = new TestCaseList (VALID_NAME, VALID_ID);
		
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS);
		assertEquals(0, t.indexOf("ID-TC3"));
		assertEquals(1, t.indexOf("ID-TC1"));
		assertEquals(2, t.indexOf("ID-TC2"));
		assertEquals(-1, t.indexOf("this isn't here"));
	}
	

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		TestCaseList t = new TestCaseList (VALID_NAME, VALID_ID);
		assertTrue(t.isEmpty());
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		assertFalse(t.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#removeTestCaseAt(int)}.
	 */
	@Test
	public void testRemoveTestCaseAt() {
		TestCaseList t = new TestCaseList (VALID_NAME, VALID_ID);
		try {
			t.removeTestCaseAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
		try {
			t.removeTestCaseAt(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
		
		TestCase test1 = new TestCase("ID-TC1", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase test2 = new TestCase("ID-TC2", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS);
		TestCase test3 = new TestCase("ID-TC3", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS);
		
		assertEquals(test1, t.removeTestCaseAt(1));
		assertEquals(test3, t.getTestCaseAt(0));
		assertEquals(test2, t.getTestCaseAt(1));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#removeTestCase(java.lang.String)}.
	 */
	@Test
	public void testRemoveTestCase() {
		TestCaseList t = new TestCaseList (VALID_NAME, VALID_ID);
		
		TestCase test1 = new TestCase("ID-TC1", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		TestCase test2 = new TestCase("ID-TC2", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS);
		TestCase test3 = new TestCase("ID-TC3", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_LATER, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME_EARLIER, ACTUAL_RESULTS, PASS);
	
		assertTrue(t.removeTestCase(test1.getTestCaseID()));
		assertEquals(test3, t.getTestCaseAt(0));
		assertEquals(test2, t.getTestCaseAt(1));
		
		assertFalse(t.removeTestCase(""));
		assertEquals(test3, t.getTestCaseAt(0));
		assertEquals(test2, t.getTestCaseAt(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bbtp.model.TestCaseList#get2DArray()}.
	 */
	@Test
	public void testGet2DArray() {
		TestCaseList t = new TestCaseList (VALID_NAME, VALID_ID);
		TestCase test1 = new TestCase("ID-TC1", DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);
		t.addTestCase(DESCRIPTION, TESTING_TYPE, CREATION_DATE_TIME, EXPECTED_RESULTS, TESTED_STATUS, LAST_TESTED_DATE_TIME, ACTUAL_RESULTS, PASS);

		Object[][] o = new Object[1][9];
		o = t.get2DArray();
		assertEquals(test1.getTestCaseID() , (String) o[0][0]);
		assertEquals(test1.getDescription() , o[0][1]);
		assertEquals(test1.getTestingType() , o[0][2]);
		assertEquals(test1.getCreationDateTime() , o[0][3]);
		assertEquals(test1.getLastTestedDateTime() , o[0][4]);
		assertEquals(test1.tested(), o[0][5]);
		assertEquals(test1.getExpectedResults(), o[0][6]);
		assertEquals(test1.getActualResults() , o[0][7]);
		assertEquals(test1.pass(), o[0][8]);
		
	}

}
