package edu.ncsu.csc316.airline_mileage.io;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.airline_mileage.data.Customer;
import edu.ncsu.csc316.airline_mileage.data.Flight;
import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * Tests the CustomerReader class
 * @author Walker Booth (wgbooth)
 *
 */
public class CustomerReaderTest {

	/** File to be used for tests */
	String validFile = "input/cardholder_data.txt";
	String validFlightsFile = "input/flight_information.txt";
	
	/** Invalid file to be used for the tests */
	String invalidFile = "garbage.txt";	
	
	/**
	 * Tests the readCustomerRecords method. 
	 * Note: Uses cardholder_data.txt text file, whose data was provided in use cases of Project 1 Part 2 guidelines. All credit 
	 * for this text file goes to the creators of these use cases.
	 */
	@Test
	public void testReadCustomerRecords() {
		try {
			@SuppressWarnings("unused")
			CustomerReader c = new CustomerReader ();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("This class should not be instantiated.", e.getMessage());
		}
		
		ArrayList<Customer> customers = null;
		ArrayList<Flight> flights = null;
		ArrayList<Flight> flightsOnFile = null;
		
		try {
			customers = CustomerReader.readCustomerRecords(invalidFile, flightsOnFile);
			fail();
		} catch (FileNotFoundException e) {
			assertNull(customers);
		}
		
		try {
			flightsOnFile = FlightReader.readFlightRecords(validFlightsFile);
			flightsOnFile.sort();
			customers = CustomerReader.readCustomerRecords(validFile, flightsOnFile);
			assertEquals("Erick", customers.get(0).getFirstName());
			assertEquals("Mcfarland", customers.get(0).getLastName());
			flights = customers.get(0).getFlightsForCustomer();
			
			assertEquals("UA", flights.get(0).getIataCode());
			assertEquals("346", flights.get(0).getFlightNumber());					//checking flights taken by cardholder
			assertEquals(1197, flights.get(0).getDistance());
			assertEquals("ORD", flights.get(0).getOrigin());
			assertEquals("MIA", flights.get(0).getDestination());
			assertEquals("DL", flights.get(1).getIataCode());
			assertEquals("1233", flights.get(1).getFlightNumber());					
			assertEquals(516, flights.get(1).getDistance());
			assertEquals("ATL", flights.get(1).getOrigin());
			assertEquals("ORF", flights.get(1).getDestination());
			assertEquals("B6", flights.get(2).getIataCode());
			assertEquals("1316", flights.get(2).getFlightNumber());					
			assertEquals(319, flights.get(2).getDistance());
			assertEquals("FLL", flights.get(2).getOrigin());
			assertEquals("JAX", flights.get(2).getDestination());
			assertEquals("UA", flights.get(3).getIataCode());
			assertEquals("346", flights.get(3).getFlightNumber());					
			assertEquals(1197, flights.get(3).getDistance());
			assertEquals("ORD", flights.get(3).getOrigin());
			assertEquals("MIA", flights.get(3).getDestination());
			assertEquals("UA", flights.get(4).getIataCode());
			assertEquals("346", flights.get(4).getFlightNumber());					
			assertEquals(1197, flights.get(4).getDistance());
			assertEquals("ORD", flights.get(4).getOrigin());
			assertEquals("MIA", flights.get(4).getDestination());			
		
			assertEquals("Kassandra", customers.get(1).getFirstName());
			assertEquals("Stiltner", customers.get(1).getLastName());
			flights = customers.get(1).getFlightsForCustomer();
			
			assertEquals("UA", flights.get(0).getIataCode());
			assertEquals("346", flights.get(0).getFlightNumber());					//checking flights taken by cardholder
			assertEquals(1197, flights.get(0).getDistance());
			assertEquals("ORD", flights.get(0).getOrigin());
			assertEquals("MIA", flights.get(0).getDestination());
			assertEquals("UA", flights.get(1).getIataCode());
			assertEquals("346", flights.get(1).getFlightNumber());					
			assertEquals(1197, flights.get(1).getDistance());
			assertEquals("ORD", flights.get(1).getOrigin());
			assertEquals("MIA", flights.get(1).getDestination());
			assertEquals("UA", flights.get(2).getIataCode());
			assertEquals("346", flights.get(2).getFlightNumber());					
			assertEquals(1197, flights.get(2).getDistance());
			assertEquals("ORD", flights.get(2).getOrigin());
			assertEquals("MIA", flights.get(2).getDestination());	
			assertEquals("UA", flights.get(3).getIataCode());
			assertEquals("346", flights.get(3).getFlightNumber());					
			assertEquals(1197, flights.get(3).getDistance());
			assertEquals("ORD", flights.get(3).getOrigin());
			assertEquals("MIA", flights.get(3).getDestination());	
			assertEquals("UA", flights.get(4).getIataCode());
			assertEquals("346", flights.get(4).getFlightNumber());					
			assertEquals(1197, flights.get(4).getDistance());
			assertEquals("ORD", flights.get(4).getOrigin());
			assertEquals("MIA", flights.get(4).getDestination());	
			
		} catch (FileNotFoundException e) {
			fail();
		}
	}

}
