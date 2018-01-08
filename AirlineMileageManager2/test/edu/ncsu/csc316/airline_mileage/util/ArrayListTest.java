package edu.ncsu.csc316.airline_mileage.util;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.airline_mileage.util.ArrayList;

/**
 * Test class for ArrayList (From Project 1)
 * @author Walker Booth (wgbooth)
 */
public class ArrayListTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.airline_mileage.util.ArrayList#add(java.lang.Object)}.
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
	
	/**
	 * Tests the sort method 
	 */
	@Test
	public void testSort () {
		ArrayList<String> list = new ArrayList<String> ();
		list.add("L");
		list.add("K");
		list.add("E");
		list.add("G");
		list.add("R");
		list.add("B");
		list.add("O");
		list.add("T");
		list.add("H");
		
		list.sort();
		assertEquals("B", list.get(0));
		assertEquals("E", list.get(1));
		assertEquals("G", list.get(2));
		assertEquals("H", list.get(3));
		assertEquals("K", list.get(4));
		assertEquals("L", list.get(5));
		assertEquals("O", list.get(6));
		assertEquals("R", list.get(7));
		assertEquals("T", list.get(8));
		
		ArrayList <String> list2 = new ArrayList<String>();
		list2.add("Z");
		list2.add("O");
		list2.add("M");
		list2.add("B");
		list2.add("I");
		list2.add("E");
		list2.sort();
		
		assertEquals("B", list2.get(0));
		assertEquals("E", list2.get(1));
		assertEquals("I", list2.get(2));
		assertEquals("M", list2.get(3));
		assertEquals("O", list2.get(4));
		assertEquals("Z", list2.get(5));
		
		ArrayList <String> list3 = new ArrayList<String>();
		list3.add("O");
		list3.add("M");
		list3.add("B");
		list3.add("I");
		list3.add("E");
		list3.sort();
		
		assertEquals("B", list3.get(0));
		assertEquals("E", list3.get(1));
		assertEquals("I", list3.get(2));
		assertEquals("M", list3.get(3));
		assertEquals("O", list3.get(4));
	}
	
	/**
	 * Tests the indexOf method 
	 */
	@Test
	public void testIndexOf () {
		ArrayList<String> list = new ArrayList<String> ();
		list.add("L");
		list.add("K");
		list.add("E");
		list.add("G");
		list.add("R");
		list.add("B");
		list.add("O");
		list.add("T");
		list.add("H");
		
		list.sort();
		
		assertEquals(0, list.indexOf("B"));
		assertEquals(5, list.indexOf("L"));
		assertEquals(8, list.indexOf("T"));
		assertEquals(-1, list.indexOf("Z"));
	}
}
