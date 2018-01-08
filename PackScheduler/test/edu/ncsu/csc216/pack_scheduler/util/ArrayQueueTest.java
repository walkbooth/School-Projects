package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayQueue class.
 * @author Yuquan 
 */
public class ArrayQueueTest {

	/**
	 * Test the construction of an ArrayQueue
	 */
	@Test
	public void testArrayQueueClass () {
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(5);
		assertEquals(0, arrayQueue.size());
		assertTrue(arrayQueue.isEmpty());
		
		arrayQueue.enqueue(1);
		assertEquals(1, arrayQueue.size());
		assertFalse(arrayQueue.isEmpty());
		
		arrayQueue.enqueue(2);
		arrayQueue.enqueue(3);
		arrayQueue.enqueue(4);
		assertEquals(4, arrayQueue.size());
		
		int i = arrayQueue.dequeue();
		assertEquals(1, i);
		assertEquals(3, arrayQueue.size());
		
		int j = arrayQueue.dequeue();
		assertEquals(2, j);
		assertEquals(2, arrayQueue.size());
		
		try
		{
			arrayQueue.setCapacity(-1);
			arrayQueue.setCapacity(2);
		} catch(IllegalArgumentException e)
		{
			assertEquals(2, arrayQueue.size());
		}
		
		arrayQueue.setCapacity(10);
		assertEquals(2, arrayQueue.size());
	}
	

	
}
