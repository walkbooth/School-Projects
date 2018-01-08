package edu.ncsu.csc216.bbtp.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the LinkedList class
 * 
 * @author Noah Trimble
 * @author Walker Booth
 *
 */
public class LinkedListTest {

	/**
	 * Tests constructing array list with default capacity
	 */
	@Test
	public void testLinkedList() {
		LinkedList list = new LinkedList();
		assertEquals(0, list.size());
	}

	/**
	 * Tests constructing array list with entered capacity
	 */
	@Test
	public void testLinkedListInt() {
		//Valid construction
		LinkedList list = new LinkedList();
		assertEquals(0, list.size());
	}

	/**
	 * Test contains method
	 */
	@Test
	public void testContains() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		
		list.add("apple");
		list.add("pink");
		assertFalse(list.isEmpty());
		
		assertFalse(list.contains("jesus"));
		assertTrue(list.contains("apple"));
	}

	/**
	 * Test adding an element to the end of the list
	 */
	@Test
	public void testAddObject() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		
		//Add some
		assertTrue(list.add("apple"));
		assertTrue(list.add("pink"));
		assertTrue(list.add("yellow"));
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("pink", list.get(1));
		assertEquals("yellow", list.get(2));
		
		//Remove and make sure you can still add
		list.remove(0);
		assertTrue(list.add("blue"));
		assertEquals(3, list.size());
		assertEquals("blue", list.get(2));
	}

	/**
	 * Test adding an element at an index
	 */
	@Test
	public void testAddIntObject() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		
		//Add some
		list.add("apple");
		list.add("pink");
		list.add("yellow");
		assertEquals(3, list.size());
		
		//Add to beginning of the list
		list.add(0, "one");
		assertEquals(4, list.size());
		assertEquals("one", list.get(0));
		
		//Add to middle of the list
		list.add(2, "two");
		assertEquals(5, list.size());
		assertEquals("two", list.get(2));
		
		//Add to the end of the list
		list.add(5, "five");
		assertEquals(6, list.size());
		assertEquals("five", list.get(5));
		
		//Attempt to add null element
		try
		{
			list.add(0, null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals(6, list.size());
		}
		
		//Lower index out of bounds
		try
		{
			list.add(-1, "b");
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(6, list.size());
		}
		
		//Upper index out of bounds
		try
		{
			list.add(7, "b");
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(6, list.size());
		}
		
		//Add some more
		list.add("bubble");
		list.add(2, "bubbles");
		
		//Try adding a duplicate
		try
		{
			list.add(2, "bubble");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(8, list.size());
		}
	}

	/**
	 * Test removing an element from the list
	 */
	@Test
	public void testRemove() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		
		//Add some
		list.add("apple");
		list.add("pink");
		list.add("yellow");
		list.add("jol");
		list.add("onemore");
		assertEquals(5, list.size());
		
		//Remove from beginning of list
		assertEquals("apple", list.remove(0));
		assertEquals(4, list.size());
		assertEquals("pink", list.get(0));
		
		//Remove from middle of list
		assertEquals("yellow", list.remove(1));
		assertEquals(3, list.size());
		assertEquals("pink", list.get(0));
		
		//Remove from end of the list
		assertEquals("onemore", list.remove(2));
		assertEquals(2, list.size());
		assertEquals("jol", list.get(1));
		
		//Lower index out of bounds
		try
		{
			list.remove(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(2, list.size());
		}
		
		//Upper index out of bounds
		try
		{
			list.remove(2);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(2, list.size());
		}
	}

	/**
	 * Test indexOf method
	 */
	@Test
	public void testIndexOf() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		
		//Add some
		list.add("apple");
		list.add("pink");
		list.add("yellow");
		list.add("jol");
		list.add("onemore");
		assertEquals(5, list.size());
		
		assertEquals(-1, list.indexOf("jogg"));
		assertEquals(0, list.indexOf("apple"));
		assertEquals(2, list.indexOf("yellow"));
		assertEquals(4, list.indexOf("onemore"));
	}
	
	/**
	 * Test getting an element
	 */
	@Test
	public void testGet()
	{
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		
		//Add some
		list.add("apple");
		list.add("pink");
		list.add("yellow");
		list.add("jol");
		list.add("onemore");
		assertEquals(5, list.size());
		
		assertEquals("apple", list.get(0));
		assertEquals("onemore", list.get(4));
		
		//Lower index out of bounds
		try
		{
			list.get(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(5, list.size());
		}
		
		//Upper index out of bounds
		try
		{
			list.get(5);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(5, list.size());
		}
	}
}

