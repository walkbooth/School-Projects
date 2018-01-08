package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests class SortedList
 * @author Daniel Grist 
 * @author Alex Johnson
 * @author Victor Schroder
 */
public class SortedListTest {
	/**
	 * Tests the constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		for (int i = 0; i < 11; i++) {
			list.add("value" + i);
		}
		assertEquals(11, list.size());
		
	}
	/**
	 * Adds an object to the list
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		// Test adding an element
		assertTrue(list.add("banana"));
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// Test adding to the front of the list
		assertTrue(list.add("apple"));
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		
		// Test adding to the middle of the list
		assertTrue(list.add("axe"));
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("axe", list.get(1));
		assertEquals("banana", list.get(2));
		
		// Test adding to the end of the list
		assertTrue(list.add("cat"));
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("axe", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals("cat", list.get(3));
		
		// Test adding null doesn't add
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("axe", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("cat", list.get(3));
		}
		
		// Test adding a duplicate doesn't add
		try {
			list.add("cat");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("axe", list.get(1));
			assertEquals("banana", list.get(2));
			assertEquals("cat", list.get(3));
		}
		
	}
	/**
	 * Test method get of SortedList
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//checks list is empty
		}
		
		list.add("Coconut");
		list.add("Pear");
		assertEquals(list.get(0), "Coconut");
		assertEquals(list.get(1), "Pear");
		assertEquals(list.size(), 2);
		
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//checks list.get when index is -1
		}
		
		try {
			list.get(list.size());
		} catch (IndexOutOfBoundsException e) {
			//checks list.get when index is equal to size
		}
	}
	
	/**
	 * Test method removed of Sorted List
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		try{
			list.remove(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			//calls .remove on an empty list
		}
		
		list.add("apple");
		list.add("banana");
		list.add("coconut");
		list.add("dragon fruit");
		try{
			list.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			//checks list.remove when index is -1
		}
		
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			//checks list.remove when index is equal to size
		}
		
		list.remove(1);
		assertEquals(3, list.size());
		list.remove(list.size() - 1);
		assertEquals(2, list.size());
		list.remove(0);
		assertEquals(1, list.size());
		list.remove(list.size() - 1);
		assertEquals(0, list.size());

	}
	/**
	 * Tests the indexOf method of Sorted List
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(-1, list.indexOf("apple"));
		list.add("apple");
		list.add("banana");
		list.add("coconut");
		assertEquals( 0, list.indexOf("apple"));
		assertEquals( 1, list.indexOf("banana"));
		assertEquals( 2, list.indexOf("coconut"));
		assertEquals( -1, list.indexOf("pig"));
		
		try{
			list.indexOf(null);
			fail();
		} catch(NullPointerException e) {
			//calls indexOf(null) 
		}
	}
	/**
	 * Test clear method of SortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add("apple");
		list.add("banana");
		list.add("coconut");
		
		list.clear();
		assertEquals(0, list.size());
		
		// Test clearing an empty list
		list.clear();
		assertEquals(0, list.size());
	}
	/**
	 * Test isEmpty method of SortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		assertTrue(list.isEmpty());
		list.add("apple");
		list.add("banana");
		list.add("coconut");

		assertFalse(list.isEmpty());
		
		list.clear();
		assertTrue(list.isEmpty());
	}
	/**
	 * Tests contains method of SortedList
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		assertFalse(list.contains("apple"));

		list.add("banana");
		list.add("coconut");
		assertFalse(list.contains("apple"));

		list.add("apple");
		assertTrue(list.contains("apple"));
		
		list.remove(list.indexOf("apple"));
		assertFalse(list.contains("apple"));
	}
	/**
	 * Tests equals method of SortedList
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("banana");
		list1.add("coconut");
		list2.add("banana");
		list2.add("coconut");
		list3.add("pear");
		
		// Test equal lists are equal both ways
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertTrue(list1.equals(list1));

		// Test different lists aren't equals
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}
	
	/**
	 * Tests hashCode method of SortedList
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("banana");
		list1.add("coconut");
		list2.add("banana");
		list2.add("coconut");
		list3.add("pear");
		
		// Test equal lists hash both ways
		assertEquals(list1.hashCode(), list2.hashCode());

		// Test different lists aren't equals
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 