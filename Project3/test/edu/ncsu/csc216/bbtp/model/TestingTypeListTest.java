package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the TestingTypeList class
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class TestingTypeListTest {

	/** Name of testing type */
	private static final String NAME = "Boundary Value";
	/** Description of testing type */
	private static final String DESCRIPTION = "Tests values at boundaries";
	/** Testing Type ID */
	private static final String TESTING_TYPE_ID = "TT1";
	
	/**
	 * Test constructing a TestingTypeList object.
	 */
	@Test
	public void testTestingTypeList() {
		TestingTypeList t = new TestingTypeList();
		assertEquals(0, t.size());
		assertTrue(t.isEmpty());
		assertEquals("Testing Types", t.getName());
	}

	/**
	 * Test adding a testing type to the list
	 */
	@Test
	public void testAddTestingType() {
		TestingTypeList t = new TestingTypeList();
		
		//Add valid
		assertTrue(t.addTestingType(NAME, DESCRIPTION));
		assertEquals(1, t.size());
		assertFalse(t.isEmpty());
		assertEquals(NAME, t.getTestingTypeAt(0).getName());
		assertEquals(DESCRIPTION, t.getTestingTypeAt(0).getDescription());
		assertEquals(TESTING_TYPE_ID, t.getTestingTypeAt(0).getTestingTypeID());
		
		//Add another
		assertTrue(t.addTestingType("name", "desc"));
		assertEquals(2, t.size());
		assertEquals("TT2", t.getTestingTypeAt(1).getTestingTypeID());
		assertEquals("name", t.getTestingTypeAt(1).getName());
		assertEquals("desc", t.getTestingTypeAt(1).getDescription());
	}

	/**
	 * Test indexOf an ID.
	 */
	@Test
	public void testIndexOf() {
		TestingTypeList t = new TestingTypeList();
		
		//Add some
		t.addTestingType(NAME, DESCRIPTION);
		t.addTestingType("n2", "d2");
		t.addTestingType("n3", "d3");
		assertEquals(3, t.size());
		
		//Test indexOf
		assertEquals(0, t.indexOf("TT1"));
		assertEquals(1, t.indexOf("TT2"));
		assertEquals(2, t.indexOf("TT3"));
		assertEquals(-1, t.indexOf("TT4"));
	}

	/**
	 * Test indexOf a name.
	 */
	@Test
	public void testIndexOfName() {
		TestingTypeList t = new TestingTypeList();
		
		//Add some
		t.addTestingType(NAME, DESCRIPTION);
		t.addTestingType("n2", "d2");
		t.addTestingType("n3", "d3");
		assertEquals(3, t.size());
		
		//Test indexOf
		assertEquals(0, t.indexOfName(NAME));
		assertEquals(1, t.indexOfName("n2"));
		assertEquals(2, t.indexOfName("n3"));
		assertEquals(-1, t.indexOfName("n1"));
	}

	/**
	 * Remove a testing type at an index
	 */
	@Test
	public void testRemoveTestingTypeAt() {
		TestingTypeList t = new TestingTypeList();
		
		//Add some
		t.addTestingType(NAME, DESCRIPTION);
		t.addTestingType("n2", "d2");
		t.addTestingType("n3", "d3");
		t.addTestingType("n4", "d4");
		assertEquals(4, t.size());
		
		//Remove from beginning of list
		assertEquals("TT1", t.removeTestingTypeAt(0).getTestingTypeID());
		assertEquals(3, t.size());
		
		//Remove from middle of list
		assertEquals("TT3", t.removeTestingTypeAt(1).getTestingTypeID());
		assertEquals(2, t.size());
		
		//Remove from end of list
		assertEquals("TT4", t.removeTestingTypeAt(1).getTestingTypeID());
		assertEquals(1, t.size());
		
		//Test that you can still add
		t.addTestingType("n5", "d5");
		assertEquals(2, t.size());
		assertEquals("TT5", t.getTestingTypeAt(1).getTestingTypeID());
		
		//Lower index out of bounds
		try
		{
			t.removeTestingTypeAt(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(2, t.size());
		}
		
		//Upper index out of bounds
		try
		{
			t.removeTestingTypeAt(2);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(2, t.size());
		}
	}

	/**
	 * Test removing a testing type by ID
	 */
	@Test
	public void testRemoveTestingType() {
		TestingTypeList t = new TestingTypeList();
		
		//Add some
		t.addTestingType(NAME, DESCRIPTION);
		t.addTestingType("n2", "d2");
		t.addTestingType("n3", "d3");
		t.addTestingType("n4", "d4");
		assertEquals(4, t.size());
		
		//Remove from beginning of list
		assertTrue(t.removeTestingType("TT1"));
		assertEquals(3, t.size());
		
		//Remove from middle of list
		assertTrue(t.removeTestingType("TT3"));
		assertEquals(2, t.size());
		
		//Remove from end of list
		assertTrue(t.removeTestingType("TT4"));
		assertEquals(1, t.size());
		
		//Try to remove one that doesn't exist
		assertFalse(t.removeTestingType("TT5"));
	}

	/**
	 * Test getting the 2D Array
	 */
	@Test
	public void testGet2DArray() {
		TestingTypeList t = new TestingTypeList();
		
		//Add some
		t.addTestingType(NAME, DESCRIPTION);
		t.addTestingType("n2", "d2");
		assertEquals(2, t.get2DArray().length);
		
		//Check array
		assertEquals(TESTING_TYPE_ID, t.get2DArray()[0][0]);
		assertEquals(NAME, t.get2DArray()[0][1]);
		assertEquals(DESCRIPTION, t.get2DArray()[0][2]);
		assertEquals("TT2", t.get2DArray()[1][0]);
		assertEquals("n2", t.get2DArray()[1][1]);
		assertEquals("d2", t.get2DArray()[1][2]);
	}

	/**
	 * Test update method
	 */
	@Test
	public void testUpdate() {
		TestingTypeList t = new TestingTypeList();
		
		t.addTestingType(NAME, DESCRIPTION);
		t.update(new TestingType(TESTING_TYPE_ID, NAME, DESCRIPTION), t.getTestingTypeAt(0));
		assertFalse(t.hasChanged());
	}
	
	/**
	 * Test getting testing type at an index
	 */
	@Test
	public void getTestingTypeAt() {
		TestingTypeList t = new TestingTypeList();
		
		//Add valid
		assertTrue(t.addTestingType(NAME, DESCRIPTION));
		assertEquals(1, t.size());
		assertFalse(t.isEmpty());
		assertEquals(NAME, t.getTestingTypeAt(0).getName());
		assertEquals(DESCRIPTION, t.getTestingTypeAt(0).getDescription());
		assertEquals(TESTING_TYPE_ID, t.getTestingTypeAt(0).getTestingTypeID());
	
		//Lower index out of bounds
		try
		{
			t.getTestingTypeAt(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(1, t.size());
		}
		
		//Upper index out of bounds
		try
		{
			t.getTestingTypeAt(1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(1, t.size());
		}
	}
}
