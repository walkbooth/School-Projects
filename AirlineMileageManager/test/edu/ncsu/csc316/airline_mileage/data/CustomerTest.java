package edu.ncsu.csc316.airline_mileage.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Customer class
 * @author Walker Booth (wgbooth)
 *
 */
public class CustomerTest {

	/**
	 * Tests the constructor for the customer class 
	 */
	@Test
	public void testCustomer() {

		Customer customer = new Customer("first", "last");
		assertEquals(0, customer.getFlightsForCustomer().size());
		assertEquals("first", customer.getFirstName());
		assertEquals("last", customer.getLastName());
	}

	/**
	 * Tests the addFlight method for the customer class
	 */
	@Test
	public void testAddFlight() {
		Customer customer = new Customer("first", "last");
		Flight flight = new Flight("DL", "395", 335, "RDU", "JFK");
		assertEquals(0, customer.getFlightsForCustomer().size());
		customer.addFlight(flight);
		assertEquals(1, customer.getFlightsForCustomer().size());
		assertEquals("DL", customer.getFlightsForCustomer().get(0).getIataCode());
		assertEquals("395", customer.getFlightsForCustomer().get(0).getFlightNumber());
		assertEquals(335, customer.getFlightsForCustomer().get(0).getDistance());
		assertEquals("RDU", customer.getFlightsForCustomer().get(0).getOrigin());
		assertEquals("JFK", customer.getFlightsForCustomer().get(0).getDestination());

	}
	
	/**
	 * Tests the compareTo method for the custoer class
	 */
	@Test
	public void testCompareTo () {
		Customer customer1 = new Customer("Walker", "Booth");
		Customer customer2 = new Customer("Wallace", "Booth");
		Customer customer3 = new Customer("Skip", "Booth");
		Customer customer4 = new Customer("Walker", "Austin");
		Customer customer5 = new Customer("Walker", "Bryan");
		
		assertEquals(-1, customer1.compareTo(customer2));
		assertEquals(1, customer1.compareTo(customer3));
		assertEquals(0, customer1.compareTo(customer1));
		assertEquals(1, customer1.compareTo(customer4));
		assertEquals(-1, customer1.compareTo(customer5));
	}

}
