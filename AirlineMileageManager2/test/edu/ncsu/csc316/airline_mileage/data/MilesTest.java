/**
 * 
 */
package edu.ncsu.csc316.airline_mileage.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Miles (From Project 1)
 * @author Walker Booth (wgbooth) 
 */
public class MilesTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.airline_mileage.data.Miles#Miles(edu.ncsu.csc316.airline_mileage.data.Airline)}.
	 */
	@Test
	public void testMiles() {
		Airline airline = new Airline ("Delta Airlines", "DL");
		Miles miles = new Miles(airline);
		
		assertEquals("DL", miles.getIataCode());
		assertEquals("Delta Airlines", miles.getAirline());
		assertEquals(0, miles.getTotalMiles()); 
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.airline_mileage.data.Miles#addMiles(int)}.
	 */
	@Test
	public void testAddMiles() {
		Airline airline = new Airline ("Delta Airlines", "DL");
		Miles miles = new Miles(airline);
		
		miles.addMiles(567);
		assertEquals(567, miles.getTotalMiles());
		
		miles.addMiles(3);
		assertEquals(570, miles.getTotalMiles());
	}

	/**
	 * Test method for compareTo method
	 */
	@Test
	public void testCompareTo() {
		Airline airline1 = new Airline ("Delta Airlines", "DL");
		Miles miles1 = new Miles(airline1);
		miles1.addMiles(400);
		
		Airline airline2 = new Airline ("United Airlines", "UA");
		Miles miles2 = new Miles(airline2);
		miles2.addMiles(800);
		
		Airline airline3 = new Airline ("Boeing Airlines", "B6");
		Miles miles3 = new Miles(airline3);
		
		assertEquals(-1, miles1.compareTo(miles2));
		assertEquals(1, miles1.compareTo(miles3));
		assertEquals(0, miles1.compareTo(miles1));
	}

}
