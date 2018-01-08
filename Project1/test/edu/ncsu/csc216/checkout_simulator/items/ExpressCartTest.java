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
 * Tests the ExpressCart class
 * @author Walker Booth
 *
 */
public class ExpressCartTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.ExpressCart#getInLine(edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister[])}.
	 */
	@Test
	public void testGetInLine() {
		ExpressCart c = new ExpressCart(0, 0);
		CheckoutRegister[] register = new CheckoutRegister[2];
		Log log = null;
		for (int i = 0; i < 2; i++) {
			register[i] = new CheckoutRegister(log);
		}
		c.getInLine(register);
		assertTrue(c.isWaitingInRegisterLine());
		assertEquals(0, c.getRegisterIndex());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.ExpressCart#getColor()}.
	 */
	@Test
	public void testGetColor() {
		ExpressCart c = new ExpressCart(0, 0);
		assertEquals(Color.green, c.getColor());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.ExpressCart#ExpressCart(int, int)}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testExpressCart() {
		try {
			ExpressCart c = new ExpressCart(-1, 0);
			fail("Arrival time cannot be less than 0.");
		} catch (IllegalArgumentException e) {
			assertEquals("Arrival time cannot be less than 0.", e.getMessage());
		}
		
		try {
			ExpressCart d = new ExpressCart(0, -1);
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
			ExpressCart c = new ExpressCart(-1, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Arrival time cannot be less than 0.", e.getMessage());
		}
		
		try {
			ExpressCart d = new ExpressCart(0, -1);
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
		ExpressCart c = new ExpressCart(40, 0);
		assertEquals(40, c.getArrivalTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		ExpressCart c = new ExpressCart(0, 0);
		assertEquals(0, c.getWaitTime());
		c.setWaitTime(4);
		assertEquals(4, c.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		ExpressCart c = new ExpressCart(0, 0);
		c.setWaitTime(4);
		assertEquals(4, c.getWaitTime());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		ExpressCart c = new ExpressCart(0, 40);
		assertEquals(40, c.getProcessTime());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.items.Cart#getRegisterIndex()}.
	 */
	@Test
	public void testGetRegisterIndex() {
		ExpressCart c = new ExpressCart(0, 0);
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
