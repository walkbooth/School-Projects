package edu.ncsu.csc316.hub_manager.util;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * Tests the ArrayList class. Test class is designed based on the tests used in Project 1 Part 2 for ArrayList. 
 * @author Walker Booth (wgbooth)
 *
 */
public class ArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.airline_mileage.list.ArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<String> ();
		
		try {
			list.get(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("List is empty, cannot retrieve item.", e.getMessage());
		}
		
		list.add("W");
		assertEquals(1, list.size());
		
		list.add("A");
		assertEquals(2, list.size());
		
		list.add("L");
		list.add("K");
		list.add("E");
		list.add("R");
		list.add("G");
		list.add("R");
		list.add("B");
		list.add("O");
		list.add("O");
		list.add("T");
		list.add("H");
		
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Requested index is out of bounds.", e.getMessage());
		}
		
		try {
			list.get(13);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Requested index is out of bounds.", e.getMessage());
		}
		
		assertEquals("W", list.get(0));
		assertEquals("A", list.get(1));
		assertEquals("T", list.get(11));
		assertEquals("H", list.get(12));

	}

}
