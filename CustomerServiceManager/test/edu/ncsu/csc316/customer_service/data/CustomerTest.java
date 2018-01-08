package edu.ncsu.csc316.customer_service.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Customer class. 
 * @author Walker Booth (wgbooth)
 *
 */
public class CustomerTest {

	/**
	 * Tests the Customer constructor
	 */
	@Test
	public void testCustomer() {
		Customer c = new Customer("Walker", "Booth");
		assertEquals("Walker", c.getFirstName());
		assertEquals("Booth", c.getLastName());
		assertEquals("Walker Booth", c.toString());
	}
	
	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo () {
		Customer c1 = new Customer("Walker", "Booth");
		Customer c2 = new Customer("Skip", "Booth");
		Customer c3 = new Customer("Zachary", "Booth");
		Customer c4 = new Customer("Walker", "Anderson");
		Customer c5 = new Customer("Walker", "Grayson");
		
		assertEquals(0, c1.compareTo(c1));
		assertEquals(1, c1.compareTo(c4));
		assertEquals(1, c1.compareTo(c2));
		assertEquals(-1, c1.compareTo(c3));
		assertEquals(-1, c1.compareTo(c5));
	}

}
