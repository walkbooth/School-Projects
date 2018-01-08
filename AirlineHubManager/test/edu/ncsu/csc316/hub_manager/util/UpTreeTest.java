package edu.ncsu.csc316.hub_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the UpTree class
 * @author Walker Booth (wgbooth)
 *
 */
public class UpTreeTest {

	/**
	 * Tests the constructor for UpTree
	 */
	@SuppressWarnings("unused")
	@Test
	public void testUpTree() {
		UpTree.setSpace(8);
		try {
			UpTree u0 = new UpTree(0);
			u0.getClass();
			assertEquals(0, u0.getId());
			UpTree u7 = new UpTree(1);
			u7.getClass();
			UpTree u12 = new UpTree(2);
			u12.getClass();
			UpTree u14 = new UpTree(3);
			u14.getClass();
			UpTree u11 = new UpTree(4);
			u11.getClass();
			UpTree u17 = new UpTree(5);
			u17.getClass();
			UpTree u8 = new UpTree(6);
			u8.getClass();
			UpTree u5 = new UpTree(7);
			u5.getClass();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			UpTree u88 = new UpTree(10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Key is out of bounds", e.getMessage());
		}
		
		try {
			UpTree u88 = new UpTree(-9);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Key is out of bounds", e.getMessage());
		}
		
	}

	/**
	 * Tests the union and find methods for UpTree
	 */
	@Test
	public void testUnion() {
		UpTree.setSpace(8);
		int u0key = 0;
		int u7key = 1;
		int u12key = 2;
		int u14key = 3;
		int u11key = 4;
		int u17key = 5;
		int u8key = 6;
		int u5key = 7;

		UpTree u0 = new UpTree(0);
		u0.getClass();
		UpTree u7 = new UpTree(1);
		u7.getClass();
		UpTree u12 = new UpTree(2);
		u12.getClass();
		UpTree u14 = new UpTree(3);
		u14.getClass();
		UpTree u11 = new UpTree(4);
		u11.getClass();
		UpTree u17 = new UpTree(5);
		u17.getClass();
		UpTree u8 = new UpTree(6);
		u8.getClass();
		UpTree u5 = new UpTree(7);
		u5.getClass();
		
		assertEquals(u0key, UpTree.findRoot(u0key));
		assertEquals(u7key, UpTree.findRoot(u7key));
		UpTree.union(u0key, u7key);
		assertEquals(u7key, UpTree.findRoot(u0key));
		assertEquals(u7key, UpTree.findRoot(u7key));
		
		UpTree.union(u7key, u12key);
		assertEquals(u7key, UpTree.findRoot(u7key));
		assertEquals(u7key, UpTree.findRoot(u12key));
		
		UpTree.union(u11key, u14key);
		assertEquals(u14key, UpTree.findRoot(u11key));
		assertEquals(u14key, UpTree.findRoot(u14key));
		
		UpTree.union(u7key, u14key);
		assertEquals(u7key, UpTree.findRoot(u7key));
		assertEquals(u7key, UpTree.findRoot(u14key));
		
		UpTree.union(u17key, u8key);
		assertEquals(u8key, UpTree.findRoot(u8key));
		assertEquals(u8key, UpTree.findRoot(u17key));
		
		UpTree.union(u17key, u5key);
		assertEquals(u8key, UpTree.findRoot(u5key));
		assertEquals(u8key, UpTree.findRoot(u17key));
		
		UpTree.union(u7key, u17key);
		assertEquals(u7key, UpTree.findRoot(u7key));
		assertEquals(u7key, UpTree.findRoot(u17key));

	}

}
