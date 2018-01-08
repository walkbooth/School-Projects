package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.awt.Color;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.HashMap;

import org.junit.Test;

/**
 * Tests class LinkedStack
 * 
 * @author Walker
 * @author Alex Johnson
 */
public class LinkedStackTest {

	/**
	 * Test the construction of an LinkedStack
	 */
	@Test
	public void testNewLinkedStack () {
		
		// Test making an ArrayList of BigInteger
		Stack<BigInteger> httpStatusCodes = new LinkedStack<BigInteger>(3);
		assertEquals(0, httpStatusCodes.size());
		httpStatusCodes.push(new BigInteger("200"));
		httpStatusCodes.push(new BigInteger("403"));
		httpStatusCodes.push(new BigInteger("404"));
		assertEquals(3, httpStatusCodes.size());
		
		// Test Making an ArrayList of String
		Stack<String> httpStatus = new LinkedStack<String>(3);
		assertEquals(0, httpStatus.size());
		httpStatus.push("OK");
		httpStatus.push("FORBIDDEN");
		httpStatus.push("NOT FOUND");
		assertEquals(3, httpStatus.size());
		
		// Test making an ArrayList of another data collection type
		Stack<HashMap<String, Color>> palettes  = new LinkedStack<HashMap<String, Color>>(3);
		HashMap<String, Color> colors = new HashMap<String, Color>();
		colors.put("r", Color.RED);
		colors.put("y", Color.YELLOW);
		colors.put("b", Color.BLUE);
		colors.put("g", Color.GREEN);
		
		// Tests that adding doesn't change data
		assertEquals(0, palettes.size());
		palettes.push(colors);
		assertEquals(1, palettes.size());
		assertEquals(Color.BLUE, palettes.pop().get("b"));
		
		
		
	}
	
	/**
	 * Tests method push of class LinkedStack
	 */
	@Test
	public void testPush () {
		// Test pushing String
		Stack<String> httpHeader = new LinkedStack<String>(5);
		assertEquals(0, httpHeader.size());
		httpHeader.push("HTTP/1.1 200 OK");
		httpHeader.push("HTTP/1.1 403 FORBIDDEN");
		httpHeader.push("HTTP/1.1 404 NOT FOUND");
		httpHeader.push("HTTP/1.1 500 INTERNAL SERVER ERROR");
		assertEquals(4, httpHeader.size());
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.pop());
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.pop());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.pop());
		assertEquals("HTTP/1.1 200 OK", httpHeader.pop());
		
		// Test that pushing works after removing
		assertEquals(0, httpHeader.size());
		httpHeader.push("HTTP/1.1 403 FORBIDDEN");
		assertEquals(1, httpHeader.size());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.pop());
		
		//Resets httpHeader
		httpHeader = new LinkedStack<String>(10);
		//Adding past capacity to see if array "grows"
		httpHeader.push("HTTP/1.1 162");
		httpHeader.push("HTTP/1.1 634");
		httpHeader.push("HTTP/1.1 244");
		httpHeader.push("HTTP/1.1 345");
		httpHeader.push("HTTP/1.1 609");
		httpHeader.push("HTTP/1.1 686");
		httpHeader.push("HTTP/1.1 354");
		httpHeader.push("HTTP/1.1 967");
		httpHeader.push("HTTP/1.1 346");
		httpHeader.push("HTTP/1.1 567");
		
		try {
			httpHeader.push("HTTP/1.1 456");
			fail("Object was pushed out of capacity");
		} catch (IllegalArgumentException e) {
			assertEquals(10, httpHeader.size());
		}
	}
	
	/**
	 * Tests the pop method of LinkedStack 
	 */
	@Test
	public void testPop () {
		
		Stack<String> httpHeader = new LinkedStack<String>(10);
		try {
			httpHeader.pop();
			fail("Attempted to pop element out of capacity");
		} catch (EmptyStackException e) {
			assertEquals(0, httpHeader.size());
		}
		
		httpHeader.push("HTTP/1.1 200 OK");
		httpHeader.push("HTTP/1.1 403 FORBIDDEN");
		httpHeader.push("HTTP/1.1 404 NOT FOUND");
		httpHeader.push("HTTP/1.1 500 INTERNAL SERVER ERROR");
		assertEquals(4, httpHeader.size());
		assertEquals("HTTP/1.1 500 INTERNAL SERVER ERROR", httpHeader.pop());
		assertEquals("HTTP/1.1 404 NOT FOUND", httpHeader.pop());
		assertEquals("HTTP/1.1 403 FORBIDDEN", httpHeader.pop());
		httpHeader.push("HTTP/1.1 200 OK!"); // adding back a different one after popping
		assertEquals("HTTP/1.1 200 OK!", httpHeader.pop());
		assertEquals("HTTP/1.1 200 OK", httpHeader.pop());
		
		try {
			httpHeader.pop();
			fail("Attempted to pop element out of capacity");
		} catch (EmptyStackException e) {
			assertEquals(0, httpHeader.size());
		}
		
	}
	
	/**
	 * Tests methods isEmpty and size in class LinkedStack
	 */
	@Test
	public void testSizeAndIsEmpty () {
		Stack<String> httpHeader = new LinkedStack<String>(10);
		assertTrue(httpHeader.isEmpty());
		assertTrue(httpHeader.isEmpty()); // twice doesn't change response
		assertEquals(0, httpHeader.size());
		httpHeader.push("HTTP/1.1 200 OK");
		assertFalse(httpHeader.isEmpty());
		assertEquals(1, httpHeader.size());
		httpHeader.push("HTTP/1.1 403 FORBIDDEN");
		assertEquals(2, httpHeader.size());
		assertEquals(2, httpHeader.size()); // twice doesn't change response
		httpHeader.push("HTTP/1.1 404 NOT FOUND");
		assertEquals(3, httpHeader.size());
		httpHeader.push("HTTP/1.1 500 INTERNAL SERVER ERROR");
		assertEquals(4, httpHeader.size());
		assertFalse(httpHeader.isEmpty());
		httpHeader.pop();
		assertEquals(3, httpHeader.size());
		assertFalse(httpHeader.isEmpty());
		httpHeader.pop();
		httpHeader.pop();
		assertEquals(1, httpHeader.size());
	}
	
	/**
	 * Tests method setCapacity of class LinkedStack
	 */
	@Test
	public void testSetCapacity () {
		
		Stack<String> httpHeader = new LinkedStack<String>(10);
		httpHeader.push("HTTP/1.1 162");
		httpHeader.push("HTTP/1.1 634");
		httpHeader.push("HTTP/1.1 244");
		httpHeader.push("HTTP/1.1 345");
		httpHeader.push("HTTP/1.1 609");
		httpHeader.push("HTTP/1.1 686");
		httpHeader.push("HTTP/1.1 354");
		httpHeader.push("HTTP/1.1 967");
		httpHeader.push("HTTP/1.1 346");
		httpHeader.push("HTTP/1.1 567");
		
		try {
			httpHeader.push("HTTP/1.1 701");
			fail("Attempted to push element out of capacity");
		} catch (IllegalArgumentException e) {
			assertEquals(10, httpHeader.size());
		}
		
		httpHeader.setCapacity(12);
		httpHeader.push("HTTP/1.1 702");
		httpHeader.push("HTTP/1.1 703");
		try {
			httpHeader.push("HTTP/1.1 704");
			fail("Attempted to push element out of capacity");
		} catch (IllegalArgumentException e) {
			assertEquals(12, httpHeader.size());
		}
		
		httpHeader.setCapacity(13); // increase to test adding
		try {
			httpHeader.setCapacity(5);
			fail("No error throw for capacity set under size");
		} catch (IllegalArgumentException e) {
			assertEquals("Capacity cannot be less than the current size", e.getMessage());
			httpHeader.push("HTTP/1.1 704"); // shouldn't throw
		}

		httpHeader.setCapacity(14); // increase to test adding
		try {
			httpHeader.setCapacity(0);
			fail("No error throw for capacity set under 1");
		} catch (IllegalArgumentException e) {
			assertEquals("Capacity cannot be less than 1", e.getMessage());
			httpHeader.push("HTTP/1.1 705"); // shouldn't throw
		}
		
	}
	
}
