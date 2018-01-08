/**
 * 
 */
package edu.ncsu.csc316.airline_mileage.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for AirlineMileageManager (From Project 1)
 * @author Walker Booth (wgbooth)
 */
public class AirlineMileageManagerTest {

	String airlineFile = "input/airline_data.txt";
	String cardholderFile = "input/cardholder_data.txt";
	String flightFile = "input/flight_information.txt";
	
	/**
	 * Test method for {@link edu.ncsu.csc316.airline_mileage.manager.AirlineMileageManager#getMiles(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetMileageReport() {
		AirlineMileageManager m = new AirlineMileageManager(airlineFile, cardholderFile, flightFile);
		String s = "Erick Mcfarland earned\n" +   
				   "    3591 miles with United Airlines (UA)\n" +
				   "    516 miles with Delta Air Lines (DL)\n" +
				   "    319 miles with JetBlue Airways (B6)\n\n" +
				   "Kassandra Stiltner earned\n" +
				   "    5985 miles with United Airlines (UA)";
		assertEquals(s, m.getMileageReport());

	}	
	
	/**
	 * Test method for {@link edu.ncsu.csc316.airline_mileage.manager.AirlineMileageManager#getMileageReport()}.
	 */
	@Test
	public void testGetMiles() {
		AirlineMileageManager m = new AirlineMileageManager(airlineFile, cardholderFile, flightFile);
		String s1 = "Erick Mcfarland earned\n" +   
					"    3591 miles with United Airlines (UA)\n" +
					"    516 miles with Delta Air Lines (DL)\n" +
					"    319 miles with JetBlue Airways (B6)";
		String s2 = "Kassandra Stiltner earned\n" +
					"    5985 miles with United Airlines (UA)";
		assertEquals(s1, m.getMiles("Erick", "Mcfarland"));
		assertEquals(s2, m.getMiles("Kassandra", "Stiltner"));

	}
}
