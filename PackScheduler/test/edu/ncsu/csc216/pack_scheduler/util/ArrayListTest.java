package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.HashMap;
import org.junit.Test;
import java.awt.Color;

/**
 * Tests the ArrayList class.
 * @author Alex Johnson
 * @author James Ritchey
 * @author Connor McCarthy 
 */
public class ArrayListTest {

	/**
	 * Tests construction of an ArrayList
	 */
	@Test
	public void testNewArrayList () {

		// Test making an ArrayList of BigInteger
		ArrayList<BigInteger> httpStatusCodes = new ArrayList<BigInteger>();
		assertEquals(0, httpStatusCodes.size());
		httpStatusCodes.add(new BigInteger("200"));
		httpStatusCodes.add(new BigInteger("403"));
		httpStatusCodes.add(new BigInteger("404"));
		assertEquals(3, httpStatusCodes.size());
		
		// Test Making an ArrayList of String
		ArrayList<String> httpStatus = new ArrayList<String>();
		assertEquals(0, httpStatus.size());
		httpStatus.add("OK");
		httpStatus.add("FORBIDDEN");
		httpStatus.add("NOT FOUND");
		assertEquals(3, httpStatus.size());
		
		// Test making an ArrayList of another data collection type
		ArrayList<HashMap<String, Color>> palettes  = new ArrayList<HashMap<String, Color>>();
		HashMap<String, Color> colors = new HashMap<String, Color>();
		colors.put("r", Color.RED);
		colors.put("y", Color.YELLOW);
		colors.put("b", Color.BLUE);
		colors.put("g", Color.GREEN);
		
		// Tests that adding doesn't change data
		assertEquals(0, palettes.size());
		palettes.add(colors);
		assertEquals(1, palettes.size());
		assertEquals(Color.BLUE, palettes.get(0).get("b"));
		
		
	}
	
	/**
	 * Tests method add of class ArrayList
	 */
	@Test
	public void testAdd () {
		
		// Test adding String
		ArrayList<String> httpHeader = new ArrayList<String>();
		assertEquals(0, httpHeader.size());
		httpHeader.add("HTTP/1.1 200 OK");
		httpHeader.add("HTTP/1.1 403 FORBIDDEN");
		httpHeader.add("HTTP/1.1 404 NOT FOUND");
		httpHeader.add("HTTP/1.1 500 INTERNAL SERVER ERROR");
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
		httpHeader.add("HTTP/1.1 301 REDIRECT");
		assertEquals(4, httpHeader.size());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.get(0));
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.get(1));
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.get(2));
		assertEquals("HTTP/1.1 301 REDIRECT", httpHeader.get(3));
		
		//Resets httpHeader
		httpHeader = new ArrayList<String>();
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
		ArrayList<String> httpHeader = new ArrayList<String>();
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
		
		try {
			httpHeader.add(0, "HTTP/1.1 403 FORBIDDEN");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, httpHeader.size());
		}
		
	}
	
	/**
	 * Tests method remove of class ArrayList
	 */
	@Test
	public void testRemove () {
		
		// test removing from an empty array
		ArrayList<String> httpHeader = new ArrayList<String>();
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
		
	}
	
	/**
	 * Tests method get of class ArrayList
	 */
	@Test
	public void testGet () {
		
		// Test getting with String
		ArrayList<String> request = new ArrayList<String>();
		
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
	
		ArrayList<String> httpHeader = new ArrayList<String>();
		
		try {
			httpHeader.set(0, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(0, httpHeader.size());
		}
		
		try {
			httpHeader.set(-1, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(0, httpHeader.size());
		}
		
		try {
			httpHeader.set(1, "HTTP/2.0");
			fail();
		} catch (IndexOutOfBoundsException e) {
			// shouldn't change anything
			assertEquals(0, httpHeader.size());
		}
		
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
		
		ArrayList<String> queries = new ArrayList<String>();
		
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
	
}
