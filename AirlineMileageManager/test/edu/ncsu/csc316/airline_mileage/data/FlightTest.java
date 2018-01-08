package edu.ncsu.csc316.airline_mileage.data;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the Flight class
 * @author Walker Booth (wgbooth)
 *
 */
public class FlightTest {

	
	/**
	 * Tests the Flight constructor
	 */
	@Test
	public void testFlight() {
		Flight flight = new Flight("DL", "395", 335, "RDU", "JFK");
		assertEquals("DL", flight.getIataCode());
		assertEquals("395", flight.getFlightNumber());
		assertEquals(335, flight.getDistance());
		assertEquals("RDU", flight.getOrigin());
		assertEquals("JFK", flight.getDestination());	}

	/**
	 * Tests the setDistance method of Flight 
	 */
	@Test
	public void testSetDistance() {
		Flight flight = new Flight("DL", "395", 335, "RDU", "JFK");
		assertEquals(335, flight.getDistance());
		flight.setDistance(0);
		assertEquals(0, flight.getDistance());
		

	}
	
	/**
	 * Tests the compareTo method of Flight
	 */
	@Test
	public void testCompareTo () {
		Flight flight1 = new Flight("DL", "395", 335, "RDU", "JFK");
		Flight flight2 = new Flight("DL", "000", 335, "RDU", "JFK");
		Flight flight3 = new Flight("DL", "400", 335, "RDU", "JFK");
		Flight flight4 = new Flight("DL", "395", 335, "AAA", "JFK");
		Flight flight5 = new Flight("DL", "395", 335, "ZZZ", "JFK");
		Flight flight6 = new Flight("DL", "395", 335, "RDU", "AAA");
		Flight flight7 = new Flight("DL", "395", 335, "RDU", "ZZZ");

		assertEquals(1, flight1.compareTo(flight2));
		assertEquals(-1, flight1.compareTo(flight3));
		assertEquals(1, flight1.compareTo(flight4));
		assertEquals(-1, flight1.compareTo(flight5));
		assertEquals(1, flight1.compareTo(flight6));
		assertEquals(-1, flight1.compareTo(flight7));
		assertEquals(0, flight1.compareTo(flight1));
	}

}
