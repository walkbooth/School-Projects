package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the TestingType class
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestingTypeTest {

	/** Name of testing type */
	private static final String NAME = "Boundary Value";
	/** Description of testing type */
	private static final String DESCRIPTION = "Tests values at boundaries";
	/** Testing Type ID */
	private static final String TESTING_TYPE_ID = "TT1";
	
	/**
	 * Test TestingType constructor
	 */
	@Test
	public void testTestingType() {
		TestingType t = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		assertEquals(NAME, t.getName());
		assertEquals(DESCRIPTION, t.getDescription());
		assertEquals(TESTING_TYPE_ID, t.getTestingTypeID());
		
		t = null;
		//Test empty testing type ID
		try
		{
			t = new TestingType("", NAME, DESCRIPTION);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertNull(t);
		}
		
		//Test null testing type ID
		try
		{
			t = new TestingType(null, NAME, DESCRIPTION);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertNull(t);
		}
	}

	/**
	 * Test setting the name.
	 */
	@Test
	public void testSetName() {
		TestingType t = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		assertEquals(NAME, t.getName());
		
		//Test empty name
		try
		{
			t.setName("");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(NAME, t.getName());
		}
		
		//Test null name
		try
		{
			t.setName(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(NAME, t.getName());
		}
		
		//Test valid name set
		t.setName("B");
		assertEquals("B", t.getName());
	}

	/**
	 * Test setting the description
	 */
	@Test
	public void testSetDescription() {
		TestingType t = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		assertEquals(DESCRIPTION, t.getDescription());
		
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
		
		//Test valid set
		t.setDescription("Does something else");
		assertEquals("Does something else", t.getDescription());
	}
	
	/**
	 * Test hashcode method
	 */
	@Test
	public void testHashCode() {
		TestingType t1 = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		TestingType t2 = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		TestingType t3 = new TestingType("TT2", NAME, DESCRIPTION);
		
		assertEquals(t1.hashCode(), t2.hashCode());
		assertNotEquals(t1.hashCode(), t3.hashCode());
	}

	/**
	 * Test equals method
	 */
	@Test
	public void testEqualsObject() {
		TestingType t1 = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		TestingType t2 = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		TestingType t3 = new TestingType("TT2", NAME, DESCRIPTION);
		
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t1));
		assertFalse(t1.equals(t3));
	}

	/**
	 * Test toString method
	 */
	@Test
	public void testToString() {
		TestingType t = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		String s = "Boundary Value";
		assertEquals(s, t.toString());
	}

	/**
	 * Test compareTo method
	 */
	@Test
	public void testCompareTo() {
		TestingType t1 = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		TestingType t2 = new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION);
		TestingType t3 = new TestingType("TT2", NAME, DESCRIPTION);
		TestingType t4 = new TestingType("TT0", NAME, DESCRIPTION);
		
		assertTrue(t1.compareTo(t2) == 0);
		assertTrue(t1.compareTo(t3) < 0);
		assertTrue(t1.compareTo(t4) > 0);
	}

}
