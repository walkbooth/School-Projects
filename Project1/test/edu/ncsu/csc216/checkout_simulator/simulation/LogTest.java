package edu.ncsu.csc216.checkout_simulator.simulation;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.ExpressCart;

/**
 * A suite of white box test cases for the Log class
 * @author Walker Booth
 *
 */
public class LogTest {

	/**
	 * Tests log constructor
	 */
	@Test
	public void testLog() {
		Log log = new Log();
		assertEquals(0, log.getNumCompleted());
	}

	/**
	 * Tests the getNumCompleted method
	 */
	@Test
	public void testGetNumCompleted() {
		Log log = new Log ();
		Cart cart = new ExpressCart(0, 0);
		log.logCart(cart);
		assertEquals(1, log.getNumCompleted());
	}

	/**
	 * Tests the logCart method
	 */
	@Test
	public void testLogCart() {
		Log log = new Log ();
		Cart cart = new ExpressCart(0, 50);
		log.logCart(cart);	
		assertEquals(50, (int)(log.averageProcessTime()));
		assertEquals(cart.getWaitTime(), (int)(log.averageWaitTime()));
	}

	/**
	 * Tests the averageWaitTime method
	 */
	@Test
	public void testAverageWaitTime() {
		Log log = new Log ();
		Cart cart = new ExpressCart(0, 50);
		log.logCart(cart);	
		assertEquals(cart.getWaitTime(), (int)(log.averageWaitTime()));	}

	/**
	 * Tests the averageProcessTime method
	 */
	@Test
	public void testAverageProcessTime() {
		Log log = new Log ();
		Cart cart = new ExpressCart(0, 50);
		log.logCart(cart);	
		assertEquals(50, (int)(log.averageProcessTime()));
	}
}
