package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the LinkedQueue class.
 * @author Yuquan 
 */
public class LinkedQueueTest {

	/**
	 * Test the construction of an LinkedQueue
	 */
	@Test
	public void testLinkedQueueClass () {
		LinkedQueue<Integer> linkedQueue = new LinkedQueue<Integer>(5);
		assertEquals(0, linkedQueue.size());
		assertTrue(linkedQueue.isEmpty());
		try { 
			linkedQueue.dequeue();
			fail("Empty link dequeued");
		} catch (NoSuchElementException e) {
			assertEquals(0, linkedQueue.size());
		}
		
		linkedQueue.enqueue(1);
		assertEquals(1, linkedQueue.size());
		assertFalse(linkedQueue.isEmpty());
		
		linkedQueue.enqueue(2);
		linkedQueue.enqueue(3);
		linkedQueue.enqueue(4);
		
		assertEquals(1, linkedQueue.dequeue().intValue());
		assertEquals(3, linkedQueue.size());

		linkedQueue.enqueue(11);
		linkedQueue.enqueue(12);
		try {
			linkedQueue.enqueue(18);
			fail("Element added out of bounds");
		} catch (IllegalArgumentException e) {
			assertEquals(5, linkedQueue.size());
		}
		linkedQueue.setCapacity(6);
		assertEquals(5, linkedQueue.size());
		linkedQueue.enqueue(18); // now its added
		assertEquals(6, linkedQueue.size());
	}
	
	

	
}
