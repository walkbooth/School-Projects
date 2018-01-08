package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

/**
 * Tests the LinkedList class.
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public class LinkedListTest {

	/**
	 * Tests construction of an LinkedList
	 */
	@Test
	public void testNewLinkedList () {

		// Test making an ArrayList of BigInteger
		LinkedList<Integer> httpStatusCodes = new LinkedList<Integer>();
		assertEquals(0, httpStatusCodes.size());
		httpStatusCodes.add(new Integer(200));
		httpStatusCodes.add(new Integer(403));
		httpStatusCodes.add(new Integer(404));
		assertEquals(3, httpStatusCodes.size());
		
		// Test Making an ArrayList of String
		LinkedList<String> httpStatus = new LinkedList<String>();
		assertEquals(0, httpStatus.size());
		httpStatus.add("OK");
		httpStatus.add("FORBIDDEN");
		httpStatus.add("NOT FOUND");
		assertEquals(3, httpStatus.size());
		
		
	}
	
	/**
	 * Tests method add of class ArrayList
	 */
	@Test
	public void testAdd () {
		
		// Test adding String
		LinkedList<String> httpHeader = new LinkedList<String>();
		assertEquals(0, httpHeader.size());
		httpHeader.add("HTTP/1.1 200 OK");
		httpHeader.add("HTTP/1.1 403 FORBIDDEN");
		httpHeader.add("HTTP/1.1 404 NOT FOUND");
		assertEquals(3, httpHeader.size());
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(1));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(2));

		
		// Test that removing can happen after adding
		httpHeader.remove(0); // removes 200
		assertEquals(2, httpHeader.size());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		
		// Test that adding works after removing
		httpHeader.add("HTTP/1.1 301 REDIRECT");
		assertEquals(3, httpHeader.size());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(2));
		
		//Resets httpHeader
		httpHeader = new LinkedList<String>();
		//Adding past capacity to see if array "grows"
		httpHeader.add("HTTP/1.1 162");
		httpHeader.add("HTTP/1.1 634");
		httpHeader.add("HTTP/1.1 244");
		httpHeader.add("HTTP/1.1 345");
		httpHeader.add("HTTP/1.1 609");
		httpHeader.add("HTTP/1.1 686");
		httpHeader.add("HTTP/1.1 354");
		httpHeader.add("HTTP/1.1 967");
		httpHeader.add("HTTP/1.1 346");
		httpHeader.add("HTTP/1.1 567");
		httpHeader.add("HTTP/1.1 456");
		

	}
	
	
	/**
	 * Tests method add of class ArrayList
	 */
	@Test
	public void testAddAtIndex () {
		
		// Test adding String
		LinkedList<String> httpHeader = new LinkedList<String>();
		assertEquals(0, httpHeader.size());
		httpHeader.add(0, "HTTP/1.1 403 FORBIDDEN");
		httpHeader.add(1, "HTTP/1.1 500 INTERNAL SERVER ERROR");
		httpHeader.add(1, "HTTP/1.1 404 NOT FOUND");
		httpHeader.add(0, "HTTP/1.1 200 OK");
		assertEquals(4, httpHeader.size());
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(1));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(2));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(3));
		
		// Test that removing can happen after adding
		httpHeader.remove(0); // removes 200
		assertEquals(3, httpHeader.size());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
		
		// Test that adding works after removing
		httpHeader.add(3, "HTTP/1.1 301 REDIRECT");
		assertEquals(4, httpHeader.size());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
		assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		
		try {
			httpHeader.add(-1, "HTTP/1.1 200 OK");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, httpHeader.size());
		}
		
		try {
			httpHeader.add(5, "HTTP/1.1 200 OK");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, httpHeader.size());
		}
		
		try {
			httpHeader.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, httpHeader.size());
		}
		
		httpHeader.add(0, "HTTP/1.1 413 FORBIDDEN");
		
	}
	
	/**
	 * Tests method remove of class ArrayList
	 */
	@Test
	public void testRemove () {
		
		// test removing from an empty array
		LinkedList<String> httpHeader = new LinkedList<String>();
		assertEquals(0, httpHeader.size());
		try {
			httpHeader.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, httpHeader.size());
		}
		
		// add some stuff to remove
		httpHeader.add("HTTP/1.1 200 OK");
		httpHeader.add("HTTP/1.1 403 FORBIDDEN");
		httpHeader.add("HTTP/1.1 404 NOT FOUND");
		httpHeader.add("HTTP/1.1 500 INTERNAL SERVER ERROR");

		try {
			httpHeader.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(1));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(2));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(3));
		}
		
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.remove(1));
		assertEquals(3, httpHeader.size());
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));

		try {
			httpHeader.remove(-1); 
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, httpHeader.size());
		}
		
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.remove(2));
		assertEquals(2, httpHeader.size());
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));

		assertEquals("HTTP/1.1 200 OK", httpHeader.remove(0));
		assertEquals(1, httpHeader.size());
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(0));

		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.remove(0));
		assertEquals(0, httpHeader.size());
		
		// test removing after removing all elements
		try {
			httpHeader.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, httpHeader.size());
		}
		
		// test removing back
		httpHeader.add("HTTP/1.1 200 OK");
		httpHeader.add("HTTP/1.1 403 FORBIDDEN");
		httpHeader.add("HTTP/1.1 404 NOT FOUND");
		httpHeader.add("HTTP/1.1 500 INTERNAL SERVER ERROR");
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.remove(3));
		
	}
	
	/**
	 * Tests method get of class ArrayList
	 */
	@Test
	public void testGet () {
		
		// Test getting with String
		LinkedList<String> request = new LinkedList<String>();
		
		// try getting from an empty Array
		try {
			request.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, request.size());
		}
		
		// testing adding
		assertEquals(0, request.size());
		// adding 1 works
		request.add("GET /courses/ma/ma241");
		assertEquals(1, request.size());
		assertEquals("GET /courses/ma/ma241", request.get(0));
		// adding one when there's another element 
		request.add("GET /courses/py/ma205");
		assertEquals(2, request.size());
		assertEquals("GET /courses/ma/ma241", request.get(0));
		assertEquals("GET /courses/py/ma205", request.get(1));
		// adding one when there's two elements
		request.add("POST /courses/csc/csc117");
		assertEquals(3, request.size());
		assertEquals("GET /courses/ma/ma241", request.get(0));
		assertEquals("GET /courses/py/ma205", request.get(1));
		assertEquals("POST /courses/csc/csc117", request.get(2));
		
		request.add("PUT /courses/csc/csc117 {id: intro}");
		assertEquals(4, request.size());
		assertEquals("GET /courses/ma/ma241", request.get(0));
		assertEquals("GET /courses/py/ma205", request.get(1));
		assertEquals("POST /courses/csc/csc117", request.get(2));
		assertEquals("PUT /courses/csc/csc117 {id: intro}", request.get(3));
		
		
		// trying gets out of bounds
		try {
			request.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(4, request.size());
			assertEquals("GET /courses/ma/ma241", request.get(0));
			assertEquals("GET /courses/py/ma205", request.get(1));
			assertEquals("POST /courses/csc/csc117", request.get(2));
			assertEquals("PUT /courses/csc/csc117 {id: intro}", request.get(3));
		}
		
		// trying gets out of bounds, top boundary
		try {
			request.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(4, request.size());
			assertEquals("GET /courses/ma/ma241", request.get(0));
			assertEquals("GET /courses/py/ma205", request.get(1));
			assertEquals("POST /courses/csc/csc117", request.get(2));
			assertEquals("PUT /courses/csc/csc117 {id: intro}", request.get(3));
		}
		
	}
	
	/**
	 * Tests method set of class ArrayList 
	 */
	@Test
	public void testSet () {
	
		LinkedList<String> httpHeader = new LinkedList<String>();
		
		try {
			httpHeader.set(0, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(0, httpHeader.size());
		}
		
		httpHeader.add("http/1.1 200 OK");
		try {
			httpHeader.set(-1, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(1, httpHeader.size());
		}
		
		try {
			httpHeader.set(2, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(1, httpHeader.size());
		}
		
		httpHeader.remove(0);
		assertEquals(0, httpHeader.size());
		httpHeader.add("HTTP/1.1 403 FORBIDDEN");
		httpHeader.add("HTTP/1.1 404 NOT FOUND");
		httpHeader.add("HTTP/1.1 500 INTERNAL SERVER ERROR");
		httpHeader.add("HTTP/1.1 301 REDIRECT");
		
		// trying setting out of bounds
		try {
			httpHeader.set(-1, "12");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
			assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		}
		
		try {
			httpHeader.set(4, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
			assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		}
		
		try {
			httpHeader.set(5, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
			assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		}
		
		// trying duplicate
		try {
			httpHeader.set(2, "HTTP/1.1 403 FORBIDDEN");
			fail();
		} catch (IllegalArgumentException e) {
			// shouldn't change anything
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
			assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		}
		
		try {
			httpHeader.set(2, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
			assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		}
		
		// trying gets out of bounds, top boundary
		try {
			httpHeader.set(4, "asd");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(4, httpHeader.size());
			assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
			assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
			assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
			assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		}
		
		httpHeader.set(0, "HTTP/1.1 200 OK");
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
		assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));

		httpHeader.set(1, "HTTP/1.1 403 FORBIDDEN");
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(1));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
		assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));

		httpHeader.set(2, "HTTP/1.1 404 NOT FOUND");
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(1));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(2));
		assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));

		httpHeader.set(3, "HTTP/1.1 500 INTERNAL SERVER ERROR");
		assertEquals("HTTP/1.1 200 OK", httpHeader.get(0));
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(1));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(2));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(3));
		
	}
	
	/**
	 * Tests method size of class ArrayList
	 */
	@Test
	public void testSize () {
		
		LinkedList<String> queries = new LinkedList<String>();
		
		assertEquals(0, queries.size());
		
		assertEquals(0, queries.size());
		
		queries.add("SELECT * FROM Students");
		assertEquals(1, queries.size());
		
		queries.add("SELECT NAME FROM Students");
		assertEquals(2, queries.size());
		
		queries.add("SELECT ID FROM Students");
		assertEquals(3, queries.size());
		
		queries.add("SELECT EMAIL FROM Students");
		queries.add("DROP TABLE Students");
		assertEquals(5, queries.size());
		assertEquals(5, queries.size());
		
	}
	
	/**
	 * Tests iterator of LinkedLink
	 */
	@Test
	public void testListIterator () {
		LinkedList<String> httpHeader = new LinkedList<String>();
		assertEquals(0, httpHeader.size());
		httpHeader.add("HTTP/1.1 403 FORBIDDEN");
		httpHeader.add("HTTP/1.1 404 NOT FOUND");
		httpHeader.add("HTTP/1.1 500 INTERNAL SERVER ERROR");
		httpHeader.add("HTTP/1.1 301 REDIRECT");
		
		int i = 0;
		for (String header : httpHeader) {
			if (i == 0) {
				assertEquals("HTTP/1.1 403 FORBIDDEN", header);
			} else if (i == 1) {
				assertEquals("HTTP/1.1 404 NOT FOUND", header);
			} else if (i == 2) {
				assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", header);
			} else if (i == 3) {
				assertEquals("HTTP/1.1 301 REDIRECT", header);
			}
			i++;
		}
		
		ListIterator<String> it = (ListIterator<String>)httpHeader.iterator();
		assertTrue(it.hasNext());
		assertFalse(it.hasPrevious());
		assertEquals(0, it.nextIndex());
		assertEquals(-1, it.previousIndex());
		assertEquals("HTTP/1.1 403 FORBIDDEN", it.next());
		assertEquals(1, it.nextIndex());
		assertEquals(0, it.previousIndex());
		it.remove();
		assertEquals(1, it.nextIndex());
		assertEquals(0, it.previousIndex());
		try {
			it.remove();
			fail("remove after remove worked");
		} catch (IllegalStateException e) {
			assertEquals(1, it.nextIndex());
			assertEquals(0, it.previousIndex());
		}
		assertEquals("HTTP/1.1 404 NOT FOUND", it.next());
		assertTrue(it.hasNext());
		assertTrue(it.hasPrevious());
		assertEquals(2, it.nextIndex());
		assertEquals(1, it.previousIndex());
		it.set("ha removed the 404 server error");
		assertEquals("ha removed the 404 server error", it.previous());
		assertEquals("ha removed the 404 server error", it.next());
	}
}
