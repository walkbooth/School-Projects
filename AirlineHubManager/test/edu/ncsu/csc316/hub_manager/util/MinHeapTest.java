package edu.ncsu.csc316.hub_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the MinHeap class.
 * @author Walker Booth (wgbooth)
 *
 */
public class MinHeapTest {

	/**
	 * Tests the constructor of MinHeap
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testMinHeap() {
		MinHeap h = new MinHeap ();
		assertEquals(0, h.size());
		
		try {
			h.removeMin();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Nothing to remove from heap, heap is empty.", e.getMessage());
			assertEquals(0, h.size());
		}
		
	}

	/**
	 * Tests methods that manipulate the heap - insert and removeMin. 
	 */
	@Test
	public void testManipulateMinHeap() {
		MinHeap<Integer> h = new MinHeap<Integer> ();
		
		h.insert(12);
		assertEquals(12, h.removeMin().intValue());
		
		h.insert(12);
		h.insert(14);
		h.insert(0);
		assertEquals(0, h.removeMin().intValue());
		assertEquals(12, h.removeMin().intValue());
		assertEquals(14, h.removeMin().intValue());
		
		h.insert(12);
		h.insert(14);
		h.insert(0);
		h.insert(7);
		h.insert(8);
		h.insert(100);
		h.insert(80);
		h.insert(20);
		h.insert(10);
		assertEquals(0, h.removeMin().intValue());
		assertEquals(7, h.removeMin().intValue());
		assertEquals(8, h.removeMin().intValue());
		assertEquals(10, h.removeMin().intValue());
		assertEquals(12, h.removeMin().intValue());
		assertEquals(14, h.removeMin().intValue());
		assertEquals(20, h.removeMin().intValue());
		assertEquals(80, h.removeMin().intValue());
		assertEquals(100, h.removeMin().intValue());
		
	}

}
