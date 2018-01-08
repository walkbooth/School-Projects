package edu.ncsu.csc216.checkout_simulator.queues;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests the Store class
 * @author Walker Booth
 *
 */
public class StoreTest {

	/**
	 * Tests the store constructor
	 */
	@Test
	public void testStore() {
		Log log = new Log();
		CheckoutRegister[] register = new CheckoutRegister[6];
		for (int i = 0; i < 6; i++) {
			register[i] = new CheckoutRegister(log);
		}
		Store store = new Store(100, register);
		assertEquals(100, store.size());
	}

	/**
	 * Tests the hasNext method
	 */
	@Test
	public void testHasNext() {
		Log log = new Log();
		CheckoutRegister[] register = new CheckoutRegister[6];
		for (int i = 0; i < 6; i++) {
			register[i] = new CheckoutRegister(log);
		}
		Store store = new Store(100, register);
		assertTrue(store.hasNext());
		
		for (int i = 0; i < 100; i++) {
			store.processNext();
		}
		assertFalse(store.hasNext());
	}

	/**
	 * Tests the processNext method
	 */
	@Test
	public void testProcessNext() { 
		Log log = new Log();
		CheckoutRegister[] register = new CheckoutRegister[6];
		for (int i = 0; i < 6; i++) {
			register[i] = new CheckoutRegister(log);
		}
		Store store = new Store(100, register);
		store.processNext();
		store.processNext();
		assertEquals(98, store.size());
	}

	/**
	 * Tests the departTimeNext method
	 */
	@Test
	public void testDepartTimeNext() {
		Log log = new Log();
		CheckoutRegister[] register = new CheckoutRegister[6];
		for (int i = 0; i < 6; i++) {
			register[i] = new CheckoutRegister(log);
		}
		Store store = new Store(50, register);
		
		for (int i = 0; i < 50; i++) {
			store.processNext();
		}
		assertEquals(Integer.MAX_VALUE, store.departTimeNext());
	}

	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		Log log = new Log();
		CheckoutRegister[] register = new CheckoutRegister[6];
		for (int i = 0; i < 6; i++) {
			register[i] = new CheckoutRegister(log);
		}
		Store store = new Store(100, register);
		store.processNext();
		store.processNext();
		assertEquals(98, store.size());
		
	}

}
