/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.queues;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.ExpressCart;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests the CheckoutRegister class
 * @author Walker Booth
 *
 */
public class CheckoutRegisterTest { 

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister#CheckoutRegister(edu.ncsu.csc216.checkout_simulator.simulation.Log)}.
	 */
	@Test
	public void testCheckoutRegister() {
		Log log = new Log();
		CheckoutRegister c = new CheckoutRegister(log);
		assertFalse(c.hasNext());
		assertEquals(0, c.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		Log log = new Log();
		CheckoutRegister c = new CheckoutRegister(log);
		Cart cart = new ExpressCart(0, 0);
		assertFalse(c.hasNext());
		c.addCartToLine(cart);
		assertTrue(c.hasNext());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister#processNext()}.
	 */
	@Test
	public void testProcessNext() {
		Log log = new Log();
		CheckoutRegister c = new CheckoutRegister(log);
		Cart cart = new ExpressCart(0, 0);
		c.addCartToLine(cart);
		assertEquals(cart, c.processNext());
		assertFalse(c.hasNext());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister#departTimeNext()}.
	 */
	@Test
	public void testDepartTimeNext() {
		Log log = new Log();
		CheckoutRegister c = new CheckoutRegister(log);
		Cart cart1 = new ExpressCart(0, 60);
		assertEquals(Integer.MAX_VALUE, c.departTimeNext());
		c.addCartToLine(cart1);
		assertEquals(60, c.departTimeNext());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister#size()}.
	 */
	@Test
	public void testSize() {
		Log log = new Log();
		CheckoutRegister c = new CheckoutRegister(log);
		Cart cart = new ExpressCart(0, 0);
		assertEquals(0, c.size());
		c.addCartToLine(cart);
		assertEquals(1, c.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister#addCartToLine(edu.ncsu.csc216.checkout_simulator.items.Cart)}.
	 */
	@Test
	public void testAddCartToLine() {
		Log log = new Log();
		CheckoutRegister c = new CheckoutRegister(log);
		Cart cart1 = new ExpressCart(0, 56);
		Cart cart2 = new ExpressCart(56, 110);
		assertEquals(0, c.size());
		c.addCartToLine(cart1);
		c.addCartToLine(cart2);
		assertEquals(2, c.size());
		
	}

}
