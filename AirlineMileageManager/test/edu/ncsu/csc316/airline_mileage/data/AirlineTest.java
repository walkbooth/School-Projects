package edu.ncsu.csc316.airline_mileage.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Airline class 
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineTest {

	/**
	 * Tests the constructor for Airline
	 */
	@Test
	public void testAirline() {

		Airline airline = new Airline("United Airlines", "UA");
		assertEquals("United Airlines", airline.getAirline());
		assertEquals("UA", airline.getIataCode());
	
	}

}
