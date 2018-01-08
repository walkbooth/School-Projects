package edu.ncsu.csc216.bbtp;

import static org.junit.Assert.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;

/**
 * This class tests the BBTP class
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class BBTPTest {

	/**
	 * Test constructing BBTP object.
	 */
	@Test
	public void testBBTP() {
		BBTP b = new BBTP();
		assertEquals(1, b.getNumTestCaseLists());
		assertFalse(b.isChanged());
		assertNotNull(b.getTestingTypeList());
		assertEquals("TCL1", b.getTestCaseList(0).getTestCaseListID());
	}

	/**
	 * Test setting changed.
	 */
	@Test
	public void testSetChangedBoolean() {
		BBTP b = new BBTP();
		assertFalse(b.isChanged());
		
		b.setChanged(true);
		assertTrue(b.isChanged());
	}

	/**
	 * Test setting the file name.
	 */
	@Test
	public void testSetFilename() {
		BBTP b = new BBTP();
		
		//Valid set
		b.setFilename("validName");
		assertEquals("validName", b.getFilename());
		
		//Try setting null filename
		try
		{
			b.setFilename(null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("validName", b.getFilename());
		}
		
		//Try setting an empty filename
		try
		{
			b.setFilename("");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals("validName", b.getFilename());
		}
	}

	/**
	 * Test getting a test case list
	 */
	@Test
	public void testGetTestCaseList() {
		BBTP b = new BBTP();
		assertEquals("New List", b.getTestCaseList(0).getName());
		assertEquals("TCL1", b.getTestCaseList(0).getTestCaseListID());
		
		//Lower index out of bounds
		try
		{
			b.getTestCaseList(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(1, b.getNumTestCaseLists());
		}
		
		//Upper index out of bounds
		try
		{
			b.getTestCaseList(1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(1, b.getNumTestCaseLists());
		}
	}

	/**
	 * Test adding a test case list
	 */
	@Test
	public void testAddTestCaseList() {
		BBTP b = new BBTP();
		
		assertEquals(1, b.addTestCaseList());
		assertEquals(2, b.getNumTestCaseLists());
		assertEquals("TCL2", b.getTestCaseList(1).getTestCaseListID());
		
		assertEquals(2, b.addTestCaseList());
		assertEquals(3, b.getNumTestCaseLists());
		assertEquals("TCL3", b.getTestCaseList(2).getTestCaseListID());
		assertEquals("New List", b.getTestCaseList(2).getName());
		
		//Exceed capacity
		assertEquals(3, b.addTestCaseList());
		assertEquals(4, b.getNumTestCaseLists());
		assertEquals("TCL4", b.getTestCaseList(3).getTestCaseListID());
		assertEquals("New List", b.getTestCaseList(3).getName());
	}

	/**
	 * Test removing a test case list
	 */
	@Test
	public void testRemoveTestCaseList() {
		BBTP b = new BBTP();
		b.addTestCaseList();
		b.addTestCaseList();
		b.addTestCaseList();
		b.addTestCaseList();
		
		//Remove from beginning
		b.removeTestCaseList(0);
		assertEquals(4, b.getNumTestCaseLists());
		assertEquals("TCL2", b.getTestCaseList(0).getTestCaseListID());
		
		//Remove from middle
		b.removeTestCaseList(1);
		assertEquals(3, b.getNumTestCaseLists());
		assertEquals("TCL4", b.getTestCaseList(1).getTestCaseListID());
		
		//Remove from end
		b.removeTestCaseList(2);
		assertEquals(2, b.getNumTestCaseLists());
		assertEquals("TCL4", b.getTestCaseList(1).getTestCaseListID());
		
		//Lower index out of bounds
		try
		{
			b.removeTestCaseList(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(2, b.getNumTestCaseLists());
		}
		
		//Upper index out of bounds
		try
		{
			b.removeTestCaseList(2);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(2, b.getNumTestCaseLists());
		}
	}

	/**
	 * Test saving to a data file.
	 */
	@Test
	public void testSaveDataFile() {
		BBTP b = new BBTP();
		assertTrue(b.openDataFile("test_files/reee.bbtp"));
		assertEquals(1, b.getNumTestCaseLists());
		assertEquals("TCL1", b.getTestCaseList(0).getTestCaseListID());
		
		assertTrue(b.saveDataFile("test_files/actual_reee.bbtp"));
		checkFiles("test_files/reee.bbtp", "test_files/actual_reee.bbtp");
	}

	/**
	 * Test opening a data file.
	 */
	@Test
	public void testOpenDataFile() {
		BBTP b = new BBTP();
		assertTrue(b.openDataFile("test_files/reee.bbtp"));
		assertEquals(1, b.getNumTestCaseLists());
		assertEquals("TCL1", b.getTestCaseList(0).getTestCaseListID());
		
		//assertFalse(b.openDataFile("test_files/empty.bbtp"));
	}
	
	/**
	 * Test for update 
	 */
	@Test
	public void testUpdate () {
		BBTP b = new BBTP();
		b.getTestingTypeList().addTestingType("Testing type", "Testing Type");
		assertTrue(b.isChanged());
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
