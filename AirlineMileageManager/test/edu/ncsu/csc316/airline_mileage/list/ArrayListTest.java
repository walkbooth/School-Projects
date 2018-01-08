package edu.ncsu.csc316.airline_mileage.list;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * Test class for ArrayList
 * @author Walker Booth (wgbooth)
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
	
	/**
	 * Tests the sort method 
	 */
	public void testSort () {
		ArrayList<String> list = new ArrayList<String> ();
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
		
		list.sort();
		assertEquals("B", list.get(0));
		assertEquals("E", list.get(1));
		assertEquals("G", list.get(2));
		assertEquals("H", list.get(3));
		assertEquals("K", list.get(4));
		assertEquals("L", list.get(5));
		assertEquals("O", list.get(6));
		assertEquals("O", list.get(7));
		assertEquals("R", list.get(8));
		assertEquals("R", list.get(9));
		assertEquals("T", list.get(10));
	}
	
	/**
	 * Tests the indexOf method 
	 */
	public void testIndexOf () {
		ArrayList<String> list = new ArrayList<String> ();
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
		
		list.sort();
		
		assertEquals(0, list.indexOf("B"));
		assertEquals(5, list.indexOf("L"));
		assertEquals(10, list.indexOf("T"));
		assertEquals(-1, list.indexOf("Z"));
	}
}
