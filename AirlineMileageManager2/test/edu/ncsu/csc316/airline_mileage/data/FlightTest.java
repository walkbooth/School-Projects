package edu.ncsu.csc316.airline_mileage.data;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests the Flight class (From Project 1)
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
		assertTrue(flight1.equals(flight1));
		assertFalse(flight1.equals(flight7));
	}
	
	/**
	 * Tests the hashCode mehtod for the customer class
	 */
	@Test
	public void testHashCode() {
		Flight f1 = new Flight("", "", 0, "", "");
		assertEquals(0, f1.hashCode());
		
		Flight f2 = new Flight("A", "A", 0, "A", "A");
		assertEquals(37060, f2.hashCode());
		
		Flight f3 = new Flight("AA", "AA", 0, "AA", "AA");
		assertEquals(-815657306, f3.hashCode());
	}

}
