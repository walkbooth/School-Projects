package edu.ncsu.csc316.airline_mileage.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.airline_mileage.data.Airline;
import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * Tests the AirlineReader io class.
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineReaderTest {

	/** File to be used for tests */
	String validFile = "input/airline_data.txt";
	
	/** Invalid file to be used for the tests */
	String invalidFile = "garbage.txt";
	
	/**
	 * Tests the readAirlineRecords method. 
	 * Note: Uses airline_data.txt text file, whose data was provided in use cases of Project 1 Part 2 guidelines. All credit 
	 * for this text file goes to the creators of these use cases.
	 */
	@Test
	public void testReadAirlineRecords() {
		try {
			@SuppressWarnings("unused")
			AirlineReader c = new AirlineReader ();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("This class should not be instantiated.", e.getMessage());
		}
		
		ArrayList<Airline> airlines = null;
		
		try {
			airlines = AirlineReader.readAirlineRecords(invalidFile);
			fail();
		} catch (FileNotFoundException e) {
			assertNull(airlines);
		}
		
		try {
			airlines = AirlineReader.readAirlineRecords(validFile);
			assertEquals("UA", airlines.get(0).getIataCode());
			assertEquals("United Airlines", airlines.get(0).getAirline());
			assertEquals("DL", airlines.get(1).getIataCode());
			assertEquals("Delta Air Lines", airlines.get(1).getAirline());
			assertEquals("B6", airlines.get(2).getIataCode());
			assertEquals("JetBlue Airways", airlines.get(2).getAirline());

		} catch (FileNotFoundException e) {
			fail();
		}
	}

}
