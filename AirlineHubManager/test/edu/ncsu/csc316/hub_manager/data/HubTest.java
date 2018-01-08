package edu.ncsu.csc316.hub_manager.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Hub class.	
 * @author Walker Booth (wgbooth)
 *
 */
public class HubTest {

	/**
	 * Tests the constructor of Hub.
	 */
	@Test
	public void testHub() {
		Hub h = new Hub(3, "RDU");
		assertEquals("RDU", h.getAirportCode());
		assertEquals(3, h.getConnections());
	}
	
	/**
	 * Tests the compareTo method of Hub. 
	 */
	@Test 
	public void testCompareTo() {
		Hub h1 = new Hub(4, "RDU");
		Hub h2 = new Hub(3, "RDU");
		Hub h3 = new Hub(5, "RDU");
		Hub h4 = new Hub(4, "AAA");
		Hub h5 = new Hub(4, "ZZZ");
		
		assertEquals(0, h1.compareTo(h1));
		assertEquals(-1, h1.compareTo(h2));	
		assertEquals(1, h1.compareTo(h3));
		assertTrue(h1.compareTo(h4) > 0);
		assertTrue(h1.compareTo(h5) < 0);		
	}

}
