/**
 * 
 */
package edu.ncsu.csc316.airline_mileage.io;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.airline_mileage.data.Flight;
import edu.ncsu.csc316.airline_mileage.util.ArrayList;

/**
 * Tests the FlightReader class. (From Project 1)
 * @author Walker Booth (wgbooth)
 */
public class FlightReaderTest {

	/** File to be used for tests */
	String validFile = "input/flight_information.txt";
	
	/** Invalid file to be used for the tests */
	String invalidFile = "garbage.txt";
	
	/**
	 * Tests the readFlightRecords method. 
	 * Note: Uses flight_information.txt text file, whose data was provided in use cases of Project 1 Part 2 guidelines. All credit 
	 * for this text file goes to the creators of these use cases.
	 */
	@Test
	public void testReadFlightRecords() {
		try {
			@SuppressWarnings("unused")
			FlightReader c = new FlightReader ();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("This class should not be instantiated.", e.getMessage());
		}
		
		ArrayList<Flight> flights = null;
		
		try {
			flights = FlightReader.readFlightRecords(invalidFile);
			fail();
		} catch (FileNotFoundException e) {
			try {
				flights = FlightReader.readFlightRecords(validFile);
				assertEquals("UA", flights.get(0).getIataCode());
				assertEquals("346", flights.get(0).getFlightNumber());
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

			} catch (FileNotFoundException ex) {
				fail();
			}
		}
	}

}
