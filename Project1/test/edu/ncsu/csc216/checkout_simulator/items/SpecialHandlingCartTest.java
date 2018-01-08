/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.items;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests the SpecialHandlingCart class
 * @author Walker Booth
 */
public class SpecialHandlingCartTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.SpecialHandlingCart#getInLine(edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister[])}.
	 */
	@Test
	public void testGetInLine() {
		SpecialHandlingCart c = new SpecialHandlingCart(0, 0);
		CheckoutRegister[] register = new CheckoutRegister[4];
		Log log = null;
		for (int i = 0; i < 4; i++) {
			register[i] = new CheckoutRegister(log);
		}
		c.getInLine(register);
		assertTrue(c.isWaitingInRegisterLine());
		assertEquals(3, c.getRegisterIndex());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.SpecialHandlingCart#getColor()}.
	 */
	@Test
	public void testGetColor() {
		SpecialHandlingCart c = new SpecialHandlingCart(0, 0);
		assertEquals(Color.red, c.getColor());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.SpecialHandlingCart#SpecialHandlingCart(int, int)}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testSpecialHandlingCart() {
		try {
			SpecialHandlingCart c = new SpecialHandlingCart(-1, 0);
			fail("Arrival time cannot be less than 0.");
		} catch (IllegalArgumentException e) {
			assertEquals("Arrival time cannot be less than 0.", e.getMessage());
		}
		
		try {
			SpecialHandlingCart d = new SpecialHandlingCart(0, -1);
			fail("Process time cannot be less than 0.");
		} catch (IllegalArgumentException e) {
			assertEquals("Process time cannot be less than 0.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#Cart(int, int)}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testCart() {
		try {
			SpecialHandlingCart c = new SpecialHandlingCart(-1, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Arrival time cannot be less than 0.", e.getMessage());
		}
		
		try {
			SpecialHandlingCart d = new SpecialHandlingCart(0, -1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Process time cannot be less than 0.", e.getMessage());
		}	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		SpecialHandlingCart c = new SpecialHandlingCart(40, 0);
		assertEquals(40, c.getArrivalTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		SpecialHandlingCart c = new SpecialHandlingCart(0, 0);
		assertEquals(0, c.getWaitTime());
		c.setWaitTime(4);
		assertEquals(4, c.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		SpecialHandlingCart c = new SpecialHandlingCart(0, 0);
		c.setWaitTime(4);
		assertEquals(4, c.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		SpecialHandlingCart c = new SpecialHandlingCart(0, 40);
		assertEquals(40, c.getProcessTime());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getRegisterIndex()}.
	 */
	@Test
	public void testGetRegisterIndex() {
		SpecialHandlingCart c = new SpecialHandlingCart(0, 0);
		assertEquals(-1, c.getRegisterIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#isWaitingInRegisterLine()}.
	 */
	@Test
	public void testIsWaitingInRegisterLine() {
		ExpressCart c = new ExpressCart(0, 0);
		assertEquals(false, c.isWaitingInRegisterLine());
		c.setRegisterIndex(1);
		assertEquals(true, c.isWaitingInRegisterLine());
		c.removeFromWaitingLine();
		assertEquals(false, c.isWaitingInRegisterLine());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#removeFromWaitingLine()}.
	 */
	@Test
	public void testRemoveFromWaitingLine() {
		ExpressCart c = new ExpressCart(0, 0);
		c.setRegisterIndex(0);
		assertEquals(true, c.isWaitingInRegisterLine());
		c.removeFromWaitingLine();
		assertEquals(false, c.isWaitingInRegisterLine());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#setRegisterIndex(int)}.
	 */
	@Test
	public void testSetRegisterIndex() {
		ExpressCart c = new ExpressCart(0, 0);
		c.setRegisterIndex(0);
		assertEquals(0, c.getRegisterIndex());
		assertEquals(true, c.isWaitingInRegisterLine());
	}

}
