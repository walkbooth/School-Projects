package edu.ncsu.csc316.hub_manager.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Airport class. 
 * @author Walker Booth (wgbooth)
 *
 */
public class AirportTest {

	/**
	 * Tests the airport constructor
	 */
	@Test
	public void test() {
		Airport a = new Airport(0, "RDU", 35.877601623535156, -78.7874984742122);
		Airport b = new Airport(1, "XXR", 0, 0);
		assertEquals(0, a.getId());
		assertEquals("RDU", a.getAirportCode());
		assertEquals(35.877601623535156, a.getLatitude(), .000000001);
		assertEquals(-78.7874984742122, a.getLongitude(), .000000001);
		
		assertTrue(a.compareTo(b) < 0);
	}

}
