package edu.ncsu.csc316.hub_manager.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the AirlineHubManager class. 
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineHubManagerTest {

	/**
	 * Tests the AirlineHubManager constructor
	 */
	@Test
	public void testAirlineHubManager() {
		AirlineHubManager m = null;
		try {
			m = new AirlineHubManager("input/invalid.txt");
			fail();
		} catch (Exception e) {
			assertNull(m);
		}
		
		try {
			m = new AirlineHubManager("input/airports.txt");
			m.getClass();
		} catch (Exception e) {
			fail();
		}
		
	}

	/**
	 * Tests the getMinimumFlights method. 
	 */
	@Test
	public void testGetMinimumFlights() {
		AirlineHubManager m = new AirlineHubManager("input/airports.txt");
		String s = "FlightList[\n   Flight[airport1=ORH, airport2=RDU, distance=576.4]," +
				   "\n   Flight[airport1=SEA, airport2=SFO, distance=679.6]," +
				   "\n   Flight[airport1=MIA, airport2=RDU, distance=702.8]," +
				   "\n   Flight[airport1=DFW, airport2=RDU, distance=1059.7]," + 
				   "\n   Flight[airport1=DFW, airport2=SFO, distance=1462.3]\n]";
		assertEquals(s, m.getMinimumFlights());
		
	}

	/**
	 * Tests the getPossibleHubs method. 
	 */
	@Test
	public void testGetPossibleHubs() {
		AirlineHubManager m = new AirlineHubManager("input/airports.txt");
		String s = "HubReport[\n   RDU has 3 connections.\n]";
		assertEquals(s, m.getPossibleHubs());
	}

}
